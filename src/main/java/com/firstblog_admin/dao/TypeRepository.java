package com.firstblog_admin.dao;

import com.firstblog_admin.pojo.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author knight1527
 * Created with IntelliJ IDEA.
 * User: suse_QiHang
 * Date: 2021/06/30 15:07
 * Description:
 * Version: V1.0
 */
public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByName(String name);
}
