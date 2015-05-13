
public class Main {

	
	
	public static void main(String[] args) {
		
		final ProducerConsumer2 processor = new ProducerConsumer2();
		
		Thread T1 = new Thread(new Runnable(){
			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread T2 = new Thread(new Runnable(){
			public void run() {
				try {
					processor.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		T1.start();
		T2.start();
	}

}
