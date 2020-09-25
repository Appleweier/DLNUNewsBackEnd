package edu.dlnu.news.survice.articleService.articleServiceImpl;

import edu.dlnu.news.entity.Remark;
import edu.dlnu.news.mapper.articleMapper.ArticleMapper;
import edu.dlnu.news.survice.articleService.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl  implements ArticleService {

    @Resource
    private ArticleMapper am;
    @Override
    public List<Map<String, Object>> getAllArticles() {
        return am.getAllArticle();
    }

    @Override
    public Map<String, Object> getArticleById(Integer id) {
        return am.getArticleById(id);
    }

    @Override
    public List<Map<String, Object>> searchByTitle(String title) {
        return am.searchByTitle(title);
    }

    @Override
    public List<Map<String, Object>> proSearch(Map<String, Object> map) {
        return am.getProfessionResult(map);
    }

    @Override
    public Map<String, Object> getLikeAndDislike(Integer id) {
        return am.getLikeAndDislike(id);
    }

    @Override
    public Integer likesIncrement(Integer id) {
        return am.likesIncrement(id);
    }

    @Override
    public Integer hatesIncrement(Integer id) {
        return am.hatesIncrement(id);
    }

    @Override
    public List<Map<String, Object>> getRemarks(Integer id) {
        return am.getRemarks(id);
    }

    @Override
    public Integer incrementRemarkLikeNum(Integer id) {
        return am.incrementRemarkLikeNum(id);
    }

    @Override
    public Integer incrementRemarkDislikeNum(Integer id) {
        return am.incrementRemarkDislikeNum(id);
    }

    @Override
    public Integer addARemark(Remark map) {
        return am.addARemark(map);
    }
}
