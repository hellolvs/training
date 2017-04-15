package com.qunar.fresh2017.mybatis.dao;

import com.qunar.fresh2017.mybatis.model.LeaveHolidayModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shuai.lv on 2017/3/22.
 */
@Repository
public interface LeaveHolidayDao {

    List<LeaveHolidayModel> selectLeaveHolidayByStaffId(Integer staffId);

    int insertLeaveHoliday(LeaveHolidayModel leaveHolidayModel);

    int deleteLeaveHolidayByStaffId(Integer staffId);

    int updateLeaveHolidayById(LeaveHolidayModel leaveHolidayModel);
}
