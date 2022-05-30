package com.example.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import liquibase.pro.packaged.M;
import org.springframework.stereotype.Service;

/**
 * @author zhaozhiwei
 * @version V1.0
 * @Title: null.java
 * @Package com.example.service
 * @Description:
 * 临时先通过服务map存储登录用户信息
 *
 * 用户量很大时可以调整为缓存存储
 * @date 2022/5/25 下午2:38
 */
@Service
public class OnLineUserService {

    private List<Map<String, Object>> onlineUserList = new ArrayList<>();

    public void add(Map<String, Object> map) {
        onlineUserList.add(map);
    }

    public void delete(String loginName) {
        onlineUserList.removeIf(next -> loginName.equals(next.get("username")));
    }

    public List<Map<String, Object>> findAll() {
        return onlineUserList;
    }
}
