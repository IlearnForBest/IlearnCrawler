package com.crawler.pageProcessor;

import com.crawler.util.DBConnection;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tsj on 16-4-1.
 */

public class MoocProcessor implements PageProcessor{
    private Site site = new Site();

    private DBConnection db = new DBConnection();

    public void process(Page page) {


        String url = page.getUrl().toString();
      //  String imgurl = page.getHtml().xpath("//img[@id='js-cover-img']").toString();
        String category_1 = "IT/计算机";
        String category_2 = page.getHtml().xpath("//div[@class='path']/a[2]").toString();
        String category_3 = page.getHtml().xpath("//div[@class='path']/a[3]").toString();
        String title = page.getHtml().xpath("//div[@class='path']/span").toString();

//        System.out.println("in......");

        if(title != null){
            title = processTitleAndClassify(title);
            category_2 = processTitleAndClassify(category_2);
            category_3 = processTitleAndClassify(category_3);
            System.out.println(title+"  "+url+"  "+category_1+"  "
                    +category_2+"  "+category_3);

            db.insert(title,url,"",category_1,category_2,category_3);

        }

        page.addTargetRequest(addNewUrl(page.getUrl().toString()));

    }




    /**
     * 获取新的url，自动加一
     * @param url
     * @return
     */
    private String  addNewUrl(String url){

        int index0 = url.lastIndexOf('/');

        int index = Integer.parseInt(url.substring(index0+1)) + 1;

        String newUrl = url.substring(0,index0+1) + String.valueOf(index);

        return newUrl;
    }


    /**
     * 处理图片路径
     * @param imgurl
     * @return
     */
    private String processImgurl(String imgurl){

        int index_start = imgurl.indexOf('"') + 1;

        Matcher matcher = Pattern.compile("\"").matcher(imgurl);

        int count=0;
        while(matcher.find()){
            count++;
            if(count==2){
                break;
            }
        }

        int index_end =matcher.start();

        String newImgurl = imgurl.substring(index_start,index_end);
        System.out.println("imgurl : "+newImgurl);

        return newImgurl;
    }


    private String processTitleAndClassify(String str){
        int index_start = str.indexOf('>') + 1;

        Matcher matcher = Pattern.compile("<").matcher(str);

        int count=0;
        while(matcher.find()){
            count++;
            if(count==2){
                break;
            }
        }

        int index_end =matcher.start();

        String newStr = str.substring(index_start,index_end);

        return newStr;
    }





    public Site getSite() {
        return site;
    }
}
