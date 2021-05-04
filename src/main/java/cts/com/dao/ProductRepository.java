package cts.com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cts.com.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

//	@Query("select id,name from product where id=?1 and name=?2")
//	public Boolean val(int id,String name);
//
//	
}
