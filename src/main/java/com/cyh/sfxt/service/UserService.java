package com.cyh.sfxt.service;

import com.cyh.sfxt.entirty.Users;
import com.cyh.sfxt.entirty.result.ResponseData;
import org.apache.http.protocol.ResponseDate;

public interface UserService {
    /**
     * 根据用户名和密码查询登陆的用户
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public Users login(String username, String password) throws Exception;
    /**
     * 根据查询信息查询用户的列表
     * @param users
     * @return
     */
    public ResponseData getUserList(Users users, int pageNum, int pageSize);
    /**
     * 删除用户信息
     * @param users
     * @return
     */
    public ResponseData deleteUser(Users users);
    /**
     * 编辑用户
     * @param users
     * @return
     */
    public ResponseData editUser(Users users);
    /**
     * 新增用户
     * @param users
     * @return
     */
    public ResponseData addUser(Users users);
}
