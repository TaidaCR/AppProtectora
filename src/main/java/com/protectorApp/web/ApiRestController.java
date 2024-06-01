package com.protectorApp.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.protectorApp.data.MascotaRepository;
import com.protectorApp.model.Mascota;

@RestController
@RequestMapping(path="/api", produces="application/json")
@CrossOrigin(origins="*")
public class ApiRestController {

	@Autowired
	private MascotaRepository mascotaRepo;
	
	@GetMapping("/mascota/{id}")
	public ResponseEntity <Mascota> MascotaById (@PathVariable ("id") Long id){
		Optional <Mascota> optMascota = mascotaRepo.findById(id);
		if (optMascota.isPresent()) {
			return new ResponseEntity <> (optMascota.get(), HttpStatus.OK);
		}
		return new ResponseEntity <> (null, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(path = "/mascota", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	private Mascota subirMascota(@RequestBody Mascota mascota) {
		System.out.println("Mascota recibida: " + mascota);
		return mascotaRepo.save(mascota);
	}
	
	@GetMapping ("/mascota/todas")
	private Iterable <Mascota> listarTodasMascotas (){
		return mascotaRepo.findAll();
	}
	
	@GetMapping ("/mascota/20")
	private Iterable <Mascota> listar20Jovenes(){
		PageRequest pageable = PageRequest.of(0, 20, Sort.by("fechaNac").ascending());
		return mascotaRepo.findAll(pageable);
	}
	
	@GetMapping("/mascota/por5/{pag}")
	public Iterable <Mascota> listarPor5(@PathVariable ("pag") int pag) {
	    PageRequest pageable = PageRequest.of(pag, 5);
	    return mascotaRepo.findAll(pageable);
	}	
	
	@DeleteMapping("/mascota")
	public void borrarMascota(@RequestBody Mascota mascota) {
		mascotaRepo.delete(mascota);
	}
	
	@GetMapping("/mascotaNombre/{nombre}")
	public ResponseEntity <Mascota> MascotaByNombre (@PathVariable ("nombre") String nombre){
		Optional <Mascota> optMascota = mascotaRepo.findByNombre(nombre);
		if (optMascota.isPresent()) {
			return new ResponseEntity <> (optMascota.get(), HttpStatus.OK);
		}
		return new ResponseEntity <> (null, HttpStatus.NOT_FOUND);
	}
}
