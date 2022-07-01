package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OssService {
    //上传头像到oss
    String uploadFile(MultipartFile file);
    //删除文件
    boolean deleteSingleFile(String fileName);

    boolean deleteMultiFile(List<String> fileNameList);
}
