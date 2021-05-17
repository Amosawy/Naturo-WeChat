package com.awy.listener;

import com.awy.entity.netty.ChatMsg;
import com.awy.entity.netty.ShutDownMsg;
import com.awy.netty.channel.ChannelGroup;
import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "ws_queue_${netty.port}")
public class WsQueueListener {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RabbitHandler
    public void wsMsg(ShutDownMsg shutDownMsg){
        //查看当前设备号是否在本机
        String did=shutDownMsg.getDid();
        Channel channel= ChannelGroup.getChannel(did);
        System.out.println("监听器获得数据"+shutDownMsg);
        if(channel!=null){
            System.out.println("监听器发送消息给客户端");
            TextWebSocketFrame resp=new TextWebSocketFrame(new Gson().toJson(shutDownMsg));
            channel.writeAndFlush(resp);
        }
    }

    @RabbitHandler
    public void wsChat(ChatMsg chatMsg){
        //根据to_id查询did,再根据did查询channel进行消息推送
        String did=redisTemplate.opsForValue().get(chatMsg.getTo_id().toString());
        Channel channel = ChannelGroup.getChannel(did);
        System.out.println("监听器获得聊天数据"+chatMsg.getMsg());
        if(channel!=null){
            System.out.println("监听器发送消息给客户端");
            TextWebSocketFrame resp=new TextWebSocketFrame(new Gson().toJson(chatMsg));
            channel.writeAndFlush(resp);
        }
    }
}
