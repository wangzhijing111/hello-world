package com.cyh.sfxt.controller.user;

import com.cyh.sfxt.entirty.Users;
import com.cyh.sfxt.entirty.result.ResponseData;
import com.cyh.sfxt.service.ResolveExcelService;
import com.cyh.sfxt.service.UserService;
import com.cyh.sfxt.util.Const;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ResolveExcelService resolveExcelService;
    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/loginUser")
    public String login(@Param("username") String username, @Param("password") String password, HttpServletRequest request, HttpServletResponse response) {
        Users users = new Users();
        System.out.println("用户名:" + username + "--------------------->密码:" + password);
        try {
            users = userService.login(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != users) {
            request.getSession().setAttribute("user",users);
            return "/base/index";
        } else {
            return "";
        }
    }

    /**
     * 导入用户
     * @param file
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/daoruUserList" ,method = RequestMethod.POST)
    public String daoruUserList(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        Object result;
        ResponseData responseData=null;
        try {
            List<Users> usersList=resolveExcelService.resolveExcel(file);
            System.out.println(usersList.toString());
            for(Users u:usersList){
                Users users=new Users();
                if(null != u){
                    if(null != u.getUsername() && !"".equals(u.getUsername())){
                        users.setUsername(u.getUsername());
                    }
                    if(null != u.getNickName() && !"".equals(u.getNickName())){
                        users.setNickName(u.getNickName());
                    }
                    if(null != u.getPhone() && !"".equals(u.getPhone())){
                        users.setPhone(u.getPhone());
                    }
                    if(null != u.getPassword() && !"".equals(u.getPassword())){
                        users.setPassword(u.getPassword());
                    }
                    if(null != u.getSex() && !"".equals(u.getSex())){
                        users.setSex(u.getSex());
                    }
                }
                //保存用户
                responseData=userService.saveUserList(users);
            }
            if(null !=responseData && "0".equals(responseData.getResultCode())){
                return "/base/index";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/base/index";
    }

    /**
     * 上传图片
     * @param file
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/daoruPhoto" ,method = RequestMethod.POST)
    public ResponseData daoruPhoto(@RequestParam("file") MultipartFile file,HttpServletRequest request, HttpServletResponse response){
        System.out.println(file.getOriginalFilename());
        return null;
    }
    /**
     * 根据条件查询用户人员列表
     *
     * @param users
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    public ResponseData getUserlist(Users users, @RequestParam(defaultValue = Const.PAGENUM) int pageNum, @RequestParam(defaultValue = Const.PAGESIZE) int pageSize) {
        return userService.getUserList(users, pageNum, pageSize);
    }
    /**
     * 逻辑删除用户
     * @param users
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ResponseData deleteUser(Users users) {
        return userService.deleteUser(users);
    }
    /**
     * 编辑用户
     * @param users
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public ResponseData editUser(@RequestBody Users users) {
        return userService.editUser(users);
    }
    /**
     * 新增用户
     *
     * @param users
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseData addUser(@RequestBody Users users) {
        return userService.addUser(users);
    }
    /**
     * 打开用户界面
     * @return
     */
    @RequestMapping(value = "openUserPage", method = RequestMethod.GET)
    public String openUserPage() {
        return "/yewu/user";
    }

    /**
     * 打开产品界面
     * @return
     */
    @RequestMapping(value = "daoruUsers", method = RequestMethod.GET)
    public String daoruUsers() {
        return "/yewu/prodect";
    }
}
