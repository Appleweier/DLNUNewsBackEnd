package edu.dlnu.news.mapper.articleMapper;

import edu.dlnu.news.entity.Remark;
import edu.dlnu.news.mapper.articleMapper.provider.ArticleProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {
    @Select("select id,title,content, img,date,adminid  from article ORDER BY date DESC")
    List<Map<String, Object>> getAllArticle();

    @Select("select id,title,content, img,date,adminid  from article where id = #{id}")
    Map<String, Object> getArticleById(Integer id);

    @Select("select id ,title,img,DATE ,adminid, type from article where title like #{title} ")
    List<Map<String, Object>> searchByTitle(String title);

    @SelectProvider(type = ArticleProvider.class,method = "resultString")
    List<Map<String, Object>> getProfessionResult(@Param("condition") Map<String,Object> map);

    @Select("select likes,hates from likeandhate l where l.article_id = #{id}")
    Map<String, Object> getLikeAndDislike(Integer id);

    @Update("UPDATE likeandhate  SET likes = likes + 1 WHERE  likeandhate.article_id = #{id} ")
    Integer likesIncrement(Integer id);

    @Update("UPDATE likeandhate  SET hates = hates + 1 WHERE  likeandhate.article_id = #{id}")
    Integer hatesIncrement(Integer id);

    @Select("SELECT s.id, user.avatar, username,article_id,user_id,remark_time,remark_content,like_num,dislike_num FROM " +
            "(SELECT * FROM remark WHERE article_id = #{id} ORDER BY remark_time " +
            ") s  INNER JOIN USER WHERE s.user_id = user.`id`")
    List<Map<String, Object>> getRemarks(Integer id);

    @Update("UPDATE remark SET like_num = like_num + 1 WHERE id = #{id} ")
    Integer incrementRemarkLikeNum(Integer id);

    @Update("UPDATE remark SET dislike_num = dislike_num + 1 WHERE id = #{id} ")
    Integer incrementRemarkDislikeNum(Integer id);

    @Insert("INSERT INTO remark (article_id,user_id,remark_time,remark_content)\n" +
            "VALUES(#{map.articleId},#{map.userId},NOW(),#{map.remarkContent})")
    Integer addARemark(@Param("map") Remark map);
}
