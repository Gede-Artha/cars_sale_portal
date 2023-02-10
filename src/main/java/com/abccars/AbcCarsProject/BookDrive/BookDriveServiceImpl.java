package com.abccars.AbcCarsProject.BookDrive;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abccars.AbcCarsProject.BookDrive.BookDriveRepository;
import com.abccars.AbcCarsProject.BookDrive.BookDrive;


@Service
public class BookDriveServiceImpl  {

	@Autowired 
	BookDriveRepository bdRepo;
	
	public void dobook(BookDrive bookdrive) {
		
		bdRepo.save(bookdrive);
	}
	
	public List<BookDrive> getAllBookDrive()
	{
		return bdRepo.findAll();
	}

	
	public void deleteBookDriveById(Long id)
    {
		bdRepo.deleteById(id);
    }
}
