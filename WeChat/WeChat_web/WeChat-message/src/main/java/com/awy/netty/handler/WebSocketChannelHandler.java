package com.awy.netty.handler;

import com.awy.entity.netty.ChatMsg;
import com.awy.entity.netty.ConnMsg;
import com.awy.entity.netty.HeartMsg;
import com.awy.entity.netty.NettyMsg;
import com.awy.netty.channel.ChannelGroup;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.data.redis.core.StringRedisTemplate;


public class WebSocketChannelHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        String text= textWebSocketFrame.text();
        System.out.println("客户端发送的数据："+text);
        Gson gson=new Gson();
        NettyMsg nettyMsg=gson.fromJson(text, NettyMsg.class);
        if(nettyMsg.getType() == 1){ //连接事件
            nettyMsg =gson.fromJson(text, ConnMsg.class);
        }else if(nettyMsg.getType() == 2){ //心跳
            nettyMsg = gson.fromJson(text, HeartMsg.class);
        }else if(nettyMsg.getType() == 3){ //单聊
            nettyMsg = gson.fromJson(text, ChatMsg.class);
        }
        channelHandlerContext.fireChannelRead(nettyMsg);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("新客户端连接");
    }

    // 客户端断开后从ChannelGroup中删除这个连接
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端关闭并删除连接");
        ChannelGroup.removeChannel(ctx.channel());

    }
}
