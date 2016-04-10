package com.crawler.pageProcessor;

import com.crawler.util.DBConnection;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsj on 16-4-5.
 */
public class TencentProcessor implements PageProcessor{
    private Site site = new Site();
    private DBConnection dbConnection = new DBConnection();
    private List<String> sourceurl = new ArrayList<String>();

    @Override
    public void process(Page page) {

        getInfo(page);
        int i=0;
        for(String url:sourceurl){
            if(url.contains("course")){
                try {
                    dbConnection.temp(url);
                    i++;
//                System.out.println(url);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        System.out.println(i);

        page.addTargetRequest(getNewUrl(page.getUrl().toString()));

    }

    @Override
    public Site getSite() {
        return this.site;
    }

    private String getNewUrl(String url){
        String newurl;
        if(!url.contains("page")){
            newurl = url+"&page=2";
        }
        else{
            int index = url.lastIndexOf("=");
            int cnt = Integer.parseInt(url.substring(index+1));
            String baseurl = url.substring(0,index+1);
            newurl = baseurl+String.valueOf(cnt+1);
        }
        return newurl;
    }

    private void getInfo(Page page){
        sourceurl = page.getHtml().xpath("//div[@class='market-bd market-bd-6 course-list course-card-list-multi-wrap']//li[@class='course-card-item']/a/@href").all();
    }
}
