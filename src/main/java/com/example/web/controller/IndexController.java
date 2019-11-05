package com.example.web.controller;

/*
 
 git init
 git add --all
 git status
 git commit -m "first commit"
 git remote add origin https://github.com/pktkumar/PhoneNumberRestWebService.git
 git push origin master

 git commit  src/main/java/com/example/web/controller/IndexController.java -m "IndexController.java modified"
 git push origin master




  
  http://localhost:8080/PhoneNumber/person/personXml?phoneNumber=1234567891
  
  http://localhost:8080/PhoneNumber/person/personJson?phoneNumber=221234567891
  
  http://www.javainterviewpoint.com/spring-rest-hello-world-example/
  

  xml response : (header change)
  Request Headers:
  Name: Accept
  Attribute Value: application/xml
  
  
  Json response : (header change)
  Request Headers:
  Name: Accept
  Attribute Value: application/json


  for web:
  http://localhost:8080/PhoneNumber/person/

  
 */

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.web.bean.Person;

//@RestController  //used for rest service call , keep this use rest call
@Controller  // http://localhost:8080/PhoneNumber/person/
@RequestMapping("/PhoneNumber/person")
public class IndexController {



	@RequestMapping("/")
	public String LoginPage() {
		return "LoginPage";

	}

	@RequestMapping(value="/personXml", method= {RequestMethod.GET,RequestMethod.POST},produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	//@RequestMapping(value="/personXml",produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	//@RequestMapping(value="/personXml", method=RequestMethod.GET,produces="application/xml")
	public Person getStudentXml(@RequestParam(value="phoneNumber", required=false) String value)
	{
		System.out.println("phoneNumber:"+ value);
		Person person = new Person();

		String valueReceive = value;
		int count = 0;
		for (int i = 0, len = valueReceive.length(); i < len; i++) {
			if (Character.isDigit(valueReceive.charAt(i))) {
				count++;
			}
		}

		if(count==10) {

			person.setRegistrationNumber(value);
			person.setValidStatus("Valid");
			person.setRegistrationStatus("Success");
		}else{

			person.setRegistrationNumber(value);
			person.setValidStatus("In Valid");
			person.setRegistrationStatus("Bad Request");

		}

		//http://localhost:8080/PhoneNumber/person/personXml?phoneNumber=12345678911
		//http://localhost:8080/PhoneNumber/person/personXml?phoneNumber=12345678911
		//http://192.168.0.13:8080/PhoneNumber/person/personXml?phoneNumber=221234567891


		return person;
	}

	@RequestMapping("/Result")
	public String ResultPage(ModelMap modelMap, @RequestParam(value="value", required=false) String valueReceive) {

		Person person = new Person();
		String result = "";

		if (valueReceive.length() == 10) {
			modelMap.put("currentValue", valueReceive + " is Valid");
			int count = 0;
			for (int i = 0, len = valueReceive.length(); i < len; i++) {
				if (Character.isDigit(valueReceive.charAt(i))) {
					result = "Result";  // success result page
				}else{
					result = "Error"; // goes go error page
				}

			}
		} else {
			result = "Error";  // goes to error page
		}
		return result;
	}


	@RequestMapping("/Error")
	public String Error() {
		return "Error";

	}
}