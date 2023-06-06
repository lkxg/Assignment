package me.kaixuan.service;

import me.kaixuan.entity.User;

import java.util.List;
public interface UserService {
    List<User> selectAllUser();
    Integer addUser(User user);

    User selectUserByEmail(String userNameOrMail, String password);

    User selectUserByUsername(String userNameOrMail, String password);

    void selectUserById(Integer id);

    void updatepwd(String password, Integer id);

    User selectUserByOneEmail(String email);

    void updateInfo(String username, String email, String avatar, Integer id);

    void deleteUserById(Integer user);
}
