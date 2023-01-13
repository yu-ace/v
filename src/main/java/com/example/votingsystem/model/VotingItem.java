package com.example.votingsystem.model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "voting_item")
@DynamicUpdate
public class VotingItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "voting_content_id")
    Integer votingContentId;
    @Column(name = "user_id")
    Integer userId;
    @Column(name = "voting_slip_id")
    Integer votingSlipId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVotingContentId() {
        return votingContentId;
    }

    public void setVotingContentId(Integer votingContentId) {
        this.votingContentId = votingContentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVotingSlipId() {
        return votingSlipId;
    }

    public void setVotingSlipId(Integer votingSlipId) {
        this.votingSlipId = votingSlipId;
    }
}
