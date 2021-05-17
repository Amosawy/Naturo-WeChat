package com.awy.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.concurrent.TimeUnit;

public class websocketServer {
    public static void main(String[] args) {
        EventLoopGroup master=new NioEventLoopGroup();
        EventLoopGroup slave=new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap=new ServerBootstrap();
            bootstrap.group(master,slave)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            ChannelPipeline pipeline=channel.pipeline();
                            pipeline.addLast(new HttpServerCodec());//httpRequest
                            pipeline.addLast(new HttpObjectAggregator(1024*10));//FullhttpRequest
                            pipeline.addLast(new WebSocketServerProtocolHandler("/"));
                            pipeline.addLast(new ReadTimeoutHandler(11, TimeUnit.SECONDS)); //客户端10s不发消息自动断开
                            pipeline.addLast(new WebSocketChannelHandler());
                        }
                    });
            ChannelFuture channelFuture=bootstrap.bind(8085).sync();
            System.out.println("服务端启动成功");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            master.shutdownGracefully();
            slave.shutdownGracefully();
        }
    }
}
