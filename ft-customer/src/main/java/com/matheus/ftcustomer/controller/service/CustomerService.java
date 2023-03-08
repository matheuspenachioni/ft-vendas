package com.matheus.ftcustomer.controller.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.ftcustomer.model.entity.Customer;
import com.matheus.ftcustomer.model.entity.dto.CustomerDTO;
import com.matheus.ftcustomer.model.repository.CustomerRepository;

import jakarta.validation.Valid;

import com.matheus.ftcustomer.controller.service.exception.DataIntegrityViolationException;
import com.matheus.ftcustomer.controller.service.exception.ObjectNotFoundException;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repository;
	
	public List<Customer> findAll() {
		return repository.findAll();
	}
	
	public Customer findById(Integer id) {
		Optional<Customer> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Customer not found! ID: "+ id));
	}
	
	public Customer findByCpf(String cpf) {
		Optional<Customer> obj = repository.findByCpfCustomer(cpf);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Customer not found! CPF: "+ cpf));
	}
	
	public Customer save(CustomerDTO objDTO) {
		objDTO.setIdCustomer(null);
		validateCpf(objDTO);
		
		Customer newObj = new Customer(objDTO);
		
		return repository.save(newObj);
	}
	
	public Customer update(Integer id, @Valid CustomerDTO objDTO) {
        Customer oldObj = findById(id);
        
        objDTO.setIdCustomer(id);
		objDTO.setDateUpdateCustomer(LocalDateTime.now());
        objDTO.setDateRegisterCustomer(oldObj.getDateRegisterCustomer());
        validateCpf(objDTO);
        
        oldObj = new Customer(objDTO);

        return repository.save(oldObj);
    } 
	
	public Customer delete(Integer id) {
		Optional<Customer> obj = repository.findById(id);
		
		if(obj != null) {
			repository.deleteById(id);
		}
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Customer not found! ID: "+ id));
	}
	
	private void validateCpf(CustomerDTO objDTO) {
		Optional<Customer> obj = repository.findByCpfCustomer(objDTO.getCpfCustomer());
		
		if(obj.isPresent() && obj.get().getIdCustomer() != objDTO.getIdCustomer()) {
			throw new DataIntegrityViolationException("CPF already exists! CPF: "+ objDTO.getCpfCustomer());
		}
	}
}
