import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;


public class TestClass {
	
	private static final String[] prefixArray = new String[]{"What was the","What is the","With which","What was","In what","In which","What is","Who is","Which"};
	private static String[] questions1 = new String[]{"What is your maternal grandfather's profession?",
		"What is the first and last name of your most memorable childhood babysitter/caregiver?",
		"What is the first name of your oldest nephew?",
		"What was the TV series you liked most as a child?",
		"What is the last name of your least favorite teacher in high school?",
		"In what city or country do you want to retire?",
		"What is your youngest cousin's first and last name?",
		"What is the first and last name of the person you went to your prom with?",
		"What was your favorite toy when you were a child?",
		"What was the first and last name of your first roommate during college?",
		"What is your maternal grandmother's first name?",
		"What is the first name of the maid of honor at your wedding?",
		"What is your maternal grandfather's first name?",
		"What is the name of the first company you worked for?",
		"What is your father's middle name?",
		"In what city was your high school? (full name of city only)",
		"What is your best friend's first name?",
		"In what city were you married? (Enter full name of city)",
		"What was the name of your High School?"}
;
	private static String[] questions2 = new String[]{"What famous person would you have liked to meet?",
		"What was the first name of your first manager?",
		"What is the name of a college you applied to but didn't attend?",
		"What was your favorite Halloween costume in elementary school?",
		"What was the last name of your fourth grade teacher?",
		"On what street was the first address you lived in outside your parents' home?",
		"What was the name of your first pet?",
		"What was the name of your first girlfriend/boyfriend?",
		"What is the first name of your oldest niece?",
		"What were your wedding colors?",
		"What was the nickname of your grandfather?",
		"In what city was your father born? (Enter full name of city only)", 
		"What is the first name of the best man at your wedding?",
		"What is your paternal grandmother's first name?",
		"In what city is your vacation home? (Enter full name of city only)",
		"What was your high school mascot?"};
	private static String[] questions3 = new String[]{"What was your favorite book as a child?",
		"Where were you when you had your first kiss?",
		"What is your oldest sibling's nickname?",
		"What is your oldest cousin's first and last name?",
		"What is the name of the band you liked most during high school?",
		"What street did your best friend in high school live on? (Enter full name of street only)",
		"What was your favorite restaurant in college?",
		"What was your favorite movie as a child?",
		"What was your favorite place to visit as a child?",
		"What was the last name of your favorite teacher in your final year of high school?",
		"What was the name of your junior high school? (Enter only \"Riverdale\" for Riverdale Junior High School)",
		"In what city were you born? (Enter full name of city only)",
		"Where did you meet your spouse for the first time? (Enter full name of city only)",
		"What was the name of the town your grandmother lived in? (Enter full name of town only)",
		"What is your mother's middle name?",
		"In what city was your mother born? (Enter full name of city only)",
		"What is your paternal grandfather's first name?"};
    private static String removePrefix(String text, String[] prefixArray){
          System.out.println("2^^^^^^^text -> "+text);
          text = removeAndReplace(text,"?","");
          for (int i = 0;i<prefixArray.length;i++){
                if (text.contains(prefixArray[i]))
                      return text.replaceFirst(prefixArray[i],"");
          }
          return text;
    }

    public static String removeAndReplace(String str, String remove, String replace) {
    	StringBuffer buf = new StringBuffer();
    	int removeLength = remove.length();
    	if (removeLength == 0) {
    	    buf.append(str);
    	    return buf.toString();
    	}

    	int length = str.length();
    	int start = 0;
    	int end = 0;
    	while (true) {
    	    if (start >= length) {
    		break;
    	    }
    	    end = str.indexOf(remove, start);
    	    if (end == -1) {
    		buf.append(str.substring(start));
    		break;
    	    }
    	    String sub = str.substring(start, end);
    	    buf.append(sub);
    	    buf.append(replace);
    	    start = end + removeLength;
    	}
    	return buf.toString();
        }
    public static final String TAG_BANK = "bank";

