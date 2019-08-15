package com.cyh.sfxt.service.impl;

import com.cyh.sfxt.entirty.Users;
import com.cyh.sfxt.entirty.result.ExceptionMsg;
import com.cyh.sfxt.entirty.result.ResponseData;
import com.cyh.sfxt.mapper.UserMapper;
import com.cyh.sfxt.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class UserServiceImpl implements UserService{


    @Autowired
    private UserMapper userMapper;

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
        PageHelper.startPage(pageNum,pageSize);
        ResponseData responseData=null;
        try{
            Example example=new Example(Users.class);
            Example.Criteria criteria=example.createCriteria();
            //用户正常状态为1
            criteria.andCondition("state = 1");
            if(StringHelper.isNullOrEmptyString(users.getUsername())){
                criteria.andLike("username","%"+users.getUsername()+"%");
            }
            Page<Users>page= (Page<Users>) userMapper.selectByExample(criteria);
            PageInfo<Users>pageInfo=new PageInfo<>(page);
            responseData=new ResponseData(pageInfo, ExceptionMsg.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            responseData= new ResponseData(ExceptionMsg.SYSERROR);
        }
        return responseData;
    }
}
