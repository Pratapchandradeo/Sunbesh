package com.Sunbash.Controllers;



import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sunbash.ExceptionHandler.BodyNotFoundException;
import com.Sunbash.PlayLoad.CustomerDto;
import com.Sunbash.Services.CustomerService;

import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin("*")
@RequestMapping("/sunbase/portal/api/")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping("/create")
	public ResponseEntity<CustomerDto> createCustomer(@RequestBody(required = false) CustomerDto customer, BindingResult bindingResult )
	{
		if(bindingResult.hasErrors())
		{
			throw new BodyNotFoundException();
		}
		
		CustomerDto response = this.customerService.createCustomer(customer);
		
		return new ResponseEntity<CustomerDto>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") UUID id ,@RequestBody(required = false) CustomerDto customer, BindingResult bindingResult )
	{
		if(bindingResult.hasErrors())
		{
			throw new BodyNotFoundException();
		}
		
		CustomerDto response = this.customerService.updateCustomer(id,customer);
		
		return new ResponseEntity<CustomerDto>(response,HttpStatus.OK);
	}
	
	@PostMapping("/delete/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") UUID customerId)
	{
		
		this.customerService.deleteCustomer(customerId);
		
		return new ResponseEntity<>("Customer Delete Succefully!!",HttpStatus.OK);
	}
	@GetMapping("/get_customer_list")
	public ResponseEntity<List<CustomerDto>> allCustomer()
	{
		
		
		List<CustomerDto> allCustomer = this.customerService.getAllCustomer();
		
		return new ResponseEntity<>(allCustomer,HttpStatus.OK);
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") UUID customerId)
	{
		
		CustomerDto customerDto = this.customerService.getById(customerId);
		
		return new ResponseEntity<>(customerDto,HttpStatus.OK);
	}

}
