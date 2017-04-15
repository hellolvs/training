package com.qunar.fresh2017.mybatis.service;

import com.qunar.fresh2017.mybatis.model.LeaveHolidayModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shuai.lv on 2017/3/22.
 */
@Service
public interface LeaveHolidayService {

    List<LeaveHolidayModel> selectLeaveHolidayByStaffId(Integer staffId);

    int insertLeaveHoliday(LeaveHolidayModel leaveHolidayModel);

    int deleteLeaveHolidayByStaffId(Integer staffId);

    int updateLeaveHolidayById(LeaveHolidayModel leaveHolidayModel);
}
