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

        String satisfaction = null;
        String url = page.getUrl().toString();
      //  String imgurl = page.getHtml().xpath("//img[@id='js-cover-img']").toString();
        String category_1 = "IT/互联网";
        String category_2 = page.getHtml().xpath("//div[@class='path']/a[2]").toString();
        String category_3 = page.getHtml().xpath("//div[@class='path']/a[3]").toString();
        String title = page.getHtml().xpath("//div[@class='path']/span").toString();

        String join_number = page.getHtml().xpath("//div[@class='statics clearfix']/div[3]/span[1]/strong").toString();
        String remark = page.getHtml().xpath("//p[@class='person-num noLogin']/a").toString();
        String grade = page.getHtml().xpath("//div[@class='satisfaction-degree-info']/h4").toString();

//        System.out.println(grade);

        if(title != null){
            title = processTitleAndClassify(title);
            category_2 = processTitleAndClassify(category_2);
            category_3 = processTitleAndClassify(category_3);

            join_number = processTitleAndClassify(join_number);
            remark = processRemark(remark);
            grade = processTitleAndClassify(grade);

            System.out.println(title+"  "+url+"  "+category_1+"  "
                    +category_2+"  "+category_3 + "  "+"学习人数:"+join_number+
                    "  "+"评论数:" + remark + "  "+"满意度:" + grade);

         //   db.insert(title,url,"",category_1,category_2,category_3);

            db.insertintoit(title,url,null,category_2,category_3,0,Integer.parseInt(remark),
                    0,Double.parseDouble(grade),Integer.parseInt(join_number),"慕课网");

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


    private String processRemark(String remark){
        int index_start = remark.indexOf('>') + 1;
        int index_end = remark.indexOf('人');
        return remark.substring(index_start,index_end);
    }



    public Site getSite() {
        return site;
    }
}
