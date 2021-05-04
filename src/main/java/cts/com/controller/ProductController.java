package cts.com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cts.com.dao.ProductRepository;
import cts.com.dao.logrepo;
import cts.com.error.ProductupdateException;
import cts.com.error.RecordNotFoundException;
import cts.com.model.Product;
import cts.com.model.User;

@RestController
@RequestMapping("/prd")
@CrossOrigin(value = "http://localhost:4200")
public class ProductController {

	@Autowired
	ProductRepository dao;
	@Autowired
	logrepo ldao;

	@RequestMapping("/getAll")
	public ResponseEntity<List<Product>> getAllProduct() {

		if(dao.findAll().size() > 0) {
		return new ResponseEntity<List<Product>>(dao.findAll(),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
		}
	}

	@ExceptionHandler(value = Exception.class)
	public void appproduct() {
		
	}
	// @RequestMapping("/search/{id}")
	// public Product searchdata(@PathVariable("id") int id) {
	// Optional<Product> opt = dao.findById(id);
	// if(opt.isPresent()) {
	// return opt.get();
	// }
	// return null;
	// }
	// **this should be used when anguler is used
	@RequestMapping("/search/{id}")
	@ExceptionHandler(value = RecordNotFoundException.class)
	public ResponseEntity<Product> searchdata(@PathVariable("id") int id ) {
		Optional<Product> opt = dao.findById(id); 
		try {
			if (opt.isPresent()) {
				return new ResponseEntity<Product>(opt.get(), HttpStatus.OK);
			} else {
				throw new RecordNotFoundException("Record with id " + id + " is not present");
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	//**try this for fun
//	@RequestMapping("/search/{id}/{name}")
//	@ExceptionHandler(value = RecordNotFoundException.class)
//	public ResponseEntity<Product> searchdata(@PathVariable("id") int id , @PathVariable("name") String name) {
//		Optional<Product> opt = dao.findById(id); 
//	//	Optional<User>optx = ldao.findById(name);
//	//	List<Product> xx = (List<Product>) opt.get();
//		Product pp = opt.get();
//		String xx = pp.getName();
//		//System.out.println(xx);
//		try {
//			if (opt.isPresent()  && xx.equalsIgnoreCase(name)) {
//				return new ResponseEntity<Product>(opt.get(), HttpStatus.OK);
//			} else {
//				throw new RecordNotFoundException("Record with id " + id + " is not present");
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
//		}
//	}
	// @RequestMapping("/delete/{id}")
	// public Product deletedata(@PathVariable("id") int id) {
	// Optional<Product> op = dao.findById(id);
	// if(op.isPresent()) {
	// dao.delete(op.get());
	// return op.get();
	// }
	// return null;
	// }
	// **this should be used when anguler is used
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Product> deletedata(@PathVariable("id") int id) {
		Optional<Product> op = dao.findById(id);
		try {
		if (op.isPresent()) {
			dao.delete(op.get());
			return new ResponseEntity<Product>(op.get(), HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("Product not found " + id + "id Cannot be deleted ");
		}
		}
		catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ResponseEntity<Product> addProduct(@RequestBody Product prd) {
		Optional<Product> opt = dao.findById(prd.getId());
		try {
		if (!opt.isPresent())
			return new ResponseEntity<Product>(dao.save(prd), HttpStatus.OK);
		else
			throw new Exception("Product already present");
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/UpdateProduct")
	public ResponseEntity<Product> updateProduct(@RequestBody Product prd) {
		Optional<Product> opt = dao.findById(prd.getId());
	    try {
		if (opt.isPresent()) {
			return new ResponseEntity<Product>(dao.save(prd), HttpStatus.OK);
	    }
	    else {
	    	throw new ProductupdateException("Product Update not present");
	    }
	   }catch (Exception e) {
			// TODO: handle exception
	    	return new ResponseEntity(e.getMessage() ,HttpStatus.NOT_FOUND);
		}
	    
	}
}
