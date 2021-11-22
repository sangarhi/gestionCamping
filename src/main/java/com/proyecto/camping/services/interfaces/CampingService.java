package com.proyecto.camping.services.interfaces;

import java.util.List;

import com.proyecto.camping.entities.Camping;

public interface CampingService {
	
public List<Camping> findAllCampings();
	
	public void save(Camping camping);
	
	public Camping findById(Long id);
	
	public void delete(Long camping);

}
