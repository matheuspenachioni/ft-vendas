package com.matheus.ftcustomer.model.entity.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matheus.ftcustomer.model.entity.Customer;

@Data
@NoArgsConstructor
public class CustomerDTO {
	private Integer idCustomer;
	@NotNull(message = "NAME is required!")
	private String nameCustomer;
	@NotNull(message = "CPF is required!")
	private String cpfCustomer;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private LocalDateTime dateRegisterCustomer;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private LocalDateTime dateUpdateCustomer;
	
	public CustomerDTO(Customer obj) {
		super();
		this.idCustomer = obj.getIdCustomer();
		this.nameCustomer = obj.getNameCustomer();
		this.cpfCustomer = obj.getCpfCustomer();
		this.dateRegisterCustomer = obj.getDateRegisterCustomer();
		this.dateUpdateCustomer = obj.getDateUpdateCustomer();
	}
}
