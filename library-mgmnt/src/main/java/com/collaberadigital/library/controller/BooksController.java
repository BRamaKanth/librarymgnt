package com.collaberadigital.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collaberadigital.library.entity.Book;
import com.collaberadigital.library.entity.Borrower;
import com.collaberadigital.library.service.BooksService;
import com.collaberadigital.library.service.BorrowerService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/library/v1/")
public class BooksController {

	@Autowired
	private BooksService bookService;

	@Autowired
	private BorrowerService borrowerService;

	/**
	 * Fetch all the Books Data
	 * @return
	 */
	@GetMapping("/bookslist")
	public ResponseEntity<List<Book>> getAllBooksDetails() {
		List<Book> booksList = bookService.getAllBooksData();
		return ResponseEntity.ok(booksList);
	}

	/**
	 * Register the Book in the library
	 * @param book
	 * @return
	 */
	@PostMapping("/registerBook")
	public BodyBuilder registerBook(@RequestBody Book book) {
		bookService.registerBook(book);
		return ResponseEntity.status(HttpStatus.CREATED);

	}

	/**
	 * Register the Borrower Data in the library
	 * @param borrower
	 * @return
	 */
	@PostMapping("/registerBorrower")
	public BodyBuilder registerBook(@RequestBody Borrower borrower) {
		borrowerService.registerBorrower(borrower);
		return ResponseEntity.status(HttpStatus.CREATED);

	}

	/**
	 * Borrow the book by providing the book isbn and borrower id
	 * @param bookid
	 * @param borrowerID
	 * @return
	 */
	@GetMapping("/borrowBook/{bookIsbn}/{borrowerID}")
	public ResponseEntity<Book> borrowBook(@PathParam("bookIsbn") String bookid,
			@PathParam("borrowerID") Long borrowerID) {

		Book book = bookService.borrowBook(bookid, borrowerID);
		return ResponseEntity.ok(book);
	}

	/**
	 * return the book by providing the book isbn and borrwer id 
	 * @param bookid
	 * @param borrowerID
	 * @return
	 */
	@GetMapping("/returnBook/{bookIsbn}/{borrowerID}")
	public ResponseEntity<Book> returnBook(@PathParam("bookIsbn") String bookid,
			@PathParam("borrowerID") Long borrowerID) {

		Book book = bookService.returnBook(bookid, borrowerID);
		return ResponseEntity.ok(book);
	}

}
