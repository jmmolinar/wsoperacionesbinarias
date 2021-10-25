/** 
	José Miguel Molina Rondón
	jmmolinarondon@gmail.com
	24/10/2021

	Modelo para Creación de Json pasando el valor del número a buscar:
 
		JSON DE ENTRADA 
 
 		MÉTODO: post
 		ENDPOINT: http://localhost:9000
 		RESOURCE: /servicio/buscar
 		FUNCIÓN: validar
 
 			{
				"numero": NUM, //5144185 por ejemplo
			}

		JSON DE SALIDA

			{
				"numero":5144185,
				"operacion":49135,
				"encontrado": true
			}
 		
 	Servicio: com.prueba.servicio/RestRegistro.java
 
 */

package com.prueba.modelo;

public class Registro {

	private Long numero;
	private Long operacion;
	private Boolean encontrado;
	
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public Long getOperacion() {
		return operacion;
	}
	public void setOperacion(Long operacion) {
		this.operacion = operacion;
	}
	public Boolean getEncontrado() {
		return encontrado;
	}
	public void setEncontrado(Boolean encontrado) {
		this.encontrado = encontrado;
	}

}
