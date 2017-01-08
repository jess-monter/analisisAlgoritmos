public class Prim{
	
	int[] mst;
 
 
 /**
	* Método que aplica el algoritmo de Prim.
	* @param Graph graph Gráfica a la que se le aplicará el algoritmo.
	* @return int[] mst Arreglo que representa al árbol generador de peso mínimo.
	*/
	public int[] primAlgorithm(Graph graph) {
		int vertex = graph.v;
		//mst Arreglo donde se guardarán los vértices del MST.
		mst = new int[vertex];
		//key Arreglo donde se guardarán los valores correspondientes 
		//a cada vértice segun el peso calculado de las aristas.
		int[] key = new int[vertex];	

		//Heap Binario Mínimo que guardará los vértices y sus valores correspondientes al correr el algoritmo.
		Heap heapBinario = new Heap(vertex);

		// Inicialización.
		for (int v = 0; v < vertex; v++)		{
			mst[v] = 0;
			//Asignamos el valor máximo a cada vértice.
			key[v] = Integer.MAX_VALUE;
			if(v==0){
				//Iniciamos en el vértice 0 por lo que asignamos 0 a su valor,
				//para que sea extraído del Heap en la primera iteración.
				heapBinario.insert(new Edge(0,0));
				key[0] = 0;
			}else{
				heapBinario.insert(new Edge(v, key[v]));	
			}
		}

		while (!heapBinario.isEmpty()) {
			Edge edge = heapBinario.deleteMin();
  		int u = edge.v;
			Node adyacenteActual = graph.array[u].head;
			
			//Recorremos la lista de adyacencias del vertice actual para actualizar su valor en el heap.
			while (adyacenteActual != null) {
				int v = adyacenteActual.dest;
				if (heapBinario.isInHeap(v) && (adyacenteActual.weight < key[v])) {
					//Si v no está en el MST y el peso del vértice adyacente es menor que el valor actual
					//actualizamos su valor y lo agregamos al MST.
					key[v] = adyacenteActual.weight;
					mst[v] = u;
				  heapBinario.updateKey(v, key[v]);
				}
				adyacenteActual = adyacenteActual.next;
			}

		}
		return mst;
	}

	/**
	* Método que imprime el árbol generador de peso mínimo.
	*/
	public void printMST() {
		System.out.println("Las aristas del árbol generador de peso mínimo de la gráfica son: ");
		for (int i = 1; i < mst.length; ++i){
			System.out.println("V1: " + i + " - " + " V2: " + mst[i]);
		}
	}


	public static void main(String[] args) {
		Prim prim = new Prim();
		int v = 9;
		Graph graph = new Graph(v);
		graph.agregaArista(0, 1, 4);
		graph.agregaArista(0, 7, 8);
		graph.agregaArista(1, 2, 8);
		graph.agregaArista(1, 7, 11);
		graph.agregaArista(2, 3, 7);
		graph.agregaArista(2, 8, 2);
		graph.agregaArista(2, 5, 4);
		graph.agregaArista(3, 4, 9);
		graph.agregaArista(3, 5, 14);
		graph.agregaArista(4, 5, 10);
		graph.agregaArista(5, 6, 2);
		graph.agregaArista(6, 7, 1);
		graph.agregaArista(6, 8, 6);
		graph.agregaArista(7, 8, 7);

		prim.primAlgorithm(graph);

		prim.printMST();

  }


}