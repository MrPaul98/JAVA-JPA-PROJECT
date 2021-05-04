package cts.com.json;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cts.com.model.Product;

public class JsonToProduct {

	
	public static void main(String[] args) 
		throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		
		System.out.println("JSON TO OBJECT -- SERILISATION");
		
		ObjectMapper mapper =  new ObjectMapper();
		Product prd = mapper.readValue(new URL("http://localhost:9090/prd/search/121"), Product.class);
	    System.out.println(prd.getId() +" "+prd.getCost()+" "+prd.getName());
	    List<Product> prdlist = mapper.readValue(new URL("http://localhost:9090/prd/getAll"), new TypeReference<List<Product>>() {});
	    for (Product product : prdlist) {
			
	    	 System.out.println(product.getId()+" "+product.getCost()+" "+product.getName());
		}
	    System.out.println("JSON TO OBJECT -- DESERILISATION");
	    
	    Product product1 = new Product();
	    product1.setId(123);
	    product1.setCost(660);
	    product1.setName("Advanced java");
	    
	    mapper.writeValue(new File("target\\Prd.json"), product1);
	}
}
