package edu.dlnu.news.controller.adminController;

import edu.dlnu.news.survice.adminService.AdminService;
import edu.dlnu.news.survice.articleService.ArticleService;
import edu.dlnu.news.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private AdminService adminService;


    @PostMapping("/rotation")
    public Integer postRotation(@RequestBody Map<String,String> map){
        redisUtils.set("id1",map.get("id1"));
        redisUtils.set("id2",map.get("id2"));
        redisUtils.set("id3",map.get("id3"));
        return 1;
    }

    @PostMapping("/getRotation")
    public Map<String,Object> getRotation(){
        Map<String,Object> map = new HashMap<>(3);
        String id1 = redisUtils.get("id1");
        String id2 = redisUtils.get("id2");
        String id3 = redisUtils.get("id3");
        map.put("id1", Integer.valueOf(id1));
        map.put("id2", Integer.valueOf(id2));
        map.put("id3", Integer.valueOf(id3));
        String img1 =  adminService.getImgByArticleId(id1);
        String img2 =  adminService.getImgByArticleId(id2);
        String img3 =  adminService.getImgByArticleId(id3);
        map.put("img1",img1);
        map.put("img2",img2);
        map.put("img3",img3);
        return map;
    }




    @PostMapping("/deleteArticle")
    public Integer deleteArticle(@RequestBody Map<String,Integer> map){
        return adminService.deleteArticle(map);
    }

    @PostMapping("/updateArticle")
    public Integer updateArticle(@RequestBody Map<String,Object> map){
        return adminService.updateArticle(map);
    }

    @PostMapping("uploadArticle")
    @Transactional
    public Integer uploadArticle(@RequestBody Map<String,String> map){
        return adminService.uploadArticle(map);
    }

    @PostMapping("admin/login")
    public Integer adminLogin(@RequestBody Map<String,String> map){
        String name = map.get("name");
        String pwd = map.get("pwd");
        Integer i =  adminService.loginValid(map);
        int result;
        if (i==1){
            result = 1;
        }else {
            result = 0;
        }
        return result;

    }

}
