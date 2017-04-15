package com.qunar.fresh2017.mybatis.dao;

import com.qunar.fresh2017.mybatis.model.EmployeeModel;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shuai.lv on 2017/3/22.
 */
@Repository
public interface EmployeeDao {

    EmployeeModel selectEmployeeByStaffId(Integer staffId);

    List<EmployeeModel> selectEmployeeByName(String name);

    List<EmployeeModel> selectEmployeeByIsValid(Integer isValid);

    List<EmployeeModel> selectEmployeeForPageSortedByStaffId(RowBounds rowBounds);

    int insertEmployee(EmployeeModel employeeModel);

    void batchInsertEmployees(List<EmployeeModel> employeeModelList);

    int deleteEmployeeByStaffId(Integer staffId);

    int updateEmployeeByStaffId(EmployeeModel employeeModel);
}
