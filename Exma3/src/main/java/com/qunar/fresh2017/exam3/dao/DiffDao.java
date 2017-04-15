package com.qunar.fresh2017.exam3.dao;

import com.qunar.fresh2017.exam3.model.DiffModel;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shuai.lv on 2017/3/23.
 */
@Repository
public interface DiffDao {

    DiffModel selectDiffById(Integer id);

    List<DiffModel> selectDiffByOwnerName(String ownerName);

    List<DiffModel> findAllForPage(RowBounds rowBounds);

    int countDiff();

    int getMaxId();

    int insertDiff(DiffModel diffModel);

    int deleteDiffById(Integer id);

    int updateDiffById(DiffModel diffModel);
}
