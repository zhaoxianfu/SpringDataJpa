package cn.itcast.dao;

import cn.itcast.pojo.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName:CustomerDaoTest
 * @Despriction: //TODO
 * @Author:zhaoxianfu
 * @Date:Created 2018/11/10  21:16
 * @Version1.0
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 保存客户：调用save(obj)方法
     */
    @Test
    public void test1() {
        Customer customer = new Customer();
        customer.setCustName("传智播客111");
        customerDao.save(customer);
    }

    /**
     * 根据id查询：调用findById(id)方法，根据主键进行查询的，返回的是一个Optional对象（jdk1.8新特性）；
     * 再通过Optional对象判断id是否存在，如果不为空就返回这个本身，为空的话的就返回在orElse设置的参数
     */
    @Test
    public void test4() {
        Customer customer = customerDao.findById(4L).orElse(null);
        System.out.println(customer);
    }

    /**
     * 修改客户：调用save(obj)方法
     * 对于save方法的解释：如果执行此方法是对象中存在id属性，即为更新操作会先根据id查询，再更新
     * 如果执行此方法中对象中不存在id属性，即为保存操作
     * 建议：先查询再更新
     */
    @Test
    public void test2() {
        Customer customer = new Customer();
        customer.setCustId(5L);
        customer.setCustName("乐视123");
        customerDao.save(customer);
    }

    /**
     * 建议：先查询再更新
     * orElse的含义是：如果能够查询对象，就返回该对象；如果查询不到，就返回null
     */
    @Transactional  //加上@Transactional注解，findById和save两个操作在一个事务当中，先findById查询出一个对象，此时事务还没有提交，对象依然受到EntityManager的管理，
    // 当更改对象后，事务提交，会比较EntityManager管理的对象和数据库是否一致，如果不一致，发送update语句，所以不用显示调用save方法。
    @Rollback(false)  //在单元测试方法上加@Transactional注解后，事务默认自动回滚，需要加上@Rollback(false)指定测试方法执行完成后，自动提交事务而不是回滚事务。
    @Test
    public void test3() {
        Customer customer = customerDao.findById(4L).orElse(null);
        customer.setCustName("乐视13");
        customerDao.save(customer);
        System.out.println(customer);
    }

    /**
     *
     * 根据id删除：调用deleteById(id)方法
     * 如果id不存在，则报错。
     */
    @Test
    public void test5() {
        customerDao.deleteById(9L);
    }

    /**
     * getOne:延迟加载   底层调用的是getReference方法
     * 如果不在单元测试方法上@Transactional注解，则会报no session异常，原因是getOne方法执行完后，事务提交，session关闭；
     * 当打印customer对象时，需要通过session去查customer对象，可是session已经关闭，报no session异常。
     */

//findById和getOne的区别：
//	findById是立即加载，底层调用的是EntityManager中的find方法；
//	getOne是延迟加载，底层调用的是EntityManager中的getReference方法；

    @Test
    @Transactional
    public void test7() {
        Customer customer = customerDao.getOne(2L);
        System.out.println(customer);
    }

    /**
     * 查询所有
     */
    @Test
    public void test8() {
        List<Customer> list = customerDao.findAll();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 查询所有,按照cust_id降序排序
     */
    @Test
    public void test9() {
        //指定排序规则
        Sort sort = new Sort(Sort.Direction.DESC,"custId");
        List<Customer> list = customerDao.findAll(sort);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 查询cst_customer表中的总记录数
     */
    @Test
    public void test10() {
        long total = customerDao.count();
        System.out.println(total);
    }

    /**
     * 检测cust_id为1的客户是否存在
     */
    @Test
    public void test11() {
        boolean flag = customerDao.existsById(1L);
        System.out.println(flag);
    }

    @Test
    public void test12() {
        List<Customer> list = customerDao.findAllCustomer();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    @Test
    public void test13() {
        Customer customer = customerDao.findCustomerByCustName("传智");
        System.out.println(customer);
    }

    @Test
    public void test14() {
        Customer customer = customerDao.findCustomerByCustIdAndCustName(2L,"传智");
        System.out.println(customer);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void test15() {
        customerDao.updateCustomer(2L,"黑马程序员1");
    }

    @Test
    public void test16(){
        List<Customer> customers = customerDao.findSql();
        System.out.println(customers);
    }

    @Test
    public void test17(){
        List<Customer> customers = customerDao.findAllCustomerSql("zhen");
        System.out.println(customers);
    }
}