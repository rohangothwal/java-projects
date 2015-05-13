import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RedBlackProblem {

	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<Integer>();
		Random random = new Random();
		
		/*
		 * Generating list
		 */
		int listSize = random.nextInt(100);
		System.out.println("List size: "+listSize);
		for (int i=0;i<listSize;i++){
			list.add(random.nextInt(2));
			System.out.print(list.get(i));
		}
		
		/*
		 * Solution
		 */
		int totalSteps = 0;
		for (int i=0;i<listSize-1;i++){
			totalSteps++;
			if (list.get(i)==1){
				while(list.get(--listSize)==1){
					totalSteps++;
				}
				//System.out.println("\ni = "+i+" j = "+listSize);
				//System.out.println("Total Steps -> "+totalSteps);
				int temp = list.get(i);
				list.set(i, list.get(listSize));
				list.set(listSize, temp);
			}
		}
		System.out.println("\nTotal Steps -> "+totalSteps);
		for(int x:list){
			System.out.print(x);
		}
	}
}
