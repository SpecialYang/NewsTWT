package com.special.newsdemo.model;

import java.util.List;

/**
 * Created by Special on 2017/10/31.
 */

public class NewContent {
    String content;
    String gonggao;
    int index;
    String newscome;
    String shengao;
    String sheying;
    String subject;
    int visitcount;
    List<String> comments;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGonggao() {
        return gonggao;
    }

    public void setGonggao(String gonggao) {
        this.gonggao = gonggao;
    }

    public String getNewscome() {
        return newscome;
    }

    public void setNewscome(String newscome) {
        this.newscome = newscome;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getShengao() {
        return shengao;
    }

    public void setShengao(String shengao) {
        this.shengao = shengao;
    }

    public int getVisitcount() {
        return visitcount;
    }

    public void setVisitcount(int visitcount) {
        this.visitcount = visitcount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSheying() {
        return sheying;
    }

    public void setSheying(String sheying) {
        this.sheying = sheying;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
