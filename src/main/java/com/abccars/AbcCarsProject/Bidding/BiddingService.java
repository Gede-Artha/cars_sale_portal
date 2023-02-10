package com.abccars.AbcCarsProject.Bidding;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abccars.AbcCarsProject.Cars.Product;


@Service
public class BiddingService {
	
	@Autowired 
	 BiddingRepository biddingRepo;
	
	public void dobidding(EntityCarBid entitycarbid) {
		
	biddingRepo.save(entitycarbid);
	}
	
	public List<EntityCarBid> getAllEntityCarBid()
	{
		return biddingRepo.findAll();
	}

	
	 public void Approve(Long id) {
		 EntityCarBid entitycarbid	=  biddingRepo.findById(id).get();
		 entitycarbid.setApprove("approve");
	     biddingRepo.save(entitycarbid);
	    }
	
	 public EntityCarBid get(Long id) {
			return biddingRepo.findById(id).get();
		}
		
	 public void save(EntityCarBid  entitycarbid) {
		 biddingRepo.save( entitycarbid);
	}
	
	  
	
	public void deleteEntityCarBidById(Long id)
    {
		biddingRepo.deleteById(id);
    }

}
