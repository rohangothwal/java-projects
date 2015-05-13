
public class LinkedList<E> {

	private class ListNode {

		private E data;
		private ListNode next;

		public ListNode() {
			this.data = null;
			this.next = null;
		}

		public ListNode(E inputData) {
			this.data = inputData;
			this.next = null;
		}

		public E getData() {
			return data;
		}

		public void setData(E data) {
			this.data = data;
		}

		public ListNode getNext() {
			return next;
		}

		public void setNext(ListNode next) {
			this.next = next;
		}
	}

	private ListNode firstNode;
	private ListNode lastNode;
	private int size;
	
	LinkedList(){
		this.firstNode = new ListNode();
		this.lastNode = new ListNode();
	}

	public void add(E inputData) {
		ListNode node = new ListNode(inputData);

		/* Make sure we cater for the case where the list is empty */
		if (this.firstNode.getData() == null) {
			this.firstNode = node;
			this.lastNode = node;
		}
		else {
			this.lastNode.setNext(node);
			this.lastNode = node;
		}

		this.size++;
	}

	public void add(E[] inputArray) {
		for (int i = 0; i < inputArray.length; i++) {
			this.add(inputArray[i]);
		}
	}

	public void remove(E inputData) {
		ListNode currentNode = this.firstNode;

		if (this.size == 0) {
			return;
		}

		boolean wasDeleted = false;

		/* Are we deleting the first node? */
		if (inputData.equals(currentNode.getData())) {

			/* Only one node in list, be careful! */
			if (currentNode.getNext() == null) {
				this.firstNode.setData(null);
				this.firstNode = new ListNode();
				this.lastNode = this.firstNode;
				this.size--;
				return;
			}

			currentNode.setData(null);
			currentNode = currentNode.getNext();
			this.firstNode = currentNode;
			this.size--;
			return;
		}

		while (true) {
			/* If end of list, stop */
			if (currentNode == null) {
				wasDeleted = false;
				break;
			}

			/* Check if the data of the next is what we're looking for */
			ListNode nextNode = currentNode.getNext();
			if (nextNode != null) {
				if (inputData.equals(nextNode.getData())) {

					/* Found the right one, loop around the node */
					ListNode nextNextNode = nextNode.getNext();
					currentNode.setNext(nextNextNode);

					nextNode = null;
					wasDeleted = true;
					break;
				}
			}

			currentNode = currentNode.getNext();
		}

		if (wasDeleted) {
			this.size--;
		}
	}

	public void remove(E[] inputArray) {
		for (int i = 0; i < inputArray.length; i++) {
			this.remove(inputArray[i]);
		}
	}

	public int size() {
		return this.size;
	}

	public String toString() {
		ListNode currentNode = this.firstNode;
		StringBuffer buffer = new StringBuffer();

		buffer.append("{");
		for (int i = 0; currentNode != null; i++) {
			if (i > 0) {
				buffer.append(",");
			}
			Object dataObject = currentNode.getData();

			buffer.append(dataObject == null ? "" : dataObject);
			currentNode = currentNode.getNext();
		}
		buffer.append("}");
		return buffer.toString();
	}

	public E elementAt(int inputPosition) {

		if (inputPosition >= this.size || inputPosition < 0) {
			return null;
		}

		ListNode currentNode = this.firstNode;

		for (int position = 0; position < inputPosition ; position++) {
			currentNode = currentNode.getNext();
		}

		return currentNode.getData();
	}

	public int indexOf(Object inputData) {
		ListNode currentNode = this.firstNode;
		int position = 0;
		boolean found = false;

		for (; ; position++) {
			if (currentNode == null) {
				break;
			}

			if (inputData.equals(currentNode.getData())) {
				found = true;
				break;
			}

			currentNode = currentNode.getNext();
		}

		if (!found) {
			position = -1;
		}

		return position;
	}


}




/*public class LinkedList<T> {
	
	class Node{
		T item;
		Node ref;
	}
	
	Node head = null;
	
	public LinkedList(){
		
	}
	
	public static LinkedList<Integer> buildOneTwoThree(){
		LinkedList<Integer> list = new LinkedList<Integer>();
		list
	}
	
	public T add(T obj){
		Node u;
		if (null==head){
			head=new Node();
			head.item = obj;
			head.ref = null;
		}else{
			u = new Node();
			if (null == head.ref){
				head.ref = u;
				u.item = obj;
				u.ref = null;
			}else{
				
			}
			
		}
		
		
	}
	
	public int length(){
		int length = 0;
		
		
		
		return length;
	}
}
*/