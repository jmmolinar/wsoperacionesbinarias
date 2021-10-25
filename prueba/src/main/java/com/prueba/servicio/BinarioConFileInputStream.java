/** 
	José Miguel Molina Rondón
	jmmolinarondon@gmail.com
	24/10/2021
	
	Clase para lectura del archivo binario empleando FileInputStrem
	No se invoca para la muestra del test pero se deja de respaldo en el proyecto
	
*/

package com.prueba.servicio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BinarioConFileInputStream {
	
	public static int[] lectura(String archivo) {

		File f = new File(archivo); // Lectura del archivo
		int tamanio = (int)f.length(); // Tamaño del archivo
		int[] datos = new int[tamanio]; // Arreglo con tamaño del archivo
		try {
			FileInputStream archivoEntrada = new FileInputStream(archivo);
			int dato = 0;
			int cont = 0;
			try {
				do {
					dato = archivoEntrada.read();
					if (dato != -1) {
						datos[cont] = dato;
						cont++;
						System.out.println("Byte: " + cont + " ... Dato: " + dato + " -- Binario: "+ Integer.toBinaryString(dato));
					}
				} while (dato != -1);
				System.out.println("El número total de bytes es: " + cont);
				archivoEntrada.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("No se ha leído el dato");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Archivo no encontrado");
		}
		return datos;

	}

}
