package com.awy.netty.handler;

import com.awy.entity.netty.ConnMsg;
import com.awy.entity.netty.ShutDownMsg;
import com.awy.netty.channel.ChannelGroup;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class ConnHandler extends SimpleChannelInboundHandler<ConnMsg> {

    private StringRedisTemplate RedisTemplate;

    public ConnHandler(StringRedisTemplate RedisTemplate){
        this.RedisTemplate=RedisTemplate;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ConnMsg connMsg) throws Exception {
        System.out.println("新的连接保存到channelGroup中："+connMsg.toString());
        //channel添加到map中
        ChannelGroup.addChannel(connMsg.getDid(), channelHandlerContext.channel());

        //根据用户id从Redis中查询did
        Integer uid=connMsg.getUser_id();
        String did=connMsg.getDid();
        String redisDid=RedisTemplate.opsForValue().get(uid.toString());
        if(redisDid != null && !redisDid.equals(did)){
            ShutDownMsg shutDownMsg = new ShutDownMsg();
            TextWebSocketFrame resp=new TextWebSocketFrame(new Gson().toJson(shutDownMsg));
            channelHandlerContext.writeAndFlush(resp);
        }
    }
}
