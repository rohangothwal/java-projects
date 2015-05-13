import java.util.Random;

public class LinkedListLopp {
	
	private static class Node{
		public int data;
		public Node reference;
	}
	
	private static Node firstNode = new Node();
	
	private static int LIST_SIZE = 100;
	
	public static void generateListWithLoop(){
		Random random = new Random();
		Node nodeForCycle = null;
		int nodeForCycleNumber = random.nextInt(LIST_SIZE);
		Node previousNode = firstNode;
		firstNode.data = 0;
		for (int i=0;i<LIST_SIZE;i++){
			Node node = new Node();
			previousNode.reference = node;
			node.data = i+1;
			previousNode = node;
			
			if (i==nodeForCycleNumber){
				nodeForCycle = node;
			}
			if (i==99){
				node.reference = nodeForCycle;
			}
		}
	}
	
	public static void generateListWithoutLoop(){
		Random random = new Random();
		Node previousNode = firstNode;
		firstNode.data = random.nextInt(1000);
		for (int i=0;i<LIST_SIZE;i++){
			Node node = new Node();
			previousNode.reference = node;
			node.data = random.nextInt(1000);
			previousNode = node;
		}
	}
	
	public static void printList(Node firstNode){
		Node tempNode = firstNode;
		
		while(tempNode.reference != null){
			System.out.println(tempNode.data);
			tempNode = tempNode.reference;
		}
	}
	
	//it is presumed that all the elements of the list are unique
	public static boolean findLoop(Node firstNode){
		Node nodeForIteration = firstNode;
		Node tempNode = null;
		Node nextNode = null;
		
		
		while(nodeForIteration.reference != null){
			
			if (nodeForIteration == firstNode){
				nextNode = nodeForIteration.reference;
				nodeForIteration.reference = null;
			}
			tempNode = nextNode.reference;
			nextNode.reference = nodeForIteration;
			nodeForIteration = nextNode;
			nextNode = tempNode;
			
			if (tempNode == null){
				if (nodeForIteration.data == firstNode.data)
					return true;
				else
					return false;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		
		generateListWithLoop();
		//generateListWithoutLoop();
		//printList(firstNode);
		
		System.out.println(findLoop(firstNode));
		
	}

}
