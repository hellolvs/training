package com.qunar.fresh2017.exam3.service;

import com.qunar.fresh2017.exam3.model.DiffModel;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by shuai.lv on 2017/3/23.
 */
@Service
public interface DiffService {

    DiffModel selectDiffById(Integer id);

    List<DiffModel> getDiffListByPage(RowBounds rowBounds);

    int countDiff();

    int getMaxId();

    int insertDiff(DiffModel diffModel);

    DiffModel diffFile(MultipartFile source, MultipartFile target, HttpServletRequest request);

    int deleteDiffById(Integer id);
}
