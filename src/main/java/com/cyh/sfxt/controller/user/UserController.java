package com.cyh.sfxt.controller.user;

import com.cyh.sfxt.entirty.Users;
import com.cyh.sfxt.entirty.result.ResponseData;
import com.cyh.sfxt.service.UserService;
import com.cyh.sfxt.util.Const;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/loginUser")
    public String login(@Param("username") String username, @Param("password")String password){
        Users users=new Users();
        System.out.println("用户名:"+username+"--------------------->密码:"+password);
        try {
            users=userService.login(username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(null != users){
                return "/base/index";
            }else {
                return "redirect:/user/loginUser";
            }
    }
    /**
     *根据条件查询用户人员列表
     * @param users
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserList" ,method = RequestMethod.GET)
    public ResponseData getUserlist(Users users, @RequestParam(defaultValue= Const.PAGENUM) int pageNum, @RequestParam(defaultValue=Const.PAGESIZE)int pageSize){
        return  userService.getUserList(users,pageNum,pageSize);
    }
    /**
     * 逻辑删除用户
     * @param users
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteUser" ,method = RequestMethod.POST)
    public ResponseData deleteUser(Users users){
        return userService.deleteUser(users);
    }
    /**
     * 编辑用户
     * @param users
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editUser" ,method = RequestMethod.POST)
    public ResponseData editUser(@RequestBody Users users){
        return userService.editUser(users);
    }
    /**
     * 新增用户
     * @param users
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addUser" ,method = RequestMethod.POST)
    public ResponseData addUser(@RequestBody Users users){
        return userService.addUser(users);
    }
    /**
     * 打开用户界面
     * @return
     */
    @RequestMapping(value = "openUserPage", method = RequestMethod.GET)
    public String openUserPage(){
        return "/yewu/user";
    }
}
