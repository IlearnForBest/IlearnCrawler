package com.crawler.pageProcessor;

import com.crawler.util.DBConnection;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by tsj on 16-4-5.
 */
public class TencentGetinfoProcessor implements PageProcessor {
    private Site site = new Site();
    private DBConnection dbConnection = new DBConnection();
    private String title;
    private String url;
    private String imgurl;
    private String category_1;
    private String category_2;
    private int collection;
    private int remark;
    private int grade;
    private double satisfaction;
    private int join_number;
    private String source_web;


    @Override
    public void process(Page page) {

        getInfo(page);
        dbConnection.insertintoit(title,url,imgurl,category_1,category_2,collection,
                remark,grade,satisfaction,join_number,source_web);
        System.out.println("imgurl:_____________>"+imgurl);


        if(page.getUrl().toString().equals("https://ke.qq.com/course/117307")){
            List<String> urls = dbConnection.qureyFromTemp();
            page.addTargetRequests(urls);
        }
    }

    @Override
    public Site getSite() {
        return this.site;
    }

    private void getInfo(Page page){
        url = page.getUrl().toString();
        title = page.getHtml().xpath("//h1[@class='page-tt']/text()").toString();
        imgurl = page.getHtml().xpath("//div[@class='imgtext-course']//img/@src").toString();
        category_1 = page.getHtml().xpath("//div[@class='course-banner inner-center clearfix']//a[3]/text()").toString();
        category_1 = exchangeCategory1(category_1);
        category_2 = page.getHtml().xpath("//div[@class='course-banner inner-center clearfix']//a[4]/text()").toString();
        collection = Integer.parseInt(page.getHtml().xpath("//span[@class='favorite-num']/text()").toString());
        String remarkstr = page.getHtml().xpath("//div[@class='tabs-tt-bar js_tab js-tab-nav']//span/text()").toString();
        remark = Integer.parseInt(remarkstr.substring(remarkstr.indexOf("(") + 1, remarkstr.indexOf(")")));
        grade = (int)Double.parseDouble(page.getHtml().xpath("//span[@class='rate-num']/text()").toString());
        join_number = Integer.parseInt(page.getHtml().xpath("//span[@class='apply-num js-apply-num']/text()").toString());
        String satisfactionstr = "";
        satisfactionstr = page.getHtml().xpath("//li[@class='purchase']em[2]/text()").toString();
//        satisfaction = Double.parseDouble(satisfactionstr.substring(1,satisfactionstr.indexOf("%")))/100.0;
        System.out.println(satisfaction);
        source_web = "腾讯课堂网";
    }

    private String exchangeCategory1(String category){
        String newcategory="";
        switch (category){
            case "编程语言": newcategory =  "编程语言";break;
            case "云计算与大数据": newcategory = "数据处理";break;
            case "系统运维": newcategory = "操作系统";break;
            case "移动开发": newcategory = "移动开发";break;
            case "产品/编辑/运营": newcategory = "其他";break;
            case "软件测试": newcategory = "其他";break;
            case "互联网产品": newcategory = "其他";break;
            case "游戏开发": newcategory = "其他";break;
            case "IT认证": newcategory = "其他";break;
            case "网络营销": newcategory = "其他";break;
            case "前端开发": newcategory = "前端开发";break;
            case "嵌入式": newcategory = "硬件";break;
            case "后台开发": newcategory = "后台开发";break;
            default :newcategory = "其他";break;

        }
        return newcategory;
    }
}
