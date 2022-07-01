package com.atguigu.oss.template;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.atguigu.oss.utils.ConstantPropertiesUtil;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
@Component
public class OssTemplate {

    //上传文件
    public String uploadFile(String fileName, InputStream inputStream){
        OSS ossClient = new OSSClientBuilder().build(ConstantPropertiesUtil.END_POINT,ConstantPropertiesUtil.ACCESS_KEY_ID , ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        ossClient.putObject(ConstantPropertiesUtil.BUCKET_NAME, fileName, inputStream);
        String url = "https://"+ConstantPropertiesUtil.BUCKET_NAME+"."+ConstantPropertiesUtil.END_POINT+"/"+fileName;
        ossClient.shutdown();
        return url;
    }
    //单个文件删除
    public boolean deleteSinplFile(String fileName){
        OSS ossClient = new OSSClientBuilder().build(ConstantPropertiesUtil.END_POINT,ConstantPropertiesUtil.ACCESS_KEY_ID , ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        ossClient.deleteObject(ConstantPropertiesUtil.BUCKET_NAME, fileName);
        ossClient.shutdown();
        return true;
    }
    //单个文件删除
    public boolean deleteMultFile(List<String> fileNameList){
        OSS ossClient = new OSSClientBuilder().build(ConstantPropertiesUtil.END_POINT,ConstantPropertiesUtil.ACCESS_KEY_ID , ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        DeleteObjectsRequest request = new DeleteObjectsRequest(ConstantPropertiesUtil.BUCKET_NAME);
        request.setKeys(fileNameList);
        ossClient.deleteObjects(request);
        ossClient.shutdown();
        return true;
    }
}
