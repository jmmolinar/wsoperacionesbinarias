/** 
	José Miguel Molina Rondón
	jmmolinarondon@gmail.com
	24/10/2021
	
	Main para lectura de archivo binario
	
	- BinarioConBufferedReader.java (se utiliza esta clase para la ejecución del proyecto)
	- BinarioConFileInputStream.java (programada inicialmente)
	
	Ejecución en puerto 9000 especificado en src/main/resources/application.properties
	
*/

package com.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.prueba.servicio.BinarioConFileInputStream;
import com.prueba.servicio.BinarioConBufferedReader;

@SpringBootApplication
public class PruebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaApplication.class, args);

		System.out.println("");
		System.out.println("-------------------------");
		System.out.println("-     TEST JAVA SONDA   -");
		System.out.println("-------------------------");
		System.out.println("BÚSQUEDA DE NÚMERO Y OPERACIÓN EN: OperacionesBinarias.bin");
		
		String rutaArchivo = "src/main/java/com/prueba/datos/OperacionesBinarias.bin";
		
		//Lectura de archivo binario e impresión de datos mediante BinarioConBufferedReader.java
		BinarioConBufferedReader.lectura(rutaArchivo);
		
		System.out.println("-----------------------------------------");
		System.out.println("     INGRESE NÚMERO EN ESTRUCTURA JSON   ");
		System.out.println("-----------------------------------------");
		System.out.println("");
		System.out.println("MÉTODO: post - ENDPOINT: http://localhost:9000 - RESOURCE: /servicio/buscar - FUNCIÓN: validar");
		System.out.println("");
		System.out.println("EJEMPLO DE ENTRADA EN JSON");
		System.out.println("");
		System.out.println(" {");
		System.out.println("  \"numero\": 5144185,");
		System.out.println(" }");
		System.out.println("");
		System.out.println("OBTENDRA SALIDA EN JSON:");
		System.out.println("");
		System.out.println(" {");
		System.out.println("  \"numero\": 5144185,");
		System.out.println("  \"operacion\":49135,");
		System.out.println("  \"encontrado\": true");
		System.out.println(" }");
		System.out.println("");
		System.out.println("Pruebe en SOAPUI con algún NÚMERO de la traza de datos mostrada inicialmente");
		System.out.println("");
		
	}

}
