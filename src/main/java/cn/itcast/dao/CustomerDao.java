package cn.itcast.dao;

import cn.itcast.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName:CustomerDao
 * @Despriction: Spring Data JPA是spring提供的一款对于数据访问层（Dao层）的框架，使用Spring Data JPA，
 * 只需要按照框架的规范提供dao接口，不需要实现类就可以完成数据库的增删改查、分页查询等方法的定义
 * @Author:zhaoxianfu
 * @Date:Created 2018/11/10  21:14
 * @Version1.0
 **/

/**
 * JpaRepository<实体类类型,主键类型>：用来完成基本的CRUD操作
 * JpaSpecificationExecutor<实体类类型>：用于复杂查询（分页等查询操作）
 */
public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {

    /**
     * 查询所有客户
     * @return
     */
    //@Query 使用jpql的方式查询。
    @Query(value="from Customer")
    List<Customer> findAllCustomer();

    //@Query 使用jpql的方式查询。?1代表参数的占位符，其中1对应方法中的参数索引，与参数列表是一一对应的
    @Query(value="from Customer where custName = ?1")
    Customer findCustomer(String custName);

    //通过使用 @Query 来执行一个更新操作，为此，我们需要在使用 @Query 的同时，
    // 用 @Modifying 来将该操作标识为修改查询，这样框架最终会生成一个更新的操作，而非查询。
    @Query(value="update Customer set custName = ?1 where custId = ?2")
    @Modifying
    void updateCustomer(String custName,Long custId);

    /**
     * 根据cust_name查询客户
     * @param custName
     * @return
     */
    @Query("from Customer where custName = ?1")
    Customer findCustomerByCustName(String custName);

    /**
     * 根据cust_id和cust_name查询客户
     * @param custName
     * @param custId
     * @return
     */
    @Query("from Customer where custId = ?1 and custName = ?2")
    Customer findCustomerByCustIdAndCustName(Long custId,String custName);

    @Query("update Customer set custName = ?2 where custId = ?1")
    @Modifying
    void updateCustomer(Long custId,String custName);

    /**
     * nativeQuery : 使用本地sql的方式查询
     */
    @Query(value="select * from cst_customer",nativeQuery=true)
    List<Customer> findSql();

    /**
     * 根据cust_name模糊查询
     * @param custName
     * @return
     */
    @Query(value="select * from cst_customer where cust_name like ?1",nativeQuery = true)
    List<Customer> findAllCustomerSql(String custName);
}
