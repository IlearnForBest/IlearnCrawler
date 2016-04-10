package com.crawler.pageProcessor;

import com.crawler.util.DBConnection;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.print.DocFlavor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsj on 16-4-3.
 * 用来处理传课网信息的专用processor
 */
public class ChuankeProcessor implements PageProcessor {
    private Site site = new Site();
    private DBConnection dbConnection = new DBConnection();
    private List<String> sourceurl = new ArrayList<String>();
    private List<String> imgurls = new ArrayList<String>();

    @Override
    public void process(Page page) {
//        根据数字的对应关系如下：
//        http://www.chuanke.com/course/72351176527446016______2.html  编程语言
//        http://www.chuanke.com/course/72351176544223232______2.html 编程语言/PHP
//        http://www.chuanke.com/course/72351176561000448______2.html 编程语言/C/C++
//        http://www.chuanke.com/course/72351176577777664______2.html 编程语言/JAVA
//        http://www.chuanke.com/course/72351176594554880______2.html 编程语言/python
//        http://www.chuanke.com/course/72351176628109312______2.html 编程语言/脚本语言
//        http://www.chuanke.com/course/72351176661663744______2.html 编程语言/汇编
//        http://www.chuanke.com/course/72351176678440960______2.html 编程语言/。NET
//        http://www.chuanke.com/course/72351176695218176______2.html 编程语言/VC/MFC
//        http://www.chuanke.com/course/72351176711995392______2.html 编程语言/JSP
//        http://www.chuanke.com/course/72351176728772608______1.html 编程语言/Objective-C
//        http://www.chuanke.com/course/72351176762327040______1.html 编程语言/VisualBasic
//        http://www.chuanke.com/course/72351176779104256______1.html 编程语言/C#
//        http://www.chuanke.com/course/72351176862990336______1.html 编程语言/Matlab
//        String id = pageurl.substring(pageurl.lastIndexOf("/") + 1, pageurl.indexOf("_"));
//        switch (id){
//            case "72351176544223232":{category_1 = "编程语言";category_2 = "PHP";}break;
//            case "72351176561000448":{category_1 = "编程语言";category_2 = "C/C++";}break;
//            case "72351176577777664":{category_1 = "编程语言";category_2 = "JAVA";}break;
//            case "72351176594554880":{category_1 = "编程语言";category_2 = "python";}break;
//            case "72351176628109312":{category_1 = "编程语言";category_2 = "脚本语言";}break;
//            case "72351176661663744":{category_1 = "编程语言";category_2 = "汇编";}break;
//            case "72351176678440960":{category_1 = "编程语言";category_2 = ".NET";}break;
//            case "72351176695218176":{category_1 = "编程语言";category_2 = "VC/MFC";}break;
//            case "72351176711995392":{category_1 = "编程语言";category_2 = ".JSP";}break;
//            case "72351176728772608":{category_1 = "编程语言";category_2 = "Objective-C";}break;
//            case "72351176762327040":{category_1 = "编程语言";category_2 = "VisualBasic";}break;
//            case "72351176779104256":{category_1 = "编程语言";category_2 = "C#";}break;
//            case "72351176862990336":{category_1 = "编程语言";category_2 = "Matlab";}break;
//        }

        String pageurl = page.getUrl().toString();

        //获取所需的参数
        getInfo(page);

        for(String url:sourceurl){
            if(!url.contains("channel")){//非广告页面才存储
                try {
                    dbConnection.temp(url);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(pageurl);


        page.addTargetRequest(getNewUrl(pageurl));
    }


    @Override
    public Site getSite() {
        return this.site;
    }


//    把url中的参数加一，获取新的url
    private String getNewUrl(String url){
        String newurl;
        if(!url.contains("?")){
            newurl = url+"?page=2";
        }
        else{
            int index = url.lastIndexOf("=");
            int cnt = Integer.parseInt(url.substring(index+1));
            String baseurl = url.substring(0,index+1);
            newurl = baseurl+String.valueOf(cnt+1);
        }
        return newurl;
    }

//    获取所需信息：url以及满意度等参数
    private void getInfo(Page page){
        sourceurl = page.getHtml().xpath("//div[@class='item-pic']/a/@href").all();
        imgurls = page.getHtml().xpath("//div[@class='item-pic']/a/img/@src").all();
    }
}
