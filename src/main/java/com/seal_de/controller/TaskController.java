package com.seal_de.controller;

import com.seal_de.domain.*;
import com.seal_de.model.PaperInfoModel;
import com.seal_de.model.PaperItemInfoModel;
import com.seal_de.model.TaskInfoModel;
import com.seal_de.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.seal_de.common.util.VerifyUtil.*;

/**
 * Created by sealde on 4/24/17.
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PaperService paperService;
    @Autowired
    private PaperDetailService paperDetailService;
    @Autowired
    private PaperItemService paperItemService;

    @ModelAttribute
    public UserInfo pre(@RequestAttribute String token_username) {
        UserInfo user = userInfoService.getByUsername(token_username);
        notNull(user, HttpStatus.NOT_FOUND, "用户不存在");
        return user;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<TaskInfoModel> taskList(UserInfo user) {
        List<Task> tasks = taskService.findByUserId(user.getId());
        List<TaskInfoModel> taskInfoModels = taskService.taskToTaskInfoModel(tasks);
        return taskInfoModels;
    }

    @GetMapping(value = "/getImg")
    public String[] getImg(UserInfo user) {
        Task task = taskService.getNotMakingTask(user.getId());
        String url = task.getUrl();
        return StringUtils.split(url, ",");
    }

    @GetMapping(value = "/getImg/{taskId}")
    public String[] getImgByTaskId(@PathVariable String taskId) {
        Task task = taskService.getById(taskId);
        String url = task.getUrl();
        return StringUtils.split(url, ",");
    }

    @RequestMapping(value = "/startMaking", method = RequestMethod.POST)
    public Map<String, String> startMaking(UserInfo user, @RequestBody Paper paper) {
        Task task = taskService.getNotMakingTask(user.getId());
        notNull(task, HttpStatus.NOT_FOUND, "操作失败：未上传图片不能执行制作操作");

        task = processTask(task, user.getId(), paper);
        paperService.save(paper);
        taskService.save(task);

        PaperDetail paperDetail = initPaperDetail(paper);
        paperDetailService.save(paperDetail);

        final String taskId = task.getId();
        return new HashMap<String, String>(){{this.put("taskId", taskId);}};
    }

    private Task processTask(Task task, String userId, Paper paper) {
        task.setUserId(userId);
        task.setPaperId(paper);
        task.setStatus(10);
        return task;
    }

    private PaperDetail initPaperDetail(Paper paper) {
        PaperDetail paperDetail = new PaperDetail();
        paperDetail.setPaperId(paper.getId());
        paperDetail.setParentIndex(0);
        paperDetail.setPaperItems(initPaperItems());
        return paperDetail;
    }

    private List<PaperItem> initPaperItems() {
        final PaperItem paperItem = new PaperItem();
        paperItem.setChildIndex(0);
        return new ArrayList<PaperItem>(){{add(paperItem);}};
    }

    @RequestMapping(value = "/editPaper/{taskId}", method = RequestMethod.GET)
    public PaperInfoModel editPaper(@PathVariable String taskId) {
        Task task = taskService.getById(taskId);
        Paper paper = task.getPaperId();

        List<PaperDetail> details = paperDetailService.findByPaperId(paper.getId());
        return new PaperInfoModel(details);
    }

    @GetMapping(value = "/getErrorMsg/{taskId}")
    public String getErrorMsg(@PathVariable String taskId) {
        Task task = taskService.getById(taskId);
        boolean isErrorStatus = (task.getStatus() == 30);
        isTrue(isErrorStatus, HttpStatus.BAD_REQUEST, "错误操作：该任务不是错误退回状态");
        return task.getErrorMessage();
    }

    @RequestMapping(value = "/addOrUpdatePaperDetail/{taskId}", method = RequestMethod.POST)
    public PaperInfoModel addOrUpdatePaperDetail(@RequestBody PaperDetail paperDetail, @PathVariable String taskId) {
        Task task = taskService.getById(taskId);
        String paperId = task.getPaperId().getId();
        verifyStatus(task);

        PaperDetail persistenceDetail = paperDetailService.getByPaperIdAndParentIndex(paperId, paperDetail.getParentIndex());

        if(persistenceDetail != null) {
            persistenceDetail = processPaperDetail(paperDetail, persistenceDetail);
        } else {
            persistenceDetail = paperDetail;
            persistenceDetail.setPaperId(paperId);
        }

        paperDetailService.saveAfterClear(persistenceDetail);

        List<PaperDetail> paperDetails = paperDetailService.findByPaperId(paperId);
        return new PaperInfoModel(paperDetails);
    }

    private PaperDetail processPaperDetail(PaperDetail paperDetail, PaperDetail persisitenceDetail) {
        if(paperDetail.getQuestionType() != null)
            persisitenceDetail.setQuestionType(paperDetail.getQuestionType());
        if(paperDetail.getParentIndex() != null)
            persisitenceDetail.setParentIndex(paperDetail.getParentIndex());
        return persisitenceDetail;
    }

    @RequestMapping(value = "/deletePaperDetail/{taskId}", method = RequestMethod.POST)
    public PaperInfoModel deletePaperDetail(@RequestParam Integer parentIndex, @PathVariable String taskId) {
        Task task = taskService.getById(taskId);
        String paperId = task.getPaperId().getId();
        verifyStatus(task);

        PaperDetail paperDetail = paperDetailService.getByPaperIdAndParentIndex(paperId, parentIndex);
        paperDetailService.verifyDeletePaperDetail(paperDetail);

        paperDetailService.delete(paperDetail);

        List<PaperDetail> paperDetails = paperDetailService.findByPaperId(paperId);
        paperDetails = paperDetailService.reduceParentIndex(paperDetails, parentIndex);
        return new PaperInfoModel(paperDetails);
    }

    @RequestMapping(value = "/addOrUpdatePaperItem/{taskId}", method = RequestMethod.POST)
    public PaperInfoModel addOrUpdatePaperItem(@RequestBody PaperItemInfoModel paperItemInfoModel, @PathVariable String taskId) {
        Task task = taskService.getById(taskId);
        String paperId = task.getPaperId().getId();
        String paperDetailId = getDetailId(paperItemInfoModel, paperId);
        PaperItem paperItem = paperItemInfoModel.getPaperItem();
        verifyStatus(task);

        PaperItem persistenceItem = paperItemService.getByPaperDetailIdAndChildIndex(
                paperDetailId, paperItem.getChildIndex());

        if(persistenceItem != null) {
            persistenceItem.setPaperDetailId(paperDetailId);
            persistenceItem = processItem(paperItem, persistenceItem);
        } else {
            persistenceItem = paperItem;
            persistenceItem.setPaperDetailId(paperDetailId);
        }
        paperItemService.saveAfterClear(persistenceItem);

        List<PaperDetail> paperDetails = paperDetailService.findByPaperId(paperId);
        return new PaperInfoModel(paperDetails);
    }

    private String getDetailId(PaperItemInfoModel paperItemInfoModel, String paperId) {
        Integer parentIndex = paperItemInfoModel.getParentIndex();
        PaperDetail paperDetail = paperDetailService.getByPaperIdAndParentIndex(paperId, parentIndex);
        return paperDetail.getId();
    }

    private PaperItem processItem(PaperItem paperItem, PaperItem persistenceItem){
        if(paperItem.getStem() != null)
            persistenceItem.setStem(paperItem.getStem());
        if(paperItem.getAnswer() != null)
            persistenceItem.setAnswer(paperItem.getAnswer());
        if(paperItem.getExamPoint() != null)
            persistenceItem.setExamPoint(paperItem.getExamPoint());
        if(paperItem.getSolution() != null)
            persistenceItem.setSolution(paperItem.getSolution());
        return persistenceItem;
    }

    @RequestMapping(value = "/deletePaperItem/{taskId}", method = RequestMethod.POST)
    public PaperInfoModel deletePaperItem(@RequestParam Integer parentIndex, @RequestParam Integer childIndex,
                                          @PathVariable String taskId) {
        Task task = taskService.getById(taskId);
        String paperId = task.getPaperId().getId();
        verifyStatus(task);

        PaperDetail paperDetail = paperDetailService.getByPaperIdAndParentIndex(paperId, parentIndex);
        PaperItem paperItem = paperItemService.getByPaperDetailIdAndChildIndex(paperDetail.getId(), childIndex);

        notNull(paperItem, HttpStatus.NOT_FOUND, "删除错误：没有这道小题");
        paperItemService.deleteAfterClear(paperItem);

        List<PaperItem> paperItems = paperItemService.findByPaperDetailId(paperDetail.getId());
        paperItemService.reduceChildIndex(paperItems, childIndex);

        List<PaperDetail> paperDetails = paperDetailService.findByPaperIdAfterClear(paperId);
        return new PaperInfoModel(paperDetails);
    }

    @PostMapping(value = "/finishEditPaper/{taskId}")
    public String finishEditPaper(@PathVariable String taskId) {
        Task task = taskService.getById(taskId);
        task.setStatus(20);
        taskService.save(task);
        return "success";
    }

    @GetMapping(value = "/check/list")
    public List<TaskInfoModel> checkList(UserInfo user) {
        verifyAuditor(user);

        List<Task> tasks = taskService.findByAuditorId(user.getId());
        List<TaskInfoModel> taskInfoModels = taskService.taskToTaskInfoModel(tasks);
        return taskInfoModels;
    }

    @GetMapping(value = "/check/getTask")
    public String checkGetTask(UserInfo user) {
        verifyAuditor(user);

        Task task = taskService.getByAuditorId(user.getId());
        isNull(task, HttpStatus.BAD_REQUEST, "操作失败：一次只能获取一个任务");

        task = taskService.getByStatus(20);
        notNull(task, HttpStatus.NOT_FOUND, "获取失败：任务池里没有任务");

        task.setStatus(21);
        task.setAuditorId(user.getId());
        taskService.save(task);
        return "success";
    }

    @PostMapping(value = "/check/saveErrorMsg/{taskId}")
    public String saveErrorMsg(UserInfo user, @PathVariable String taskId, @RequestParam String errorMsg) {
        verifyAuditor(user);

        Task task = taskService.getById(taskId);
        task.setStatus(30);
        task.setErrorMessage(errorMsg);
        taskService.save(task);
        return "success";
    }

    @PostMapping(value = "/check/store/{taskId}")
    public String checkStore(UserInfo user, @PathVariable String taskId) {
        verifyAuditor(user);

        Task task = taskService.getById(taskId);
        task.setErrorMessage(null);
        task.setStatus(100);
        taskService.save(task);
        return "success";
    }

    private void verifyStatus(Task task) {
        Integer status = task.getStatus();
        boolean isEditPermit = (status >= 10 && status < 20) || (status >= 30 && status < 40);
        isTrue(isEditPermit, HttpStatus.BAD_REQUEST,
                "非法操作：现在不允许修改试卷");
    }

    private void verifyAuditor(UserInfo user) {
        boolean isAuditor = user.getRole().intValue() == 2;
        isTrue(isAuditor, HttpStatus.BAD_REQUEST, "权限不足：不是审核者");
    }
}
