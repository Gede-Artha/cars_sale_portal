package com.abccars.AbcCarsProject.Cars;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.abccars.AbcCarsProject.Users.User;


@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idCars")
	private Long id;
	
	@Column(name = "make")
	private String make;
	
	@Column(name = "years")
	private String years;
	
    @Column(name = "status")
    private String status;

    @Column(name = "sold")
	private String sold;
    
	@Column(name = "model")
	private String model;
	
	@Column(name = "price")
	private String price;
	
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String image;
	
	
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "user_id")
	private User user;

	
	 
	 
	 
	public String getSold() {
		return sold;
	}


	public void setSold(String sold) {
		this.sold = sold;
	}


	public Long getId() {
			return id;
		}


		public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


		public void setId(Long id) {
			this.id = id;
		}


		public String getMake() {
			return make;
		}


		public void setMake(String make) {
			this.make = make;
		}


		public String getYears() {
			return years;
		}


		public void setYears(String years) {
			this.years = years;
		}


		public String getModel() {
			return model;
		}


		public void setModel(String model) {
			this.model = model;
		}


		public String getPrice() {
			return price;
		}


		public void setPrice(String price) {
			this.price = price;
		}


		public String getImage() {
			return image;
		}


		public void setImage(String image) {
			this.image = image;
		}


		public User getUser() {
			return user;
		}


		public void setUser(User user) {
			this.user = user;
		}


		@Override
		public String toString() {
			return "Product [id=" + id + ", make=" + make + ", years=" + years + ", status=" + status + ", sold=" + sold
					+ ", model=" + model + ", price=" + price + ", image=" + image + ", user=" + user + "]";
		}


		


	


	

}
