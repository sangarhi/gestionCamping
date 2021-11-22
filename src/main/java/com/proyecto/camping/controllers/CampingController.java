package com.proyecto.camping.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.camping.common.Alojamiento;
import com.proyecto.camping.entities.Camping;
import com.proyecto.camping.services.interfaces.CampingService;

@Controller
@RequestMapping("/camping")
public class CampingController {
	
	Logger log = LoggerFactory.getLogger(CampingController.class);
	
	@Autowired
	CampingService service;
	
	@GetMapping
	public String getCampings(Model model) {
		log.debug("getCampings()");
		
		model.addAttribute("listado", service.findAllCampings());
		model.addAttribute("alojamientos", Alojamiento.values());
		
		return "campings";
		
	}
	
	@GetMapping("/{id}")
	public String getCamping(Model model, @PathVariable Long id) {
		log.debug("getCamping()");
		
		model.addAttribute("camping", service.findById(id));
		model.addAttribute("alojamientos", Alojamiento.values());
		
		return "camping";
		
	}
	
	@PostMapping
	public String createCamping(@ModelAttribute Camping camping, RedirectAttributes attribute) {
		log.debug("createCamping()");
		
		service.save(camping);
		attribute.addFlashAttribute("success", "Nuevo cliente creado con éxito.");

		
		return "redirect:/camping";	
	}
	
	@PostMapping("/{id}")
	public String updateCamping(@ModelAttribute Camping camping, @PathVariable Long id, RedirectAttributes attribute) {
		log.debug("updateCamping()");
		
		service.save(camping);
		attribute.addFlashAttribute("success", "Cliente modificado correctamente.");
		
		return "redirect:/camping/" + id;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteCamping(@PathVariable Long id, RedirectAttributes attribute) {
		log.debug("deleteCamping()");
		
		service.delete(id);

		attribute.addFlashAttribute("error", "Cliente eliminado correctamente.");
		
		return "redirect:/camping";
	}
	

}
