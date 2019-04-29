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

    int checkUserEmail(String email);

    int checkUserQuestion(@Param("username") String username,@Param("question") String question,@Param("answer") String answer);

    int checkUserPassword(@Param("userId") int userId,@Param("oldPassword") String oldPassword);

    User selectLogin(@Param("username") String username,@Param("password") String password);

    String selectQuestionByUsername(@Param("username") String username);

    int setUserPassword(@Param("username")String username, @Param("newPassword")String newPassword);

}