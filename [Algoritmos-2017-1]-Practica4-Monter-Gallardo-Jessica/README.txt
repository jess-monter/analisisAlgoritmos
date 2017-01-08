Monter Gallardo Jessica
305116941

Las digráficas se construyen en el método main de Dijkstra.java
Para correr el algoritmo:
En consola
	javac Dijkstra.java
	java Dijkstra




La digráfica se construye creando un objeto Graph con el constructor de la clase, cuyo parámetro será el número de vértices
de la gráfica y agregando aristas a dicho objeto con el método agregaArista cuyos parámetros
son el vértice de origen, el vértice de destino y el peso de la arista, estas aristas
se agregaran a la lista de adyacencia de cada vértice correspondiente.

Así:

		Graph graph = new Graph(5);
		graph.agregaArista(0, 4, 5);
		graph.agregaArista(0, 2, 1);
		graph.agregaArista(1, 0, 6);
		graph.agregaArista(1, 4, 8);
		graph.agregaArista(1, 3, 7);
		graph.agregaArista(2, 1, 2);
		graph.agregaArista(2, 0, 5);
		graph.agregaArista(3, 2, 4);

Nos da la digráfica:

		Vértice Origen: 0 Vértices Destino: 2 4  
		Vértice Origen: 1 Vértices Destino: 3 4 0  
		Vértice Origen: 2 Vértices Destino: 0 1  
		Vértice Origen: 3 Vértices Destino: 2  
		Vértice Origen: 4 Vértices Destino:  

Origen y Destino nos dicen de dónde parte la arista y hacia donde se dirige, respectivamente.
		
		graph.agregaArista(3, 2, 4);
		Vértice Origen: 3 Vértice Destino: 2  

Nos dice que hay una arista de esta forma: 3 ---4----> 2