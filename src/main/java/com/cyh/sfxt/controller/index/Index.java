package com.cyh.sfxt.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Index {

    /**
     * 登陆首页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "/base/index";
    }

    @RequestMapping("/ceShi")
    public String ceShi(){
        return "/base/ceShi";
    }

    /**
     * 登陆界面
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "/yewu/login";
    }
}
