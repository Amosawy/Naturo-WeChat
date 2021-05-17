package com.awy.netty.server;

import com.awy.netty.handler.ChatHandler;
import com.awy.netty.handler.ConnHandler;
import com.awy.netty.handler.HeartHandler;
import com.awy.netty.handler.WebSocketChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class startWebsocketServer implements CommandLineRunner {

    @Value("${netty.port}")
    private Integer port;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * springboot初始化成功后调用
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
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
//                            pipeline.addLast(new ReadTimeoutHandler(11, TimeUnit.SECONDS)); //客户端11s不发消息自动断开
                            pipeline.addLast(new WebSocketChannelHandler());
                            pipeline.addLast(new ConnHandler(redisTemplate));
                            pipeline.addLast(new HeartHandler());
                            pipeline.addLast(new ChatHandler(rabbitTemplate));
                        }
                    });
            ChannelFuture channelFuture=bootstrap.bind(port).sync();
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
