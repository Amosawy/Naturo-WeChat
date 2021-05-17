package com.awy.netty.handler;

import com.awy.entity.netty.HeartMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class HeartHandler extends SimpleChannelInboundHandler<HeartMsg> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HeartMsg heartMsg) throws Exception {
        System.out.println("处理心跳:"+heartMsg.toString());
        //响应心跳
        TextWebSocketFrame resp=new TextWebSocketFrame("heart");
        channelHandlerContext.writeAndFlush(resp);
    }
}
