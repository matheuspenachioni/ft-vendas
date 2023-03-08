package com.matheus.ftproduct.model.entity;

import java.math.BigDecimal;

import com.matheus.ftproduct.model.entity.dto.ProductDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_product")
public class Product {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product")
	private Long idProduct;
	@Column(name = "name_product", nullable = false)
	private String nameProduct;
	@Column(name = "description_product", nullable = false)
	private String descriptionProduct;
	@Column(name = "cost_price_product", nullable = false)
	private BigDecimal costPriceProduct;
	@Column(name = "amount_product", nullable = false)
	private BigDecimal amountProduct;
	
	public Product(ProductDTO obj) {
		super();
		this.idProduct = obj.getIdProduct();
		this.nameProduct = obj.getNameProduct();
		this.descriptionProduct = obj.getDescriptionProduct();
		this.costPriceProduct = obj.getCostPriceProduct();
		this.amountProduct = obj.getAmountProduct();
	}
}
