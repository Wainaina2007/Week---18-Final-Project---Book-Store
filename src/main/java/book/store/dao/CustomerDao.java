package book.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import book.store.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}
