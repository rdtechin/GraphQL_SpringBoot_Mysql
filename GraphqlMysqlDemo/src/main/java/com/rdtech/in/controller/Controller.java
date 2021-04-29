package com.rdtech.in.controller;


import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.rdtech.in.service.GraphQlService;


import graphql.ExecutionResult;

@RestController
public class Controller {

	 @Autowired
	  private GraphQlService graphQLService;
	  
	  @PostMapping("/property")
	  public ResponseEntity<Object> getAllProperty(@RequestBody String query) {
	    ExecutionResult execute = graphQLService.executeGraphQL(query);
	    return new ResponseEntity<>(execute, HttpStatus.OK);
	  }
}
