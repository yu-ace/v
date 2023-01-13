package com.example.votingsystem.vo;

import java.util.Date;
import java.util.List;

public class VoVoting {
    String id;
    String name;
    String description;
    Date createTime;
    Date startTime;
    Date endTime;
    Integer count;
    Integer userId;
    Integer status;
    List<VoVotingContent> contentList;

//    sdfsd contentList;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<VoVotingContent> getContentList() {
        return contentList;
    }

    public void setContentList(List<VoVotingContent> contentList) {
        this.contentList = contentList;
    }

//
//    public sdfsd getContentList() {
//        return contentList;
//    }
//
//    public void setContentList(sdfsd contentList) {
//        this.contentList = contentList;
//    }
}
