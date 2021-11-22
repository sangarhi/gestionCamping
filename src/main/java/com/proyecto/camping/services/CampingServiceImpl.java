package com.proyecto.camping.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.camping.entities.Camping;
import com.proyecto.camping.repositories.CampingRepository;
import com.proyecto.camping.services.interfaces.CampingService;

@Service
public class CampingServiceImpl implements CampingService{

	Logger log = LoggerFactory.getLogger(Camping.class);
	
	@Autowired
	CampingRepository repository;
	
	@Override
	public List<Camping> findAllCampings() {
		log.debug("findAllCampings()");
		
		return repository.findAll();
	}

	@Override
	public void save(Camping camping) {
		log.debug("save()");
		
		repository.save(camping);
		
	}

	@Override
	public Camping findById(Long id) {
		log.debug("findById()");
		
		return repository.getById(id);
	}

	@Override
	public void delete(Long id) {
		log.debug("delete");
		
		repository.deleteById(id);
		
		
	}

}
