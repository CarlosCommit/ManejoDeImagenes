package com.imagenes.implementacion.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.imagenes.implementacion.service.ImagenService;

import jakarta.annotation.PostConstruct;

@Service
public class ImagenServiceImpl implements ImagenService {

	@PostConstruct
	@Override
	public void iniciarAlmacenDeArchivos() {
		try {
			Files.createDirectories(Paths.get("assets"));
		}catch (IOException excepcion) {
			System.out.println("Error al inicializar la ubicación en el almacen de archivos");
		}
	}

	@Override
	public String almacenarArchivo(MultipartFile archivo) {
		
		String nombreArchivo = archivo.getOriginalFilename();
		if(archivo.isEmpty()) {
		System.out.println("No se puede almacenar un archivo vacio");
		}
		try {
			InputStream inputStream  = archivo.getInputStream();
			//copiar el archivo, fuente, destino, opciones
			Files.copy(inputStream,Paths.get("assets").resolve(nombreArchivo),StandardCopyOption.REPLACE_EXISTING);
		}catch (IOException excepcion) {
			System.out.println("Error al guardar el archivo"+ excepcion.getMessage());
		}
		return nombreArchivo;
	}

	@Override
	public Path cargarArchivo(String nombreArchivo) {
		return Paths.get("assets").resolve(nombreArchivo);
	}

	@Override
	public Resource cargarComoRecurso(String nombreArchivo) {
		try {
			Path archivo = cargarArchivo(nombreArchivo);
			Resource recurso = new UrlResource(archivo.toUri());
			
			if(recurso.exists() || recurso.isReadable()) {
				return recurso;
			}else {
			System.out.println("No se pudo encontrar el archivo " + nombreArchivo);
			}
		
		}catch (MalformedURLException excepcion) {
			System.out.println("No se pudo encontrar el archivo " + nombreArchivo);
		}
		return null; 
	}
	

	@Override
	public void eliminarArchivo(String nombreArchivo) {
		Path archivo = cargarArchivo(nombreArchivo);
		try {
			FileSystemUtils.deleteRecursively(archivo);
		}catch (Exception excepcion) {
			System.out.println(excepcion);
		}
		
	}

}
