import java.io.IOException;

import javax.management.modelmbean.XMLParseException;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class Driver {

	public static void main(String[] args)throws SAXException , IOException{
		XMLReader reader = XMLReaderFactory.createXMLReader();
		reader.setContentHandler(new SAXHandler());
		reader.parse("Data2.xml");
	}

}
