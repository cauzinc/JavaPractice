package com.myMall.dao;

import com.myMall.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String username);

    // 注册时检查邮箱是否被使用
    int checkUserEmail(String email);

    // 修改资料时检查邮箱是否被别人使用
    int checkEmailUsed(@Param("email")String email, @Param("userId")int userId);

    int checkUserQuestion(@Param("username") String username,@Param("question") String question,@Param("answer") String answer);

    int checkUserPassword(@Param("userId") int userId,@Param("oldPassword") String oldPassword);

    User selectLogin(@Param("username") String username,@Param("password") String password);

    String selectQuestionByUsername(@Param("username") String username);

    int setUserPassword(@Param("username")String username, @Param("newPassword")String newPassword);

}