package book.store.controller; 

import java.util.List; 

import java.util.Map; 

 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.http.HttpStatus; 

import org.springframework.web.bind.annotation.DeleteMapping; 

import org.springframework.web.bind.annotation.GetMapping; 

import org.springframework.web.bind.annotation.PathVariable; 

import org.springframework.web.bind.annotation.PostMapping; 

import org.springframework.web.bind.annotation.PutMapping; 

import org.springframework.web.bind.annotation.RequestBody; 

import org.springframework.web.bind.annotation.RequestMapping; 

import org.springframework.web.bind.annotation.ResponseStatus; 

import org.springframework.web.bind.annotation.RestController; 

 

import lombok.extern.slf4j.Slf4j; 

import book.store.controller.model.BookStoreData; 

import book.store.controller.model.BookStoreData.BookStoreCustomer; 

import book.store.controller.model.BookStoreData.BookStoreEmployee; 

import book.store.service.BookStoreService; 

 

@RestController 

@RequestMapping("/book_store") 

@Slf4j 

public class BookStoreController { 

 

	@Autowired 

private BookStoreService bookStoreService; 

 

@PostMapping 

@ResponseStatus(code = HttpStatus.CREATED) 

public BookStoreData insertBookStore(@RequestBody BookStoreData bookStoreData) { 

log.info("Creating book store: {}", bookStoreData); 

return bookStoreService.saveBookStore(bookStoreData); 

} 

@PutMapping("/{bookStoreId}") 

public BookStoreData insertBookStore(@PathVariable Long bookStoreId,  

		@RequestBody BookStoreData bookStoreData) { 

bookStoreData.setBookStoreId(bookStoreId); 

log.info("Updating book store {}", bookStoreData); 

 

return bookStoreService.saveBookStore(bookStoreData); 

} 

@PostMapping("/{bookStoreId}/employee") 

@ResponseStatus(code = HttpStatus.CREATED) 

public BookStoreEmployee addEmployeeToBookStore( 

@PathVariable Long bookStoreId, 

@RequestBody BookStoreEmployee bookStoreEmployee) { 

log.info("Adding employee {} to book store with ID={}", bookStoreEmployee, bookStoreId); 

 

return bookStoreService.saveEmployee(bookStoreId, bookStoreEmployee); 

} 

@PostMapping("/{bookStoreId}/customer")  

@ResponseStatus(code = HttpStatus.CREATED)  

public BookStoreCustomer addCustomerToBookStore(  

@PathVariable Long bookStoreId,  

	@RequestBody BookStoreCustomer bookStoreCustomer) {  

log.info("Adding customer {} to book store with ID={}", bookStoreCustomer, bookStoreId);  

 

return bookStoreService.saveCustomer(bookStoreId, bookStoreCustomer);  

} 

@GetMapping 

public List<BookStoreData> retrieveAllBookStores() { 

	 log.info("Retrieving all book stores"); 

	 return bookStoreService.retrieveAllBookStores(); 

} 

@GetMapping("/{bookStoreId}") 

public BookStoreData retrieveBookStoreById(@PathVariable Long bookStoreId) { 

	  

	log.info("Retrieving book store with ID={}", bookStoreId); 

	 return bookStoreService.retrieveBookStoreById(bookStoreId); 

	  

} 

@DeleteMapping("/{bookStoreId}")  

public Map<String, String> deleteBookStoreById(@PathVariable Long bookStoreId) { 

log.info("Deleting book store with ID={}", bookStoreId); 

bookStoreService.deleteBookStoreById(bookStoreId); 

return Map.of("message", "Book store with ID=" + bookStoreId + "deleted."); 

} 

} 
