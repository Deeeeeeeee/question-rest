package com.seal_de.controller;

import com.seal_de.common.util.FileUploadUtil;
import com.seal_de.domain.Task;
import com.seal_de.domain.UserInfo;
import com.seal_de.service.TaskService;
import com.seal_de.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import static com.seal_de.common.util.VerifyUtil.*;

@RestController
@SessionAttributes("fileUploadPathList")
public class FileUploadController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserInfoService userInfoService;

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
        String dirPath = FileUploadUtil.createFileDir();

        File dir = new File(realPath + dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        List<String> list = FileUploadUtil.fileUploads(files, realPath, dirPath);
        session.setAttribute("fileUploadPathList", list);

        return list;
    }

    @PostMapping(value = "task/fileUpload")
    public String fileUpload(@RequestPart(value = "files[]")MultipartFile[] files) throws IOException {
        String username = "";
        String oldUrl = "";
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            if("username".equals(cookie.getName())) {
                username = cookie.getValue();
            }
        }
        username = URLDecoder.decode(username, "UTF-8");
        username = username.substring(1, username.length() - 1);

        UserInfo user = userInfoService.getByUsername(username);
        notNull(user, HttpStatus.NOT_FOUND, "用户不存在");

        Task task = taskService.getNotMakingTask(user.getId());
        if(task != null)
            oldUrl = task.getUrl();

        String realPath = request.getSession().getServletContext().getRealPath("/");
        String dirPath = FileUploadUtil.createFileDir();

        List<String> list = FileUploadUtil.fileUploads(files, realPath, dirPath);
        String url = StringUtils.join(list, ",");

        task = processTask(task, user.getId(), url);
        taskService.save(task);

        FileUploadUtil.deleteFile(realPath + oldUrl);

        return "success";
    }

    private Task processTask(Task task, String userId, String url) {
        if(task == null)
            task = new Task();
        task.setStatus(0);
        task.setCreateTime(new Date());
        task.setUserId(userId);
        task.setUrl(url);
        return task;
    }
}
