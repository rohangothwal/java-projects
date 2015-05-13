import java.util.Arrays;


public class AmazonQuestion1 {
	
	public static void main(String[] args){
		
		String[] a = new String[]{"a1","a2","a3","a4","a5","a6","a7","b1","b2","b3","b4","b5","b6","b7","c1","c2","c3","c4","c5","c6","c7"};
		System.out.println(Arrays.toString(a));
		changeA(a,a.length,a.length);
		System.out.println(Arrays.toString(a));
	}

	private static void changeA(String[] a,int length,int count){
		if (count == 0){
			return;
		}
		String save = a[getValue(--count, length)];
		changeA(a, length,count);
		a[count]=save;
	}
	
	private static int getValue(int position,int size){
		int group = (position)/3;
		int count = (position)%3;
		int groupSize = size/3;
		return groupSize*count+group;
	}
}

//0 ,1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11
//a1,a2,a3,a4,a5,a6,a7,b1,b2,b3,b4,b5,b6,b7,c1,c2,c3,c4,c5,c6,c7
//a1,b1,c1,a2,b2,c2,a3,b3,c3,a4,b4,c4