	/**
	 * TAG_BANK = "bank"
	 */
	public static final String TAG_INSURANCE = "insurance";

	/**
	 * TAG_LOAN = "loan"
	 */
	public static final String TAG_LOAN = "loans";

	/**
	 * TAG_CREDITS = "credits"
	 */
	public static final String TAG_CREDITS = "credits";

	/**
	 * TAG_INVESTMENT = "stocks"
	 */
	public static final String TAG_INVESTMENT = "stocks";

	/**
	 * TAG_BILLPAY = "bill_payment"
	 */
	public static final String TAG_BILLPAY = "bill_payment";

	/**
	 * TAG_BILL = "bills"
	 */
	public static final String TAG_BILL = "bills";

	/**
	 * TAG_MORTGAGE = "mortgage"
	 */
	public static final String TAG_MORTGAGE = "mortgage";

	/**
	 * TAG_TAX = "tax"
	 */
	public static final String TAG_TAX = "tax";

	/**
	 * TAG_ALL = "all"
	 */
	public static final String TAG_ALL = "allAccounts";

	/**
	 * TAG_MILES = "miles"
	 */
	public static final String TAG_MILES = "miles";

	/**
	 * IS_PERSONAL_INFO_REQUIRED = false
	 */
	public static final boolean IS_PERSONAL_INFO_REQUIRED = false;
    public static HashMap<String, String> containerTypeMap = new HashMap<String, String>();
	static {
		
		containerTypeMap.put("card", TAG_CREDITS);
		containerTypeMap.put("progress", TAG_BANK);
		containerTypeMap.put("spec. proj.", TAG_BANK);
		containerTypeMap.put("savings", TAG_BANK);
		containerTypeMap.put("cornerstone", TAG_BANK);
		containerTypeMap.put("line of credit", TAG_BANK);
		containerTypeMap.put("chequing", TAG_BANK);
		containerTypeMap.put("loan", TAG_LOAN);
		containerTypeMap.put("mortgage", TAG_MORTGAGE);
		containerTypeMap.put("Chèques", TAG_BANK);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception{
		// TODO Auto-generated method stub
		
		/*FileWriter fileWriter = new FileWriter("c:/questions1.txt");
        BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
        java.util.List<String> questionList = Arrays.asList(questions3);//add your questions in this list
        
        Iterator<String> iterator = questionList.iterator();
        while(iterator.hasNext()){
              String displayText = iterator.next();
              String textValue = removePrefix(displayText,prefixArray);
              System.out.println(textValue);
              bufferWriter.write("<option value=\""+textValue.trim() + "\" style=\"width: 500px\">"+displayText.trim()+"</option>"+"\r\n");
        }
        bufferWriter.close();*/
		
		//System.out.println("Chèques".toLowerCase());
		
		/*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/new.txt")));
        String line = bufferedReader.readLine();
        StringBuilder siiToQuery = new StringBuilder();
        while (line != null) {
        	//siiToQuery.append(line);
        	System.out.println(line);
        	line = bufferedReader.readLine();
        }
        System.out.println(siiToQuery);*/
		
		String[] items = new String[]{"Current Estimated Charges","Next Automatic Payment"};
		System.out.println(STATEMENTS_PAGE_IDFRS.length);
		String[] waitString = removeItemsFromArray(STATEMENTS_PAGE_IDFRS, items);
		System.out.println(waitString.length);
	}
	
	protected static final String[] STATEMENTS_PAGE_IDFRS = new String[]{"sadfasd","asdfr","agasddf","Current Estimated Charges","Next Automatic Payment","html:span-tooltiptitle-Last Payment"};

	
	public static String[] removeItemsFromArray(String[] array, String[] items) {

		
		java.util.List<String> output = new ArrayList<String>(Arrays.asList(array));
		java.util.List<String> itemsToRemove = new ArrayList<String>(Arrays.asList(items));
		output.removeAll(itemsToRemove);
		System.out.println(output.size());
		return output.toArray(new String[0]);
	}
	

}