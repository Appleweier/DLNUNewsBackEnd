package edu.dlnu.news.survice.userSurvice.userServiceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import edu.dlnu.news.entity.User;
import org.springframework.stereotype.Service;

import edu.dlnu.news.mapper.userMapper.UserMapper;
import edu.dlnu.news.survice.userSurvice.UserService;
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper um;
    @Override
    public List<Map<String, Object>> loginValid(Map<String, String> loginInfo) {
        return um.loginValid(loginInfo);
    }

    @Override
    public Map<String, Object> isRegister(Integer sno) {
        return um.isRegister(sno);
    }

    @Override
    public Integer toRegisterAUser(User user) {
        return um.toRegisterAUser(user);
    }

    @Override
    public Integer updateUserPwd(String pwd, Integer no) {
        return um.updateUserPwd(pwd,no);
    }

    @Override
    public Integer updateAvatar(Integer id, String avatar) {
        return um.updateUserAvatar(id,avatar);
    }

    @Override
    public String getUserAvatar(Integer id) {
        return um.getUserAvatar(id);
    }

}
