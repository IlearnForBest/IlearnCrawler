package com.crawler.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by sl on 16-4-1.
 */
public class MoocPipeline implements Pipeline {


    public void process(ResultItems resultItems, Task task) {


        if(resultItems.get("resources")!=null){


        }

    }
}
