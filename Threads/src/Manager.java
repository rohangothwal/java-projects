class ThreadA extends Thread{
	public void run(){
		for (int i=0;i<10;i++){
			System.out.println(i);
		}
	}
	
}

class ThreadB extends Thread{
	public void run(){
		for (int i=11;i<20;i++){
			System.out.println(i);
		}
	}
	
}

public class Manager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*Thread t1 = new ThreadA();
		t1.start();
		Thread t2 = new ThreadB();
		t2.start();*/
		Thread t3 = new B();
		t3.start();
		//t2.setDaemon(true);
		System.out.println("done");
		/*for (int i=21;i<30;i++){
			System.out.println(i);
		}*/
	}
}

class B extends Thread{
	public void run(){
		for (int i=0;i<10;i++){
			System.out.println(i);
		}
	}
}
