package com.awy.netty.handler;

import com.awy.entity.netty.ChatMsg;
import com.awy.entity.netty.ConnMsg;
import com.awy.entity.netty.ShutDownMsg;
import com.awy.netty.channel.ChannelGroup;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

public class ChatHandler extends SimpleChannelInboundHandler<ChatMsg> {

    private RabbitTemplate rabbitTemplate;

    public ChatHandler(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate=rabbitTemplate;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ChatMsg chatMsg) throws Exception {
        System.out.println("用户聊天信息："+chatMsg.toString());
        //消息入库

        //推送聊天信息,转发到交换机
        rabbitTemplate.convertAndSend("ws_exchange","",chatMsg);
    }
}
