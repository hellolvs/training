package com.qunar.fresh2017.exam3.service.impl;

import com.qunar.fresh2017.exam3.dao.DiffDao;
import com.qunar.fresh2017.exam3.model.DiffModel;
import com.qunar.fresh2017.exam3.service.DiffService;
import com.qunar.fresh2017.exam3.util.ContentDiffUtil;
import com.qunar.fresh2017.exam3.util.MultiPartFileUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by shuai.lv on 2017/3/23.
 */
@Service("diffServiceImpl")
public class DiffServiceImpl implements DiffService {

    @Autowired
    private DiffDao diffDao;

    public void setDiffDao(DiffDao diffDao) {
        this.diffDao = diffDao;
    }

    @Override
    public DiffModel selectDiffById(Integer id) {
        return diffDao.selectDiffById(id);
    }

    @Override
    public List<DiffModel> getDiffListByPage(RowBounds rowBounds) {
        return diffDao.findAllForPage(rowBounds);
    }

    @Override
    public int countDiff() {
        return diffDao.countDiff();
    }

    @Override
    public int getMaxId() {
        return diffDao.getMaxId();
    }

    @Override
    public int insertDiff(DiffModel diffModel) {
        return diffDao.insertDiff(diffModel);
    }

    @Override
    public DiffModel diffFile(MultipartFile source, MultipartFile target, HttpServletRequest request) {

        //获取源和目标文件内容
        String sourceContent = MultiPartFileUtil.read(source);
        String targetContent = MultiPartFileUtil.read(target);

        //对比差异，获取差异内容
        String diffContent = ContentDiffUtil.diff(sourceContent, targetContent);

        //从cookie获取用户信息
        String username = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
            }
        }

        //构建数据对象
        DiffModel diffModel = new DiffModel();
        diffModel.setOwnerName(username);
        diffModel.setDiffTime(new Date());
        diffModel.setSourceFileContent(sourceContent);
        diffModel.setTargetFileContent(targetContent);
        diffModel.setDiffContent(diffContent);

        //已登录用户将对比记录插入到数据库，未登录用户只显示对比结果
        if (!username.equals("")) {
            insertDiff(diffModel);
        }

        return diffModel;
    }

    @Override
    public int deleteDiffById(Integer id) {
        return diffDao.deleteDiffById(id);
    }

}
