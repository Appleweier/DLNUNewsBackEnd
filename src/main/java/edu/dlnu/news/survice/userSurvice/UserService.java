package edu.dlnu.news.survice.userSurvice;

import edu.dlnu.news.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
     List<Map<String,Object>> loginValid(Map<String,String> loginInfo);
     Map<String, Object> isRegister(Integer sno);
     Integer toRegisterAUser(User user);
     Integer updateUserPwd(String pwd, Integer no);
     Integer updateAvatar(Integer id,String avatar);
     String getUserAvatar(Integer id);
}
