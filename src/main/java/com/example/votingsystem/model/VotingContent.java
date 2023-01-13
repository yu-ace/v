package com.example.votingsystem.model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "voting_content")
@DynamicUpdate
public class VotingContent {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "content")
    String content;
    @Column(name = "count")
    Integer count;
    @Column(name = "voting_id")
    Integer votingId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getVotingId() {
        return votingId;
    }

    public void setVotingId(Integer votingId) {
        this.votingId = votingId;
    }
}
