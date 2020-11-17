package com.wcy.rhapsody.admin.websocket;

import com.alibaba.fastjson.JSON;
import com.wcy.rhapsody.admin.core.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ws节点：获取在线人数
 *
 * @author Yeeep 2020/11/12
 */
@Slf4j
@Component
// @ServerEndpoint(value = "/websocket/online/{token}", encoders = ServerEncoder.class)
@ServerEndpoint(value = "/websocket/online", encoders = ServerEncoder.class)
public class OnlineServer {

    /**
     * 记录当前在线连接数,应该把它设计成线程安全的。
     */
    private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static final ConcurrentHashMap<String, OnlineServer> WEB_SOCKET_MAP = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 当前发消息的人员编号
     */
    private String token = null;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    // public void onOpen(@PathParam(value = "token") String token, Session session) {
    public void onOpen(Session session) {
        // 初始化创建Session
        this.session = session;
        // 接收到发送消息的人员编号
        this.token = session.getId();
        // 加入map中
        if (!WEB_SOCKET_MAP.containsKey(token)) {
            // +1
            WEB_SOCKET_MAP.put(token, this);
            // 在线数加1
            ONLINE_COUNT.incrementAndGet();
        }

        log.info("{}上线，当前在线人数为：{}", token, ONLINE_COUNT.get());
        sendAll(R.ok().message(MessageFormat.format("连接成功，当前在线人数:{0}", ONLINE_COUNT.get())).data(ONLINE_COUNT.get()));
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (WEB_SOCKET_MAP.containsKey(token)) {
            //从set中删除
            WEB_SOCKET_MAP.remove(token);
            //在线数减1
            ONLINE_COUNT.decrementAndGet();
            log.info("{}离线，当前在线人数为：{}", token, ONLINE_COUNT.get());
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("客户端:{}", message);
        sendAll(R.ok().message(MessageFormat.format("Hello，当前在线人数:{0}", ONLINE_COUNT.get())).data(ONLINE_COUNT.get()));
        //可以群发消息
        //消息保存到数据库、redis
        // if (StringUtils.isNotBlank(message)) {
        //     try {
        //         //解析发送的报文
        //         JSONObject jsonObject = JSON.parseObject(message);
        //         //追加发送人(防止串改)
        //         jsonObject.put("fromUserId", this.token);
        //         String toUserId = jsonObject.getString("toUserId");
        //         //传送给对应toUserId用户的websocket
        //         if (StringUtils.isNotBlank(toUserId) && webSocketMap.containsKey(toUserId)) {
        //             webSocketMap.get(toUserId).sendMessage(jsonObject.toJSONString());
        //         } else {
        //             log.error("请求的userId:" + toUserId + "不在该服务器上");
        //             //否则不在这个服务器上，发送到mysql或者redis
        //         }
        //     } catch (Exception e) {
        //         e.printStackTrace();
        //     }
        // }
    }

    /**
     * 连接错误
     *
     * @param error
     */
    @OnError
    public void onError(Throwable error) {
        log.error("用户错误:" + this.token + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送，服务端发送消息给客户端
     */
    private void sendMessage(Object message) {
        try {
            log.info("服务端给客户端发送消息：{}", JSON.toJSONString(message));
            session.getBasicRemote().sendObject(message);
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败：{0}", e);
        }
    }

    /**
     * 发送自定义消息
     */
    public static void sendInfo(String message, @PathParam("token") String token) throws IOException {
        log.info("发送消息到:" + token + "，报文:" + message);
        if (StringUtils.isNotBlank(token) && WEB_SOCKET_MAP.containsKey(token)) {
            WEB_SOCKET_MAP.get(token).sendMessage(message);
        } else {
            log.error("用户" + token + ",不在线！");
        }
    }

    /**
     * 给所有人发消息
     *
     * @param message
     */
    private void sendAll(Object message) {
        //遍历HashMap
        for (String key : WEB_SOCKET_MAP.keySet()) {
            //判断接收用户是否是当前发消息的用户
            // if (!token.equals(key)) {
            try {
                session.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException e) {
                log.error("服务端发送消息给客户端失败：{0}", e);
            }
            // }

        }
    }
}
