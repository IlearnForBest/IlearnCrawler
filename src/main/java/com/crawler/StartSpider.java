package com.crawler;

import com.crawler.pageProcessor.*;
import com.crawler.pipeline.MoocPipeline;
import us.codecraft.webmagic.Spider;

/**
 * Created by tsj on 16-4-1.
 */
public class StartSpider {


    public static void main(String args[]){

//       Spider spider = new  Spider(new MoocProcessorImg());//
//        spider.addUrl("http://www.imooc.com/view/347");
//        spider.addUrl("http://www.imooc.com/course/list?page=24");
//        spider.run();

        Spider spider = new  Spider(new MoocProcessorImg());//
        spider.addUrl("http://www.imooc.com/course/list?page=24");

//        慕课网
//       Spider spider = new  Spider(new MoocProcessorImg());//
//        spider.addUrl("http://www.imooc.com/view/347");
//        spider.addUrl("http://www.imooc.com/course/list?page=24");

//        百度传课网
//        Spider spider = new  Spider(new ChuankeGetinfoProcessor());//
//        spider.addUrl("http://www.chuanke.com/1018455-83980.html");
//        spider.run();

//        腾讯教育
//        Spider spider = new  Spider(new TencentGetinfoProcessor());
//        spider.addUrl("https://ke.qq.com/course/117307");
        spider.run();
    }

}