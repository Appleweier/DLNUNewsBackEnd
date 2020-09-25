package edu.dlnu.news.controller.userController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.dlnu.news.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.dlnu.news.survice.userSurvice.UserService;
import edu.dlnu.news.utils.JwtUtils;
import edu.dlnu.news.utils.RedisUtils;

@RestController
public class UserController{
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("/user/pwd")
    public Map<String,Object> UpdateUserPwd(@RequestBody Map<String,Object> loginInfo,HttpServletRequest request){
        Map<String,Object> info = new HashMap<>();
        Integer no = Integer.parseInt((String) loginInfo.get("no"));
        String token = request.getHeader("Authorization");

        Map<String, Object> tMap = JwtUtils.validateToken(token);
        Integer sno = (Integer) tMap.get("sno");

        String pwd = (String) loginInfo.get("pwd");
        if (!(sno.equals(no))){
            info.put("result","-1");
            return info;

        }

        Integer integer = userService.updateUserPwd(pwd, no);
        if ( integer==0){
            info.put("result","0");
        }else {
            info.put("result","1");
        }
        return info;
    }

    @PostMapping("/user/login")
    public Map<String,Object> LoginValid(@RequestBody Map<String,String> loginInfo, HttpServletRequest request,HttpServletResponse response){
        List<Map<String,Object>> result = userService.loginValid(loginInfo);
        Map<String,Object> info = new HashMap<>();

        if(!StringUtils.isEmpty(loginInfo.get("token"))){
            String token = loginInfo.get("token");
            Map<String, Object> userInfo = JwtUtils.validateToken(token);
            String id = String.valueOf(userInfo.get("id"));
            String checkToken = redisUtils.getToken(id);
            if (StringUtils.isEmpty(checkToken)){
                info.put("result","0");
            }else {
                info.put("result","1");
                Object name = userInfo.get("name");
                Object sno = userInfo.get("sno");
                info.put("sno",String.valueOf(sno));
                info.put("name",String.valueOf(name));
                info.put("id",id);
            }
            return info;

        }

        if(result.size()==1){
            Map<String,Object> user = result.get(0);
            String token = JwtUtils.generateToken((String) user.get("username"), user.get("id"), user.get("no"));
            redisUtils.addToken(token, String.valueOf(user.get("id")));
            info.put("result", "1");
            info.put("token", token);
            info.put("id",user.get("id"));
            info.put("username",user.get("username"));
            Map<String,Object> haha =  JwtUtils.validateToken(token);
            log.info( haha.get("sno").toString()+"Login in");
        }else{
            info.put("result", "0");
        }
        return info;
    }

    @PostMapping("/user/register")
    public Map<String,Object> Register(@RequestBody User user){
        Map<String,Object> info = new HashMap<>();
        Map<String, Object> result;
        result = userService.isRegister(Integer.parseInt(user.getNo()));
        if (result != null ){

            info.put("result","0");
            info.put("info","该学号已经被注册");
        }else {
            info.put("result","1");
            userService.toRegisterAUser(user);

        }
        return info;
    }
    @DeleteMapping("/user/out")
    public Map<String,Object> Out(@RequestBody User user,HttpServletRequest request){
        String token = request.getHeader("Authorization");
        log.info(token);
        Map<String,Object> info = new HashMap<>();
        Integer id = user.getId();
        Long aLong = redisUtils.delToken(String.valueOf(id));
        if (aLong>0){
            info.put("result","1");
        }else {
            info.put("result","0");
        }
        return info;


    }

    @PostMapping("/user/avatar")
    public Map<String,Object> UpdateAvatar(@RequestBody Map<String,Object> user ,HttpServletRequest  request){
        Map<String,Object> info = new HashMap<>();
        Integer id = (Integer) user.get("id");

        String avatar = (String) user.get("avatar");
        Integer res =  userService.updateAvatar(id,avatar);
        if (res!=0){
            info.put("result",1);
        }else {
            info.put("result",0);
        }
        return info;

    }
    @PostMapping("/user/getavatar")
    public Map<String,Object> getUserAvatar(@RequestBody Map<String,Object> info,HttpServletRequest request){
        Integer id ;
        String string = info.get("id").toString();
        id = Integer.parseInt(string);
        String userAvatar = userService.getUserAvatar(id);
        Map<String,Object> result = new HashMap<>();
        result.put("avatar",userAvatar);
        return result;

    }




}
