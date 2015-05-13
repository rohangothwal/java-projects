import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileThread implements Runnable{

	static List<String> list = new ArrayList<String>();
	
	
	
	public void run(){
		// TODO Auto-generated method stub
		try {
			BufferedReader in = new BufferedReader(new FileReader("c:/sum.txt"));
			String c;
			int i = 1;
			while ((c=in.readLine())!=null && i<=100){
				System.out.println("Read -> "+c);
				list.add(c);
				i++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String arg[]) throws InterruptedException{
		FileThread obj = new FileThread();
		Thread t1 = new Thread(obj);
		t1.start();
		t1.join();
		
		
		System.out.println("coming here");
	}
	
}


