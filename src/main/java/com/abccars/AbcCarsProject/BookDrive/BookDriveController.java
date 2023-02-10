package com.abccars.AbcCarsProject.BookDrive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.abccars.AbcCarsProject.Bidding.BiddingService;
import com.abccars.AbcCarsProject.Bidding.EntityCarBid;
import com.abccars.AbcCarsProject.BookDrive.BookDrive;





@Controller
public class BookDriveController {


	
	@Autowired
	BookDriveServiceImpl service;
	
	@Autowired
	BiddingService bService;
	
	
	@GetMapping("/bidding")
	public String BiddingForm(Model model) {
		model.addAttribute("entitycarbid", new EntityCarBid());
		
		return "bidding_form";
	}
	
	@PostMapping(value = "/biddingNow")
	public String saveBidding(@ModelAttribute("entitycarbid")EntityCarBid entitycarbid) {
		bService.dobidding(entitycarbid);
		return "redirect:/users";
	}
	
	@GetMapping("/book")
	public String BookForm(Model model) {
		model.addAttribute("bookdrive", new BookDrive());
		
		return "book_form";
	}
	
	@PostMapping(value = "/bookNow")
	public String saveThread(@ModelAttribute("bookdrive")BookDrive bookdrive) {
		service.dobook(bookdrive);
		return "redirect:/users";
	}
}
