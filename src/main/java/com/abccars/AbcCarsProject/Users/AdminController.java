package com.abccars.AbcCarsProject.Users;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.abccars.AbcCarsProject.BookDrive.BookDrive;
import com.abccars.AbcCarsProject.BookDrive.BookDriveServiceImpl;
import com.abccars.AbcCarsProject.Cars.Product;
import com.abccars.AbcCarsProject.Cars.ProductService;

@Controller
public class AdminController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private BookDriveServiceImpl tdService;
	
	 @GetMapping("/adminsearch")
	    public ModelAndView searchProduct(@RequestParam(value = "searchValue", required = false) String searchValue,
	    		HttpServletRequest request, HttpServletResponse response, ModelAndView model){
	        List<Product> searchResponse = productService.searchProduct(searchValue);
	        		
	    
	    	if (searchResponse  == null ||searchResponse .isEmpty()) {
	    		model.addObject("msgsearchResult", "Sorry we couldn't find any result for " + "'" + searchValue + "'");
	    	} else {
	    		model.addObject("msgsearchResult", "Result of your search for " + "'" + searchValue + "'");
	    		model.addObject("products", searchResponse );    		
	    	}
	    	model.setViewName("adminsearchResult");
	        return model;
	    }
	 
	    @RequestMapping(value = "adminsearch-price")
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
	        mav.setViewName("adminsearchResult");
	        return mav;
	    }

	
	
	@GetMapping("/userrequest")
	public String showRequest(Model model)
	{
		List<BookDrive> bookkdrive = tdService.getAllBookDrive();
		model.addAttribute("bookkdrive", bookkdrive);
		return "/listrequest";
	}
	
	 @GetMapping("/deleteRequest/{id}")
	    public String deleteRequest(@PathVariable("id") Long id)
	    {
	    	
		 tdService.deleteBookDriveById(id);
	    	return "redirect:/admin";
	    }
	
	 
	  
	  @GetMapping("/deleteProd/{id}")
	    public String deleteProduct(@PathVariable("id") Long id){
	    	productService.deleteProductById(id);
	    	return "redirect:/list-cars";
	    }
	  
	  @GetMapping("/deleteUser/{id}")
	    public String deleteUser(@PathVariable("id") Long id)
	    {
	    	
	    	service.deleteUserById(id);
	    	return "redirect:/admin";
	    }
	  

	
	@GetMapping("/list-cars")
	public String showExampleView(Model model)
	{
		List<Product> products = productService.getAllProduct();
		model.addAttribute("products", products);
		return "/listProducts";
	}

	@GetMapping("/admin")
	public String listUsers(Model model) {
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);
		
		return "admin";
	}
	
	@GetMapping("/admin/edit/{id}")
	public String editUserdata(@PathVariable("id") Long id, Model model) {
		User user = service.get(id);
		List<Role> listRoles = service.listRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		return "user_form";
	}
	
	@GetMapping("/cars/edit/{id}")
	public String  saveEditCars(@PathVariable("id") Long id, Model model) {
		Product products = productService.get(id);
		model.addAttribute("products", products);
	
		return "cars_form";
	}
	
	
	
	
	@PostMapping("/editcars/save")
	public String saveCars(Product product) {
		 productService.save(product);
		
		return "redirect:/list-cars";
	}	
	
	
//	@PostMapping("/editcars/save")
//	public String saveCars(@RequestParam("file") MultipartFile file) {
//		 productService.saveEdit(file);
//		return "redirect:/list-cars";
//	}	
	
	@PostMapping("/admin/save")
	public String saveUser(User user) {
		service.saveUser(user);
		
		return "redirect:/admin";
	}	
	
	@GetMapping("/homepage")
	public String viewHomePage() {
		return "homepage";
	}
	
	@GetMapping("/aboutUs")
	public String Aboutus() {
		return "aboutus";
	}
	
	@GetMapping("/contactUs")
	public String CotactUs() {
		return "contactus";
	}

}
