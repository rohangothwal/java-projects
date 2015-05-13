import java.util.StringTokenizer;

import javax.swing.JOptionPane;


public class QueryGenerator {
	
	
	public static void main(String[] args){
		String tableNames = JOptionPane.showInputDialog(null,"Enter Names -> ");
		StringTokenizer st = new StringTokenizer(tableNames," ");
		
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			System.out.println("select * from "+token+" where cache_item_id in ()");
		}
	}

}
