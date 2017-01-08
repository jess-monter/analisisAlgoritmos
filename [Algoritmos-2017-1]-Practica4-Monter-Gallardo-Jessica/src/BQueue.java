public class BQueue{
	
	BQNode head;
	int tam;

	public BQueue(){
		this.head = null;
		this.tam = 0;
	}


	public void insert(BQNode h){
		if(tam == 0){
			head = h;
			head.next = null;
		}else{
			BQNode tmp = head;
			head = h;
			head.next = tmp;
			order();
		}
		tam++;
	}

	public BQNode extractMin(){
		BQNode tmp = head;
		head.next = head;
		tam--;
		return tmp;
	}

	public void order(){
		BQNode tmp = head;
		BQNode tmp2 = head;
		while(tmp.root.heap[0].key>head.next.root.heap[0].key && tmp.next != null){
			tmp2 = head;
			head = head.next;
			head.next = tmp2;
			tmp = head.next;
		}
	}


	public static void main(String[] args){
		BQueue q = new BQueue();
		Heap h1 = new Heap(2);
		Heap h2 = new Heap(3);
		BQNode n1 = new BQNode();
		BQNode n2 = new BQNode();
		BQNode n3 = new BQNode();
		
		Edge e1 = new Edge(1,2);
		Edge e2 = new Edge(3,3);
		Edge e3 = new Edge(4,1);
		Edge e4 = new Edge(5,1);
		Edge e5 = new Edge(7,6);
		
		h1.insert(e1);
		h1.insert(e2);

		h2.insert(e3);
		h2.insert(e4);
		h2.insert(e5);

		n2.root = h2;
		n1.root = h1;
		q.insert(n1);
		q.insert(n2);

		n3 = q.extractMin();

		n3.root.printHeap();

		//System.out.println(q.head.root.heap[0].key);
		//System.out.println(q.head.next.root.heap[0].key);

	}

}