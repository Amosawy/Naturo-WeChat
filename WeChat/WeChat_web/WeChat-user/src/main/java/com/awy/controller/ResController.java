package com.awy.controller;

import com.awy.entity.User;
import com.awy.entity.base.ResultEntity;
import com.awy.service.IUserService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/res")
public class ResController {

    @Autowired
    private IUserService userService;


    /**
     * 上传文件
     * @param userId 用户Id
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadImage")
    public ResultEntity uploadImage(Integer userId,MultipartFile file) throws Exception {
        if(file.isEmpty()){
            throw new Exception();
        }
        //文件名字
        String fileName=file.getOriginalFilename();
        //文件后缀
        String fileExName=fileName.substring(fileName.lastIndexOf(".")+1);
        //上传原图与缩略图
        String filePath=userId+"_head."+fileExName;
        String maxHead="H:/移动app/WeChat/res/"+filePath;
        String minHead=maxHead.replace(".","_100x100.");
        File file1=new File(maxHead);
        file.transferTo(file1);
        File min_Head=new File(minHead);
        if(!min_Head.exists()){
            min_Head.createNewFile();
        }
        Thumbnails.of(maxHead).size(100,100).toFile(minHead);
        //修改用户表头像
        String head="http://172.20.10.4:8888/res/getImage?fileName="+filePath;
        String min_head="http://172.20.10.4:8888/res/getImage?fileName="+filePath.replace(".","_100x100.");
        if(userId!=null){
            User user=userService.selectById(userId);
            user.setHead(head);
            user.setMin_head(min_head);
            userService.update(user);
        }
        //返回包含原图与缩略图的map
        Map<String,String> map=new HashMap<>();
        map.put("head",head);
        map.put("min_head",min_head);
        return ResultEntity.success(map);
    }

    /**
     * 访问图片
     * @param fileName 图片路径
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/getImage",produces = MediaType.IMAGE_GIF_VALUE)
    @ResponseBody
    public byte[] getImage(String fileName) throws IOException {
        File file=new File("H:/移动app/WeChat/res/"+fileName);
        FileInputStream FileInput=new FileInputStream(file);
        byte[] bytes=new byte[FileInput.available()];
        FileInput.read(bytes,0,FileInput.available());
        FileInput.close();
        return bytes;
    }
}
