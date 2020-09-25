package edu.dlnu.news.survice.adminService;

import java.util.Map;

public interface AdminService {
    Integer loginValid(Map<String, String> map);

    Integer uploadArticle(Map<String, String> map);

    Integer updateArticle(Map<String, Object> map);

    Integer deleteArticle(Map<String, Integer> map);

    String getImgByArticleId(String id1);
}
