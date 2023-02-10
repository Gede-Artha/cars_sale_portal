package com.abccars.AbcCarsProject.Cars;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.abccars.AbcCarsProject.Bidding.BiddingService;
import com.abccars.AbcCarsProject.Bidding.EntityCarBid;
import com.abccars.AbcCarsProject.Cars.Product;
import com.abccars.AbcCarsProject.Cars.ProductService;
import com.abccars.AbcCarsProject.Users.User;

import com.abccars.AbcCarsProject.Cars.ProductRepositry;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired 
	private ProductRepositry productRepo;
	
	@Autowired
	private BiddingService bService;
	    
	
	 @GetMapping("/deleteBid/{id}")
	    public String deleteBidding(@PathVariable("id") Long id){
		 bService.deleteEntityCarBidById(id);
	    	return "redirect:/bidding-list";
	    }
	 
	 @GetMapping("/bidding/edit/{id}")
		public String  saveEditBidding(@PathVariable("id") Long id, Model model) {
		 EntityCarBid entitycarbid = bService.get(id);
			model.addAttribute("entitycarbid", entitycarbid);
		
			return "editbid_form";
		}
		
	 @PostMapping("/editbidding/save")
		public String savenewBid(EntityCarBid entitycarbid) {
		 bService.save(entitycarbid);
			
			return "redirect:/bidding-list";
		}	
	 
	@GetMapping("/Cars-list")
	public String showExampleView(Model model)
	{
		List<Product> products = productService.getAllProduct();
		List<EntityCarBid> entitycarbid = bService.getAllEntityCarBid();
		model.addAttribute("entitycarbid", entitycarbid);
		model.addAttribute("products", products);
		return "/Cars-list";
	}
	
	@GetMapping("/bidding-list")
	public String showBidding(Model model)
	{
		List<EntityCarBid> entitycarbid = bService.getAllEntityCarBid();
		model.addAttribute("entitycarbid", entitycarbid);
		return "/bidding-list";
	}
	
    @GetMapping("/cars")
    public String showAddProduct()
    {
    	
    	return "/addProduct.html";
    }
    
    @PostMapping(value = "addP")
    public String saveProduct(@RequestParam("file") MultipartFile file,
    		
    
    @RequestParam("pmake") String make,
	@RequestParam("price") String price,
	@RequestParam("model") String model,
	@RequestParam("years") String years)
    {
    	productService.saveProductToDB(file, make, model, price, years);
    	return "redirect:/Cars-list";
    }
    
  
   
    
    @RequestMapping(value = "/addProduct",method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView returnAddProduct()
	{
	  ModelAndView mv = new ModelAndView();
	  mv.setViewName("addProduct");
	  return mv;
	  
	}
	
	@RequestMapping(value = "/listProduct",method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView returnListProduct()
	{
	  ModelAndView mv = new ModelAndView();
	  List<Product> products = productRepo.findAll();
	  
	  mv.setViewName("listProducts");
	  mv.addObject("products", products);
	  return mv;
	  
	}
	
	
	 @GetMapping("/search")
	    public ModelAndView searchProduct(@RequestParam(value = "searchValue", required = false) String searchValue,
	    		HttpServletRequest request, HttpServletResponse response, ModelAndView model){
	        List<Product> searchResponse = productService.searchProduct(searchValue);
	        		
	    
	    	if (searchResponse  == null ||searchResponse .isEmpty()) {
	    		model.addObject("msgsearchResult", "Sorry we couldn't find any result for " + "'" + searchValue + "'");
	    	} else {
	    		model.addObject("msgsearchResult", "Result of your search for " + "'" + searchValue + "'");
	    		model.addObject("products", searchResponse );    		
	    	}
	    	model.setViewName("searchResult");
	        return model;
	    }
	 
	    @RequestMapping(value = "search-price")
	    public ModelAndView searchWithPrice(@RequestParam(value = "min", required = false) String min,
	            @RequestParam(value = "max", required = false) String max,
	            ModelAndView mav) {
	        List<Product> result = productService.searchByPrice(min, max);

	        if (result == null || result.isEmpty()) {
	            mav.addObject("msg_result", "Sorry we couldn't find any result");
	        } else {
	            mav.addObject("msg_result", "Result of your search");
	            mav.addObject("products", result);
	        }
	        mav.setViewName("searchResult");
	        return mav;
	    }


}
