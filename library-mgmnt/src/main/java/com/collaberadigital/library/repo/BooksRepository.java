package com.collaberadigital.library.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.collaberadigital.library.entity.Book;

public interface BooksRepository extends JpaRepository<Book, Long>{
	
	public List<Book>  findByIsbn(String isbn);

}
