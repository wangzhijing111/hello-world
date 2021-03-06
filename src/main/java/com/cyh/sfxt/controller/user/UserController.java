package com.cyh.sfxt.controller.user;
import	java.text.SimpleDateFormat;

import cn.hutool.json.JSONObject;
import com.cyh.sfxt.entirty.Liebiao;
import com.cyh.sfxt.entirty.Users;
import com.cyh.sfxt.entirty.result.ExceptionMsg;
import com.cyh.sfxt.entirty.result.ResponseData;
import com.cyh.sfxt.service.ResolveExcelService;
import com.cyh.sfxt.service.SavePhotoService;
import com.cyh.sfxt.service.UserService;
import com.cyh.sfxt.util.Const;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ResolveExcelService resolveExcelService;
    @Autowired
    private SavePhotoService savePhotoService;

    /**
     * 用户登陆
     *
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
            request.getSession().setAttribute("user", users);
            return "/base/index";
        } else {
            return "";
        }
    }

    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Description 答辩登录
     * @Date 17:00 2020/3/29
     * @Param [username, password, request, response]
     **/
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/loginUserDb", method = RequestMethod.POST)
    public Map<String, Object> loginDb(@RequestBody Users users, HttpServletRequest request, HttpServletResponse response) {
        Users users1 = new Users();
        System.out.println("用户名:" + users.getUsername() + "--------------------->密码:" + users.getPassword());
        Map<String, Object> map = new HashMap<>();
        try {
            users1 = userService.login(users.getUsername(), users.getPassword());
            if(null != users1){
                map.put("code", "0");
                map.put("user", users1);
            }else{
                map.put("code", "1");
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @Description  答辩获取列表
     * @Date 0:34 2020/3/30
     * @Param [request, response]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/loginUserDbData", method = RequestMethod.POST)
    public List<Map<String,Object>> loginDbData(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String,Object>> list = new ArrayList<>();
        list =  userService.finfAll();
        return  list;
    }
    /**
     * @Description  答辩添加数据
     * @Date 0:34 2020/3/30
     * @Param [liebiao, request, response]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public  Map<String, Object> saveData(@RequestBody Liebiao liebiao, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(null != liebiao && StringUtils.isNotBlank(liebiao.getId())){
            userService.update(liebiao.getName(),liebiao.getProvince(),liebiao.getCity(), liebiao.getAddress(), liebiao.getZip(),liebiao.getDate(),liebiao.getId());
        }else{
            userService.save(UUID.randomUUID().toString().replace("-", ""),liebiao.getName(),liebiao.getProvince(),liebiao.getCity(), liebiao.getAddress(), liebiao.getZip(),sdf.format(new Date()));
        }
        map.put("code","0");
        return map;
    }

    /**
     * @Description  答辩条件查询数据
     * @Date 0:34 2020/3/30
     * @Param [liebiao, request, response]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/loginDbDataSearch", method = RequestMethod.POST)
    public List<Map<String,Object>> loginDbDataSearch(@RequestBody Liebiao liebiao, HttpServletRequest request, HttpServletResponse response) {
        List<Map<String,Object>> list = new ArrayList<>();
        list =  userService.loginDbDataSearch(liebiao.getName(),liebiao.getAddress());
        return  list;
    }
    /**
     * @Description  答辩删除数据
     * @Date 0:35 2020/3/30
     * @Param [liebiao, request, response]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public List<Map<String,Object>> delete(@RequestBody Liebiao liebiao, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put("code","0");
        userService.delete(liebiao.getId());
        List<Map<String,Object>> list = new ArrayList<>();
        list =  userService.finfAll();
        return  list;
    }

    /**
     * 导入用户
     * @param file
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/daoruUserList", method = RequestMethod.POST)
    public String daoruUserList(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        Object result;
        ResponseData responseData = null;
        try {
            List<Users> usersList = resolveExcelService.resolveExcel(file);
            System.out.println(usersList.toString());
            for (Users u : usersList) {
                Users users = new Users();
                if (null != u) {
                    if (null != u.getUsername() && !"".equals(u.getUsername())) {
                        users.setUsername(u.getUsername());
                    }
                    if (null != u.getNickName() && !"".equals(u.getNickName())) {
                        users.setNickName(u.getNickName());
                    }
                    if (null != u.getPhone() && !"".equals(u.getPhone())) {
                        users.setPhone(u.getPhone());
                    }
                    if (null != u.getPassword() && !"".equals(u.getPassword())) {
                        users.setPassword(u.getPassword());
                    }
                    if (null != u.getSex() && !"".equals(u.getSex())) {
                        users.setSex(u.getSex());
                    }
                }
                //保存用户
                responseData = userService.saveUserList(users);
            }
            if (null != responseData && "0".equals(responseData.getResultCode())) {
                return "/base/index";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/base/index";
    }

    /**
     * 上传图片
     *
     * @param file
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/daoruPhoto", method = RequestMethod.POST)
    public ResponseData daoruPhoto(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        try {
            // TODO 保存数据到数据库
            savePhotoService.insertSelective(file);
            Calendar currTime = Calendar.getInstance();
            String time = String.valueOf(currTime.get(Calendar.YEAR)) + String.valueOf((currTime.get(Calendar.MONTH) + 1));
            String path = "D:" + File.separator + "img" + File.separator + time;
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            suffix = suffix.toLowerCase();
            if (suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".png") || suffix.equals(".gif")) {
                String fileName = UUID.randomUUID().toString() + suffix;
                File targeFile = new File(path, fileName);
                if (!targeFile.getParentFile().exists()) { //注意，判断父级路径是否存在
                    targeFile.getParentFile().mkdirs();
                }
                long size = 0;
                // TODO 保存
                file.transferTo(targeFile);
                size = file.getSize();
                JSONObject result = new JSONObject();
                result.put("fileUrl", "/img/" + time + fileName);
                result.put("url", "/img/" + time + fileName);
                result.put("state", "SUCCESS");
                result.put("title", fileName);
                result.put("original", fileName);
                result.put("type", suffix);
                result.put("size", size);
                ResponseData responseData = new ResponseData(ExceptionMsg.SUCCESS, result);
                return responseData;
            } else {
                JSONObject result = new JSONObject();
                result.put("ss", false);
                result.put("msg", "格式不支持");
                ResponseData responseData = new ResponseData(ExceptionMsg.FAILED, result);
                return responseData;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
     *
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
     *
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
     *
     * @return
     */
    @RequestMapping(value = "openUserPage", method = RequestMethod.GET)
    public String openUserPage() {
        return "/yewu/user";
    }

    /**
     * 打开产品界面
     *
     * @return
     */
    @RequestMapping(value = "daoruUsers", method = RequestMethod.GET)
    public String daoruUsers() {
        return "/yewu/prodect";
    }
}
