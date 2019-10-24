package com.controll;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.entities.Service;
import com.entities.Service;
import com.services.ServiceRepository;
import com.services.ServiceRepository;

import busLogicImp.serviceLogicImp;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/service")
public class ServiceControll {
	@Autowired
	private ServiceRepository serviceRepository;
	@Autowired
	private serviceLogicImp serviceLogic;

	@PostMapping(path = "/addService") // Map ONLY GET Requests
	public Service addService(@RequestParam String name,@RequestParam String desc,@RequestParam String imgUrl) {

		return serviceLogic.addService(name,desc,imgUrl);

	}

	@GetMapping(path = "/allServices")
	public List<Service> getAllServices() {
		// This returns a JSON or XML with the users
		return serviceLogic.getAllService();

	}

	@GetMapping(path = "/findService/{id}")
	public Optional<Service> findService(@PathVariable(value = "id") Long id) {
		// This returns a JSON or XML with the users
		return serviceRepository.findById(id);

	}

	@GetMapping(path = "/findServiceByName/{name}")
	public Service findServiceByName(@PathVariable(value = "name") String name) {

		return serviceLogic.findService(name);
	}

	@PutMapping(path = "/updateService")
	public void updateService(@RequestParam String name, @RequestParam String newName, @RequestParam String desc) {
		 serviceLogic.UpdateService(name,newName,desc);
		 
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id) {
		Optional<Service> user = serviceRepository.findById(id);
		serviceRepository.deleteById(id);
		return user + " is deleted";
	}

	@DeleteMapping("/delete/{name}")
	public String delete(@PathVariable(value = "name") String name) {
		serviceLogic.deleteService(name);
		return "deleted";
	}

}
