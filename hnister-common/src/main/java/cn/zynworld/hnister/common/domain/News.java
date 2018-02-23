package cn.zynworld.hnister.common.domain;

import java.util.Date;

public class News {
    private Long id;

    private String title;

    private String author;

    private Date postDate;

    private Integer moduleId;

    private String content;


    public Long getId() {
        return id;
    }

    public News setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public News setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public News setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Date getPostDate() {
        return postDate;
    }

    public News setPostDate(Date postDate) {
        this.postDate = postDate;
        return this;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public News setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public News setContent(String content) {
        this.content = content;
        return this;
    }

    public static void main(String[] args) {
        News news = new News();

        news.setTitle("dizl");
        news.setModuleId(1);
        news.setId(1L);
        news.setPostDate(new Date());

        System.out.println(news.getTitle());
    }
}