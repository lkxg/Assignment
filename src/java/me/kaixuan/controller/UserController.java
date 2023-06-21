package me.kaixuan.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.kaixuan.entity.User;
import me.kaixuan.service.UserService;
import me.kaixuan.utils.SendEmailUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @Description: 用户控制器
 * 1.删除用户
 * 2.添加用户
 * 3.用户管理
 * 4.注销
 * 5.修改用户信息
 * 6.忘记密码
 * 7.修改密码
 * 8.显示用户信息
 * 9.登录
 * 10.注册
 * 11.退出登录
 * 12.发送验证码
 */
@Controller
public class UserController {
    //注入UserService
    @Resource
    private UserService userService;
    //密码正则表达式（6-16位，必须包含大小写字母、数字、特殊符号）
    public String regex = "\\S*(?=\\S{6,})(?=\\S*\\d)(?=\\S*[A-Z])(?=\\S*[a-z])(?=\\S*[!@#$%^&amp;*? ])\\S*";
    //邮箱正则表达式 （邮箱格式）
    String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    //验证码（6位随机数）
    String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));

    /**
     * @param model  控制器和视图之间传递数据
     * @param userId 用户id
     * @return 跳转到usermanage
     * @Description: 删除用户
     */
    @RequestMapping("/deleteuser")
    public String deleteUser(Integer userId, Model model) {
        //调用userService删除用户
        userService.deleteUserById(userId);
        //设置消息，用于前端提示
        model.addAttribute("msg", "删除成功！");
        //返回usermanage.jsp
        return "forward:usermanage";
    }

    /**
     * @param user  前端输入信息传入用户
     * @param model 控制器和视图之间传递数据
     * @return 跳转到usermanage
     * @Description: 添加用户
     */

    @RequestMapping("/adduser")
    public String addUser(User user, Model model) {
        //设置默认头像
        user.setAvatar("https://www.helloimg.com/images/2023/05/24/oJs0km.jpg");
        //调用userService添加用户
        userService.addUser(user);
        //设置消息，用于前端提示
        model.addAttribute("msg", "添加成功！");
        //返回usermanage.jsp
        return "forward:usermanage";
    }

    /**
     * @param model    控制器和视图之间传递数据
     * @param pageNum  当前页码(默认为1)
     * @param pageSize 每页显示条数(默认为8)
     * @param search   搜索内容
     * @return 跳转到usermanage.jsp
     * @Description: 用户分页管理
     */

    @RequestMapping("/usermanage")
    public String userManage(Model model, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "8") Integer pageSize, String search) {
        //分页
        PageHelper.startPage(pageNum, pageSize);
        //查询所有用户
        List<User> users = userService.selectAllUser();
        //将查询到的用户封装到PageInfo对象中
        PageInfo<User> pageInfo = new PageInfo<>(users);
        //将PageInfo对象存入model中
        model.addAttribute("pageInfo", pageInfo);
        //将查询到的用户存入model中
        model.addAttribute("userList", pageInfo.getList());
        //搜索内容不为空
        if (search != null) {
            //判断搜索内容是否为邮箱
            if (Pattern.matches(regEx1, search)) {
                //根据邮箱查询用户
                List<User> user = userService.findUserByEmail(search);
                //判断是否查询到用户
                if (user != null) {
                    //将查询到的用户存入model中
                    model.addAttribute("searchList", user);
                    //返回usermanage.jsp
                    return "usermanage";
                } else {
                    //若没有查询到用户，设置消息，用于前端提示
                    model.addAttribute("msg", "没有找到该用户！");
                }
            } else {
                //根据用户名查询用户
                List<User> user = userService.findUserByUsername(search);
                //判断是否查询到用户
                if (user != null) {
                    //将查询到的用户存入model中
                    model.addAttribute("searchList", user);
                    //返回usermanage.jsp
                    return "usermanage";
                } else {
                    //若没有查询到用户，设置消息，用于前端提示
                    model.addAttribute("msg", "没有找到该用户！");
                }
            }

        }
        //返回usermanage.jsp
        return "usermanage";
    }

    /**
     * @param request  请求
     * @param response 响应
     * @return 跳转到reg
     * @Description: 注销
     */

    @RequestMapping("/cancelout")
    public String cancelOut(HttpServletRequest request, HttpServletResponse response) {
        //获取用户输入的原密码
        String oldpwd = request.getParameter("old-password");
        //获取cookie
        Cookie[] cookies = request.getCookies();
        //判断原密码是否正确
        if (oldpwd.equals(((User) request.getSession().getAttribute("user")).getPassword())) {
            //遍历cookie
            for (Cookie cookie : cookies) {
                //查找cookie中的username的值
                if (cookie.getName().equals("username")) {
                    //设置cookie的有效期为0
                    cookie.setMaxAge(0);
                    //将cookie添加到响应中
                    response.addCookie(cookie);
                }
                //查找cookie中的avatar的值
                if (cookie.getName().equals("avatar")) {
                    //设置cookie的有效期为0
                    cookie.setMaxAge(0);
                    //将cookie添加到响应中
                    response.addCookie(cookie);
                }
                //查找cookie中的password的值
                if (cookie.getName().equals("password")) {
                    //设置cookie的有效期为0
                    cookie.setMaxAge(0);
                    //将cookie添加到响应中
                    response.addCookie(cookie);
                }
                //查找cookie中的usertype的值
                if (cookie.getName().equals("usertype")) {
                    //设置cookie的有效期为0
                    cookie.setMaxAge(0);
                    //将cookie添加到响应中
                    response.addCookie(cookie);
                }
            }
            //调用userService删除用户
            userService.deleteUserById(((User) request.getSession().getAttribute("user")).getId());
            //清除session
            request.getSession().invalidate();
            //跳转到登录页面
            return "redirect:reg";
        } //若原密码为空
        else if (oldpwd.equals("")) {
            //设置消息，用于前端提示
            request.setAttribute("msg", "请先输入原密码");
            //返回info.jsp
            return "info";
        } //若原密码错误
        else {
            //设置消息，用于前端提示
            request.setAttribute("msg", "原密码错误");
            //返回info.jsp
            return "info";
        }
    }

    @RequestMapping("/updateinfo")
    public String updateInfo(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        User user = request.getSession().getAttribute("userManage") == null ? ((User) request.getSession().getAttribute("user")) : ((User) request.getSession().getAttribute("userManage"));
        boolean isAmdin = request.getSession().getAttribute("userManage") == null ? false : true;
        Integer userType = null;
        if (isAmdin) {
            userType = request.getParameter("userType").equals("common") ? 2 : 3;
        }
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String avatar = request.getParameter("avatar") == null ? user.getAvatar() : request.getParameter("avatar");
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
        if (avatar.equals("avatar")) {
            avatar = path;
        }
        if (avatar.equals("avatar1")) {
            avatar = "https://www.helloimg.com/images/2023/05/26/oJpm6S.png";
        }
        if (avatar.equals("avatar2")) {
            avatar = "https://www.helloimg.com/images/2023/06/07/osxC5E.gif";
        }
        if (avatar.equals("avatar3")) {
            avatar = "https://www.helloimg.com/images/2023/06/07/osqySu.gif";
        }
        if (avatar.equals("avatar4")) {
            avatar = "https://www.helloimg.com/images/2023/06/07/osx5pv.gif";
        }
        userService.updateInfo(username, email, avatar, user.getId(), userType);
        if (!isAmdin) {
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
        }
        if (isAmdin) {
            request.setAttribute("msg", "修改成功");
            return "forward:info?userId=" + user.getId();
        } else {
            return "redirect:info";
        }
    }

    /**
     * 忘记密码
     *
     * @param email 用户邮箱
     * @param model 数据模型
     * @return 返回页面
     */

    @RequestMapping("/fgpwd")
    public String forgetpwd(String email, Model model) {
        //通过邮箱查询用户
        User user = userService.selectUserByOneEmail(email);
        //判断用户是否存在
        if (user == null) {
            model.addAttribute("msg", "邮箱不存在");
            return "forward:forgetpwd";
        }
        try {
            //发送邮件
            SendEmailUtil sendEmailUtil = new SendEmailUtil();
            sendEmailUtil.sendEmail(email, "忘记密码", "您好，尊敬的" + user.getUsername() + "，您的密码为: " + user.getPassword() + "，请妥善保管，切勿告知他人！【如果不是您的操作，请忽略】");
            //提示成功发送信息
            model.addAttribute("msg", "密码已发送至您的邮箱，请注意查收");
            //返回页面
            return "forward:forgetpwd";
        } catch (MessagingException | IOException e) {
            //提示失败发送信息
            model.addAttribute("msg", "发送失败");
            //返回页面
            return "forward:forgetpwd";
        }

    }

    @RequestMapping("/updatepwd")
    public String updatepwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("new-password");
        String oldPassword = request.getParameter("old-password");
        String confirmPassword = request.getParameter("confirm-password");
        if (oldPassword.equals("")) {
            request.setAttribute("msg", "请输入原密码");
            return "info";
        }
        if (newPassword.equals("")) {
            request.setAttribute("msg", "请输入新密码");
            return "info";
        }

        if (confirmPassword.equals("")) {
            request.setAttribute("msg", "请输入确认密码");
            return "info";
        }
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("msg", "两次密码不一致");
            return "info";
        }
        if (!Pattern.matches(regex, newPassword)) {
            request.setAttribute("msg", "密码格式不正确");
            return "info";
        }
        if (!oldPassword.equals(((User) request.getSession().getAttribute("user")).getPassword())) {
            request.setAttribute("msg", "原密码错误");
            return "info";
        }
        User user = (User) request.getSession().getAttribute("user");
        userService.updatepwd(newPassword, user.getId());
        request.setAttribute("msg", "修改成功");
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
    public String info(HttpServletRequest request, Integer userId) {
        if (userId != null) {
            User userManage = userService.selectUserById(userId);
            request.getSession().setAttribute("userManage", userManage);
        } else {
            request.getSession().removeAttribute("userManage");
        }
        Cookie[] cookies = request.getCookies();
        String username = "";
        String password = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                }
                if ("password".equals(cookie.getName())) {
                    password = cookie.getValue();
                }
            }
        }
        User user = userService.selectUserByUsername(username, password);
        request.getSession().setAttribute("user", user);
        if (userId != null && user.getId() == userId) {
            return "redirect:info";
        }
        return "info";
    }

    /**
     * 用户登录
     *
     * @param userNameOrMail 用户名或邮箱
     * @param password       密码
     * @param model          向前端传递数据
     * @param response       响应
     * @param session        会话
     * @return 返回页面
     */

    @RequestMapping("/signup")
    public String signup(String userNameOrMail, String password, Model model, HttpServletResponse response, HttpSession session) {
        User user = null;
        //判断是否为邮箱
        if (Pattern.matches(regEx1, userNameOrMail)) {
            //若为用户输入为邮箱，通过邮箱查询用户
            user = userService.selectUserByEmail(userNameOrMail, password);
            //判断用户是否存在
            if (user == null) {
                //不存在则提示错误信息
                model.addAttribute("msg2", "电子邮件地址或密码错误,请重试！");
                //返回登录页面
                return "reg";
            } else {
                //存在则将用户信息存入session
                session.setAttribute("user", user);
                //设置session过期时间
                session.setMaxInactiveInterval(60 * 60);
                //设置cookie
                Cookie cookie = new Cookie("username", user.getUsername());
                Cookie cookie1 = new Cookie("password", user.getPassword());
                Cookie cookie2 = new Cookie("avatar", user.getAvatar());
                Cookie cookie3 = new Cookie("usertype", user.getUserType().toString());
                //设置cookie过期时间
                cookie.setMaxAge(60 * 60 * 24 * 7);
                cookie1.setMaxAge(60 * 60 * 24 * 7);
                cookie2.setMaxAge(60 * 60 * 24 * 7);
                cookie3.setMaxAge(60 * 60 * 24 * 7);
                //添加cookie
                response.addCookie(cookie);
                response.addCookie(cookie1);
                response.addCookie(cookie2);
                response.addCookie(cookie3);
                //跳转到主页
                return "redirect:main";
            }
        } else {
            //若为用户输入为用户名，通过用户名查询用户
            user = userService.selectUserByUsername(userNameOrMail, password);
            //判断用户是否存在
            if (user == null) {
                //不存在则提示错误信息
                model.addAttribute("msg2", "用户名或密码错误，请重试！");
                //返回登录页面
                return "reg";
            } else {
                //存在则将用户信息存入session
                session.setAttribute("user", user);
                //设置session过期时间
                session.setMaxInactiveInterval(60 * 60);
                //设置cookie
                Cookie cookie = new Cookie("username", user.getUsername());
                Cookie cookie1 = new Cookie("password", user.getPassword());
                Cookie cookie2 = new Cookie("avatar", user.getAvatar());
                Cookie cookie3 = new Cookie("usertype", user.getUserType().toString());
                //设置cookie过期时间
                cookie.setMaxAge(60 * 60 * 24 * 7);
                cookie1.setMaxAge(60 * 60 * 24 * 7);
                cookie2.setMaxAge(60 * 60 * 24 * 7);
                cookie3.setMaxAge(60 * 60 * 24 * 7);
                //添加cookie
                response.addCookie(cookie);
                response.addCookie(cookie1);
                response.addCookie(cookie2);
                response.addCookie(cookie3);
                //跳转到主页
                return "redirect:main";
            }
        }
    }

    /**
     * 用户注册
     *
     * @param user     前端传递的用户信息存入user对象
     * @param model    向前端传递数据
     * @param response 添加cookie
     * @param request  请求获取用户输入的验证码和第二次密码
     * @return 返回主页面
     */
    @RequestMapping("/login")
    public String login(User user, Model model, HttpServletResponse response, HttpServletRequest request) {
        //设置默认头像
        String avatar = "https://www.helloimg.com/images/2023/05/24/oJs0km.jpg";
        //获得用户输入的第二次密码
        String password2 = request.getParameter("password2");
        //获得用户输入的验证码
        String code = request.getParameter("code");
        //由于页面刷新，须重新自动填入用户已输入的用户名、密码、邮箱、重复密码、验证码
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("mail", user.getEmail());
        model.addAttribute("password2", password2);
        model.addAttribute("code", code);
        //判断验证码是否正确
        if (!code.equals(this.code)) {
            //不正确则提示错误信息
            model.addAttribute("msg", "验证码错误");
            //返回注册页面
            return "forward:reg";
        } else {
            //正确则返回验证码
            model.addAttribute("code", code);
        }
        //判断密码格式是否正确
        if (!Pattern.matches(regex, user.getPassword())) {
            //不正确则提示错误信息
            model.addAttribute("msg", "密码格式错误");
            //返回注册页面
            return "forward:reg";
        }
        //判断两次密码是否一致
        if (!user.getPassword().equals(password2)) {
            //不一致则提示错误信息
            model.addAttribute("msg", "两次密码不一致");
            //返回注册页面
            return "forward:reg";
        }
        user.setAvatar(avatar);
        //调用userService的addUser方法将用户信息存入数据库
        userService.addUser(user);
        //设置cookie
        Cookie cookie = new Cookie("username", user.getUsername());
        Cookie cookie1 = new Cookie("password", user.getPassword());
        Cookie cookie2 = new Cookie("avatar", user.getAvatar());
        Cookie cookie3 = new Cookie("usertype", "2");
        //设置cookie过期时间
        cookie.setMaxAge(60 * 60 * 24 * 7);
        cookie1.setMaxAge(60 * 60 * 24 * 7);
        cookie2.setMaxAge(60 * 60 * 24 * 7);
        cookie3.setMaxAge(60 * 60 * 24 * 7);
        //添加cookie
        response.addCookie(cookie);
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        response.addCookie(cookie3);
        //跳转到主页
        return "redirect:main";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("user");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username") || cookie.getName().equals("password") || cookie.getName().equals("avatar") || cookie.getName().equals("usertype") || cookie.getName().equals("JSESSIONID")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return "redirect:main";
    }

    /**
     * 发送注册邮箱验证码
     *
     * @param mail      邮箱
     * @param userName  用户名
     * @param password  密码
     * @param password2 重复密码
     * @param model     数据模型
     * @return String 跳转页面
     */

    @RequestMapping("/sendmail")
    public String sendMail(String mail, String userName, String password, String password2, Model model) {
        try {
            //获得sendEmailUtil对象
            SendEmailUtil sendEmailUtil = new SendEmailUtil();
            //发送邮件验证码
            sendEmailUtil.sendEmail(mail, "注册验证码", "您好，" + userName + "，您正在注册本网站账号，您的验证码为: " + code + " 【本验证码10分钟内有效，如果不是您的操作，请忽略】");
            //设置成功提示信息
            model.addAttribute("msg", "验证码发送成功");
            //由于页面刷新，须重新自动填入用户已输入的用户名、密码、邮箱、重复密码
            model.addAttribute("userName", userName);
            model.addAttribute("password", password);
            model.addAttribute("mail", mail);
            model.addAttribute("password2", password2);
            //返回reg
            return "forward:reg";
        } catch (MessagingException | IOException e) {
            //设置失败提示信息
            model.addAttribute("msg", "邮件发送失败");
            //返回reg
            return "forward:reg";
        }
    }

    @RequestMapping("/test")
    public String test(@RequestParam("file") MultipartFile file, Model model, HttpServletRequest request) {
        //判断上传文件是否为空
        if (!file.isEmpty() && file.getSize() > 0) {
            //获取原文件名
            String originalFilename = file.getOriginalFilename();
            //设置文件上传路径
            String dirPath = request.getServletContext().getRealPath("/upload/");
            System.out.println(dirPath);
            //判断文件夹是否存在
            File filePath = new File(dirPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            //获取用户名
            String userName = (String) request.getSession().getAttribute("username");
            String newFilename = userName + "_" + UUID.randomUUID() + "_" + originalFilename;
            System.out.println(newFilename);
            try {
                file.transferTo(new File(dirPath + newFilename));
                model.addAttribute("imgurl", "/upload/" + newFilename);
                model.addAttribute("msg", "上传成功");
                return "forward:totest";
            } catch (IOException e) {
                model.addAttribute("msg", "上传失败");
                return "forward:totest";
            }
        }
        model.addAttribute("msg", "上传失败");
        return "forward:totest";
    }
}
