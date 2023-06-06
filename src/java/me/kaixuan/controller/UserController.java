package me.kaixuan.controller;

import me.kaixuan.entity.User;
import me.kaixuan.service.UserService;
import me.kaixuan.utils.SendMail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.regex.Pattern;

@Controller
public class UserController {
    @Resource
    private UserService userService;
    //密码正则表达式
    public String regex ="\\S*(?=\\S{6,})(?=\\S*\\d)(?=\\S*[A-Z])(?=\\S*[a-z])(?=\\S*[!@#$%^&amp;*? ])\\S*";
    //验证码
    String code = String.valueOf((int) ((Math.random()*9+1)*100000));
    @RequestMapping("/cancelout")
    public String cancelOut(HttpServletRequest request, HttpServletResponse response) {
        String oldpwd = request.getParameter("old-password");
        Cookie[] cookies = request.getCookies();
        if (oldpwd.equals(((User) request.getSession().getAttribute("user")).getPassword())) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
                if (cookie.getName().equals("avatar")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
                if (cookie.getName().equals("password")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
                if (cookie.getName().equals("usertpye")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
            userService.deleteUserById(((User) request.getSession().getAttribute("user")).getId());
            request.getSession().invalidate();
            return "redirect:reg";
        } else if (oldpwd.equals("")) {
            request.setAttribute("msg", "请先输入原密码");
            return "info";
        } else{
            request.setAttribute("msg", "原密码错误");
            return "info";
        }
    }
    @RequestMapping("/updateinfo")
    public String updateInfo(@RequestParam("file") MultipartFile file,HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String avatar = request.getParameter("avatar")==null?"https://www.helloimg.com/images/2023/05/24/oJs0km.jpg":request.getParameter("avatar");
        Cookie[] cookies = request.getCookies();
        String path = null;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path paths = Paths.get("E:\\WorkPlace\\web_workplace\\Assignment\\web\\image\\" + file.getOriginalFilename());
                Files.write(paths, bytes);
                path = "/image/" + file.getOriginalFilename();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (avatar.equals("avatar")){
            avatar = path;
        }
        if (avatar.equals("avatar1")){
            avatar = "https://www.helloimg.com/images/2023/05/24/oJs0km.jpg";
        }
        if (avatar.equals("avatar2")){
            avatar = "https://www.helloimg.com/images/2023/05/29/oJjukm.png";
        }
        if (avatar.equals("avatar3")){
            avatar = "https://www.helloimg.com/images/2023/05/29/oJu5ah.png";
        }
        if (avatar.equals("avatar4")){
            avatar = "https://www.helloimg.com/images/2023/05/29/oJjj90.png";
        }
        userService.updateInfo(username,email,avatar,user.getId());
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("avatar")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                Cookie cookie1 = new Cookie("avatar", avatar);
                cookie1.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(cookie1);
            }
            if (cookie.getName().equals("username")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                Cookie cookie1 = new Cookie("username", username);
                cookie1.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(cookie1);
            }
        }
        return "redirect:info";
    }
    @RequestMapping("/fgpwd")
    public String forgetpwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        User user = userService.selectUserByOneEmail(email);
        if (user == null){
            request.setAttribute("msg","邮箱不存在");
            return "forward:forgetpwd";
        }
        try {
            SendMail sendMail = new SendMail();
            MimeMessage message = sendMail.getMessage();
            message.setRecipient(MimeMessage.RecipientType.TO, new javax.mail.internet.InternetAddress(request.getParameter("email")));
            message.setSubject("我的网站【www.kaixuan.me】");
            message.setContent("您好，尊敬的"+user.getUsername()+"，您的密码为: "+user.getPassword()+"，请妥善保管，切勿告知他人！【如果不是您的操作，请忽略】","text/html;charset=UTF-8");
            sendMail.getTransport().sendMessage(message,message.getAllRecipients());
            request.setAttribute("msg","身份验证邮件发送成功");
            return "forward:forgetpwd";
        } catch (MessagingException e) {
            request.setAttribute("msg","身份验证邮件发送失败");
            return "forward:forgetpwd";
        } catch (GeneralSecurityException e) {
            request.setAttribute("msg","身份验证邮件发送失败");
            return "forward:forgetpwd";
        }
    }

    @RequestMapping("/main")
    public String mainpage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            return "main";
    }
    @RequestMapping("/updatepwd")
    public String updatepwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("new-password");
        String oldPassword = request.getParameter("old-password");
        String confirmPassword = request.getParameter("confirm-password");
        if (oldPassword.equals("")){
            request.setAttribute("msg","请输入原密码");
            return "info";
        }
        if (newPassword.equals("")){
            request.setAttribute("msg","请输入新密码");
            return "info";
        }

        if (confirmPassword.equals("")){
            request.setAttribute("msg","请输入确认密码");
            return "info";
        }
        if (!newPassword.equals(confirmPassword)){
            request.setAttribute("msg","两次密码不一致");
            return "info";
        }
        if (!Pattern.matches(regex,newPassword)){
            request.setAttribute("msg","密码格式不正确");
            return "info";
        }
        if (!oldPassword.equals(((User)request.getSession().getAttribute("user")).getPassword())){
            request.setAttribute("msg","原密码错误");
            return "info";
        }
        User user = (User) request.getSession().getAttribute("user");
        userService.updatepwd(newPassword,user.getId());
        request.setAttribute("msg","修改成功");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("password".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    Cookie cookie1 = new Cookie("password", newPassword);
                    cookie1.setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(cookie1);
                }
            }
        }
        return "info";
    }
    @RequestMapping("/info")
    public String info(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        String username = "";
        String password = "";
        String userTpye = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                }
                if ("password".equals(cookie.getName())) {
                    password = cookie.getValue();
                }
                if ("usertype".equals(cookie.getName())) {
                    userTpye = cookie.getValue();
                }
            }
        }
        User user = userService.selectUserByUsername(username, password);
        System.out.println(user);
        System.out.println(userTpye);
        if (Integer.parseInt(userTpye)==3){
            System.out.println("进入");
        }
        request.getSession().setAttribute("user", user);
        return "info";
    }
    //登录
    @RequestMapping("/signup")
    public String signup(String userNameOrMail, String password, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        User user = null;
        if (Pattern.matches(regEx1,userNameOrMail)){
             user = userService.selectUserByEmail(userNameOrMail, password);
            if (user==null){
                request.setAttribute("msg2","电子邮件地址或密码错误,请重试！");
                return "reg";
            } else {
                request.getSession().setAttribute("user", user);
                Cookie cookie = new Cookie("username", user.getUsername());
                Cookie cookie1 = new Cookie("password", user.getPassword());
                Cookie cookie2 = new Cookie("avatar", user.getAvatar());
                Cookie cookie3 = new Cookie("usertype",user.getUserType().toString());
                cookie.setMaxAge(60 * 60 * 24 * 7);
                cookie1.setMaxAge(60 * 60 * 24 * 7);
                cookie2.setMaxAge(60 * 60 * 24 * 7);
                cookie3.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(cookie);
                response.addCookie(cookie1);
                response.addCookie(cookie2);
                response.addCookie(cookie3);
                return "redirect:main";
             }
        }else {
             user = userService.selectUserByUsername(userNameOrMail, password);
            if (user==null){
                request.setAttribute("msg2","用户名或密码错误，请重试！");
                return "reg";
            } else {
                request.getSession().setAttribute("user", user);
                Cookie cookie = new Cookie("username", user.getUsername());
                Cookie cookie1 = new Cookie("password", user.getPassword());
                Cookie cookie2 = new Cookie("avatar", user.getAvatar());
                Cookie cookie3 = new Cookie("usertype",user.getUserType().toString());
                cookie.setMaxAge(60 * 60 * 24 * 7);
                cookie1.setMaxAge(60 * 60 * 24 * 7);
                cookie2.setMaxAge(60 * 60 * 24 * 7);
                cookie3.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(cookie);
                response.addCookie(cookie1);
                response.addCookie(cookie2);
                response.addCookie(cookie3);
                return "redirect:main";
            }
        }
    }
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("mail");
        String avatar = "https://www.helloimg.com/images/2023/05/24/oJs0km.jpg";
        String password= request.getParameter("password");
        String password2 = request.getParameter("password2");
        String code =  request.getParameter("code");
        request.setAttribute("userName",request.getParameter("userName"));
        request.setAttribute("password",request.getParameter("password"));
        request.setAttribute("mail",request.getParameter("mail"));
        request.setAttribute("password2",request.getParameter("password2"));
        request.setAttribute("code",request.getParameter("code"));
        if ( !code.equals(this.code)){
            request.setAttribute("msg","验证码错误");
            return "forward:reg";
        } else {
            request.setAttribute("code",code);
        }
        if (!Pattern.matches(regex,password)){
            request.setAttribute("msg","密码格式错误");
            return "forward:reg";
        }
        if (!password.equals(password2)){
            request.setAttribute("msg","两次密码不一致");
            return "forward:reg";
        }
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.setAvatar(avatar);
        userService.addUser(user);
        Cookie cookie = new Cookie("username",userName);
        Cookie cookie1 = new Cookie("password",password);
        Cookie cookie2 = new Cookie("avatar",avatar);
        Cookie cookie3 = new Cookie("usertype","2");
        cookie.setMaxAge(60*60*24*7);
        cookie1.setMaxAge(60*60*24*7);
        cookie2.setMaxAge(60*60*24*7);
        cookie3.setMaxAge(60*60*24*7);
        response.addCookie(cookie);
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        response.addCookie(cookie3);
        return "redirect:main";
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("user");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")||cookie.getName().equals("password")||cookie.getName().equals("avatar")||cookie.getName().equals("usertype")){
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return "redirect:main";
    }
    @RequestMapping("/sendmail")
    public String sendMail(HttpServletRequest request) {
        try {
        SendMail sendMail = new SendMail();
        MimeMessage message = sendMail.getMessage();
        message.setRecipient(MimeMessage.RecipientType.TO, new javax.mail.internet.InternetAddress(request.getParameter("mail")));
        message.setSubject("我的网站【www.kaixuan.me】");
        message.setContent("您好，"+request.getParameter("userName")+"，您正在注册本网站账号，你的验证码为: "+code+" 【本验证码10分钟内有效，如果不是您的操作，请忽略】","text/html;charset=UTF-8");
        sendMail.getTransport().sendMessage(message,message.getAllRecipients());
        request.setAttribute("msg","验证码发送成功");
        request.setAttribute("userName",request.getParameter("userName"));
        request.setAttribute("password",request.getParameter("password"));
        request.setAttribute("mail",request.getParameter("mail"));
        request.setAttribute("password2",request.getParameter("password2"));
        return "forward:reg";
        } catch (MessagingException e) {
            request.setAttribute("msg","邮件发送失败");
            return "forward:reg";
        } catch (GeneralSecurityException e) {
            request.setAttribute("msg","邮件发送失败");
            return "forward:reg";
        }
    }
    @RequestMapping("/test")
    public String test(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get("E:\\WorkPlace\\web_workplace\\Assignment\\web\\image\\" + file.getOriginalFilename());
        Files.write(path, bytes);
        model.addAttribute("msg","上传成功");
        return "forward:totest";
    }
}
