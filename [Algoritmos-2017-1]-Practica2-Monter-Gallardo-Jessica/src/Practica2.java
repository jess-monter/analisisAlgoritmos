public class Practica2{

/*---implementar metodos despues de esta linea---*/
	

	/**
	 * Método que llama al método recursivo de quickSort.
	 * @param arr Arreglo de enteros.
	 */
	public static int[] quickSort(int[] arr){
		return quickSortRec(arr, 0, arr.length-1);
	}

	/**
	 * Método recursivo quickSort que ordena un arreglo de números.
	 * @param arr Arreglo de enteros.
	 * @param izquierda indice inicial.
	 * @param derecha indice final.
	 */
	public static int[] quickSortRec(int arr[], int izquierda, int derecha) {
	  int indice = particionArreglo(arr, izquierda, derecha);
	  if (izquierda < indice - 1){
	    quickSortRec(arr, izquierda, indice - 1);
	  }
	  if (indice < derecha) {
	    quickSortRec(arr, indice, derecha);
	  }
	  return arr;
	}

	/**
	 * Método que particiona el arreglo y hace las comparaciones para ordenar los elementos.
	 * @param arr Arreglo de enteros.
	 * @param izquierda indice inicial.
	 * @param derecha indice final.
	 */
	public static int particionArreglo(int arr[], int izquierda, int derecha) {
	  int i = izquierda;
	  int j = derecha;
	  int tmp;
	  int pivot = arr[(izquierda + derecha) / 2];
	 
	  while (i <= j) {
	    while (arr[i] < pivot){
	      i++;
	    }
	    while (arr[j] > pivot){
	      j--;
	    }
	    if (i <= j) {
	      swap(arr, i, j);
	      i++;
	      j--;
	    }
	  }
	  return i;
	}
	 

	/**
	 * Método que llama al método recursivo de mergeSort.
	 * @param arr Arreglo de enteros.
	 */
	public static int[] mergeSort(int[] arr){
		return mergeSortRec(arr, 0, arr.length-1);
	}

	/**
	 * Método recursivo que ordena un arreglo de números de menor a mayor.
	 * @param arr Arreglo de enteros.
	 * @param izquierda indice inicial.
	 * @param derecha indice final.
	 */
	public static int[] mergeSortRec(int[] arr, int izquierda, int derecha) {
		if(izquierda < derecha) {
			int centro = izquierda + (derecha-izquierda)/2;
			mergeSortRec(arr, izquierda, centro);
			mergeSortRec(arr, centro+1, derecha);
			merge(arr, izquierda, centro, derecha);
		}

		return arr;

	}

	/**
	 * Método que ordena y mezcla las partes del arreglo.
	 * @param arr Arreglo de enteros.
	 * @param izquierda indice inicial.
	 * @param centro indice del centro.
	 * @param derecha indice final.
	 */
	public static int[] merge(int[] arr, int izquierda, int centro, int derecha) {
		int[] tmp = new int[arr.length];

    for (int i = izquierda; i <= derecha; i++) {
      tmp[i] = arr[i];
    }

    int i = izquierda;
    int j = centro + 1;
    int k = izquierda;

    while (i <= centro && j <= derecha) {
      if (tmp[i] <= tmp[j]) {
        arr[k] = tmp[i];
        i++;
      } else {
        arr[k] = tmp[j];
        j++;
      }
      k++;
    }
    while (i <= centro) {
      arr[k] = tmp[i];
      k++;
      i++;
    }
    return arr;
  }

	/**
	 * Método que ordena un arreglo de numeros.
	 * @param arr Arreglo de enteros.
	 */
	public static int[] bubbleSort(int[] arr){
		int i=1;
		boolean ordenado = false;
		while(i < arr.length && !ordenado){
			ordenado = true;
			for(int j=0; j<arr.length-i; j++) {
				if(arr[j]>arr[j+1]){
					ordenado = false;
					swap(arr,j,j+1);
				}
			}
			i=i+1;
		}
		return arr;
	}

	/**
	 * Método que cambia el elemento de la posición i a la posición j, y el elemento
	 * de la posición j a la posición i.
	 * @param arr Arreglo de enteros.
	 * @param i indice i en el arreglo.
	 * @param j indice j en el arreglo.
	 */
	public static int[] swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
		return arr;
	}

	/**
	 * Método que genera arreglos correspondientes al peor caso de quickSort.
	 * @param tam tamaño del arreglo a crear
	 * El arreglo creado será de la forma 1 2 1 2... así el pivote elegido será siempre el menor o el mayor
	 * elemento en el arreglo por lo que se realizarán más comparaciones.
	 */
	public static int[] peorCasoQuickSort(int tam){
		int[] c = new int[tam];
		int tmp = tam/2;
		for(int i=0; i<tam; i++) {
			while(tmp > 0) {
				c[tmp] = 4;
				tmp = tmp/2;
			}
			if(i!=tmp) {
				c[i] = 1;
			}
		}
		return c;
	}

  /**
	 * Método que genera arreglos correspondientes al peor caso de mergeSort.
	 * @param tam tamaño del arreglo a crear
	 * El arreglo creado parte de un arreglo ordenado que se va desordenando segun mergeSort en reversa, de
	 * tal forma que en cada parte de merge, las comparaciones y swaps se maximizan.
	 */
	public static int[] peorCasoMergeSort(int tam){
		int[] c = new int[tam];
		for(int i=0; i<tam; i++) {
			c[i] = i;
		}
		c = generaPeorCaso(c, 0, tam-1);
		return c;
	}

	/**
	 * Método que mezcla los arreglos del peor caso para general uno sólo.
	 * @param arr Arreglo de enteros.
	 * @param izquierda Arreglo izquierdo.
	 * @param l indice izquierdo
	 * @param m indice central
	 * @param r indice derecho
	 * @param derecha Arreglo derecho.
	 */
	public static void pega(int[] arr, int[] izquierda, int[] derecha, int l, int m, int r) {
	  int i;
	  for (i = 0; i <= m - l; i++){
	    arr[i] = izquierda[i];
	  }

	  for (int j = 0; j < r - m; j++){
	    arr[i + j] = derecha[j];
	  }
	}
 
 	/**
	 * Método que separa los arreglos para generar en cada mitad el peor caso de mergeSort.
	 * @param arr Arreglo de enteros.
	 * @param izquierda Arreglo izquierdo.
	 * @param l indice izquierdo
	 * @param m indice central
	 * @param r indice derecho
	 * @param derecha Arreglo derecho.
	 */
	public static void separa(int[] arr, int[] izquierda, int[] derecha, int l, int m, int r) {
	  for (int i = 0; i <= m - l; i++){
	    izquierda[i] = arr[i * 2];
	  }

	  for (int i = 0; i < r - m; i++){
	    derecha[i] = arr[i * 2 + 1];
	  }
	}


	/**
	 * Método que genera el peor caso de mergeSort.
	 * @param arr Arreglo de enteros.
	 * @param l indice izquierdo
	 * @param r indice derecho
  */
	public static int[] generaPeorCaso(int[] arr, int l, int r){
	  if (l < r) {
	    int m = l + (r - l) / 2;

	    int[] izquierda = new int[m - l + 1];
	    int[] derecha = new int[r - m];

	    separa(arr, izquierda, derecha, l, m, r);

	    generaPeorCaso(izquierda, l, m);
	    generaPeorCaso(derecha, m + 1, r);

	    pega(arr, izquierda, derecha, l, m, r);
	  }
	  return arr;
	}

	/**
	 * Método que genera el peor caso de BubbleSort.
	 * @param tam tamaño del arreglo.
	 * Genera un arreglo ordenado de mayor a menor incrementando las comparaciones y swaps de bubbleSort.
  */
	public static int[] peorCasoBubbleSort(int tam){
		int[] c = new int[tam];
		int tmp = tam;
		for(int i=0; i<tam; i++) {
			tmp = tmp-1;
			c[i] = tmp;
		}
		return c;
	}

	/**
	 * Método que genera el mejor caso de QuickSort.
	 * @param tam tamaño del arreglo.
	 * Genera un arreglo ordenado de menor a mayor ya que dada nuestra elección de pivote (n/2) siempre se eligirá la media de cada subarreglo.
  */
	public static int[] mejorCasoQuickSort(int tam){
		int[] c = new int[tam];
		for(int i=0; i<tam; i++) {
			c[i] = i;
		}
		return c;
	}

	/**
	 * Método que genera el mejor caso de MergeSort.
	 * @param tam tamaño del arreglo.
	 * Genera un arreglo ordenado de menor a mayor ya que no se realizarán swaps en cada parte del arreglo.
  */
	public static int[] mejorCasoMergeSort(int tam){
		int[] c = new int[tam];
		for(int i=0; i<tam; i++) {
			c[i] = i;
		}
		return c;
	}


	/**
	 * Método que genera el mejor caso de BubbleSort.
	 * @param tam tamaño del arreglo.
	 * Genera un arreglo ordenado de menor a mayor ya que no se realizarán swaps en cada parte del arreglo.
  */
	public static int[] mejorCasoBubbleSort(int tam){
		int[] c = new int[tam];
		for(int i=0; i<tam; i++) {
			c[i] = i;
		}
		return c;
	}
