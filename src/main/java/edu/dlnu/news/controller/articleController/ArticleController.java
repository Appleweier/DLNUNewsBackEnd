package edu.dlnu.news.controller.articleController;

import edu.dlnu.news.entity.Remark;
import edu.dlnu.news.survice.articleService.ArticleService;
import edu.dlnu.news.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class ArticleController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ArticleService articleService;
    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("/addARemark")
    public Integer addARemark(@RequestBody Remark map){
        return articleService.addARemark(map);
    }

    @PostMapping("/article/remark/like")
    public Integer incrementRemarkLikeNum(@RequestBody Map<String,Integer> map){
        Integer id = map.get("id");
        return articleService.incrementRemarkLikeNum(id);
    }

    @PostMapping("/article/remark/dislike")
    public Integer incrementRemarkDislikeNum(@RequestBody Map<String,Integer> map){
        return articleService.incrementRemarkDislikeNum(map.get("id"));
    }


    @PostMapping("/article/remark")
    public List<Map<String, Object>>   getRemarks(@RequestBody Map<String,Object> map){
        return articleService.getRemarks(Integer.parseInt(String.valueOf(map.get("id"))));
    }


    @GetMapping("/likeHate/{id}")
    public Map<String,Object> getLikeHate(@PathVariable("id") Integer id){
        return articleService.getLikeAndDislike(id);
    }

    @PostMapping("/article/hate")
    public Integer incrementHate(@RequestBody Map<String,Object> map){
        Object id = map.get("id");
        int i = Integer.parseInt(String.valueOf(id));
        return articleService.hatesIncrement(i);
    }

    @PostMapping("/article/like")
    public Integer incrementLike(@RequestBody Map<String,Object> map){
        Object id = map.get("id");
        int i = Integer.parseInt(String.valueOf(id));
        return articleService.likesIncrement(i);
    }


    @PostMapping("/professionalSearch")
    public List<Map<String, Object>> getSearchResult(@RequestBody Map<String,Object> map,HttpServletRequest request){
//        Object st = map.get("st");
//        Object et = map.get("et");
//        Object type = map.get("type");
//        Object title = map.get("title");
//        Object content = map.get("content");
//        boolean empty = StringUtils.isEmpty(String.valueOf(st));
        return articleService.proSearch(map);
//        List<Map<String, Object>>
    }


    @PostMapping("/searchByTitle")
    public List<Map<String, Object>> getArticleByTitle(@RequestBody Map<String,Object> map, HttpServletRequest request){
        String title = String.valueOf(map.get("title"));
        title = "%"+title+"%";
        return articleService.searchByTitle(title);
    }

    @GetMapping("/getarticle")
    public List<Map<String, Object>> getAllArticles(HttpServletRequest request) throws Exception {
//        String token = request.getHeader("Authorization");
//        log.info(String.valueOf("sdsdsd"+token==null));
//        Map<String, Object> userInfo = JwtUtils.validateToken(token);
//        Object ids = userInfo.get("id");
//        int id = Integer.parseInt(String.valueOf(ids));
//        String token1 = redisUtils.getToken(String.valueOf(id));
//        if (!token.equals(token1)){
//            throw new Exception("fff");
//        }else {
        List<Map<String, Object>> allArticles = articleService.getAllArticles();
        return allArticles;
//        }

    }

    @PostMapping("/getArticleById")
    public Map<String,Object> getArticleById(@RequestBody Map<String,Object> info){
        Object idO = info.get("id");
        int i = Integer.parseInt(String.valueOf(idO));
        return articleService.getArticleById(i);
    }
}
