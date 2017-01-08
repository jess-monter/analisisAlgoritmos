import java.util.*;


/**
*Clase Graph que representa a una gráfica.
*@args int v, número de vértices.
*@args AdjList lista de adyacencia de cada vértice en la gráfica.
*/
public class Graph {
	
	int v;
	AdjList[] array;

	/**
	*@constructor Crea la gráfica
	*@param int v Número de vértices de la gráfica.
	*
	*/
	public Graph(int v) {
		this.v = v;
		this.array = new AdjList[v];

		for (int i = 0; i < v; ++i){
			AdjList list = new AdjList();
			this.array[i] = list;
			this.array[i].head = null;
		}
	}


	/**
	* Método que agrega aristas a la gráfica.
	* @param int origen, vertice del que parte la arista.
	* @param int dest, vértice al que llega la arista
	* @param int weight, peso de la arista.
	*/
	public void agregaArista(int origen, int dest, int weight) {
		Node nuevo = new Node(dest, weight);
		nuevo.next = array[origen].head;
		array[origen].head = nuevo;
		//Como la gráfica no es dirigida, agregamos la adyacencia al otro vertice.
		nuevo = new Node(origen, weight);
		nuevo.next = array[dest].head;
		array[dest].head = nuevo;
	}


	/**
	*Método que imprime la gráfica por medio de las listas de adyacencia de cada vértice.
	*/
	public void imprimeGraph() {
		for(int i=0; i<this.v; i++){
			System.out.print("Vertice: " + i );
			Node adyacenciatmp = array[i].head;
			System.out.print(" Vertice Adyacente: "); 
			while(adyacenciatmp != null){
				System.out.print(adyacenciatmp.dest + " ");
				adyacenciatmp = adyacenciatmp.next;
			}
			System.out.println(" ");
		}
	}

	public static void main(String[] args) {

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

		graph.imprimeGraph();
	}




}