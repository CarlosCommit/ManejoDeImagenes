package com.imagenes.implementacion.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imagenes.implementacion.model.Imagen;

//FROM SPRING DATA JPA ITS NOT THE SAME JPA
@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Long> {

	
}
