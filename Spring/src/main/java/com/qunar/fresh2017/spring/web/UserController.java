package com.qunar.fresh2017.spring.web;

import com.google.common.base.Preconditions;
import com.qunar.fresh2017.spring.model.User;
import com.qunar.fresh2017.spring.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <code>UserController</code>
 * 用户CURD页面控制
 * Created by shuai.lv on 2017/3/15.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private static final int ROWBOUNDS_LIMIT = 4;

    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public String add(@ModelAttribute("user") User user) {
        user.setCreateTime(new Date());
        user.setLastModifiedTime(new Date());
        userService.save(user);
        LOG.info("添加用户完成：id = {}，跳转到用户列表页面", user.getId());
        return "redirect:/user/list/1";
    }

    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public String addPage() {
        LOG.info("展示添加用户页面");
        return "add";
    }

    @RequestMapping(value = {"/list/{pageNo}"}, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String list(@PathVariable(value = "pageNo") int pageNo, Model model) {
        Preconditions.checkArgument((pageNo > 0), "not a valid page number");
        RowBounds rowBounds = new RowBounds((pageNo - 1) * ROWBOUNDS_LIMIT, ROWBOUNDS_LIMIT);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("userList", userService.findAll(rowBounds));
        LOG.info("获取用户列表完成，展示用户列表页面，当前页码：{}", pageNo);
        return "list";
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public String get(@PathVariable(value = "id") int id, Model model) {
        Preconditions.checkArgument((id > 0), "not a valid user id");
        model.addAttribute("user", userService.findById(id));
        LOG.info("查询用户完成：id = {}，展示用户详细信息页面", id);
        return "detail";
    }

    @RequestMapping(value = "/update/{id}", method = {RequestMethod.POST})
    public String update(@PathVariable(value = "id") int id, @ModelAttribute("user") User user) {
        user.setLastModifiedTime(new Date());
        userService.update(user);
        LOG.info("修改用户完成：id = {}，跳转到用户列表页面", id);
        return "redirect:/user/list/1";
    }

    @RequestMapping(value = "/update/{id}", method = {RequestMethod.GET})
    public String updatePage(@PathVariable(value = "id") int id, Model model) {
        Preconditions.checkArgument((id > 0), "not a valid user id");
        model.addAttribute("user", userService.findById(id));
        LOG.info("获取用户信息：id = {}，展示用户修改页面", id);
        return "update";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.POST})
    public String delete(@PathVariable(value = "id") int id, @RequestParam(value = "isDelete") boolean isDelete) {
        LOG.info("是否删除用户：isDelete = {}", isDelete);
        if (isDelete) {
            userService.removeById(id);
            LOG.info("删除用户完成：id = {}，跳转到用户列表页面", id);
            return "redirect:/user/list/1";
        }
        LOG.info("取消删除用户：id = {}，跳转到用户列表页面", id);
        return "redirect:/user/list/1";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET})
    public String deletePage(@PathVariable(value = "id") int id, Model model) {
        Preconditions.checkArgument((id > 0), "not a valid user id");
        model.addAttribute("id", id);
        LOG.info("展示删除用户页面");
        return "delete";
    }

    /*异常处理，输出异常信息*/
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<String> handlerRuntimeException(RuntimeException e) {
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        LOG.info(e.getMessage(), e);
        return responseEntity;
    }

}
