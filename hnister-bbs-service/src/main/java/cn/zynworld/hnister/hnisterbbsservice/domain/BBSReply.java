package cn.zynworld.hnister.hnisterbbsservice.domain;

import java.util.Date;

public class BBSReply {
    private Long id;

    private Integer status;

    private Long tierId;

    private String username;

    private String replyedUsername;

    private Integer type;

    private Date postTime;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getTierId() {
        return tierId;
    }

    public void setTierId(Long tierId) {
        this.tierId = tierId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getReplyedUsername() {
        return replyedUsername;
    }

    public void setReplyedUsername(String replyedUsername) {
        this.replyedUsername = replyedUsername == null ? null : replyedUsername.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}