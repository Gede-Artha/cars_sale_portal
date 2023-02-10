package com.abccars.AbcCarsProject.BookDrive;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.abccars.AbcCarsProject.Users.User;

@Entity
public class BookDrive {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idBook")
	private Long id;
	
	@Column(name = "time")
	private Time time;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "car_reg.no")
	private Integer car_regNum;
	
	@Column(name = "username")
	private String username;
	
	@OneToOne(cascade = CascadeType.ALL )
	private User user;
	
	

	public BookDrive() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "BookDrive [id=" + id + ", time=" + time + ", date=" + date + ", car_regNum=" + car_regNum
				+ ", username=" + username + ", user=" + user + "]";
	}
	
	
	
}
