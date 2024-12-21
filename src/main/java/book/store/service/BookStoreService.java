package book.store.service; 

import java.util.LinkedList; 

import java.util.List; 

import java.util.NoSuchElementException; 

import java.util.Objects; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.stereotype.Service; 

import org.springframework.transaction.annotation.Transactional; 

import book.store.controller.model.BookStoreData; 

import book.store.controller.model.BookStoreData.BookStoreCustomer; 

import book.store.controller.model.BookStoreData.BookStoreEmployee; 

import book.store.dao.CustomerDao; 

import book.store.dao.EmployeeDao;

import book.store.dao.BookStoreDao; 

import book.store.entity.Customer; 

import book.store.entity.Employee; 

import book.store.entity.BookStore; 

@Service 

public class BookStoreService { 

	@Autowired 

private BookStoreDao bookStoreDao; 
	 
	@Autowired 

	private EmployeeDao employeeDao; 
	 
	@Autowired 

	private CustomerDao customerDao; 

	@Transactional(readOnly = false) 

	public BookStoreData saveBookStore(BookStoreData bookStoreData) { 

		Long bookStoreId = bookStoreData.getBookStoreId(); 

		BookStore bookStore = findOrCreateBookStore(bookStoreId); 

		copyBookStorefields(bookStore, bookStoreData); 

return new BookStoreData(bookStoreDao.save(bookStore)); 

	} 

	private void copyBookStorefields(BookStore bookStore, BookStoreData bookStoreData) { 

		bookStore.setBookStoreAddress(bookStoreData.getBookStoreAddress()); 

		bookStore.setBookStoreCity(bookStoreData.getBookStoreCity()); 

		bookStore.setBookStoreId(bookStoreData.getBookStoreId()); 

		bookStore.setBookStoreName(bookStoreData.getBookStoreName()); 

		bookStore.setBookStorePhone(bookStoreData.getBookStorePhone()); 

		bookStore.setBookStoreState(bookStoreData.getBookStoreState()); 

		bookStore.setBookStoreZip(bookStoreData.getBookStoreZip()); 

	} 

	private BookStore findOrCreateBookStore(Long bookStoreId) { 

		if(Objects.isNull(bookStoreId)) { 

			return new BookStore(); 

		} 

		else { 

			return findBookStoreById(bookStoreId); 

		} 

} 

	private BookStore findBookStoreById(Long bookStoreId) { 

		return bookStoreDao.findById(bookStoreId) 

			.orElseThrow(() -> new NoSuchElementException( 

					"Book Store with ID=" + bookStoreId + "was not found."));	 

	} 

	@Transactional (readOnly = false) 

	public BookStoreEmployee saveEmployee(Long bookStoreId, BookStoreEmployee bookStoreEmployee) { 

		BookStore bookStore = findBookStoreById(bookStoreId); 

		Long employeeId = bookStoreEmployee.getEmployeeId(); 

		Employee employee = findOrCreateEmployee(bookStoreId, employeeId); 

		copyEmployeeFields (employee, bookStoreEmployee); 

		employee.setBookStore(bookStore); 

		bookStore.getEmployees().add(employee); 
 
		Employee dbEmployee = employeeDao.save(employee); 

		return new BookStoreEmployee(dbEmployee); 

	} 

	private void copyEmployeeFields(Employee employee, BookStoreEmployee bookStoreEmployee) { 

		employee.setEmployeeFirstName(bookStoreEmployee.getEmployeeFirstName()); 

		employee.setEmployeeId(bookStoreEmployee.getEmployeeId()); 

		employee.setEmployeeJobTitle(bookStoreEmployee.getEmployeeJobTitle()); 

		employee.setEmployeeLastName(bookStoreEmployee.getEmployeeLastName()); 

		employee.setEmployeePhone(bookStoreEmployee.getEmployeePhone()); 

	} 

	private Employee findOrCreateEmployee(Long bookStoreId, Long employeeId) { 

		if (Objects.isNull(employeeId)) { 

		return new Employee(); 
	} 

		return findEmployeeById(bookStoreId, employeeId); 

	} 

	private Employee findEmployeeById(Long bookStoreId, Long employeeId) { 

		Employee employee = employeeDao.findById(employeeId) 

			.orElseThrow(() -> new NoSuchElementException("Employee with ID=" + employeeId + "was not found."));  

		if(employee.getBookStore().getBookStoreId() !=bookStoreId) { 

			throw new IllegalArgumentException("The employee with ID=" + employeeId + " is not employed by the book store with ID=" + bookStoreId + "."); 

		} 

		return employee; 

	} 

	@Transactional 

	public BookStoreCustomer saveCustomer(Long bookStoreId, BookStoreCustomer bookStoreCustomer) { 

		BookStore bookStore = findBookStoreById(bookStoreId);  

		Long customerId = bookStoreCustomer.getCustomerId();  

		Customer customer = findOrCreateCustomer(bookStoreId, customerId); 		  

		copyCustomerFields (customer, bookStoreCustomer);  

		customer.getBookStores().add(bookStore);  

		bookStore.getCustomers().add(customer);  

		Customer dbCustomer = customerDao.save(customer);  

		return new BookStoreCustomer(dbCustomer); 

	} 

	private void copyCustomerFields(Customer customer, BookStoreCustomer bookStoreCustomer) { 

		customer.setCustomerEmail(bookStoreCustomer.getCustomerEmail()); 

		customer.setCustomerFirstName(bookStoreCustomer.getCustomerFirstName()); 

		customer.setCustomerId(bookStoreCustomer.getCustomerId()); 

		customer.setCustomerLastName(bookStoreCustomer.getCustomerLastName()); 	 

	} 

	private Customer findOrCreateCustomer(Long bookStoreId, Long customerId) { 

		if(Objects.isNull(customerId)) { 

			return new Customer(); 

		} 

		return findCustomerById(bookStoreId, customerId); 

	} 

	private Customer findCustomerById(Long bookStoreId, Long customerId) { 

		Customer customer = customerDao.findById(customerId) 

			.orElseThrow(() -> new NoSuchElementException("Customer with ID=" + customerId + " was not found."));  

		boolean found = false; 

		for(BookStore bookStore : customer.getBookStores()) { 

			if(bookStore.getBookStoreId() == bookStoreId) { 

				found = true; 

				break; 

			} 

		} 

		 
		if(!found) { 

			throw new IllegalArgumentException("The customer with ID=" + customerId + "is not a member of the book store with ID=" + bookStoreId); 

		} 

		return customer; 

	} 


	@Transactional (readOnly = true) 

	public List<BookStoreData> retrieveAllBookStores() { 

		List<BookStore> bookStores = bookStoreDao.findAll(); 

		List<BookStoreData> result = new LinkedList<>();  

		for(BookStore bookStore : bookStores) { 

			BookStoreData psd = new BookStoreData(bookStore); 		 

			psd.getCustomers() .clear(); 

			psd.getEmployees() .clear(); 		 

			result.add(psd); 		 

		} 
	 
		return result; 

	} 

	@Transactional (readOnly = true) 

	public BookStoreData retrieveBookStoreById(Long bookStoreId) { 

		return new BookStoreData(findBookStoreById(bookStoreId)); 	 

	} 

	@Transactional (readOnly = false) 

	public void deleteBookStoreById(Long bookStoreId) { 

		BookStore bookStore = findBookStoreById(bookStoreId); 

		bookStoreDao.delete(bookStore); 

		 

	} 

} 