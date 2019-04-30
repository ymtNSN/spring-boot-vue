package com.ymt.manage.base.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ymt.manage.base.RespEntity;
import com.ymt.manage.base.state.RespState;
import com.ymt.manage.utility.ZLibUtility;
import com.ymt.manage.utility.security.AesUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/4/30
 */
@Order(1)
@ControllerAdvice(basePackages = "com.ymt.manage.app")
public class RespAdvice implements ResponseBodyAdvice {

    @Autowired
    private AesUtility aesUtility;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof RespEntity) {
            RespEntity respEntity = (RespEntity) body;
            // 是否需要加密
            if (respEntity.obscure) {
                try {
                    String jsonStr = JSON.toJSONString(respEntity.getData(), SerializerFeature.DisableCircularReferenceDetect);
                    // 加密压缩
                    respEntity.data = ZLibUtility.compress(aesUtility.encryptByte(jsonStr, respEntity.security));
                    respEntity.security = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    respEntity.code = RespState.badSecurity;
                    respEntity.data = null;
                }
                return respEntity;
            }
        }
        return body;
    }
}
