package com.lemeng.lecloud.model.chat;

import java.io.Serializable;
import java.util.Date;

public class UserLinkmanRelation implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5372848430090870544L;

	private Long id;

    private Long userId;

    private Long linkmanId;

    private String remarkName;

    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLinkmanId() {
        return linkmanId;
    }

    public void setLinkmanId(Long linkmanId) {
        this.linkmanId = linkmanId;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName == null ? null : remarkName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}