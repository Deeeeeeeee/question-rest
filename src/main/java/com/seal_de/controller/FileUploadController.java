package com.seal_de.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.seal_de.common.util.VerifyUtil.*;

@RestController
@SessionAttributes("fileUploadPathList")
public class FileUploadController {
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "getFileUpload", method = RequestMethod.GET)
    public List<String> getUpload(Model model){
        isTrue(model.containsAttribute("fileUploadPathList"),
                HttpStatus.NOT_FOUND, "图片未上传");
        return (List<String>) model.asMap().get("fileUploadPathList");
    }

    @RequestMapping(value = "fileupload", method= RequestMethod.POST)
    public List<String> processUpload(
            @RequestPart(value = "files[]", required = false) MultipartFile[] files, HttpSession session) throws IOException {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String dirPath = createFileDir();

        File dir = new File(realPath + dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        List<String> list = fileUploads(files, realPath, dirPath);
        session.setAttribute("fileUploadPathList", list);

        return list;
    }

    private String createFileDir(){
        String dirPath = "uploads";
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        return dirPath + "/" + year + "/" + month;
    }

    private List<String> fileUploads(MultipartFile[] files, String realPath, String dirPath) throws IOException {
        List<String> list = new LinkedList<String>();
        String filePath = realPath + dirPath;
        for(MultipartFile file : files) {
            isTrue(!file.isEmpty(), HttpStatus.BAD_REQUEST, "文件不能为空");
        }
        for(MultipartFile file : files) {
            String newFilename = createNewFilename(file);
            file.transferTo(new File(filePath + newFilename));
            list.add(dirPath + newFilename);
        }
        return list;
    }

    private String createNewFilename(MultipartFile file) {
        String oldFilename = file.getOriginalFilename();
        String fileSuffix = oldFilename.substring(oldFilename.lastIndexOf('.'));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String date = sdf.format(new Date());
        long randomCode = Math.round(Math.random() * 10000) + 1;

        return "/" + date + randomCode + fileSuffix;
    }
}
