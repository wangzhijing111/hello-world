package com.cyh.sfxt.service;

import com.cyh.sfxt.entirty.Users;
import com.cyh.sfxt.entirty.result.ResponseData;
import org.apache.http.protocol.ResponseDate;

import java.util.List;
import java.util.Map;

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

    /**
     * 保存导入用户
     * @param users
     * @return
     */
    public ResponseData saveUserList(Users users);

    public List<Map< String, Object>> finfAll();

    public  void save(String id,String name,String province,String city,String address ,String zip,String date);

    public  void update(String name,String province,String city,String address ,String zip,String date,String id);

    public  List<Map< String, Object>> loginDbDataSearch(String name,String address);

    public  void delete(String id);
}
