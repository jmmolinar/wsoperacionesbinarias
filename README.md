# wsoperacionesbinarias  

## WS en Spring para lectura de archivo binario

-  WS en Spring que obtiene de entrada un Número de Registro y genera como salida el Número de Registro y su Operación.
-  La entrada y salida están estructuradas en JSON
-  Se lee un archivo binario que cuenta con 1 millón de registros de largo de 9 bytes; los primeros 7 bytes corresponden al número del Registro (que debe ser comparado por el parámetro de entrada del ws) y los últimos 2 al número de operación (dato que se debe responder).
