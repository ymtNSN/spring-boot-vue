package com.ymt.manage.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ymt.manage.base.state.RespState;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description 通用数据返回封装
 * @Author yangmingtian
 * @Date 2019/4/30
 */
public class RespEntity<T> {

    @ApiModelProperty(value = "执行结果的状态", required = true)
    public RespState code = RespState.ok;

    @ApiModelProperty(value = "附加消息,以code为准，返回信息不一定完整", required = true)
    public String message = "ok";

    @ApiModelProperty(value = "数据是否加密压缩", required = true)
    public boolean obscure = false;

    @ApiModelProperty(value = "【内部参数】当前过程中使用到的aesKey", hidden = true)
    @JSONField(deserialize = false)
    @JsonIgnore
    public String security = "";

    @ApiModelProperty(value = "正常返回的数据体,若执行失败该部分会为null，若无数据为空[]", required = false)
    public T data;

    /**
     * 描述: 创建一个对象，以ok的状态
     *
     * @return com.ymt.manage.base.RespEntity<T>
     * @author yangmingtian
     * @params []
     */
    public static <T> RespEntity<T> One() {
        return new RespEntity<>();
    }

    /**
     * 根据错误状态创建对象
     *
     * @param state 状态
     * @return 结果对象
     */
    public static <T> RespEntity<T> One(RespState state) {
        RespEntity<T> respEntity = new RespEntity<>();
        respEntity.code = state;
        return respEntity;
    }

    /**
     * 需要返回加密数据的调用
     *
     * @param req 请求
     * @return 结果对象
     */
    public static <T> RespEntity<T> One(BaseReq req) {
        RespEntity<T> respEntity = new RespEntity<>();
        respEntity.obscure = true;
        respEntity.security = req.security;
        return respEntity;
    }


    public int getCode() {
        return code.getValue();
    }

    public String getMessage() {
        if (code != RespState.ok && message.equals("ok")) {
            return code.toString();
        }
        return message;
    }

    public Boolean getObscure() {
        return obscure;
    }

    public T getData() {
        return data;
    }
}
