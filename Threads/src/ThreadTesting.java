
public class ThreadTesting {
	
	public static void main(String args[]){
		
		
		Thread obj = new Thread(new Threads());
		
		obj.start();
		obj.run();
		
	}
	
	static class Threads implements Runnable{
		
		public void run(){
			
		}
		
	}

}
