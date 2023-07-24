package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Model;
import com.example.demo.Controller1.SampleController1;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;

@RestController
@RequestMapping("/a/")
public class SampleController {
	@Autowired(required = true)
	ObjectMapper objectMapper;
	@Autowired(required = true)
	XmlMapper xmlMapper;
	@Autowired(required = true)
	RestTemplate restTemplate;

	@GetMapping(value = "jsonToXml", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public String jsonToXml(@RequestBody List<Model> model) throws JsonMappingException, JsonProcessingException {

		String xml = xmlMapper.writeValueAsString(model);
		return xml;
	}

	@GetMapping(value = "xmlToJson", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String xmlToJson(@RequestBody List<Model> model) throws JsonMappingException, JsonProcessingException {

		String json = objectMapper.writeValueAsString(model);
		return json;

	}

	@GetMapping(value = "convertJsonToXml")
	public ResponseEntity<Model> callToController(@RequestBody Model model)
			throws JsonMappingException, JsonProcessingException {

		String xmlModel = xmlMapper.writeValueAsString(model);
//	Model model1 = xmlMapper.readValue(xmlModel, Model.class);
//	SampleController1 sampleController1 =new SampleController1();
//	  return sampleController1.jsonToXml(model1);
//	}
//	
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_XML);

		HttpEntity<String> httpEntity = new HttpEntity<>(xmlModel, httpHeaders);

		ResponseEntity<Model> responseEntity = restTemplate.postForEntity("http://localhost:8080/b/jsonToXml",
				httpEntity, Model.class);

		return responseEntity;
	}

	
	@GetMapping(value = "convertXmltoJson")
	public ResponseEntity<Model> callToController1(@RequestBody Model model)
			throws JsonMappingException, JsonProcessingException {

		String jsonModel = objectMapper.writeValueAsString(model);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> httpEntity = new HttpEntity<>(jsonModel, httpHeaders);

		ResponseEntity<Model> responseEntity = restTemplate.postForEntity("http://localhost:8080/b/xmlTojson",
				httpEntity, Model.class);

		return responseEntity;
	}

}
