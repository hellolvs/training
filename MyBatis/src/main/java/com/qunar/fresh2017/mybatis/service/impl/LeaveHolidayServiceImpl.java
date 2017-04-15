package com.qunar.fresh2017.mybatis.service.impl;

import com.qunar.fresh2017.mybatis.dao.LeaveHolidayDao;
import com.qunar.fresh2017.mybatis.model.LeaveHolidayModel;
import com.qunar.fresh2017.mybatis.service.LeaveHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shuai.lv on 2017/3/22.
 */
@Service("leaveHolidayServiceImpl")
public class LeaveHolidayServiceImpl implements LeaveHolidayService {

    @Autowired
    private LeaveHolidayDao leaveHolidayDao;

    public void setLeaveHolidayDao(LeaveHolidayDao leaveHolidayDao) {
        this.leaveHolidayDao = leaveHolidayDao;
    }

    @Override
    public List<LeaveHolidayModel> selectLeaveHolidayByStaffId(Integer staffId) {
        return leaveHolidayDao.selectLeaveHolidayByStaffId(staffId);
    }

    @Override
    public int insertLeaveHoliday(LeaveHolidayModel leaveHolidayModel) {
        return leaveHolidayDao.insertLeaveHoliday(leaveHolidayModel);
    }

    @Override
    public int deleteLeaveHolidayByStaffId(Integer staffId) {
        return leaveHolidayDao.deleteLeaveHolidayByStaffId(staffId);
    }

    @Override
    public int updateLeaveHolidayById(LeaveHolidayModel leaveHolidayModel) {
        return leaveHolidayDao.updateLeaveHolidayById(leaveHolidayModel);
    }
}
