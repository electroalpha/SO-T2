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
	}
}