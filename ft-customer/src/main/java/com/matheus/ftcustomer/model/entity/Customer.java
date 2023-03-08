package com.matheus.ftcustomer.model.entity;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matheus.ftcustomer.model.entity.dto.CustomerDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity @Data @Table(name = "tb_customer")
@NoArgsConstructor @AllArgsConstructor
public class Customer {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCustomer;
	private String nameCustomer;
	@CPF
	@Column(unique = true)
	private String cpfCustomer;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private LocalDateTime dateRegisterCustomer;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private LocalDateTime dateUpdateCustomer;
	
	@PrePersist
	public void prePersist() {
		setDateRegisterCustomer(LocalDateTime.now());
		setDateUpdateCustomer(LocalDateTime.now());
	}

	public Customer(CustomerDTO obj) {
		super();
		this.idCustomer = obj.getIdCustomer();
		this.nameCustomer = obj.getNameCustomer();
		this.cpfCustomer = obj.getCpfCustomer();
		this.dateRegisterCustomer = obj.getDateRegisterCustomer();
		this.dateUpdateCustomer = obj.getDateUpdateCustomer();
	}
}
