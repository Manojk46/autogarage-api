package com.garage.autogarage.entity;

import com.garage.autogarage.dto.CustomerRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(nullable = false)
	private String Name;
	@Column(nullable = false,unique = true)
	private String email;
	@Column(nullable = false)
	private String phone;
	@Column(nullable = false)
	private String address;
	public static Customer builder() {
		
		return null;
	}
}
