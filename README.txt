Integrantes:
	Camilo Farah, 201773534-1
	Fabio Pazos, 201773503-1 


Ejecución:
  -Ubicarse en el directorio en donde se encuentren los archivos descargados.
  -Para la compilación:
    -En la consola, escribir el comando: make
  -Para la ejecución:
    -En la consola, escribir el comando: make run


Explicación:
  - Desarrollamos el problema 2, usando el algoritmo Merge Sort para ordenar los arreglos de enteros. Esto estaba pensado
    en complementarse con el uso de threads, ya que al separar en dos sub arreglos el original entregado, se lograba
    que cada sub arreglo se trabajara en un hilo diferente, lo cual disminuía el tiempo de ordenamiento del proceso.
  - Cabe mencionar que esta mejora en tiempo de ejecución fue testeada, y empieza a ser notoria cuando los arreglos son 
    de mas de un millon de datos.