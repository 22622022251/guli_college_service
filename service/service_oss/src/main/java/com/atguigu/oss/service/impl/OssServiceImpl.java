package com.atguigu.oss.service.impl;

import com.atguigu.oss.service.OssService;
import com.atguigu.oss.template.OssTemplate;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Autowired
    private OssTemplate ossTemplate;
    //上传头像到oss
    @Override
    public String uploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        //创建uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        fileName = uuid+fileName;
        //获取当前日期
        String datePath = new DateTime().toString("yyyy/MM/dd");
        //按照日期分类
        fileName = datePath + "/" + fileName;
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        String url = ossTemplate.uploadFile(fileName, inputStream);
        return url;
    }

    //删除文件
    @Override
    public boolean deleteSingleFile(String fileName) {
        boolean flag = ossTemplate.deleteSinplFile(fileName);
        return flag;
    }

    //多个文件删除
    @Override
    public boolean deleteMultiFile(List<String> fileNameList) {
        boolean flag = ossTemplate.deleteMultFile(fileNameList);
        return flag;
    }

}
