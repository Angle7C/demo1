package com.community.model;

public class Comment {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.id
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.parent_id
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    private Long parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.type
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.commentator
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    private Integer commentator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.gmt_create
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    private Long gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.gmt_modified
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    private Long gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.like_count
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    private Long likeCount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.id
     *
     * @return the value of comment.id
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.id
     *
     * @param id the value for comment.id
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.parent_id
     *
     * @return the value of comment.parent_id
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.parent_id
     *
     * @param parentId the value for comment.parent_id
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.type
     *
     * @return the value of comment.type
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.type
     *
     * @param type the value for comment.type
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.commentator
     *
     * @return the value of comment.commentator
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    public Integer getCommentator() {
        return commentator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.commentator
     *
     * @param commentator the value for comment.commentator
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    public void setCommentator(Integer commentator) {
        this.commentator = commentator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.gmt_create
     *
     * @return the value of comment.gmt_create
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.gmt_create
     *
     * @param gmtCreate the value for comment.gmt_create
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.gmt_modified
     *
     * @return the value of comment.gmt_modified
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.gmt_modified
     *
     * @param gmtModified the value for comment.gmt_modified
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.like_count
     *
     * @return the value of comment.like_count
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    public Long getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.like_count
     *
     * @param likeCount the value for comment.like_count
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }
}