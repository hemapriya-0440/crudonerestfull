package com.example.crudEx1.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Posts {
	@Id
	@GeneratedValue
private Integer postId;
private String message;

@ManyToOne(fetch=FetchType.LAZY)
@JsonIgnore
private user usr;

public Integer getPostId() {
	return postId;
}
public void setPostId(Integer postId) {
	this.postId = postId;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public user getUsr() {
	return usr;
}
public void setUsr(user usr) {
	this.usr = usr;
}
@Override
public String toString() {
	return "Posts [postId=" + postId + ", message=" + message + "]";
}

}