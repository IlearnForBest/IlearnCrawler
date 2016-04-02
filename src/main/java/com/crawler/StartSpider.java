package com.crawler;

import com.crawler.pageProcessor.MoocProcessor;
import com.crawler.pipeline.MoocPipeline;
import us.codecraft.webmagic.Spider;

/**
 * Created by tsj on 16-4-1.
 */
public class StartSpider {


    public static void main(String args[]){
       Spider spider = new  Spider(new MoocProcessor());//
        spider.addUrl("http://www.imooc.com/view/5")
                .addPipeline(new MoocPipeline());
                spider.setEmptySleepTime(10000);
        spider.run();
    }

}