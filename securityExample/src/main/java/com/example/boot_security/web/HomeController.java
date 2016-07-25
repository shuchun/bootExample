package com.example.boot_security.web;

import com.example.boot_security.domain.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IBM on 2016/7/11.
 */

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model){
        Msg msg=new Msg("测试标题","测试内容","额外信息,只对管理员显示。");
        model.addAttribute("msg",msg);

        return "index";
    }
}
