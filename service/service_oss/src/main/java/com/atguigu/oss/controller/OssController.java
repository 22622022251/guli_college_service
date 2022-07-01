package com.atguigu.oss.controller;

import com.atguigu.commutils.R;
import com.atguigu.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduoss/file")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    //上传文件
    @PostMapping("/upload")
    public R uploadFile(MultipartFile file){
        //开始上传文件
        //获取上传oss之后存到oss里面的路径
        String url = ossService.uploadFile(file);
        return R.ok().data("url",url);
    }
    //删除单个文件
    @DeleteMapping("/deleteSingleFile")
    public R deleteSingleFile(String fileName) {
        boolean flag = ossService.deleteSingleFile(fileName);
        if (flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    //删除多个文件
    @DeleteMapping("/deleteSingleFile")
    public R deleteMultiFile(List<String> fileNameList) {
        boolean flag = ossService.deleteMultiFile(fileNameList);
        if (flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }
}
