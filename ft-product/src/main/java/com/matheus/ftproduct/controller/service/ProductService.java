package com.matheus.ftproduct.controller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.ftproduct.controller.service.exception.DataIntegrityViolationException;
import com.matheus.ftproduct.controller.service.exception.ObjectNotFoundException;
import com.matheus.ftproduct.model.entity.Product;
import com.matheus.ftproduct.model.entity.dto.ProductDTO;
import com.matheus.ftproduct.model.repository.ProductRepository;

import jakarta.validation.Valid;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll() {
		return repository.findAll();
	}

	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Product not found! ID: "+ id));
	}
	
	public Product save(ProductDTO objDTO) {
		objDTO.setIdProduct(null);
		
		Product newObj = new Product(objDTO);
		
		return repository.save(newObj);
	}
	
	public Product update(Long id, @Valid ProductDTO objDTO) {
        objDTO.setIdProduct(id);

       Product oldObj = findById(id);
        
        if(oldObj.hashCode() == objDTO.hashCode()) {
			throw new DataIntegrityViolationException("No changes were detected!");
        }
        
        oldObj = new Product(objDTO);

        return repository.save(oldObj);
    } 
	
	public Product delete(Long id) {
		Optional<Product> obj = repository.findById(id);
		
		if(obj != null) {
			repository.deleteById(id);
		}
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Product not found! ID: "+ id));
		
	}
}
