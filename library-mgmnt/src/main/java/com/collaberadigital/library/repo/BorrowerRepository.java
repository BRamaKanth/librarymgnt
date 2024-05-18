package com.collaberadigital.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.collaberadigital.library.entity.Borrower;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {

}
