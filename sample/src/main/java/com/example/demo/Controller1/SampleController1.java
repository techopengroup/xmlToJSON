package com.example.demo.Controller1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@RestController
@RequestMapping("/b/")
public class SampleController1 {
	
	@Autowired(required = true)
	XmlMapper xmlMapper;

	    
	@PostMapping(value="jsonToXml")
	public Model jsonToXml(@RequestBody Model model) throws JsonMappingException, JsonProcessingException {
		
         return model;        		
		}
	
	@PostMapping(value="xmlTojson",produces = MediaType.APPLICATION_JSON_VALUE)
	public Model xmlTojson(@RequestBody Model model) throws JsonMappingException, JsonProcessingException {
		String brg=null;
         return model;  
         
		}
	
}
