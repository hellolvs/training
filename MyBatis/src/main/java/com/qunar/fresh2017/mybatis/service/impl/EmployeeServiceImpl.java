package com.qunar.fresh2017.mybatis.service.impl;

import com.qunar.fresh2017.mybatis.dao.EmployeeDao;
import com.qunar.fresh2017.mybatis.model.EmployeeModel;
import com.qunar.fresh2017.mybatis.service.EmployeeService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shuai.lv on 2017/3/22.
 */
@Service("employeeServiceImpl")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public EmployeeModel selectEmployeeByStaffId(Integer staffId) {
        return employeeDao.selectEmployeeByStaffId(staffId);
    }

    @Override
    public List<EmployeeModel> selectEmployeeByName(String name) {
        return employeeDao.selectEmployeeByName(name);
    }

    @Override
    public List<EmployeeModel> selectEmployeeByIsValid(Integer isValid) {
        return employeeDao.selectEmployeeByIsValid(isValid);
    }

    @Override
    public List<EmployeeModel> selectEmployeeForPageSortedByStaffId(RowBounds rowBounds) {
        return employeeDao.selectEmployeeForPageSortedByStaffId(rowBounds);
    }

    @Override
    public int insertEmployee(EmployeeModel employeeModel) {
        return employeeDao.insertEmployee(employeeModel);
    }

    @Override
    public void batchInsertEmployees(List<EmployeeModel> employeeModelList) {
        employeeDao.batchInsertEmployees(employeeModelList);
    }

    @Override
    public int deleteEmployeeByStaffId(Integer staffId) {
        return employeeDao.deleteEmployeeByStaffId(staffId);
    }

    @Override
    public int updateEmployeeByStaffId(EmployeeModel employeeModel) {
        return employeeDao.updateEmployeeByStaffId(employeeModel);
    }
}
