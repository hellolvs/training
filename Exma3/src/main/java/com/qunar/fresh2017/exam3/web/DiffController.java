package com.qunar.fresh2017.exam3.web;

import com.google.common.base.Preconditions;
import com.qunar.fresh2017.exam3.model.DiffModel;
import com.qunar.fresh2017.exam3.service.DiffService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件对比控制
 * Created by shuai.lv on 2017/3/23.
 */
@Controller
@RequestMapping("/diff")
public class DiffController {

    private static final Logger LOG = LoggerFactory.getLogger(DiffController.class);
    private static final int ROWBOUNDS_LIMIT = 5;

    @Autowired
    private DiffService diffService;

    public void setDiffService(DiffService diffService) {
        this.diffService = diffService;
    }

    /*首页，分页显示对比记录*/
    @RequestMapping(value = {"/list/{pageNo}"}, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String list(@PathVariable("pageNo") int pageNo, Model model) {

        Preconditions.checkArgument((pageNo > 0), "not a valid page number");
        int totalRecord = diffService.countDiff();
        int maxPageNo = (totalRecord - 1) / ROWBOUNDS_LIMIT + 1;
        Preconditions.checkArgument((pageNo <= maxPageNo), "page number out of bounds，max page number = " + maxPageNo);

        model.addAttribute("pageNo", pageNo);
        model.addAttribute("totalRecord", totalRecord);
        model.addAttribute("maxPageNo", maxPageNo);
        model.addAttribute("diffList", diffService.getDiffListByPage(new RowBounds((pageNo - 1) * ROWBOUNDS_LIMIT, ROWBOUNDS_LIMIT)));
        LOG.debug("获取对比列表完成，展示对比列表页面，当前页码：{}，最大页码：{}", pageNo, maxPageNo);
        return "list";
    }

    /*对比文件，添加对比记录*/
    @RequestMapping(value = "/submit", method = {RequestMethod.POST})
    public String add(@RequestParam("source") MultipartFile source, @RequestParam("target") MultipartFile target, HttpServletRequest request,Model model) {

        Preconditions.checkArgument((source != null && !source.isEmpty()), "please upload source file");
        Preconditions.checkArgument((target != null && !target.isEmpty()), "please upload target file");
        Preconditions.checkArgument((source.getSize() < 1024 && target.getSize() < 1024), "file size must be within 1kb");

        DiffModel diffModel = diffService.diffFile(source, target, request);
        model.addAttribute("diff", diffModel);
        return "detail";
    }

    /*删除对比记录*/
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.POST})
    public String delete(@PathVariable(value = "id") int id, @RequestParam(value = "isDelete") boolean isDelete) {
        LOG.debug("是否删除对比记录：isDelete = {}", isDelete);
        if (isDelete) {
            int deleted = diffService.deleteDiffById(id);
            LOG.debug("删除对比记录完成：deleted = {}，跳转到对比记录列表页面", deleted);
            return "redirect:/diff/list/1";
        }
        LOG.debug("取消删除对比记录，跳转到对比记录列表页面", id);
        return "redirect:/diff/list/1";
    }

    /*展示删除对比记录页面*/
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET})
    public String deletePage(@PathVariable(value = "id") int id, Model model) {

        Preconditions.checkArgument((id > 0), "not a valid diff id");
        int maxId = diffService.getMaxId();
        Preconditions.checkArgument((id <= maxId), "diff id out of bounds,max id = " + maxId);

        model.addAttribute("ownername", diffService.selectDiffById(id).getOwnerName()); //添加记录所有者属性，用于校验删除权限
        model.addAttribute("id", id);
        LOG.debug("展示删除对比记录页面");
        return "delete";
    }

    /*异常处理，输出异常信息*/
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<String> handlerRuntimeException(RuntimeException e) {
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        LOG.error(e.getMessage(), e);
        return responseEntity;
    }

}
