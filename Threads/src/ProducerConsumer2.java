import java.util.LinkedList;
import java.util.Random;


public class ProducerConsumer2 {
	
	private LinkedList<Integer> lList = new LinkedList<Integer>();
	private static final int LIMIT = 10;
	private Object lock = new Object();

	public void produce() throws InterruptedException{
		int value = 0;
		
		while(true){
			synchronized (lock) {
				
				if (lList.size() == LIMIT){
					lock.wait();
				}
				lList.add(value++);
				lock.notify();
			}
		}

	}

	public void consume() throws InterruptedException{
		
		Random random = new Random();
		
		while(true){
			synchronized (lock) {
				
				if (lList.size() == 0){
					lock.wait();
				}
				
				System.out.print("List before: "+lList.size());
				int value = lList.removeFirst();
				System.out.println("List after removing "+value+": "+lList.size());
				lock.notify();
			}
			Thread.sleep(random.nextInt(1000));
		}

	}

}
