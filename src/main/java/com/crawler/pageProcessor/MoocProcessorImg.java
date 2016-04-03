package com.crawler.pageProcessor;

import com.crawler.util.DBConnection;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sl on 16-4-3.
 */
public class MoocProcessorImg implements PageProcessor {
    private Site site = new Site();

    private DBConnection db = new DBConnection();

    @Override
    public void process(Page page) {
        List<String> img = page.getHtml().xpath("//img").all();

        for(String i : img){
            if(i!="<img src=\"/static/img/index/QR-code.jpg\" />")
            //System.out.println(i);
            processImgurl(i);
        }

        page.addTargetRequest(addNewUrl(page.getUrl().toString()));
    }



    /**
     * 获取新的url，url中的page自动加一
     * @param url
     * @return
     */
    private String  addNewUrl(String url){

        int index0 = url.indexOf('=');

        int index = Integer.parseInt(url.substring(index0+1)) + 1;

        String newUrl = url.substring(0,index0+1) + String.valueOf(index);

        return newUrl;
    }


    /**
     * 处理图片路径
     * @param imgurl
     * @return
     */
    private void processImgurl(String imgurl){

        int title_start=0,title_end=0,imgurl_start=0,imgurl_end=0;

        Matcher matcher = Pattern.compile("\"").matcher(imgurl);

        int count=0;
        while(matcher.find()){
            count++;
            if(count==5){
                title_start = matcher.start() + 1;
            }else if(count==6){
                title_end = matcher.start();
            }else if(count==7){
                imgurl_start = matcher.start() + 1;
            }else if(count==8){
                imgurl_end = matcher.start();
            }
        }


        String newImgurl = imgurl.substring(imgurl_start,imgurl_end);
        String title = imgurl.substring(title_start,title_end);

        System.out.println(newImgurl);
        System.out.println(title);
        System.out.println();

        db.update(newImgurl,title);
    }


    @Override
    public Site getSite() {
        return this.site;
    }
}
