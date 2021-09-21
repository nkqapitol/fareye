package com.fareye.integration.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fareye.qa.core.util.DateUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MailUtils {

	private static Session session = null;
	private static Folder folder = null;
	private static Store store = null;
	private static String output  = null;
	
	public static boolean isMailPresent(String userName, String password, String mailSubject, long startTime, String folderName) {
	    try {
	    	Message msg = getLastMail(userName, password, mailSubject, startTime, folderName);       
	        String strMailSubject = "";	
			Object subject = msg.getSubject();
			strMailSubject = (String) subject;		
			long sentDate = msg.getSentDate().getTime();
			
			if (strMailSubject.contains(mailSubject) 
					&& DateUtil.getCurrentDate("dd-MM-yyyy hh:mm", startTime)
			.compareTo(DateUtil.getCurrentDate("dd-MM-yyyy hh:mm", sentDate)) >= 0)
			    return true;
			} catch (MessagingException messagingException) {
				 messagingException.printStackTrace();
			}catch (Exception e) {
				log.debug("An exception occurred", e);
			}finally {
			    if (folder != null) {
			        try {
			            folder.close(true);
			        } catch (MessagingException e) {
			            log.debug("An exception occurred", e);
			    }
			}
			if (store != null) {
			    try {
			        store.close();
			    } catch (MessagingException e) {
			        log.debug("An exception occurred", e);
			            }
			        }
			    }
			return false;
		}
	 
	public static String getAccountVerificationURL(String userName, String password, String mailSubject, long startTime, String folderName) {

		try {
			getMessageDetails(getLastMail(userName, password, mailSubject, startTime, folderName));
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
		    if (folder != null) {
		        try {
		            folder.close(true);
		        } catch (MessagingException e) {
		            log.debug("An exception occurred", e);
		    }
		}
		if (store != null) {
		    try {
		        store.close();
		    } catch (MessagingException e) {
		        log.debug("An exception occurred", e);
		            }
		        }
		    }
		
		Document doc = Jsoup.parse(output);
		Element link = doc.select("a").first();
		String url = link.attr("href");
		
		return url;
	}
	
	public static Map<String,String> getCarrierDetails(String userName, String password, String mailSubject, long startTime, String folderName) {

		Map<String,String> map = new LinkedHashMap<>();
		
		try {
			Thread.sleep(5000);
			getMessageDetails(getLastMail(userName, password, mailSubject, startTime, folderName));
		} catch (IOException e) {
			e.printStackTrace();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
		    if (folder != null) {
		        try {
		            folder.close(true);
		        } catch (MessagingException e) {
		            log.debug("An exception occurred", e);
		    }
		}
		if (store != null) {
		    try {
		        store.close();
		    } catch (MessagingException e) {
		        log.debug("An exception occurred", e);
		            }
		        }
		    }
		
		Document doc = Jsoup.parse(output);
		Element table = doc.select("table").get(0);
		Elements rows = table.select("tr");

		for (int i = 4; i < rows.size()-1; i++) { 
		    Element row = rows.get(i);
		    Elements cols = row.select("td");

		    map.put(cols.text().replaceAll(":(.*)", "").trim(), cols.text().replaceAll("^[^:-]*[:-]\\s*", "").trim());
		}
		return map;
	}
	private static Message getLastMail(String userName, String password, String mailSubject, long startTime, String folderName) {
		Message[] messages = null;
		try {
	    	establishConnection(userName,password);
	        folder = store.getFolder(folderName);
			folder.open(Folder.READ_ONLY);
			messages = folder.getMessages();
			log.info("No of Messages : " + folder.getMessageCount());
			log.info("No of Unread Messages : " + folder.getUnreadMessageCount());          
			    
			} catch (MessagingException messagingException) {
				log.debug("An exception occurred", messagingException);
			} 
	    return messages[messages.length - 1];
	}
	
	private static void establishConnection(String userName, String password) {
	    try {
	        Properties props = new Properties();
	        
	        props.put("mail.imap.host", "imap.gmail.com");
			props.put("mail.imap.port", "993");
			props.put("mail.imap.starttls.enable", "true");
			props.put("mail.imap.ssl.trust", "imap.gmail.com");
			
			session = Session.getInstance(props);
			store = session.getStore("imaps");
			store.connect("imap.gmail.com", userName, password);
			 }catch (MessagingException messagingException) {
				 messagingException.printStackTrace();
			 }catch (Exception exception) {
			     exception.printStackTrace();
			 }
	}
	
	private static void getMessageDetails(Message msg) throws IOException {
	    try {
	        Object cont = msg.getContent();
	
	        if (cont instanceof Multipart) {
	            handleMultipart((Multipart) cont);
	        } 
	        else {
	            handlePart(msg);
	        }
	    } catch (MessagingException ex) {
	        ex.printStackTrace();
	    }
	}
	
	private static void handleMultipart(Multipart multipart) throws MessagingException, IOException {
	    for (int i = 0, n = multipart.getCount(); i < n; i++) {
	        handlePart(multipart.getBodyPart(i));
	    }
	}
	
	private static void handlePart(Part part) throws MessagingException, IOException {	
	    String disposition = part.getDisposition();
	    String contentType = part.getContentType();
	    
	    ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(stdout);
		PrintStream old = System.out;
		System.setOut(ps);
	    
	    if (disposition == null) {
	        System.out.println("Null: " + contentType);
		if ((contentType.length() >= 10)
		        && (contentType.toLowerCase().substring(
		        0, 10).equals("text/plain"))) {
		    part.writeTo(System.out);
			System.out.flush();
			System.setOut(old);
			output = stdout.toString();
		} else if ((contentType.length() >= 9)
		        && (contentType.toLowerCase().substring(
		        0, 9).equals("text/html"))) {
		    part.writeTo(System.out);
			System.out.flush();
			System.setOut(old);
			output = stdout.toString();
		} else if ((contentType.length() >= 9)
		        && (contentType.toLowerCase().substring(
		        0, 9).equals("text/html"))) {
		    part.writeTo(System.out);
			System.out.flush();
			System.setOut(old);
			output = stdout.toString();
		}else{
			part.writeTo(System.out);
			System.out.flush();
			System.setOut(old);
			output = stdout.toString();
		}
		} else if (disposition.equalsIgnoreCase(Part.ATTACHMENT)) {
		    System.out.println("Attachment: " + part.getFileName()+ " : " + contentType);
		} else if (disposition.equalsIgnoreCase(Part.INLINE)) {
		    System.out.println("Inline: "+ part.getFileName()+ " : " + contentType);
		} else {
		    System.out.println("Other: " + disposition);
		}
	}
}
