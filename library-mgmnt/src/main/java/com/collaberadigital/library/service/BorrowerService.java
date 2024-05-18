package com.collaberadigital.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collaberadigital.library.entity.Borrower;
import com.collaberadigital.library.repo.BorrowerRepository;

@Service
public class BorrowerService {

	@Autowired
	private BorrowerRepository borrowerRepo;

	public Borrower registerBorrower(Borrower borrower) {
		return borrowerRepo.save(borrower);
	}

}
