package com.myMall.util;


import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class FTPUtil {
    private static final Logger logger = LoggerFactory.getLogger(FTPUtil.class);
    private static String ftpIp = PropertiesUtil.getProperty("ftp.server.ip");
    private static String ftpUser = PropertiesUtil.getProperty("ftp.user");
    private static String ftpPass = PropertiesUtil.getProperty("ftp.pass");
    private static Integer ftpPort = 21;

    private FTPUtil(String ip, int port, String user, String pwd) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    // 暴露给外部调用的上传方法
    public static boolean uploadFile(List<File> fileList) throws IOException{
        FTPUtil ftpUtil = new FTPUtil(ftpIp, ftpPort, ftpUser, ftpPass);
        logger.info("开始连接ftp服务器");
        boolean result = ftpUtil.upload("img", fileList);
        logger.info("上传完成");
        return result;
    }

    // 进行上传
    private boolean upload(String remotePath, List<File> fileList) throws IOException{
        boolean uploaded = true;
        FileInputStream fis = null;
        if(connectServer()) {
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);  // 设置缓存区
                ftpClient.setControlEncoding("UTF-8"); // 设置编码方式
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);  // 文件设置为二进制文件
                // 保存文件
                for (File fileItem : fileList) {
                    fis = new FileInputStream(fileItem);
                    ftpClient.storeFile(fileItem.getName(), fis);
                }
            } catch (IOException ex) {
                logger.error("上传文件异常", ex);
                uploaded = false;
            } finally {
                // 记得关闭文件流和连接流, 这里会抛出异常
                fis.close();
                ftpClient.disconnect();
            }
        }

        return uploaded;
    }

    // 连接到ftp服务器
    private boolean connectServer() {
        boolean result = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            result = ftpClient.login(user, pwd);
        } catch (Exception e) {
            logger.error("连接ftp服务器异常", e);
        }
        return result;
    }

    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}
