package me.kaixuan.mapper;

import me.kaixuan.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface UserMapper {
    List<User> selectAllUser();

    Integer addUser(User user);

    User selectUserByEmail(@Param("email") String userNameOrMail, @Param("password") String password);

    User selectUserByUsername(@Param("username") String userNameOrMail, @Param("password") String password);

    User selectUserById(Integer id);

    void updatepwd(@Param("password")String password,@Param("id") Integer id);

    User selectUserByOneEmail(String email);

    void updateInfo(@Param("username") String username,@Param("email") String email, @Param("avatar") String avatar, @Param("id") Integer id, @Param("userType") Integer userType);

    void deleteUserById(Integer user);

    List<User> findUserByUsername(String search);

    List<User> findUserByEmail(String search);
}
