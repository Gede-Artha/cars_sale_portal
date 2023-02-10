package com.abccars.AbcCarsProject.Bidding;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.abccars.AbcCarsProject.Cars.Product;
import com.abccars.AbcCarsProject.Users.User;


@Entity
public class EntityCarBid  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idBiding")
    private Long id;

    @Column(name = "comments")
    private String comments;
	
	@Column(name = "car_reg.no")
	private Integer car_regNum;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "approve")
	private String approve;

	public EntityCarBid() {
		
	}

	
	
	
	@Override
	public String toString() {
		return "EntityCarBid [id=" + id + ", comments=" + comments + ", car_regNum=" + car_regNum + ", username="
				+ username + ", approve=" + approve + "]";
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getCar_regNum() {
		return car_regNum;
	}

	public void setCar_regNum(Integer car_regNum) {
		this.car_regNum = car_regNum;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}




	public String getApprove() {
		return approve;
	}




	public void setApprove(String approve) {
		this.approve = approve;
	}
	

    
}
