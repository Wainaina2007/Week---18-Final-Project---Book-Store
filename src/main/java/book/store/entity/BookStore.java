package book.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn; 
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity 
@Data 

public class BookStore {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 

	private Long bookStoreId; 
	private String bookStoreName; 
	private String bookStoreAddress; 
	private String bookStoreCity; 
	private String bookStoreState; 
	private String bookStoreZip; 
	private String bookStorePhone;  

	@ManyToMany(cascade = CascadeType.PERSIST) 

	@JoinTable(name = "book_store_customer", joinColumns =  

	@JoinColumn(name = "book_store_id"),  

		inverseJoinColumns = @JoinColumn(name = "customer_id")) 

	@EqualsAndHashCode.Exclude 

	@ToString.Exclude 

	private Set<Customer> customers = new HashSet<>(); 

	 

	@OneToMany(mappedBy = "bookStore", cascade = CascadeType.ALL, orphanRemoval = true) 

	@EqualsAndHashCode.Exclude 

	@ToString.Exclude 

	private Set<Employee> employees = new HashSet<>();	
}
