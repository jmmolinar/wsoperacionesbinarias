/** 
	José Miguel Molina Rondón
	jmmolinarondon@gmail.com
	24/10/2021
	
	Clase para lectura del archivo binario empleando InputStream y BufferedReader
	Activa para la ejecución del proyecto
	
*/

package com.prueba.servicio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

public class BinarioConBufferedReader {

	// Utilizada por función byteArrayToHexStringSinEspacios
	private static final int MASK_FF = 0xff;
	// Utilizada por función valor7bytesToInt
	private static final int MAXIMO_BYTE_TARJETA = 7;

	// Función de lectura del archivo con InputStream y BufferedReader
	public static String[] lectura(String archivo) {

		File f = new File(archivo); // Lectura del archivo
		int tamanio = (int) f.length(); // Tamaño del archivo
		String todosRegistros[] = new String[tamanio]; // Arreglo con tamaño del archivo
		long todoValor7Bytes[] = new long[tamanio]; // Arreglo Números Registro con tamaño del archivo
		int todoValor2Bytes[] = new int[tamanio]; // Arreglo Operaciones con tamaño del archivo
		String numeroConOperacion[] = new String[tamanio]; // Arreglo Número de Registro con la Operación del tamaño del archivo

		try {

			int cont = 0;

			InputStream is = new FileInputStream(archivo);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String linea = br.readLine();

			while (linea != null) {

				// byte[] myBytes = linea.getBytes("UTF-8");
				byte[] lineBytes = linea.getBytes(); // Obtengo los bytes de la linea actual en el archivo
				String conversion = byteArrayToHexStringSinEspacios(lineBytes); // Conversión a hexadecimal
				byte[] lineBytes7 = fillArraySize(0, 7, lineBytes); // Arreglo de bytes de tamaño 7 - fillArraySize(posicion_inicial, tamanio, arreglo)
				long value7Bytes = valor7bytesToInt(lineBytes7);
				byte[] lineBytes2 = fillArraySize(7, 2, lineBytes); // Arreglo de bytes de tamaño 2 - fillArraySize(posicion_inicial, tamanio, arreglo)
				int value2Bytes = valor2bytesToInt(lineBytes2);

				// todosRegistros[cont] = convertStringToBinary(conversion); //En binario

				todosRegistros[cont] = conversion; // Agrego a la posición del arreglo el Hexadecimal
				todoValor7Bytes[cont] = value7Bytes; // Agrego a la posición del arreglo el Número
				todoValor2Bytes[cont] = value2Bytes; // Agrego a la posición del arreglo la Operación
				
				numeroConOperacion[cont] = value7Bytes + "," + value2Bytes; // Agrero el Número y la Operación
				
				
				cont++;

				linea = br.readLine();
			}

			int nulos = 0;
			for (int i = 0; i < todosRegistros.length; i++) {
				if (todosRegistros[i] != null) {
					System.out.println("Linea: " + (i + 1) + " - Número: " + todoValor7Bytes[i] + " - Operación: "
							+ todoValor2Bytes[i] + " - Hexadecimal: " + todosRegistros[i]);
					//System.out.println("Linea: " + (i + 1) + " - Número y Operación: " + numeroConOperacion[i]);
				} else {
					nulos++;
				}
			}

			System.out.println("");
			System.out.println("TAMAÑO EN BYTES DEL ARCHIVO: " + todosRegistros.length);
			System.out.println("");
			System.out.println("Total de registros nulos: " + nulos);

			br.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
			e.printStackTrace();
		} catch (IOException ex) {
			System.out.println("Error");
			ex.printStackTrace();
		}
		
		return numeroConOperacion;

	}

	// Función para convertir de string a binario
	public static String convertStringToBinary(String input) {

		StringBuilder result = new StringBuilder();
		char[] chars = input.toCharArray();
		for (char aChar : chars) {
			result.append(String.format("%8s", Integer.toBinaryString(aChar)) // char -> int, auto-cast
					.replaceAll(" ", "0") // zero pads
			);
		}
		return result.toString();

	}

	// Función suministrada en el test - Arreglo de byte a Hexadecimal
	public static String byteArrayToHexStringSinEspacios(final byte[] bytes) {
		final int desplazamiento = 4;
		char[] hexArray = "0123456789ABCDEF".toCharArray();
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & MASK_FF;
			hexChars[j * 2] = hexArray[v >>> desplazamiento];
			final int mascara = 0x0F;
			hexChars[j * 2 + 1] = hexArray[v & mascara];
		}
		return new String(hexChars);
	}

	// Función suministrada en el test - 7 bytes a long
	public final static long valor7bytesToInt(final byte[] leidoIn) throws IOException {
		byte[] leido = inverseByteArray(leidoIn); // Invierte el arreglo
		final int largoValor = MAXIMO_BYTE_TARJETA;
		if (leido.length != largoValor) {
			throw new IOException("Arreglo debe ser de 7 bytes.");
		}
		final byte largoLong = 8;
		byte[] bytes = new byte[largoLong];
		final int indiceValor = 1;
		for (int i = 0; i < largoValor; i++) {
			bytes[indiceValor + i] = leido[0 + i];
		}
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.put(bytes);
		buffer.flip(); // need flip
		return buffer.getLong();
	}

	// Función suministrada en el test - 2 bytes a long
	public static int valor2bytesToInt(final byte[] leidoIn) throws IOException {
		byte[] leido = inverseByteArray(leidoIn); // Invierte el arreglo
		final int largoValor = 2;
		if (leido.length != largoValor) {
			throw new IOException("Arreglo debe ser de 2 bytes.");
		}
		final byte largoInt = 4;
		byte[] bytes = new byte[largoInt];
		final int indiceValor = 2;
		bytes[indiceValor] = leido[0];
		bytes[indiceValor + 1] = leido[1];
		ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES);
		byteBuffer.put(bytes);
		byteBuffer.flip();
		return byteBuffer.getInt();
	}

	// Función que invierte el arreglo de bytes y lo retorna
	public static byte[] inverseByteArray(final byte[] bytes) {
		byte[] aux = new byte[bytes.length];
		int j = 0;
		for (int i = (bytes.length) - 1; i >= 0; i--) {
			aux[j] = bytes[i];
			//System.out.println(aux[j]);
			j++;
		}
		return aux;
	}

	// Función para obtener el arreglo de bytes de tamaño size
	public static byte[] fillArraySize(int initial, int size, byte[] bytes) {
		byte[] aux = new byte[size];
		if (bytes != null) {
			int j = 0;
			for (int i = initial; i < bytes.length; i++) {
				if (j < aux.length) {
					aux[j] = bytes[i];
				}
				j++;
			}
		}
		return aux;
	}

}
