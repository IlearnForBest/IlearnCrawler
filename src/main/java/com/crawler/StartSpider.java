package com.crawler;

import com.crawler.pageProcessor.*;
import com.crawler.pipeline.MoocPipeline;
import com.crawler.util.DBConnection;
import us.codecraft.webmagic.Spider;

import java.sql.SQLException;

/**
 * Created by tsj on 16-4-1.
 */
public class StartSpider {


    public static void main(String args[]){

        //test
        DBConnection db = new DBConnection();



//
//        db.updateValue("IT/互联网", "移动开发");
//        db.updateSelf("category_2","萌妹子教你玩转UI设计","UI设计");
//        db.updateSelf("category_2","就业·求职","求职");
//        db.updateSelf("category_3","职称英语","其他");
//        db.updateSelf("category_2","页面设计","设计相关");
//        db.updateSelf("category_2","网站制作","设计相关");
//        db.updateSelf("category_2","平面设计","设计相关");
//        db.updateSelf("category_2","UI","设计相关");
//        db.updateSelf("category_2","CG动画","其他");
//        db.updateSelf("category_2","Web前端开发","全栈工程师");
//        db.updateSelf("category_2","游戏设计","设计相关");


//        db.updateValue("移动开发","swift");
//
//        db.Qurey("category_2","category_1","更多分类");
        System.out.println("-----------------------");
        db.addKey();
//        db.insertcategory12();
//        db.qureyFromSql();
//        System.out.println("-----------------------");
//        db.Qurey("category_3","category_2","平面设计");
//        System.out.println("-----------------------");
//        db.Qurey("category_3","category_2","设计软件");
//        System.out.println("-----------------------");
//        db.Qurey("category_3","category_2","游戏美术设计");
//        System.out.println("-----------------------");
//        db.Qurey("category_3","category_2","动漫设计");
//        System.out.println("-----------------------");
//        db.Qurey("category_3","category_2","影视后期设计");
//        System.out.println("-----------------------");
//        db.Qurey("category_3","category_2","环境艺术设计");
//        System.out.println("-----------------------");
//        db.Qurey("category_3","category_2","管理类");
//        System.out.println("-----------------------");
//        db.Qurey("category_3","category_2","医药类");
//        System.out.println("-----------------------");
//        db.Qurey("category_3","category_2","建工类");
//        System.out.println("-----------------------");
//        db.Qurey("category_3","category_2","公务员考试");
//        System.out.println("-----------------------");
//        db.Qurey("category_3","category_2","学历教育");
//
//        System.out.println("-----------------------");
//        db.Qurey("后台开发");

//       Spider spider = new  Spider(new MoocProcessorImg());//
//        spider.addUrl("http://www.imooc.com/view/347");
//        spider.addUrl("http://www.imooc.com/course/list?page=24");
//        spider.run();

//        Spider spider = new  Spider(new MoocProcessorImg());//
//        spider.addUrl("http://www.imooc.com/course/list?page=24");

//        慕课网
//       Spider spider = new  Spider(new MoocProcessorImg());//
//        spider.addUrl("http://www.imooc.com/view/347");
//        spider.addUrl("http://www.imooc.com/course/list?page=24");

//        百度传课网
//        Spider spider = new  Spider(new ChuankeGetinfoProcessor());//
//        spider.addUrl("http://www.chuanke.com/2337781-136060.html");
//        spider.run();

//        腾讯教育
//        Spider spider = new  Spider(new TencentGetinfoProcessor());
//        spider.addUrl("https://ke.qq.com/course/68498");
//        spider.run();
    }

}