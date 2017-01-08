public class Dijkstra{

	int[] arbol;

	public int[] dijkstra(Graph graph) {

		int vertex = graph.v;
		//arbol Arreglo donde se guardarán los vértices del arbol.
		arbol = new int[vertex];
		//key Arreglo donde se guardarán los valores correspondientes 
		//a cada vértice segun el peso calculado de las aristas.
		int[] key = new int[vertex];	

		//Heap Binario Mínimo que guardará los vértices y sus valores correspondientes al correr el algoritmo.
		Heap heapBinario = new Heap(vertex);

		// Inicialización.
		for (int v = 0; v < vertex; v++){
			arbol[v] = 0;
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
	
		//Creamos la cola binomial y agregamos el heap.
		BQueue q = new BQueue();
		BQNode n = new BQNode();
		n.root = heapBinario;
		q.insert(n);

		while(!q.extractMin().root.isEmpty()){
			Edge edge = heapBinario.deleteMin();
  		int u = edge.v;
			Node adyacenteActual = graph.array[u].head;
			
			//Recorremos la lista de adyacencias del vertice actual para actualizar su valor en el heap.
			while (adyacenteActual != null) {
				int v = adyacenteActual.dest;
				if (q.extractMin().root.isInHeap(v) && (adyacenteActual.weight + edge.key < key[v])) {
					//Si v no está en el arbol y el peso del vértice adyacente más la etiqueta es menor que el valor actual
					//actualizamos su valor y lo agregamos al arbol.
					key[v] = adyacenteActual.weight+edge.key;
					arbol[v] = u;
					q.extractMin().root.updateKey(v,key[v]);
				}
				adyacenteActual = adyacenteActual.next;
			}

		}
		return arbol;

	}


	/**
	* Método que imprime el árbol de rutas más cortas.
	*/
	public void printTree() {
		System.out.println("Las aristas del árbol de rutas más cortas de la gráfica son: ");
		for (int i = 1; i < arbol.length; ++i){
			System.out.println("Vértice Origen: " + arbol[i] + " -> " + i + " :Vértice Destino");
		}
	}


	public static void main(String[] args){
		Dijkstra d = new Dijkstra();
		Graph graph = new Graph(5);
		graph.agregaArista(0, 4, 5);
		graph.agregaArista(0, 2, 1);
		graph.agregaArista(1, 0, 6);
		graph.agregaArista(1, 4, 8);
		graph.agregaArista(1, 3, 7);
		graph.agregaArista(2, 1, 2);
		graph.agregaArista(2, 0, 5);
		graph.agregaArista(3, 2, 4);
		
		System.out.println("Digráfica: ");
		graph.imprimeGraph();
		
		d.dijkstra(graph);
		d.printTree();

		// Graph graph2 = new Graph(6);
		// graph2.agregaArista(0, 1, 2);
		// graph2.agregaArista(0, 2, 1);
		// graph2.agregaArista(0, 3, 5);
		// graph2.agregaArista(1, 5, 1);
		// graph2.agregaArista(1, 3, 2);
		// graph2.agregaArista(2, 3, 1);
		// graph2.agregaArista(3, 5, 3);
		// graph2.agregaArista(3, 4, 2);
		// graph2.agregaArista(4, 2, 4);

		// d.dijkstra(graph2);
		// d.printMST();

	}




}