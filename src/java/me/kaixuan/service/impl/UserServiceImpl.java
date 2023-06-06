package me.kaixuan.service.impl;

import me.kaixuan.mapper.UserMapper;
import me.kaixuan.entity.User;
import me.kaixuan.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    /*查询所有用户*/
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    /*添加用户*/
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public User selectUserByEmail(String userNameOrMail, String password) {
        return userMapper.selectUserByEmail(userNameOrMail, password);
    }

    @Override
    public User selectUserByUsername(String userNameOrMail, String password) {
        return userMapper.selectUserByUsername(userNameOrMail, password);
    }

    @Override
    public void selectUserById(Integer id) {
        userMapper.selectUserById(id);
    }

    @Override
    public void updatepwd(String password,Integer id) {
        userMapper.updatepwd(password,id);
    }

    @Override
    public User selectUserByOneEmail(String email) {
        return userMapper.selectUserByOneEmail(email);
    }

    @Override
    public void updateInfo(String username, String email, String avatar, Integer id) {
        userMapper.updateInfo(username,email,avatar,id);
    }

    @Override
    public void deleteUserById(Integer user) {
        userMapper.deleteUserById(user);
    }
}