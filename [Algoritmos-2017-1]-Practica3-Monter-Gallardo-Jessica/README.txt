La gráfica se construye creando un objeto Graph con el constructor de la clase, cuyo parámetro será el número de vértices
de la gráfica y agregando aristas a dicho objeto con el método agregaArista cuyos parámetros
son el vértice de origen, el vértice de destino y el peso de la arista, estas aristas
se agregaran a la lista de adyacencia de cada vértice correspondiente.
Como la gráfica no es dirigida, las aristas 0---->1 y 1---->0 deben ser agregadas según la
lista de adyacencia del vértice 0 y el vértice 1, lo que hace el método agregaArista automáticamente.

Así:

		Graph graph = new Graph(9);
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

Nos da la gráfica:

		Vertice: 0 Vertice Adyacente: 7 1  
		Vertice: 1 Vertice Adyacente: 7 2 0  
		Vertice: 2 Vertice Adyacente: 5 8 3 1  
		Vertice: 3 Vertice Adyacente: 5 4 2  
		Vertice: 4 Vertice Adyacente: 5 3  
		Vertice: 5 Vertice Adyacente: 6 4 3 2  
		Vertice: 6 Vertice Adyacente: 8 7 5  
		Vertice: 7 Vertice Adyacente: 8 6 1 0  
		Vertice: 8 Vertice Adyacente: 7 6 2  

Origen y Destino nos dicen de dónde parte la arista y hacia donde se dirige, respectivamente.
		
		Vertice: 0 Vertice Adyacente: 7 1  

Nos dice que hay una arista de esta forma: 0 ----8---- 7
y una arista de la forma                   1---4-----1