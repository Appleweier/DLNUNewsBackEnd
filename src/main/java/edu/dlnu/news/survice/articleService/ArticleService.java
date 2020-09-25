package edu.dlnu.news.survice.articleService;

import edu.dlnu.news.entity.Remark;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    List<Map<String, Object>> getAllArticles();
    Map<String,Object> getArticleById(Integer id);
    List<Map<String, Object>> searchByTitle(String title);
    List<Map<String, Object>> proSearch(Map<String, Object> map);
    Map<String, Object> getLikeAndDislike(Integer id);
    Integer likesIncrement(Integer id);
    Integer hatesIncrement(Integer id);


    List<Map<String, Object>> getRemarks(Integer id);

    Integer incrementRemarkLikeNum(Integer id);

    Integer incrementRemarkDislikeNum(Integer id);

    Integer addARemark(Remark map);
}
