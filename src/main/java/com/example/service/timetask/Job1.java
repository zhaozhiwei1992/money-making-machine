package com.example.service.timetask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: Job1
 * @Package com/example/service/timetask/Job1.java
 * @Description: 测试任务
 * @author zhaozhiwei
 * @date 2022/6/2 下午5:11
 * @version V1.0
 */
public class Job1 {

    private static final Logger log = LoggerFactory.getLogger(Job1.class);

    public void execute() {
        log.info("{} 执行了...", Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
