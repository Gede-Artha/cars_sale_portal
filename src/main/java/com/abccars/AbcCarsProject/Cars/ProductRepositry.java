package com.abccars.AbcCarsProject.Cars;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.abccars.AbcCarsProject.Cars.Product;



@Repository
public interface ProductRepositry extends JpaRepository<Product, Long> {
	@Query(value = "Select * from product where :input IN(model, make, price, years)",
			nativeQuery = true)
	
	List<Product> SearchProductByInput(@Param("input") String inp);
	
	public List<Product> findByPriceBetween(String startPrice, String endPrice);

}
