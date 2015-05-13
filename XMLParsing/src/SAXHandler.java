import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SAXHandler extends DefaultHandler {

	public void startDocument () throws SAXException{
		System.out.println("Inside startDocument");
	}
	public void endDocument () throws SAXException{
		System.out.println("Inside endDocument");
	}
	public void startElement (String uri, String localName,String qName, Attributes attributes)throws SAXException{
		System.out.println("URI -> "+uri);
		System.out.println("localName -> "+localName);
		System.out.println("qName -> "+qName);
		
	}
	public void endElement (String uri, String localName, String qName)throws SAXException{
		System.out.println("URI -> "+uri);
		System.out.println("localName -> "+localName);
		System.out.println("qName -> "+qName);
	}
	public void characters (char ch[], int start, int length)throws SAXException{
		for (int i=start;i<(start+length);i++){
			System.out.print(ch[i]);
		}
	}
}
