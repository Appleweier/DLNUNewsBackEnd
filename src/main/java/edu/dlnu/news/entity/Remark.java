package edu.dlnu.news.entity;

import java.io.Serializable;

public class Remark implements Serializable {
    public String remarkContent;
    public String articleId;
    public String userId;

    public Remark() {
    }

    public Remark(String articleId,String userId,String remarkContent  ) {
        this.remarkContent = remarkContent;
        this.articleId = articleId;
        this.userId = userId;
    }

    public String getRemarkContent() {
        return remarkContent;
    }

    public void setRemarkContent(String remarkContent) {
        this.remarkContent = remarkContent;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
