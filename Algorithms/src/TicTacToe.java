
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class TicTacToe {
	
	static int cases[][][]= new int[8][3][2];
	static int _count=0;
	static{
		
		cases[0] = new int[3][2];
		cases[1] = new int[3][2];
		cases[2] = new int[3][2];
		cases[3] = new int[3][2];
		cases[4] = new int[3][2];
		cases[5] = new int[3][2];
		cases[6] = new int[3][2];
		cases[7] = new int[3][2];
		
		cases[0][0] = new int[] {0,0};
		cases[0][1] = new int[] {0,1};
		cases[0][2] = new int[] {0,2};
		
		cases[1][0] = new int[] {1,0};
		cases[1][1] = new int[] {1,1};
		cases[1][2] = new int[] {1,2};
		
		cases[2][0] = new int[] {2,0};
		cases[2][1] = new int[] {2,1};
		cases[2][2] = new int[] {2,2};
		
		cases[3][0] = new int[] {0,0};
		cases[3][1] = new int[] {1,0};
		cases[3][2] = new int[] {2,0};
		
		cases[4][0] = new int[] {0,1};
		cases[4][1] = new int[] {1,1};
		cases[4][2] = new int[] {2,1};
		
		cases[5][0] = new int[] {0,2};
		cases[5][1] = new int[] {1,2};
		cases[5][2] = new int[] {2,2};
		
		cases[6][0] = new int[] {0,0};
		cases[6][1] = new int[] {1,1};
		cases[6][2] = new int[] {2,2};
		
		cases[7][0] = new int[] {0,2};
		cases[7][1] = new int[] {1,1};
		cases[7][2] = new int[] {2,0};

	}
/* Complete the function below to print 2 integers separated by a single space which will be your next move */
	
	static String caseStringBuilder(int caseIndex,String []bord){
		StringBuilder strBuild= new StringBuilder();
		for(int i=0;i<=2;i++){
			char charAt= bord[cases[caseIndex][i][0]].charAt(cases[caseIndex][i][1]);
			strBuild.append(charAt);
		}
		
		return strBuild.toString();
	}
	
	
	static boolean runCase(int caseIndex,String player,String boardStr){
		_count=0;
		
		if( (boardStr.charAt(0)+"").equalsIgnoreCase("_") ){
			_count++;
			
			if( ((boardStr.charAt(1)+"").equalsIgnoreCase(boardStr.charAt(2)+""))   ){
				
				if( (boardStr.charAt(1)+"").equalsIgnoreCase("_") && (boardStr.charAt(2)+"").equalsIgnoreCase("_")  ){
					_count++;
					_count++;
				}
				else if( ((boardStr.charAt(1)+"").equalsIgnoreCase("_") || (boardStr.charAt(2)+"").equalsIgnoreCase("_") )){
					_count++;
				}
				else{
					System.out.println(cases[caseIndex][0][0] + " " + cases[caseIndex][0][1]);	
					return true;
				}
				
			}
			else if(((boardStr.charAt(1)+"").equalsIgnoreCase("_") || (boardStr.charAt(2)+"").equalsIgnoreCase("_") )){
				_count++;
			}
		}
			else if ( (boardStr.charAt(1)+"").equalsIgnoreCase("_") ){
				_count++;
				
				if((boardStr.charAt(2)+"").equalsIgnoreCase("_") ){
					_count++;
					
				}
				else if(((boardStr.charAt(0)+"").equalsIgnoreCase(boardStr.charAt(2)+""))){
					System.out.println(cases[caseIndex][1][0] + " " + cases[caseIndex][1][1]);	
					return true;
				}
				
			}
			else if((boardStr.charAt(2)+"").equalsIgnoreCase("_")){
				_count++;
				
				if(((boardStr.charAt(0)+"").equalsIgnoreCase(boardStr.charAt(1)+""))){
					System.out.println(cases[caseIndex][2][0] + " " + cases[caseIndex][2][1]);	
					return true;
				}
			}
		
		return false;
	}
	
    static void nextMove(String player, String [] board){
    	
//    	ArrayList<Integer> one_list = new ArrayList<Integer>();
    	ArrayList<Integer> two_list = new ArrayList<Integer>();
    	ArrayList<Integer> three_list = new ArrayList<Integer>();
    	
    	if( (board[1].charAt(1)+"").equalsIgnoreCase("_") ){
    		System.out.println("1 1");
    	}
    	
    	else{
	    		for(int i=0;i<=7;i++){
//	    				System.out.println(caseStringBuilder(i, board));
	    				boolean ifReturn= runCase(i, player, caseStringBuilder(i, board));
	    				if(ifReturn) return;
	    				else{
	    					 if (_count==2){
	    						two_list.add(i);
	    					}
	    					else if(_count==3){
	    						three_list.add(i);
	    					}
	    					_count=0;
	   				}
	    		}
	    		
	    		if(!two_list.isEmpty()){
	    			int caseIndex= two_list.get(0);
	    			String caseStr=caseStringBuilder(caseIndex, board);
	    			if( (caseStr.charAt(1)+"").equalsIgnoreCase("_") ){
	    				System.out.println(cases[caseIndex][1][0] + " " + cases[caseIndex][1][1]);
	    			}
	    			else if ((caseStr.charAt(0)+"").equalsIgnoreCase("_")){
	    				System.out.println(cases[caseIndex][0][0] + " " + cases[caseIndex][0][1]);
	    			}
	    			
	    			else {
	    				System.out.println(cases[caseIndex][2][0] + " " + cases[caseIndex][2][1]);
	    			}
	    			return;
	    		}
	    		if(!three_list.isEmpty()){
	    			int caseIndex= three_list.get(0);
	    			System.out.println(cases[caseIndex][1][0] + " " + cases[caseIndex][1][1]);
	    			return;
	    		}
	    		
    	}
    	
    }
  
  public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String player;
        String board[] = new String[3];

        //If player is X, I'm the first player.
        //If player is O, I'm the second player.
        player = in.next();

        //Read the board now. The board is a 3x3 array filled with X, O or _.
        for(int i = 0; i < 3; i++) {
            board[i] = in.next();
        }

  		nextMove(player,board);	
    
    }
}
