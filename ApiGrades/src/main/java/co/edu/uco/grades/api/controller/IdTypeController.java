package co.edu.uco.grades.api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.grades.dto.IdTypeDTO;

@RestController
@RequestMapping("/api/v1/idtype")
public class IdTypeController {
	
	@PostMapping
	public void create(@RequestBody IdTypeDTO dto) {
		System.out.println("Estoy en crear!!");
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable("id") int id, @RequestBody IdTypeDTO dto) {
		System.out.println("Estoy en actualizar!!");
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") int id) {
		System.out.println("Estoy en eliminar!!");
	}
	
	@GetMapping("/{id}")
	public void findByid(@PathVariable("id") int id) {
		System.out.println("Estoy en consultar por id!!");
	}
	
	@GetMapping
	public void find() {
		System.out.println("Estoy en consultar todos!!");
	}
}
