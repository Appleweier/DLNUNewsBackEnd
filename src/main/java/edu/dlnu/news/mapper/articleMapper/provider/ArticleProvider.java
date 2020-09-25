package edu.dlnu.news.mapper.articleMapper.provider;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class ArticleProvider {
    public String resultString(Map<String,Object> params){
        Map<String,Object> condition = (Map<String,Object>)params.get("condition");
        SQL querySql = new SQL();
        querySql.SELECT("id,title,content, img,date,adminid ").FROM("article").WHERE("type = #{condition.type}");
        String et = String.valueOf(condition.get("et"));
        String st = String.valueOf(condition.get("st"));
        if (!StringUtils.isEmpty(et) && !StringUtils.isEmpty(st)){
            querySql.WHERE(" date > #{condition.st} and date < #{condition.et}");
        }
        String title = String.valueOf(condition.get("title"));
        if (!StringUtils.isEmpty(title)){
            title = "%"+title+"%";
            condition.put("title",title);
            querySql.WHERE("title like #{condition.title}");
        }

        String content = String.valueOf(condition.get("content"));
        if (!StringUtils.isEmpty(content)){
            content = "%"+content+"%";
            condition.put("content",content);
            querySql.WHERE("content like #{condition.content}");
        }
        return querySql.toString();


    }
}
