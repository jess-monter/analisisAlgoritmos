/**
* Clase que representa una arista de una gráfica.
* @arg int v, Vertice de  partida.
* @arg int key, Peso de la arista.
*/
public class Edge{

	int v;
	int key;

	/**
	*@constructor Construye una arista.
	*@param int v Vértice inicial.
	*@param int key Peso de la arista.
	*/
	public Edge(int v, int key){
		this.v = v;
		this.key = key;
	}
}