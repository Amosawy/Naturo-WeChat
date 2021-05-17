package com.awy.websocket;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.EventExecutorGroup;

public class WebSocketChannelHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        String content=textWebSocketFrame.text();
        System.out.println("读取客户端的内容："+content);
        if("heart".equals(content)){
            TextWebSocketFrame resp=new TextWebSocketFrame("heart");
            channelHandlerContext.writeAndFlush(resp);
        }else {
            TextWebSocketFrame resp = new TextWebSocketFrame("en");
            channelHandlerContext.writeAndFlush(resp);
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("新客户端连接");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端关闭");
    }
}
