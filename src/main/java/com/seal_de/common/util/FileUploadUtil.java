package com.seal_de.common.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sealde on 5/18/17.
 */
public class FileUploadUtil {
    public static String createFileDir(){
        String dirPath = "uploads";
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        return dirPath + "/" + year + "/" + month;
    }

    public static List<String> fileUploads(MultipartFile[] files, String realPath, String dirPath) throws IOException {
        mkdir(realPath + dirPath);
        List<String> list = new LinkedList<String>();
        String filePath = realPath + dirPath;
        for(MultipartFile file : files) {
            VerifyUtil.isTrue(!file.isEmpty(), HttpStatus.BAD_REQUEST, "文件不能为空");
        }
        for(MultipartFile file : files) {
            String newFilename = createNewFilename(file);
            file.transferTo(new File(filePath + newFilename));
            list.add(dirPath + newFilename);
        }
        return list;
    }

    public static void mkdir(String path) {
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdirs();
        }
    }

    private static String createNewFilename(MultipartFile file) {
        String oldFilename = file.getOriginalFilename();
        String fileSuffix = oldFilename.substring(oldFilename.lastIndexOf('.'));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String date = sdf.format(new Date());
        long randomCode = Math.round(Math.random() * 10000) + 1;

        return "/" + date + randomCode + fileSuffix;
    }

    public static void deleteFile(String path) {
        File file = new File(path);
        if(file.exists() && file.isFile()) {
            file.delete();
        }
    }
}
