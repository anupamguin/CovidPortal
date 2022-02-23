package com.anupam.CovidPortal.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anupam.CovidPortal.model.RegisterModel;

@Repository
public interface UserRepo extends CrudRepository<RegisterModel,Integer>{

	RegisterModel findByEmail(String id);	
}
