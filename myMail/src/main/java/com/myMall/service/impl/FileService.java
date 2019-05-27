package com.myMall.service.impl;

import com.google.common.collect.Lists;
import com.myMall.service.IFileService;
import com.myMall.util.FTPUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service("iFileService")
public class FileService implements IFileService {
    private Logger logger = LoggerFactory.getLogger(FileService.class);

    public String upload(MultipartFile file, String path) {
        // 将用户上传的文件转换为唯一字符串
        String fileOriginalName = file.getOriginalFilename();
        String extensionName = fileOriginalName.substring(fileOriginalName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + extensionName;
        // 生成路径和文件
        File fileDir = new File(path);
        if(!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);


        try {
            // 将文件流从内存中复制到本地
            file.transferTo(targetFile);

            // 将文件上传到ftp服务器
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));

            // 将原来的文件删除
            targetFile.delete();

        } catch (Exception e) {
            logger.error("上传文件异常", e);
            return null;
        }
        return targetFile.getName();
    };
}
