package com.visolink.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;

public class FTPUtil { 
    public static void main(String[] args) { 
    	//upload(); 
        //testDownload(); 
    } 

    /** 
     * FTP上传单个文件测试 
     */ 
    public static void uploadPic(InputStream in,String fileName) { 
        FTPClient ftpClient = new FTPClient(); 
        FileInputStream fis = null; 

        try { 
            ftpClient.connect("123.56.181.109"); 
            ftpClient.login("douqiuftp", "douqiu"); 

           
            fis = (FileInputStream) in; 
            ftpClient.enterLocalPassiveMode();//关闭防火墙
            //设置上传目录 
            ftpClient.changeWorkingDirectory("pic"); 
            ftpClient.setControlEncoding("GBK"); 
            //设置文件类型（二进制） 
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); 
            ftpClient.storeFile(fileName, fis); 
        } catch (IOException e) { 
            e.printStackTrace(); 
            throw new RuntimeException("FTP客户端出错！", e); 
        } finally { 
            IOUtils.closeQuietly(fis); 
            try { 
                ftpClient.disconnect(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
                throw new RuntimeException("关闭FTP连接发生异常！", e); 
            } 
        } 
    } 
    
} 