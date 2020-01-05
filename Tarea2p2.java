import java.util.Random;

public class Tarea2p2{

	// Hacer el merge final con los dos sub arreglos que se trabajaron el los THREADS
	// esta funcion es igual a MergeSort solo que ya estan hechos los subarrays
	public static void finalMerge(int[] a, int[] b) {
    int[] result = new int[a.length + b.length];
    int i=0;
    int j=0;
    int r=0;
    while (i < a.length && j < b.length) {
      if (a[i] >= b[j]) {
        result[r]=a[i];
        i++;
        r++;
      } else {
        result[r]=b[j];
        j++;
        r++;
      }
      if (i==a.length) {
        while (j<b.length) {
          result[r]=b[j];
          r++;
          j++;
        }
      }
      if (j==b.length) {
        while (i<a.length) {
          result[r]=a[i];
          r++;
          i++;
        }
      }
    }

    //  PRINTEAR ARREGLO ORDENADO CON THREADS
    System.out.println("Arreglo ordenado con threads:");
    for (j = 0; j < a.length + b.length; j++){
      System.out.print(result[j]+" ");
    }
    System.out.print("\n");
    
  }

	public static void main(String[] args) throws InterruptedException {

    	// Se crea un arreglo con valores aleatorios
	    Random rand = new Random();
	    int[] array = new int[10];
	    for (int i=0; i<array.length; i++) {
	      array[i] = rand.nextInt(100);
	    }

		//PRINTEAR ARREGLO
	    System.out.println("Arreglo a ordenar:");
	    int x;
	    for (x = 0; x < array.length; x++){
	      System.out.print(array[x]+" ");
	    }
	    System.out.println("\n");

		// Se separa en 2 sub arreglos para luego hacer merge con threads
		long startTime = System.currentTimeMillis();
		int[] arrayIzq = new int[array.length/2];
	    int[] arrayDer = new int[array.length - array.length/2];
	    System.arraycopy(array, 0, arrayIzq, 0, array.length/2);
	    System.arraycopy(array, array.length/2, arrayDer, 0, array.length - array.length/2);

	    Buenardo sortarrayIzq = new Buenardo(arrayIzq);
	    Buenardo sortarrayDer = new Buenardo(arrayDer);
		//Inicio los threads donde cada uno trabaja con un subarray
	    sortarrayIzq.start();
	    sortarrayDer.start();
		//Estos join sirven para que estos threads no mueran hasta que muera el thread donde son llamados
	    sortarrayIzq.join();
	    sortarrayDer.join();

		//Se hace el merge final
	    finalMerge (sortarrayIzq.getarrayResult(), sortarrayDer.getarrayResult());
		long stopTime = System.currentTimeMillis();
	    long tiempo_con_threads = stopTime - startTime;

		System.out.println("\n");
	    System.out.println("Tiempo de ejecución para MergeSort con threads:" + (float)tiempo_con_threads/1000 + " segundos");


	    //Se ordena el arreglo sin threads
	    startTime = System.currentTimeMillis();
	    MergeSort ms = new MergeSort();
	    ms.sort(array, 0, array.length-1);
	    stopTime = System.currentTimeMillis();
	    long tiempo_sin_threads = stopTime - startTime;

	    //PRINTEAR ARREGLO ORDENADO SIN THREADS
	    System.out.println("Arreglo ordenado sin threads:");
	    for (x = 0; x < array.length; x++){
	      System.out.print(array[x]+" ");
	    }
	    System.out.print("\n");
	    System.out.println("Tiempo de ejecución para MergeSort sin threads: " + (float)tiempo_sin_threads/1000 + " segundos");

	}
}

//clase utilizada para emplear el uso de threads
class Buenardo extends Thread {
	//atributos
  	private int[] arrayResult;

  	//constructor
  	Buenardo(int[] array) {
    	arrayResult = array;
  	}

  	//getters
  	public int[] getarrayResult() {
      	return arrayResult;
  	}

  	//Funcion que retorna la mitad izquierda de un array
  	public int[] mitadIzq(int[] array) {
    	int lenIzq = array.length / 2;
    	int[] arrayIzq = new int[lenIzq];

    	for (int i = 0; i < lenIzq; i++) {
       		arrayIzq[i] = array[i];
    	}
    	return arrayIzq;
  	}
  	//Funcion que retorna la mitad derecha de un array
  	public int[] mitadDer(int[] array) {
    	int lenIzq = array.length / 2;
    	int lenDer = array.length - lenIzq;
    	int[] arrayDer = new int[lenDer];

    	for (int i = 0; i < lenDer; i++) {
       		arrayDer[i] = array[i + lenIzq];
    	}
    	return arrayDer;
  	}

  	//funcion que realiza el merge con las mitades de un arreglo
  	public void merge(int[] result, int[] arrayIzq, int[] arrayDer) {
    	int contIzq = 0;
    	int contDer = 0;

    	for (int i = 0; i < result.length; i++) {
      		if (contDer >= arrayDer.length || (contIzq < arrayIzq.length && arrayIzq[contIzq] >= arrayDer[contDer])) {
        	result[i] = arrayIzq[contIzq];
        		contIzq++;
      		}else{
        		result[i] = arrayDer[contDer];
        		contDer++;
      		}
    	}
  	}

  	//funcion que ordena usando MergeSort
  	public void mergeSort(int[] array) {
    	if (array.length > 1) {
      		int[] arrayIzq = mitadIzq(array);
      		int[] arrayDer = mitadDer(array);

      		mergeSort(arrayIzq);
      		mergeSort(arrayDer);

      		merge(array, arrayIzq, arrayDer);
    	}
  	}

  	public void run() {
    	mergeSort(arrayResult);
  	}
}
