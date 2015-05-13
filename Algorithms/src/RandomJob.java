import java.util.Random;


public class RandomJob {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Random random = new Random();
		
		for (int i=0;i<70;i++){
			int one = random.nextInt(200);
			int two = one + random.nextInt(30);
			System.out.println(one+"  "+two);
		}

	}

}
