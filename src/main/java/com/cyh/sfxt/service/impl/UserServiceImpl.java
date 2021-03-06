package com.cyh.sfxt.service.impl;
import	java.util.Map;

import com.cyh.sfxt.entirty.Users;
import com.cyh.sfxt.entirty.result.ExceptionMsg;
import com.cyh.sfxt.entirty.result.ResponseData;
import com.cyh.sfxt.mapper.UserMapper;
import com.cyh.sfxt.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * 根据用户名和密码登陆
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @Override
    public Users login(String username, String password) throws Exception {
        return userMapper.login(username,password);
    }
    /**
     * 根据用户信息查询用户的列表
     * @param users
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResponseData getUserList(Users users, int pageNum, int pageSize) {
        ResponseData rData=null;
        try{
            Example example=new Example(Users.class);
            Example.Criteria criteria=example.createCriteria();
            //用户正常状态为1
            criteria.andCondition("state != -1");
            if(null != users.getUsername() && !"".equals(users.getUsername())){
                criteria.andLike("username","%"+users.getUsername()+"%");
            }
            PageHelper.startPage(pageNum,pageSize);
            List<Users> usersList= userMapper.selectByExample(example);
            PageInfo<Users>pageInfo=new PageInfo<Users>(usersList);
            rData=new ResponseData(pageInfo, ExceptionMsg.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            rData= new ResponseData(ExceptionMsg.SYSERROR);
        }
        return rData;
    }
    /**
     * 删除用户信息
     * @param users
     * @return
     */
    @Override
    public ResponseData deleteUser(Users users) {
        ResponseData responseData=null;
        try {
            if(null == users.getState() || "".equals(users.getState())){
                return new ResponseData(ExceptionMsg.FAILED,"删除状态不能为空!");
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            users.setCreatTime(df.format(new Date()));
            int num =userMapper.deleteUSerList(users);
            if(0==num){
                return new ResponseData(ExceptionMsg.FAILED);
            }
            responseData=new ResponseData(ExceptionMsg.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            responseData=new ResponseData(ExceptionMsg.SYSERROR);
        }
        return responseData;
    }
    /**
     *编辑用户
     * @param users
     * @return
     */
    @Override
    public ResponseData editUser(Users users) {
            ResponseData responseData=null;
            try{
                int num=userMapper.updateByPrimaryKey(users);
                if(num == 0){
                    return new ResponseData(ExceptionMsg.FAILED);
                }
                responseData=new ResponseData(ExceptionMsg.SUCCESS);
            }catch (Exception e){
                e.printStackTrace();
                responseData= new ResponseData(ExceptionMsg.SYSERROR);
            }
        return responseData;
    }
    /**
     * 新增用户
     * @param users
     * @return
     */
    @Override
    public ResponseData addUser(Users users) {
        ResponseData responseData=null;
        try{
            int num=0;
            if(null != users){
                users.setState("1");
                users.setPassword("123456");
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                users.setCreatTime(df.format(new Date()));
                num =userMapper.insert(users);
            }else {
                return new ResponseData(ExceptionMsg.NODATA);
            }
            if(0 != num){
                responseData=new ResponseData(ExceptionMsg.SUCCESS);
            }else {
                responseData=new ResponseData(ExceptionMsg.FAILED);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseData(ExceptionMsg.SYSERROR);
        }
        return responseData;
    }

    @Override
    public ResponseData saveUserList(Users users) {
        ResponseData responseData=null;
        try{
            int num=0;
            if(null != users){
                if(null == users.getPassword() || "".equals(users.getPassword())){
                    users.setPassword("123456");
                }
                users.setState("1");
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                users.setCreatTime(df.format(new Date()));
                num =userMapper.insert(users);
            }else {
                return new ResponseData(ExceptionMsg.NODATA);
            }
            if(0 != num){
                responseData=new ResponseData(ExceptionMsg.SUCCESS);
            }else {
                responseData=new ResponseData(ExceptionMsg.FAILED);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseData;
    }
    @Override
    public  List<Map< String, Object>> finfAll(){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from liebiao");
        return  jdbcTemplate.queryForList(String.valueOf(sql));
    }

    @Override
    public  void save(String id,String name,String province,String city,String address ,String zip,String date){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO `cyh`.`liebiao` (`id`, `name`, `province`, `city`, `address`, `zip`, `date`) VALUES (?, ?, ?, ?, ?, ?, ?)");
        jdbcTemplate.update(String.valueOf(sql),id,name,province,city,address,zip,date);
    }

    @Override
    public  void update(String name,String province,String city,String address ,String zip,String date,String id){
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE liebiao t set t.`name` = ?,t.province = ?, t.city = ?,t.address = ?,t.zip = ?,t.date = ? where t.id = ?");
        jdbcTemplate.update(String.valueOf(sql),name,province,city,address,zip,date,id);
    }

    @Override
    public  List<Map< String, Object>> loginDbDataSearch(String name,String address){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from liebiao t  where 1 = 1 ");
        if (StringUtils.isNotBlank(name)){
            sql.append(" and t.name like '%" + name +"%'  ");
        }
        if(StringUtils.isNotBlank(address)){
            sql.append(" and t.address like '%" + address +"%' ");
        }
        return  jdbcTemplate.queryForList(String.valueOf(sql));
    }

    @Override
    public  void delete(String id){
        StringBuilder sql = new StringBuilder();
        sql.append("delete from liebiao  where id = ? ");
        jdbcTemplate.update(String.valueOf(sql),id);
    }

}
