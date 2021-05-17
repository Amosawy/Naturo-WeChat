package com.awy.netty.channel;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ChannelGroup {
    /**
     * key:设备id
     * value：设备id的连接对象
     */
    public static Map<String,Channel> channelMap = new HashMap<>();

    /**
     * 添加channel到map中
     * @param did
     * @param channel
     */
    public static void addChannel(String did,Channel channel){
        channelMap.put(did,channel);
    }

    /**
     * 获取channel对象
     * @param did
     * @return
     */
    public static Channel getChannel(String did){
        return channelMap.get(did);
    }

    /**
     * 删除channel对象
     * @param did
     */
    public static void removeChannel(String did){
        channelMap.remove(did);
    }

    /**
     * 删除channel对象
     * @param channel
     */
    public static void removeChannel(Channel channel){
        if(channelMap.containsValue(channel)){
            Set<Map.Entry<String,Channel>> entries= channelMap.entrySet();
            for(Map.Entry<String,Channel> entry : entries){
                if(entry.getValue() == channel){
                    channelMap.remove(entry.getKey());
                    break;
                }
            }
        }
    }
}
