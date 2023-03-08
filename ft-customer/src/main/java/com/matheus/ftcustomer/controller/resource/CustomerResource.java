package com.matheus.ftcustomer.controller.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matheus.ftcustomer.model.entity.Customer;
import com.matheus.ftcustomer.model.entity.dto.CustomerDTO;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;

import com.matheus.ftcustomer.controller.service.CustomerService;

@RestController
@RequestMapping(value = "/api/v1/customers")
public class CustomerResource {
	@Autowired
	private CustomerService service;
	
	@ApiOperation(value = "")
	@GetMapping(value = "/listAll")
	public ResponseEntity<List<CustomerDTO>> findAll() {
		List<Customer> list = service.findAll();
		List<CustomerDTO> listDTO = list.stream().map(obj -> new CustomerDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@ApiOperation(value = "Search a Customer by ID")
	@GetMapping(value = "/searchId/{id}")
	public ResponseEntity<CustomerDTO> findById(@PathVariable Integer id) {
		Customer obj = service.findById(id);
		
		return ResponseEntity.ok().body(new CustomerDTO(obj));
	}
	
	@ApiOperation(value = "Search a Customer by CPF")
	@GetMapping(value = "/searchCpf/{cpf}")
	public ResponseEntity<CustomerDTO> findByCpf(@PathVariable String cpf) {
		Customer obj = service.findByCpf(cpf);
		
		return ResponseEntity.ok().body(new CustomerDTO(obj));
	}
	
	@ApiOperation(value = "Saves the data of a Customer")
	@PostMapping(value = "/save")
	public ResponseEntity<CustomerDTO> save(@Valid @RequestBody CustomerDTO objDTO) {
		Customer newObj = service.save(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getIdCustomer()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Updates the data of a Customer")
	@PutMapping(value = "update/{id}")
	public ResponseEntity<CustomerDTO> update(@PathVariable Integer id, @Valid @RequestBody CustomerDTO objDTO) {
		Customer obj = service.update(id, objDTO);
		
		return ResponseEntity.ok().body(new CustomerDTO(obj));
	}
	
	@ApiOperation(value = "")
	@DeleteMapping(value = "delete/{id}")
	public ResponseEntity<CustomerDTO> delete(@PathVariable Integer id) {
		service.delete(id); 
		
		return ResponseEntity.noContent().build();
	}
}
