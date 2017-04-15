package com.qunar.fresh2017.mybatis.service;

import com.qunar.fresh2017.mybatis.model.EmployeeModel;
import com.qunar.fresh2017.mybatis.model.HolidayModel;
import com.qunar.fresh2017.mybatis.model.LeaveHolidayModel;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuai.lv on 2017/3/22.
 */
@ContextConfiguration(locations = {"classpath:spring/app.xml"})
public class ServiceTest extends AbstractJUnit4SpringContextTests {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceTest.class);
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private HolidayService holidayService;
    @Autowired
    private LeaveHolidayService leaveHolidayService;

    /*1、保存新入职员工韩梅梅的信息，员工工号为8，女士，其它自定*/
    @Test
    public void testInsertEmployee() throws Exception {
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setStaffId(8);
        employeeModel.setName("韩梅梅");
        employeeModel.setMobile("13122142114");
        employeeModel.setArea("北京");
        employeeModel.setGender(2);
        employeeModel.setIsValid(1);
        LOG.info("插入到员工表中，插入条数={}", employeeService.insertEmployee(employeeModel));
        LOG.info("新员工信息为：{}", employeeService.selectEmployeeByStaffId(8));
    }

    /*2、查询员工工号1的员工信息*/
    @Test
    public void testSelectEmployeeByStaffId() throws Exception {
        EmployeeModel employeeModel = employeeService.selectEmployeeByStaffId(1);
        LOG.info("员工工号1的员工信息：{}",employeeModel);
    }

    /*3、查询2号员工的请假详单*/
    @Test
    public void testSelectLeaveHolidayByStaffId() throws Exception {
        List<LeaveHolidayModel> leaveHolidayModels = leaveHolidayService.selectLeaveHolidayByStaffId(2);
        LOG.info("2号员工的请假详单：{}",leaveHolidayModels.toString());
    }

    /*4、更新3号员工的年假剩余数为3*/
    @Test
    public void testUpdateHolidayByStaffId() throws Exception {
        HolidayModel holidayModel = holidayService.selectHolidayByStaffId(3);
        LOG.info("更新前3号员工的年假剩余数：{}",holidayModel);
        holidayModel.setAnnualNum(3);
        holidayService.updateHolidayByStaffId(holidayModel);
        LOG.info("更新后3号员工的年假剩余数：{}",holidayService.selectHolidayByStaffId(3));
    }

    /*5、删除已经离职的员工*/
    @Test
    public void testDelete() throws Exception {
        List<EmployeeModel> employeeModels = employeeService.selectEmployeeByIsValid(2);
        LOG.info("离职员工人数：{}",employeeModels.size());
        for (EmployeeModel employeeModel : employeeModels) {
            employeeService.deleteEmployeeByStaffId(employeeModel.getStaffId());
            LOG.info("删除离职员工：staffId={}", employeeModel.getStaffId());
        }
    }

    /*6、查询姓张的在职员工总数*/
    @Test
    public void testSelectEmployeeByName() throws Exception {
        List<EmployeeModel> employeeModels = employeeService.selectEmployeeByName("张");
        LOG.info("姓张的在职员工总数:{}",employeeModels.size());
    }

    /*7、查询请年假天数小于3天的员工id号*/
    @Test
    public void testSelectHolidayMoreThanAnnualNum() throws Exception {
        List<HolidayModel> holidayModels = holidayService.selectHolidayMoreThanAnnualNum(10-3);
        LOG.info("请年假天数小于3天的员工id有：");
        for (HolidayModel holidayModel : holidayModels) {
            LOG.info("staffId:{}",holidayModel.getStaffId());
        }
    }

    /*8、分别查询按照工号排名的前1-3名员工和4-6名员工详情（用分页实现）*/
    @Test
    public void testSelectEmployeeForPageSortedByStaffId() throws Exception {
        LOG.info("工号排名的前1-3名员工：{}",employeeService.selectEmployeeForPageSortedByStaffId(new RowBounds(0,3)).toString());
        LOG.info("工号排名的前4-6名员工：{}",employeeService.selectEmployeeForPageSortedByStaffId(new RowBounds(3,3)).toString());
    }

    /*9、插入10个员工数据（数据自定义）*/
    @Test
    public void testBatchInsertEmployees() throws Exception {
        List<EmployeeModel> employeeModels = new ArrayList<EmployeeModel>();
        for (int i=10;i<20;i++){
            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setStaffId(i);
            employeeModel.setName("韩梅梅");
            employeeModel.setMobile("13122142114");
            employeeModel.setArea("北京");
            employeeModel.setGender(2);
            employeeModel.setIsValid(1);
            employeeModels.add(employeeModel);
        }
        employeeService.batchInsertEmployees(employeeModels);
        LOG.info("插入10个员工数据完成");
    }

    /*10、统计查询工号2，4的已请年假天数和剩余年假天数*/
    @Test
    public void testSelectHolidayByStaffId() throws Exception {
        HolidayModel holidayModel1 = holidayService.selectHolidayByStaffId(2);
        HolidayModel holidayModel2 = holidayService.selectHolidayByStaffId(4);
        LOG.info("工号2的已请年假天数：{}",10-holidayModel1.getAnnualNum());
        LOG.info("工号2的剩余年假天数：{}",holidayModel1.getAnnualNum());
        LOG.info("工号4的已请年假天数：{}",10-holidayModel2.getAnnualNum());
        LOG.info("工号4的剩余年假天数：{}",holidayModel2.getAnnualNum());
    }

    /*11、一个员工要请病假，请列出业务查询思路*/
    /**1、首先，查询假期表判断剩余病假天数 是否满足 要请的病假天数，如果满足则继续，否则退出。
     * 2、1条件满足则开启一个事务：
     *      在请假表中插入请假事项；
     *      在假期表更新（set sickNum = sickNum - 请假天数） 或者 加锁（set sickNum = 剩余天数）；
     *      提交事务。
     */
}

