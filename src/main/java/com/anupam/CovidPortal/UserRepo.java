package com.anupam.CovidPortal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anupam.CovidPortal.model.RegisterModel;

@Repository
public interface UserRepo extends CrudRepository<RegisterModel,Long>{

	RegisterModel findByEmail(String id);	
}
