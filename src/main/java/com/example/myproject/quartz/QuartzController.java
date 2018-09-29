package com.example.myproject.quartz;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 赵亚辉 on 2017/12/18.
 */
@Slf4j
@RestController
@RequestMapping("/api/quartz")
@Api(description = "定时任务")
public class QuartzController {
    @Autowired
    public SchedulerAllJob myScheduler;

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    @ApiOperation(value = "启动任务")
    public String schedule() throws SchedulerException {
        myScheduler.scheduleJobs();
        return "success";
    }

    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    @ApiOperation(value = "结束任务")
    public String stop() throws SchedulerException {
        myScheduler.stop();
        return "success";
    }
}
