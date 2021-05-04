import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import cts.com.model.Product;

public class Test1 {

	public static void main(String[] args) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Product> entity = restTemplate.getForEntity("http://localhost:9090/prd/search/121"
				, Product.class);
		Product product;
		System.out.println(entity.getStatusCodeValue());
		if(entity.getStatusCodeValue() == 200) {
			product = entity.getBody();
			System.out.println("ID NAME COST");
			System.out.println(product.getId()+" "+product.getCost()+" "+product.getName());
		}
		else {
			System.out.println("Product not found");
		}
	}
}
