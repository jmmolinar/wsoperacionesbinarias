/** 
	José Miguel Molina Rondón
	jmmolinarondon@gmail.com
	24/10/2021
	
	SERVICIO:
 
 		MÉTODO: post
 		ENDPOINT: http://localhost:9000
 		RESOURCE: /servicio/buscar
 		FUNCIÓN: validar
 
 */

package com.prueba.servicio;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.modelo.Registro;

@RestController
@RequestMapping(path = "/servicio")
public class RestRegistro {
	
	@RequestMapping(
			method = RequestMethod.POST, 
			path = "/buscar", 
			consumes = "application/json", 
			produces = "application/json"
	)
	public @ResponseBody Registro validar(@RequestBody Registro registro) throws Exception {
		Registro response = new Registro();
		response.setEncontrado(false);
		
		System.out.println("BÚSQUEDA CON JSON EN SOAPUI");
		System.out.println("Número: " + registro.getNumero());

		String rutaArchivo = "src/main/java/com/prueba/datos/OperacionesBinarias.bin";
		
		try {
			String[] data = BinarioConBufferedReader.lectura(rutaArchivo);
			for(int i = 0 ; i < data.length; i++) {
				if(data[i] != null) {
					
					System.out.println(data[i]); // data contiene el Número y la Operación separados por una coma
					String[] partes = data[i].split(","); 
					
					//Busco el número del JSON de entrada
					//Si es igual a la primera parte del data
					//Se da respuesta del JSON con número, operación y valor true de encontrado
					if (registro.getNumero().equals(Long.parseLong(partes[0]))) {
						response.setNumero(registro.getNumero()); // Asigno al response el Número
						response.setOperacion(Long.parseLong(partes[1])); //Asigno al response la Operación
						response.setEncontrado(true);
						
						System.out.println("");
						System.out.println("DATO ENCONTRADO");
						System.out.println("---------------");
						System.out.println("Número: " + partes[0]);
						System.out.println("Operación: " + partes[1]);
						System.out.println("");
						
					}
				}
			}
			
			System.out.println("");
			System.out.println("-------------------------");
			System.out.println("-                       -");
			System.out.println("-    DATO ENCONTRADO    -");
			System.out.println("-                       -");
			System.out.println("-------------------------");
			System.out.println("");
			System.out.println("Número: " + response.getNumero().toString());
			System.out.println("Operación: " + response.getOperacion().toString());
			System.out.println("");
			System.out.println("VER JSON DE RESPUESTA EN SOAPUI, POSTMAN U OTRO CLIENTE");
			
			
			return response;
		} catch (Exception ex) {

			return response;
		}
	}

}
