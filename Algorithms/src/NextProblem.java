import java.io.*;
class NextProblem
{
	public static void main (String[] args) {
    System.out.println("Enter a String length of string must be multiple of 3 :");
    Console con=System.console();
    String str="aqaaabqbbbcqccc";
    char arr[]=str.toCharArray();
    int pos1,pos2,cpos;
    pos1=arr.length/3;
    pos2=2*pos1;
    int mp,gp,lp;
    mp=pos1-1;
    while(mp>0)
    {
    	cpos=pos1;
    	gp=1;
    	while (gp<=2)
    	{
    		lp=0;
    		while (lp<(mp*gp))
    		{
    			char temp=arr[cpos];
    			arr[cpos]=arr[cpos-1];
    			arr[cpos-1]=temp;
    			cpos--;
    			lp++;
    		}
    		cpos=pos2;
    		gp++;
    	}
    	pos1+=2;
    	pos2+=1;
    	mp--;
    }
    for (int i = 0; i<arr.length; i++)
    {
    	System.out.print(arr[i]);
    }
    }
}