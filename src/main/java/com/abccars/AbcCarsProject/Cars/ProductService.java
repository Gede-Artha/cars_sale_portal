package com.abccars.AbcCarsProject.Cars;


import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService  {
	@Autowired
	private ProductRepositry productRepo;
	
	public void  saveProductToDB(MultipartFile file,String make, String price, String model
			,String years)
	{
		Product p = new Product();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		try {
			p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.setMake(make);
        p.setModel(model);
        p.setYears(years);
        p.setPrice(price);
        p.setStatus("active");
        
        productRepo.save(p);
	}
	
	public void save(Product product) {
		 productRepo.save( product);
		 
	}
	

	  
	    public void deleteProductById(Long id)
	    {
	    	productRepo.deleteById(id);
	    }
	
	public Product get(Long id) {
		return productRepo.findById(id).get();
	}
	
	public List<Product> getAllProduct()
	{
		return productRepo.findAll();
	}
   
    
	
	public List<Product> searchProduct(String inp) {
		List<Product> product =  productRepo.SearchProductByInput(inp);
		return product;
	}
	
	 public List<Product> searchByPrice(String min, String max){
	        return productRepo.findByPriceBetween(min, max);
	    }
}

