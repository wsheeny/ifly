package com.wcy.rhapsody.admin.websocket;

import com.alibaba.fastjson.JSON;
import com.wcy.rhapsody.admin.core.R;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * ServerEncoder为了防止在websocket向客户端发送sendObject方法的时候提示如下错误：
 * javax.websocket.EncodeException: No encoder specified for object of class [class org.ywzn.po.Messagepojo]
 *
 * @author Yeeep 2020/11/12
 */
public class ServerEncoder implements Encoder.Text<R> {
    /**
     * 代表websocket调用sendObject方法返回客户端的时候，必须返回的是DomainResponse对象
     *
     * @param domainResponse
     * @return
     */
    @Override
    public String encode(R domainResponse) {
        //将java对象转换为json字符串
        return JSON.toJSONString(domainResponse);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }
}