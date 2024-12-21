package book.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import book.store.entity.BookStore;
import book.store.entity.Customer;
import book.store.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 

public class BookStoreData {

	 private Long bookStoreId; 
	 private String bookStoreName; 
	 private String bookStoreAddress; 
	 private String bookStoreCity; 
	 private String bookStoreState; 
	 private String bookStoreZip; 
	 private String bookStorePhone; 
	 private Set<BookStoreCustomer> customers = new HashSet<>(); 
	 private Set<BookStoreEmployee> employees = new HashSet<>(); 

	 public BookStoreData(BookStore bookStore) { 

	 	bookStoreId = bookStore.getBookStoreId(); 
	 	bookStoreName = bookStore.getBookStoreName(); 
	 	bookStoreAddress = bookStore.getBookStoreAddress(); 
	 	bookStoreCity = bookStore.getBookStoreCity(); 
	 	bookStoreState = bookStore.getBookStoreState(); 
	 	bookStoreZip = bookStore.getBookStoreZip(); 
	 	bookStorePhone = bookStore.getBookStorePhone(); 

	 	for(Customer customer : bookStore.getCustomers()) { 
	 	 customers.add(new BookStoreCustomer(customer));	 

	 	} 

	 	 

	 	for(Employee employee : bookStore.getEmployees()) { 
	 		employees.add(new BookStoreEmployee(employee)); 

	 	} 

	 } 

@Data 

@NoArgsConstructor 

public static class BookStoreCustomer { 

	private Long customerId; 
	private String customerFirstName; 
	private String customerLastName; 
	private String customerEmail;	 
	
public BookStoreCustomer(Customer customer) {  

	customerId = customer.getCustomerId(); 
	customerFirstName = customer.getCustomerFirstName(); 
	customerLastName = customer.getCustomerLastName(); 
	customerEmail = customer.getCustomerEmail(); 

} 

} 



@Data 

@NoArgsConstructor 

public static class BookStoreEmployee { 

	private Long employeeId; 
private String employeeFirstName; 
private String employeeLastName; 
private String employeePhone; 
private String employeeJobTitle; 
public BookStoreEmployee(Employee employee) { 

	employeeId = employee.getEmployeeId(); 
	employeeFirstName = employee.getEmployeeFirstName(); 
	employeeLastName = employee.getEmployeeLastName(); 
	employeePhone = employee.getEmployeePhone(); 
	employeeJobTitle = employee.getEmployeeJobTitle(); 

} 

} 
		
}
