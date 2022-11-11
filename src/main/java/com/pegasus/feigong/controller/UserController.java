package com.pegasus.feigong.controller;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

        // todo 密码明文写死
        String user = "admin";
        String pass = "admin";

        @ApiOperation(value = "登录")
        @RequestMapping("/login")
        public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("captcha") String captcha, Model model, HttpSession session, HttpServletRequest request) {

            // 验证码复用
            if (!CaptchaUtil.ver(captcha, request)) {
                CaptchaUtil.clear(request);
                model.addAttribute("msg", "验证码不正确");
                return "login";
            }

            if (user.equals(username) && pass.equals(password)) {
                session.setAttribute("LoginUser", username);
                return "redirect:/index";
            } else {
                model.addAttribute("msg", "用户名或者密码错误");
                return "login";
            }
        }

        @ApiOperation(value = "注销")
        @GetMapping("/logout")
        public String logout(HttpSession session) {
            session.invalidate();
            return "redirect:/login";
        }

        @ApiOperation(value = "获取验证码")
        @RequestMapping("/captcha")
        public void captcha(javax.servlet.http.HttpServletRequest request, HttpServletResponse response) throws Exception {

            // 设置请求头为输出图片类型
            response.setContentType("image/gif");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            //字母类型
            //SpecCaptcha captcha = new SpecCaptcha(130, 48, 4);

            // 中文gif类型
            //ChineseGifCaptcha captcha = new ChineseGifCaptcha(130, 48);

            // 中文类型
            //ChineseCaptcha captcha = new ChineseCaptcha(130, 48);

            // 算术类型
            ArithmeticCaptcha captcha = new ArithmeticCaptcha();
            captcha.setLen(2);  // 几位数运算，默认是两位
            captcha.getArithmeticString();  // 获取运算的公式：3+2=?
            captcha.text();  // 获取运算的结果：5

            //验证码存入session或存入redis
            //存redis通过UUID.randomUUID()生成key，specCaptcha.text().toLowerCase()生成value，存入redis并将key返回给前端，在登录时从redis中获取并验证
            //存session则如下，
            request.getSession().setAttribute("captcha", captcha.text().toLowerCase());
            // 输出图片流
            captcha.out(response.getOutputStream());
        }


}
