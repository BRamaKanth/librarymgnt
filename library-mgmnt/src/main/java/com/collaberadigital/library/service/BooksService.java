package com.collaberadigital.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collaberadigital.library.entity.Book;
import com.collaberadigital.library.entity.Borrower;
import com.collaberadigital.library.repo.BooksRepository;
import com.collaberadigital.library.repo.BorrowerRepository;

import jakarta.transaction.Transactional;

/**
 * This class provides services to return and borrow the Books
 */
@Service
public class BooksService {

	@Autowired
	private BooksRepository booksRepo;

	@Autowired
	private BorrowerRepository borrwRepo;

	public List<Book> getAllBooksData() {
		return booksRepo.findAll();
	}

	public Book registerBook(Book book) {
		return booksRepo.save(book);
	}

	/**
	 * call this method to borrow the book
	 * 
	 * @param isbn
	 * @param borrwerId
	 * @return
	 */
	@Transactional
	public Book borrowBook(String isbn, Long borrwerId) {
		List<Book> bookList = booksRepo.findByIsbn(isbn);
		Book getBook = null;
		if (!bookList.isEmpty()) {

			for (Book bk : bookList) {
				if (bk.getBookAvailable() == false) {
					Borrower borrower = borrwRepo.findById(borrwerId).get();
					bk.setBookAvailable(true);
					bk.setBorrower(borrower);
					getBook = booksRepo.save(bk);
					break;
				}
				if (getBook == null) {
					throw new IllegalArgumentException("No Book is avilable");
				}
			}
			return getBook;
		}

		throw new IllegalArgumentException("Book Not found");
	}

	/**
	 * call this method for returning the book
	 * 
	 * @param isbn
	 * @param borrwerId
	 * @return
	 */
	@Transactional
	public Book returnBook(String isbn, Long borrwerId) {
		List<Book> bookList = booksRepo.findByIsbn(isbn);
		Book getBook = null;
		if (!bookList.isEmpty()) {

			for (Book bk : bookList) {
				if (bk.getBookAvailable() == true) {
					Borrower borrower = borrwRepo.findById(borrwerId).get();
					if (borrower.getId().equals(borrwerId)) {
						bk.setBookAvailable(false);
						bk.setBorrower(null);
						getBook = booksRepo.save(bk);
						break;
					}

				}
			}
			return getBook;
		}

		throw new IllegalArgumentException("Book Not found");

	}
}
