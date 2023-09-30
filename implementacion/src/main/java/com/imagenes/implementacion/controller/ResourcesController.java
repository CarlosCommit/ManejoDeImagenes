package com.imagenes.implementacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imagenes.implementacion.service.ImagenService;

@RestController
@RequestMapping(path="/images")
public class ResourcesController {

	@Autowired
	ImagenService img; 
	@GetMapping("/{nombre:.+}")
	public ResponseEntity<Resource> obtenerImagen(@PathVariable String nombre)
	{
		Resource resource = img.cargarComoRecurso(nombre);
	    HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); 
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
	}
}
