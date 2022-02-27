package com.anupam.CovidPortal.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anupam.CovidPortal.model.ContactForm;

@Repository
public interface FirstRepo extends CrudRepository<ContactForm, Integer> {

}
