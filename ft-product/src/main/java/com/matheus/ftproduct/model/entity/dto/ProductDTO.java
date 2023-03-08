package com.matheus.ftproduct.model.entity.dto;

import java.math.BigDecimal;

import com.matheus.ftproduct.model.entity.Product;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
	private Long idProduct;
	@NotNull(message = "NAME is required!")
	private String nameProduct;
	@NotNull(message = "DESCRIPTION is required!")
	private String descriptionProduct;
	@NotNull(message = "COST PRICE is required!")
	private BigDecimal costPriceProduct;
	@NotNull(message = "AMOUNT is required!")
	private BigDecimal amountProduct;
	
	public ProductDTO(Product obj) {
		super();
		this.idProduct = obj.getIdProduct();
		this.nameProduct = obj.getNameProduct();
		this.descriptionProduct = obj.getDescriptionProduct();
		this.costPriceProduct = obj.getCostPriceProduct();
		this.amountProduct = obj.getAmountProduct();
	}
}
