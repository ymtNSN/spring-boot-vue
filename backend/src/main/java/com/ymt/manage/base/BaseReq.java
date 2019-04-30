package com.ymt.manage.base;

import io.swagger.annotations.ApiParam;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/4/30
 */
public class BaseReq {

    @ApiParam(value = "【公共参数】用户访问的认证信息", defaultValue = "b7c55893b14e45cd9e1f0faec3245945")
    private String accessToken;

    @ApiParam(value = "【内部参数】", hidden = true)
    public String security;

    @ApiParam(value = "【公共参数】签名参数，采用发起请求时的系统时间搓，到毫秒", defaultValue = "0", required = true)
    private Long sign;
}
