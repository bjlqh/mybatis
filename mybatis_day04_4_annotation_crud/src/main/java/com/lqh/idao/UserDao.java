package com.lqh.idao;

import com.lqh.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

    @Insert("insert into user (username,birthday,sex,address)" +
            "values(#{username},#{birthday},#{sex},#{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void saveUser(User user);

    @Update("update user set username=#{username} where id=#{id}")
    public void updateUser(User user);

    @Delete("delete from user where id =#{id}")
    public void deleteUser(User user);

    @Select("select * from user")
    public List<User> findAllUser();

    @Select("select * from user where id =#{id}")
    public User findUserById(int id);

    @Select("select * from user where address like'%${address}%'")
    public List<User> findUserLikeX(User user);

    @Select("select count(*) from user where address like '%${address}%'")
    public int findCount(User user);

    @Select("<script>select * from user" +
            "<where>\n" +
            "            <if test=\"username != null and username != '' \">\n" +
            "                and username=#{username}\n" +
            "            </if>\n" +
            "            <if test=\"sex != null and sex != '' \">\n" +
            "                and sex = #{sex}\n" +
            "            </if>\n" +
            "            <if test=\"address !=null and address !=''\">\n" +
            "                and address =#{address}\n" +
            "            </if>\n" +
            "        </where></script>")
    public List<User> findUsers(User user);

    @Select("<script>  select * from user" +
            "<foreach collection=\"list\" item=\"lis\" open=\"WHERE id IN(\" close=\")\" separator=\",\">\n" +
            "            #{lis}\n" +
            "</foreach>" +
            "</script>")
    //select * from user WHERE id IN(#{lis})
    public List<User> findUserByListId(List list);
}