/*---implementar metodos antes de esta linea---*/

	public static void main(String[] args){

		long tiempoInicio, tiempoFin;
		int[] tamanos = {10, 100, 200, 500, 1000, 2000, 5000};
		System.out.println("Longitud del arreglo:");
		for (int i = 0; i < tamanos.length; ++i){
			System.out.print("\t"+(tamanos[i]));
		}
		System.out.println("\n\nTiempos para QuickSort:\nPeor Caso:");
		for (int i = 0; i < tamanos.length; ++i){
			int[] arr = peorCasoQuickSort(tamanos[i]);
			tiempoInicio = System.currentTimeMillis();
			arr = quickSort(arr);
			tiempoFin = System.currentTimeMillis();
			System.out.print("\t"+(tiempoFin - tiempoInicio));
		}
		System.out.println("\nMejor Caso:");
		for (int i = 0; i < tamanos.length; ++i){
			int[] arr = mejorCasoQuickSort(tamanos[i]);
			tiempoInicio = System.currentTimeMillis();
			arr = quickSort(arr);
			tiempoFin = System.currentTimeMillis();
			System.out.print("\t"+(tiempoFin - tiempoInicio));
		}

		System.out.println("\n\nTiempos para MergeSort:\nPeor Caso:");
		for (int i = 0; i < tamanos.length; ++i){
			int[] arr = peorCasoMergeSort(tamanos[i]);
			tiempoInicio = System.currentTimeMillis();
			arr = quickSort(arr);
			tiempoFin = System.currentTimeMillis();
			System.out.print("\t"+(tiempoFin - tiempoInicio));
		}
		System.out.println("\nMejor Caso:");
		for (int i = 0; i < tamanos.length; ++i){
			int[] arr = mejorCasoMergeSort(tamanos[i]);
			tiempoInicio = System.currentTimeMillis();
			arr = quickSort(arr);
			tiempoFin = System.currentTimeMillis();
			System.out.print("\t"+(tiempoFin - tiempoInicio));
		}

		System.out.println("\n\nTiempos para BubbleSort:\nPeor Caso:");
		for (int i = 0; i < tamanos.length; ++i){
			int[] arr = peorCasoBubbleSort(tamanos[i]);
			tiempoInicio = System.currentTimeMillis();
			arr = quickSort(arr);
			tiempoFin = System.currentTimeMillis();
			System.out.print("\t"+(tiempoFin - tiempoInicio));
		}
		System.out.println("\nMejor Caso:");
		for (int i = 0; i < tamanos.length; ++i){
			int[] arr = mejorCasoBubbleSort(tamanos[i]);
			tiempoInicio = System.currentTimeMillis();
			arr = quickSort(arr);
			tiempoFin = System.currentTimeMillis();
			System.out.print("\t"+(tiempoFin - tiempoInicio));
		}
		System.out.println("");
	}
}

