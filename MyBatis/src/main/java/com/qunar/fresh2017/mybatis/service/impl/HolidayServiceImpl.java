package com.qunar.fresh2017.mybatis.service.impl;

import com.qunar.fresh2017.mybatis.dao.HolidayDao;
import com.qunar.fresh2017.mybatis.model.HolidayModel;
import com.qunar.fresh2017.mybatis.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shuai.lv on 2017/3/22.
 */
@Service("holidayServiceImpl")
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private HolidayDao holidayDao;

    public void setHolidayDao(HolidayDao holidayDao) {
        this.holidayDao = holidayDao;
    }

    @Override
    public HolidayModel selectHolidayByStaffId(Integer staffId) {
        return holidayDao.selectHolidayByStaffId(staffId);
    }

    @Override
    public List<HolidayModel> selectHolidayMoreThanAnnualNum(Integer annualNum) {
        return holidayDao.selectHolidayMoreThanAnnualNum(annualNum);
    }

    @Override
    public int insertHoliday(HolidayModel holidayModel) {
        return holidayDao.insertHoliday(holidayModel);
    }

    @Override
    public int deleteHolidayByStaffId(Integer staffId) {
        return holidayDao.deleteHolidayByStaffId(staffId);
    }

    @Override
    public int updateHolidayByStaffId(HolidayModel holidayModel) {
        return holidayDao.updateHolidayByStaffId(holidayModel);
    }
}
