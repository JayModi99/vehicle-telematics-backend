package com.vehicletelematics.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String firstName;
	
	private String lastName;
	
	@NotBlank
	@Email
	private String email;
	
	private String mobileNumber;
	
	@NotBlank
	@Size(min = 6, max = 30)
	private String password;
	
	private String company;
	
	private String address;
	
	private String profileSet;

}
