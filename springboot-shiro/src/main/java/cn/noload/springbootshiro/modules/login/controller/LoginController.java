package cn.noload.springbootshiro.modules.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author caohao
 * @date 2018-04-14
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @RequestMapping("")
    public String login() {
        return "login.html";
    }

    @RequestMapping("success")
    public String success() {
        return "success.html";
    }
}
