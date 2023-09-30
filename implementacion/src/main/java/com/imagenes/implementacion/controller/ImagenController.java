package com.imagenes.implementacion.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.imagenes.implementacion.service.ImagenService;

@Controller
@RequestMapping(path="/imagen")
public class ImagenController {

	@Autowired
	ImagenService imgService; 
	@PostMapping("/subir")
	public ResponseEntity<Object> upLoadImage(@RequestParam(name = "imagen") MultipartFile imagen) throws URISyntaxException
	{
		Map<String, Object> response = new HashMap<String,Object>();
		String ruta = imgService.almacenarArchivo(imagen); 
		response.put("ruta", ruta); 
		URI uri = new URI(ruta);
		response.put("URI", uri.toString()); 
	return new ResponseEntity<Object>(response ,HttpStatus.CREATED); 	
	}
}
