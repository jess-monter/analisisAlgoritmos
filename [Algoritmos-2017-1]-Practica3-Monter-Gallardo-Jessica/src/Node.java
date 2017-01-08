/**
* Clase que representa las adyacencias de los vértices de una gráfica.
* @arg int dest Vertice al que llega la arista.
* @arg int weight Peso de la arista.
* @arg Node next Siguiente vertice adyacente.
*/

public class Node {

	int dest;
	int weight;
	Node next;


	/**
	* @constructor Método que construye el nodo.
	* @param int dest Vertice destino.
	* @param int weight Peso de la arista.
	*/
	public Node(int dest, int weight) {
		this.dest = dest;
		this.weight = weight;
		this.next = null;
	}



}