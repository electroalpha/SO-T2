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

	public static void main(String[] args){

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
	    finalMerge (sortarrayIzq.getSortedArray(), sortarrayDer.getSortedArray());
		long stopTime = System.currentTimeMillis();
	    long tiempo_con_threads = stopTime - startTime;

		System.out.println("\n");
	    System.out.println("Tiempo de ejecución para MergeSort con threads:" + (float)tiempo_con_threads/1000 + " segundos");


	    //Se ordena el arreglo sin threads
	    startTime = System.currentTimeMillis();
	    MergeSort ms = new MergeSort();
	    ms.sort(array, 0, array.length-1);
	    long stopTime = System.currentTimeMillis();
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
