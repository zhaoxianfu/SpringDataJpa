package cn.itcast.dao;

import cn.itcast.pojo.Customer;
import cn.itcast.pojo.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName:Test1
 * @Despriction: //TODO
 * @Author:zhaoxianfu
 * @Date:Created 2018/11/12  21:36
 * @Version1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestOneToMany {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;
    /**
     * 一对多保存
     */
    @Test
    @Transactional
    @Rollback(false)
    public void test1() {
        Customer customer = new Customer();
        customer.setCustName("lla");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小1");

        customer.getLinkmans().add(linkMan);
        linkMan.setCustomer(customer);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }
}
