package com.example.web.rest;

import com.example.domain.Server;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozhiwei
 * @version V1.0
 * @Title: ServerMetricsResource
 * @Package com/example/web/rest/ServerMetricsResource.java
 * @Description: 服务监控信息
 * @date 2022/6/30 下午10:18
 */
@RestController
@RequestMapping("/api")
public class ServerMetricsResource {

    @GetMapping("")
    @RequestMapping("/sys/server/metrics")
    public Server metrics() throws Exception {
        final Server server = new Server();
        server.copyTo();
        return server;
    }
}
