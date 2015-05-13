import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;


public class MailUtil<T>
{   private static String removableHeadersNos="";
	public void sendMail(MailBean mailBean,List list,String[] removableAttributes) throws MessagingException,IllegalArgumentException, IllegalAccessException
	{
		Properties props = new Properties();
		props.put("mail.smtp.host","192.168.234.200");
		Session session = Session.getInstance(props, null);
		MimeMessage msg = new MimeMessage(session);
		try {
			addAddressToMessage(mailBean.getTo(), msg, "TO");
			addAddressToMessage(mailBean.getBcc(), msg, "BCC");
			addAddressToMessage(mailBean.getCc(), msg, "CC");

			InternetAddress fromAddress = new InternetAddress(mailBean.getFrom());
			msg.setFrom(fromAddress);

			msg.setSubject(mailBean.getSubject());
			msg.setSentDate(new Date());
			msg.setText("Attachment");			

			// create the message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			
			String bodyText=getbodyText(list,mailBean,removableAttributes);
			YDataLogger.out("2^^^^^^^^^^^^^^ body text:"+bodyText);
			messageBodyPart.setContent(bodyText , "text/html");

			// Put parts in message
			Multipart multiPart = new MimeMultipart();
			multiPart.addBodyPart(messageBodyPart);
			addAttachmentToMessage(mailBean.getAttacmentLocation(), multiPart, mailBean.getAttachmentFileName());
			msg.setContent(multiPart);
			System.out.println("2^^^^^^^^^^^^^^^^^^^send successful ");

			/* ******************************************************************** */
			Transport.send(msg);


		} catch (MessagingException mex) {
			System.out.println("2^^^^^^^^^^^^^^^^^^^send failed, exception: "+ mex);
		}

	}	

	private void addAddressToMessage(List<String> addressList,Message msg, String identifier)
	{    try{
		if(null==addressList)
			return;
		
		for(String str:addressList)
		{
			InternetAddress internetAddress = new InternetAddress(str);
			if(identifier.equals("TO"))
				msg.addRecipient(Message.RecipientType.TO, internetAddress);
			else if(identifier.equals("BCC"))
				msg.addRecipient(Message.RecipientType.BCC, internetAddress);
			else if(identifier.equals("CC"))
				msg.addRecipient(Message.RecipientType.CC, internetAddress);

		}
	}
	catch (MessagingException e) {
		e.printStackTrace();
	} 
	}

	private void addAttachmentToMessage(String[] attachmentLocation,Multipart multiPart, String[] fileNames)
	{        try{
		if(attachmentLocation==null)
			return;
		for(int i=0;i<attachmentLocation.length;i++)
		{
			MimeBodyPart attachmentBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(attachmentLocation[i]);
			attachmentBodyPart.setDataHandler(new DataHandler(source));
			attachmentBodyPart.setFileName(fileNames[i]);
			multiPart.addBodyPart(attachmentBodyPart);
		}
	}          catch (MessagingException e) {
		e.printStackTrace();
	}  

	}

	private String getbodyText(List<T> list,MailBean mailBean,String[] removableAttributes)throws IllegalArgumentException, IllegalAccessException
	{
		String bodyText;
		String strs = "\'s";

		bodyText = "Hi Team,<br>";
		bodyText = bodyText +"<br>";

		bodyText = bodyText+mailBean.getBodyTextDescription();
		if(null!=list)
		  bodyText = bodyText +"<br>" + addDataInTable(list,removableAttributes) + "<br><br>" +  "<b>Note: Please do not reply to this auto-generated mail. For any further assistance, reach out to your respective manager/lead.";

		bodyText=bodyText + "<br><br>" + "<b>"+"<font size =\"2.5\" face=\"Lucida Calligraphy\" color:\"CornflowerBlue\">"+
		                                     "Thanks & Regards,"+"<br>"+"IAE -TTR Alert System"+"</font></b>";
		//bodyText=bodyText+"<br><IMG SRC=\"D:\\QI_Automated_reports\\AgentLatencyReport\\logo.gif\" width=80% height=60%>";
		
		//rgb(0,112,192)
		return bodyText;
	}


	private String addDataInTable(List<T> list,String[] removableAttributes)throws IllegalArgumentException, IllegalAccessException
	{

		String htmlBody = "<html><head><meta content=\"text/html; charset=ISO-8859-1\" http-equiv=\"content-type\"><title></title></head>"
			+"<body><table style=\"text-align: left; width: 70%;\" border=\"1\"cellpadding=\"2\" cellspacing=\"2\">"
			+"<tbody>";
		int iteration=0;


		for (T containerObj : list)
		{
			Class c = containerObj.getClass();
			Field f[] = c.getDeclaredFields();

			String rowColor = "<tr>";			

			if(iteration==0)
			{
				rowColor = "<strong><tr style=\"background-color: rgb(102, 102, 204);\"></strong>";
				htmlBody = htmlBody +rowColor;
				for(int i = 0 ; i < f.length ; i++)
				{   f[i].setAccessible(true);				
				if (null != f[i].get(containerObj)||!isRemovable(f[i].getName().toUpperCase(), removableAttributes,i))
				{   
					htmlBody = htmlBody +"<td style=\"width: 10%;\">"+f[i].getName().toUpperCase() +"</td>";
				}
				}
				htmlBody=htmlBody+"</tr>";
				rowColor = "<tr>";		
			}				

			htmlBody = htmlBody +rowColor;

			for(int i = 0 ; i < f.length ; i++)
			{   

				f[i].setAccessible(true);				
				Object obj = f[i].get(containerObj);
				
				if(!removableHeadersNos.contains(String.valueOf(i)))
				{	if(obj==null)
					  htmlBody = htmlBody+"<td style=\"width: 10%;\">"+" "+"</td>";
				    else
					  htmlBody = htmlBody+"<td style=\"width: 10%;\">"+obj+"" +"</td>";
				
				}

			}

			htmlBody=htmlBody+"</tr>";
			iteration++;
		}

		htmlBody = htmlBody + "</tbody>"+"</table>"+"<br>"+"</body>"+"</html>";
		YDataLogger.out("2^^^^^^^^^^^^^^^^^^^^ htmlBody:"+htmlBody);

		return htmlBody;                                             
	}

	private boolean isRemovable(String str, String[] removableAttributes,int i)
	{
		for(String attr:removableAttributes)
		{
			if(attr.toUpperCase().equals(str.toUpperCase()))
			{	
				removableHeadersNos=removableHeadersNos+String.valueOf(i);
				YDataLogger.out("2^^^^^^^^^^^^^^^ removableHeaders"+removableHeadersNos);
				return true;
			}
		}
		return false;
	}

}
