package com.firstblog_admin.service;

import com.firstblog_admin.pojo.User;

/**
 * @author knight1527
 * Created with IntelliJ IDEA.
 * User: suse_QiHang
 * Date: 2021/06/30 15:07
 * Description:
 * Version: V1.0
 */
public interface UserService {
    User checkUser(String username, String password);
}
