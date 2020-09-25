package edu.dlnu.news.survice.adminService.adminServiceImpl;

import edu.dlnu.news.mapper.adminMapper.AdminMapper;
import edu.dlnu.news.survice.adminService.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    @Override
    public Integer loginValid(Map<String, String> map) {
        return adminMapper.loginValid(map);
    }

    @Override
    public Integer uploadArticle(Map<String, String> map) {

         adminMapper.uploadArticle(map);
         int id = adminMapper.getNewArticleId();
         return adminMapper.insertLikeAndHate(id);
    }

    @Override
    public Integer updateArticle(Map<String, Object> map) {
        return adminMapper.updateArticle(map);
    }

    @Override
    public Integer deleteArticle(Map<String, Integer> map) {
        return adminMapper.deleteArticle(map);
    }

    @Override
    public String getImgByArticleId(String id1) {
        return adminMapper.getImgByArticleId(id1);
    }
}
