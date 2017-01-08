/**
*Clase que representa un heap binario cuyos elementos serán las aristas de la gráfica.
*@param int size tamaño del heap
*@param Edge[] heap Arreglo donde se guardarán los nodos del heap.
*@param int last_index Entero que indica la ultima posición ocupada en el arreglo.
*/
public class Heap{
	
	public int size;
	public Edge[] heap;
	public int last_index;

  /**
  *@constructor Construye el heap.
  *@param int size tamaño del heap.
  */
	public Heap(int size) {
		this.size = size;
		this.heap = new Edge[size];
		this.last_index = -1;
	}

	/**
	* Método que inserta un nuevo elemento al heap.
	* @param Edge arista Arista a insertar al heap.
	*/
	public void insert(Edge arista){
		if(last_index==-1){
			heap[0] = arista;
		}else{
	    heap[last_index+1] = arista;
	    int currentItem = last_index+1;
	    while( heap[getParent(currentItem)].key > heap[currentItem].key ){
	    	//System.out.println("getParent: " + getParent(currentItem));
	    	//System.out.println("currentItem: " + currentItem);
        swap(getParent(currentItem),currentItem);
        currentItem = getParent(currentItem);
	    }
	  }
	  last_index++;
	  //System.out.println("last_index: " + last_index);
	}

	/**
	* Método que regresa la posición del padre del nodo actual.
	* @param int position Posición del nodo actual
	* @return int Posición del padre del nodo actual.
	**/
	private int getParent(int position){
	  return (position-1)/2;
	}

	/**
	* Método que regresa la posición del hijo izquierdo del nodo actual.
	* @param int position Posición del nodo actual
	* @return int Posición del hijo izquierdo del nodo actual.
	**/
	private int getLeftChild(int position){
	  return (2*position)+1;
	}

	/**
	* Método que regresa la posición del hijo derecho del nodo actual.
	* @param int position Posición del nodo actual
	* @return int Posición del hijo derecho del nodo actual.
	**/
	private int getRightChild(int position){
	  return (2*position)+2;
	}

	/**
	* Método que intercambia nodos.
	* @param int position1 Posición del primer nodo.
	* @return int position2 Posición del segundo nodo.
	**/
	private void swap(int position1, int position2){
    Edge temp = heap[position1];
    heap[position1] = heap[position2];
    heap[position2] = temp;
	}

	/**
	* Método que revisa si el nodo en una posicion es o no una hoja.
	* @param int position1 Posición del nodo.
	* @return boolean, true si es hoja, false en otro caso.
	**/
	private boolean isLeaf(int position){
    if(position > size/2){
      return true;
    }
    return false;
	}

	/**
	* Método que elimina el mínimo del heap.
	* @return Edge Nodo eliminado.
	**/
	public Edge deleteMin(){
    Edge itemPopped = heap[0];
    //Edge last = new Edge(1000,1000,1000);
    heap[0] = heap[last_index];
    heapify(0);
    //heap[last_index] = last;
    last_index--;
    return itemPopped;
	}

	/**
	* Método que revisa si existe un nodo (arista) está o no en el heap.
	* @param int v, Vertice de la arista.
	* @return boolean true si está en el heap, false en otro caso.
	**/
	public boolean isInHeap(int v){
		boolean found = false;
		for(int i=0; i<=last_index; i++){
			if(heap[i].v == v){
				found = true;
			}
		}
		return found;
	}

	/**
	* Método que busca el indice de un nodo en el heap.
	* @param int v, Vertice de la arista.
	* @return int Indice del nodo, -1 si el nodo no se encuentra.
	**/
	public int findEdge(int v){
		int found = -1;
		for(int i=0; i<=last_index; i++){
			if(heap[i].v == v){
				found = i;
			}
		}
		return found;
	}

	/**
	* Método que ordena el heap para conservar la propiedad de Heap Minimo.
	* @param int v, nodo desde el que se realiza el ordenamiento.
	**/
	private void heapify(int position){
    if(isLeaf(position)){
      return;
    }

    if(getLeftChild(position) <= last_index && getRightChild(position) <= last_index){
	    if ( heap[position].key > heap[getLeftChild(position)].key || heap[position].key > heap[getRightChild(position)].key){
	      if(heap[getLeftChild(position)].key < heap[getRightChild(position)].key){
	        swap(position , getLeftChild(position));
	        heapify(getLeftChild(position));
	      }
	      else{
	        swap(position , getRightChild(position));
	        heapify(getRightChild(position));
	      }
	    }
	  }
	}

	/**
	* Método que revisa si el heap está vacío.
	* @return boolean true Si el heap esta vacío, false en otro caso.
	**/
	public boolean isEmpty(){
		return last_index==-1;
	}

	/**
	* Método que actualiza el valor del nodo en el heap.
	* @param int v, Vertice de la arista.
	* @param int key, nuevo valor.
	**/
	public void updateKey(int v, int key) {
    int i = findEdge(v);
    heap[i].key = key;
    heapify(0);
	}

	/**
	* Método que imprime el heap.
	**/
	public void printHeap(){
		for(int i=0; i<=this.last_index; i++){
			System.out.println("Vertice: " + this.heap[i].v + " KEY " + this.heap[i].key);
		}
	}


	public static void main(String[] args) {
		Edge e1 = new Edge(1,2);
		Edge e2 = new Edge(3,3);
		Edge e3 = new Edge(4,1);
		Edge e4 = new Edge(5,1);
		Edge e5 = new Edge(7,6);
	
		Heap h = new Heap(5);

		System.out.println("EmptyHeap?: " + h.isEmpty());

		h.insert(e1);
		h.insert(e2);
		h.insert(e3);
		h.insert(e4);
		h.insert(e5);
		h.printHeap();


		System.out.println("Is 19 in heap?: " + h.isInHeap(19));
		System.out.println("EmptyHeap?: " + h.isEmpty());
		System.out.println("After deleteMin");

		h.deleteMin();
		h.deleteMin();
  	h.deleteMin();
		h.printHeap();

	}


}