import java.util.*;

public class BinaryHeap {
	
	public Node head;

	public static class Node{
		public int val;
		public int peso;
		public Node next;

		public Node(int num, int peso){
			this.peso = peso;
			this.val = num;
			this.next = null;
		}

		public void imprimeLista(Node head) {
			Node tmp = head;
			while(tmp != null) {
				System.out.println("Nodo: " + tmp.val);
				tmp = tmp.next;
			}
		}
	
	}

	public static Node addEdge(Node head, int num, int peso) {
		Node nuevo = new Node(num, peso);
		nuevo.next = head;
		head = nuevo;
		return nuevo;
	}

	public static class Edge{
		public int u, v;
		public int peso;
	
		public Edge(int nodo1, int nodo2, int peso){
			this.u = nodo1;
			this.v = nodo2;
			this.peso = peso;
		}
	}

	public static void enqueue(Edge[] heap, int size, Edge value) {
    heap[size] = value;
    int i = size;
    Edge tmp;

    while (i >= 1) {
      if (heap[i / 2].peso > heap[i].peso) {
        tmp = heap[i / 2];
        heap[i / 2] = heap[i];
        heap[i] = tmp;
        i = i / 2;
      } else {
        break;
      }
    }
	}

	public static void heapify(Edge[] heap, int size, int index)	{
    int i = index;
    Edge tmp;
 
    while ((2 * i) < size) {
      if ((2 * i) + 1 >= size) {
        if (heap[i].peso > heap[2 * i].peso) {
          tmp = heap[i];
          heap[i] = heap[2 * i];
          heap[2 * i] = tmp;

          break;
        }
      }

      if (heap[i].peso > heap[2 * i].peso || heap[i].peso > heap[2 * i + 1].peso) {
        if (heap[2 * i].peso <= heap[(2 * i) + 1].peso) {
          tmp = heap[2 * i];
          heap[2 * i] = heap[i];
          heap[i] = tmp;

          i = 2 * i;
        } else if (heap[2 * i].peso > heap[(2 * i) + 1].peso) {
          tmp = heap[(2 * i) + 1];
          heap[(2 * i) + 1] = heap[i];
          heap[i] = tmp;

          i = (2 * i) + 1;
        }
      } else {
        break;
      }
    }
	}

	// Deletes and entry in the Priority Queue
	public static void deleteNode(Edge[] heap, int size, int index)	{
    
    System.out.println("indice del heap: " + index);
    System.out.println("tamanio del heap: " + heap.length + " size: " + size);
    Edge tmp = heap[index];
    heap[index] = heap[size - 1];
    heap[size - 1] = tmp;
    int i = index;
    --size;
    heapify(heap, size, i);
	}

		// Returns the element with
	// Minimum Priority and deletes it
	public static Edge extractMin(Edge heap[], int size) {
    Edge min = heap[0];
    deleteNode(heap, size, 0);
    return min;
	}

	public static Node[] prim(Node lista_adyacencias[], int vertices, int aristas, int verticeInicial, Node mst[])	{
    int actual = verticeInicial;
    int newVertex;
    boolean[] visited = new boolean[vertices+1];
    Node tmp;
    Edge var = new Edge(0,0,0);
    Edge[] priorityQueue = new Edge[2 * aristas];
    int queueSize = 0;
 
    int i;
 
 		//Inicialización del algoritmo, marca a todos lo vértices como no visitados.
    for (i = 0; i <= vertices; ++i) {
      visited[i] = false;
    }
 
    i = 0;
 
    while (i < vertices) {
      if (!visited[actual]) {            // If actual node is not visited
        visited[actual] = true;        // Mark it visited
        tmp = lista_adyacencias[actual];

        while (tmp != null) {
          var.u = actual;
          var.v = tmp.val;
          var.peso = tmp.peso;

          if (!visited[var.v]) {
            // If the edge leads to an un-visited
            // vertex only then enqueue it
            enqueue(priorityQueue, queueSize, var);
            ++queueSize;
          }

          tmp = tmp.next;
        }

        var = extractMin(priorityQueue, queueSize);     // The greedy choice
        --queueSize;

        newVertex = var.v;
        actual = var.u;

        if (!visited[newVertex]) {
          // If it leads to an un-visited vertex, add it to mst
          mst[actual] = addEdge(mst[actual], newVertex, var.peso);
          mst[newVertex] = addEdge(mst[newVertex], actual, var.peso);
        }

        actual = newVertex;
        ++i;
      } else {

        var = extractMin(priorityQueue, queueSize);
        --queueSize;

        newVertex = var.v;
        actual = var.u;

        if (!visited[newVertex]) {
          mst[actual] = addEdge(mst[actual], newVertex, var.peso);
          mst[newVertex] = addEdge(mst[newVertex], actual, var.peso);
        }

        actual = newVertex;
      }
    }
		return mst;
	}


  public static void main(String[] args) {
		
		// int vertices, aristas, i, j, v1, v2, peso;
 	// 	Scanner in = new Scanner(System.in);

  //   System.out.println("Ingrese el numero de Vertices: ");
  //   vertices = in.nextInt();
 
  //   System.out.println("Ingrese el numero de Aristas: ");
  //   aristas = in.nextInt();
 
  //   Node[] lista_adyacencias = new Node[vertices+1];
  //   Node[] mst = new Node[vertices+1];
 
  //   for (i = 0; i <= vertices; ++i) {
  //     lista_adyacencias[i] = null;
  //     mst[i] = null;
  //   }
 
  //   for (i = 1; i <= aristas; ++i) {
  // 		System.out.println("Introduzca el vertice v1:");
  // 		v1 = in.nextInt();
  // 		System.out.println("Introduzca el siguiente vertice v2:");
  // 		v2 = in.nextInt();
  // 		System.out.println("Introduzca el peso de la arista entre los vertices v1 y v2:");
  // 		peso = in.nextInt();
  //     lista_adyacencias[v1] = addEdge(lista_adyacencias[v1], v2, peso);       //Adding edge v1 ---W---> v2
  //     lista_adyacencias[v2] = addEdge(lista_adyacencias[v2], v1, peso);       //Adding edge v2 ---W---> v1
  //   }

  //       // Printing Adjacency List
  //   System.out.println("Lista de adyacencias:");
  //   for (i = 1; i <= vertices; ++i) {
  //     System.out.println("lista_adyacencias[" + "vertice: " + i + "]");
  //     Node tmp = lista_adyacencias[i];

  //     while (tmp != null) {
  //       System.out.println("Valor: " + tmp.val);
  //       tmp = tmp.next;
  //     }
  //   }
 
  //   int verticeInicial;
 
  //   System.out.println("Ingrese el vertice inicial");
  //   verticeInicial = in.nextInt();
  //   mst = prim(lista_adyacencias, vertices, aristas, verticeInicial, mst);
 
  //   // Printing MST
  //   System.out.println("Arbol de peso minimo");
  //   for (i = 1; i <= vertices; ++i) {
  //       System.out.println("MST["+ i +"]");
  //       Node tmp = mst[i];
 
  //       while (tmp != null) {
  //         System.out.println(tmp.val);
  //         tmp = tmp.next;
  //       }
 
  //       System.out.println("NULL");
  //   }

  		int enteroPrueba = 3;

  		Edge heap[] = new Edge[3];
  		Edge e1 = new Edge(0,1,3);
  		Edge e2 = new Edge(1,2,4);

			enqueue(heap, 1, e1);
			enqueue(heap, 2, e2);

			for(int i=0; i<2; i++){
				System.out.println("Heap " + i + "V1: " + heap[i].u + "V2: " + heap[i].v);
			}






	}

}



