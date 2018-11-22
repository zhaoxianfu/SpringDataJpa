package cn.itcast.dao;

import cn.itcast.pojo.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName:LinkManDao
 * @Despriction: //TODO
 * @Author:zhaoxianfu
 * @Date:Created 2018/11/12  21:37
 * @Version1.0
 **/
public interface LinkManDao extends JpaRepository<LinkMan,Long>, JpaSpecificationExecutor<LinkMan> {

}
