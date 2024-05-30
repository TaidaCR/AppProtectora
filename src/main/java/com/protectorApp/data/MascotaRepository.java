package com.protectorApp.data;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.protectorApp.model.Mascota;

public interface MascotaRepository extends PagingAndSortingRepository <Mascota, Long>{

	Optional<Mascota> findByNombre(String nombre);
	

}
