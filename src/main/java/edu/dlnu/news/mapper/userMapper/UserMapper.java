package edu.dlnu.news.mapper.userMapper;

import java.util.List;
import java.util.Map;

import edu.dlnu.news.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface UserMapper {

    @Insert("insert into test_connection (id) values(2)")
    int Insert();

    @Select("SELECT * FROM user WHERE no = #{loginSno} AND pwd = #{loginPwd} ")
    List<Map<String,Object>> loginValid(Map<String,String> loginInfo);

    @Select("SELECT id,username from user where no = #{sno} ")
    Map<String,Object> isRegister(Integer sno);

    @Insert("INSERT INTO user (username,pwd,NO) VALUES(#{username},#{pwd},#{no})")
    Integer toRegisterAUser(User user);

    @Update("UPDATE user set pwd = #{pwd} WHERE NO= #{no}")
    Integer updateUserPwd(String pwd, Integer no);

    @Update("update user set avatar = #{avatar} where id = #{id}")
    Integer updateUserAvatar(Integer id,String avatar);

    @Select("SELECT avatar FROM user WHERE id = #{id}")
    String getUserAvatar(Integer id);

}
