package com.example.config;

/**
 * @Title: NoticeRecTypeEnum
 * @Package com/example/config/NoticeRecTypeEnum.java
 * @Description: 消息接收类型
 *
 * <el-radio :label="1">所有用户</el-radio>
 * <el-radio :label="2">指定用户</el-radio>
 * <el-radio :label="3">角色</el-radio>
 * <el-radio :label="4">单位</el-radio>
 *
 * @author zhaozhiwei
 * @date 2022/7/23 下午4:48
 * @version V1.0
 */
public enum NoticeRecTypeEnum {
    ALL_USER("1", "所有用户"),
    // 逗号分隔
    SOME_USER("2", "指定用户"),
    SOME_ROLE("3", "角色"),
    SOME_AGENCY("4", "单位");

    private String code;
    private String desc;

    NoticeRecTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
