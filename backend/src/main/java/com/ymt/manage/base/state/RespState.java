package com.ymt.manage.base.state;

import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 常规的api请求返回的状态枚举
 * @Author yangmingtian
 * @Date 2019/4/30
 */
public enum RespState {
    //状态完全正常
    @ApiModelProperty(value = "状态完全正常", required = true)
    ok(0),
    //错误的加密密钥
    @ApiModelProperty(value = "错误的加密密钥", required = true)
    badSecurity(1),
    //错误的用户认证信息
    @ApiModelProperty(value = "错误的用户认证信息", required = true)
    badAuthor(2),
    //参数错误
    @ApiModelProperty(value = "参数错误", required = true)
    badParams(3),
    //其他抛出错误
    @ApiModelProperty(value = "其他抛出错误", required = true)
    exception(4),
    //查询对象不存在
    @ApiModelProperty(value = "查询对象不存在", required = true)
    notExist(5);

    private int value;
    private static Map<Integer, RespState> map = new HashMap<>();

    RespState(int value) {
        this.value = value;
    }

    static {
        for (RespState pageType : RespState.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static RespState valueOf(int pageType) {
        return map.get(pageType);
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        switch (this) {
            case ok:
                return "ok";
            case badSecurity:
                return "加密密钥或加密签名不正确";
            case badAuthor:
                return "错误的用户认证信息";
            case badParams:
                return "请求参数错误";
            case exception:
                return "运行错误";
            case notExist:
                return "查找的对象不存在";
            default:
                return "";

        }
    }
}
