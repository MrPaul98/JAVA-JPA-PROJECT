package cts.com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cts.com.model.User;

@Repository
public interface logrepo extends JpaRepository<User, String>{

}
