public class MergeSort
{
  // Hace merge a dos subarreglos del arreglo arr[].
  // Primer subarreglo es arr[p..m]
  // Segundo subarreglo seria arr[m+1..u]
  void merge(int arr[], int p, int m, int u)
  {
    // Encuentra los tamaños de los 2 subarreglos que se les hara merge
    int n1 = m - p + 1;
    int n2 = u - m;

    /* Crea 2 arreglos temporales */
    int L[] = new int [n1];
    int R[] = new int [n2];

    /*Copia la info a los arreglos temporales*/
    for (int i=0; i<n1; ++i)
      L[i] = arr[p + i];
    for (int j=0; j<n2; ++j)
      R[j] = arr[m + 1+ j];

    /* Hace merge a los arreglos temporales */
    // Indices iniciales de los subarreglos
    int i = 0, j = 0;

    int k = p;
    while (i < n1 && j < n2)
    {
      if (L[i] >= R[j])
      {
        arr[k] = L[i];
        i++;
      }
      else
      {
        arr[k] = R[j];
        j++;
      }
      k++;
    }

    /*Copia elementos si es que quedan de L[]  */
    while (i < n1)
    {
      arr[k] = L[i];
      i++;
      k++;
    }

    /* Copia elementos ,si es que quedan, de R[] */
    while (j < n2)
    {
      arr[k] = R[j];
      j++;
      k++;
    }
  }


  // Funcion principal que ordena el arreglo arr[l..r] usando merge()
  void sort(int arr[], int l, int r)
  {
    if (l < r)
    {
      // Encuentra el punto medio
      int m = (l+r)/2;

      // Ordena la primera y la segunda mitad
      sort(arr, l, m);
      sort(arr , m+1, r);

      // Hace merge a las mitades ordenadas
      merge(arr, l, m, r);
    }
  }
}
