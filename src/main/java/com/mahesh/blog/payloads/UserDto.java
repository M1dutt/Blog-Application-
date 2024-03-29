package com.mahesh.blog.payloads;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	
	private int  id;
	
	@NotEmpty
	@Size(min = 4,message = "userName must be min of 4 characters")
	private String name;
	
	@Email(message = "email format is incorrect")
	private String email;
	
	@NotEmpty
	@Size(min = 3,max = 10,message = "password must be in btw 3 and 10")
	private String password;
	
	@NotEmpty
	private String about;



}
