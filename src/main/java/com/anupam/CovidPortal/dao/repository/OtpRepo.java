package com.anupam.CovidPortal.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anupam.CovidPortal.model.OtpResponseModel;

@Repository
public interface OtpRepo extends CrudRepository<OtpResponseModel,Integer>{

	OtpResponseModel findByEmail(String id);
}
