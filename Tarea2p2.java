import java.util.Random;

public class Tarea2p2{
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

	    //Se ordena el arreglo sin threads
	    long startTime = System.currentTimeMillis();
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