package edu.dlnu.news.mapper.adminMapper;

import org.apache.ibatis.annotations.*;

import java.util.Map;

@Mapper
public interface AdminMapper {

    @Select("SELECT COUNT(1) FROM admin WHERE name = #{map.name} AND pwd = #{map.pwd} ")
    Integer loginValid(@Param("map") Map<String, String> map);

    @Insert("insert into  article (title, content,img,date,adminid,type) " +
            " values(#{map.title},#{map.content},#{map.img},NOW(),110,1)  ")
    Integer uploadArticle(@Param("map") Map<String, String> map);

    @Select("SELECT MAX(id) FROM article")
    Integer getNewArticleId();
    @Insert("INSERT INTO likeandhate (article_id) VALUES(#{id})")
    Integer insertLikeAndHate(int id);

    @Update("UPDATE article SET title =  #{map.title}   WHERE id = #{map.id} ")
    Integer updateArticle(@Param("map") Map<String, Object> map);

    @Delete("DELETE FROM article WHERE id = #{map.id} ")
    Integer deleteArticle(@Param("map") Map<String, Integer> map);

    @Select("SELECT img FROM article WHERE id = #{id1} ")
    String getImgByArticleId(String id1);
}
