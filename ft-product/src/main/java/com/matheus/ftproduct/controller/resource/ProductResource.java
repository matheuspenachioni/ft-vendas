package com.matheus.ftproduct.controller.resource;

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

import com.matheus.ftproduct.controller.service.ProductService;
import com.matheus.ftproduct.model.entity.Product;
import com.matheus.ftproduct.model.entity.dto.ProductDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductResource {
	@Autowired
	private ProductService service;
	
	@GetMapping(value = "/searchAll")
	public ResponseEntity<List<ProductDTO>> findAll() {
		List<Product> list = service.findAll();
		List<ProductDTO> listDTO = list.stream().map(obj -> new ProductDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/searchId/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
		Product obj = service.findById(id);
		
		return ResponseEntity.ok().body(new ProductDTO(obj));
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<ProductDTO> save(@Valid @RequestBody ProductDTO objDTO) {
		Product newObj = service.save(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getIdProduct()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO objDTO) {
		Product obj = service.update(id, objDTO);
		
		return ResponseEntity.ok().body(new ProductDTO(obj));
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<ProductDTO> delete(@PathVariable Long id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
