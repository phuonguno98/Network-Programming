/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
//import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.*;
//import javax.mail.;
import javax.swing.JOptionPane;
import Controlers.htmlTagsRemove;
import java.nio.charset.Charset;
import javax.mail.Flags;
import javax.mail.search.FlagTerm;
 
/**
 *
 * @author BUDDHIMA
 */
 
public class gmailClient {
 
    private String userName;
    private String password;
    private String sendingHost;
    private int sendingPort;
    private String from;
    private String to;
    private String subject;
    private String text;
    private String receivingHost;
    private String saveDirectory = "/attachments";
//    private int receivingPort;
 
    public void setAccountDetails(String userName,String password){
 
        this.userName=userName;//sender's email can also use as User Name
        this.password=password;
 
    }
 
    public void sendGmail(String from, String to, String subject, String text){

        this.from=from;
        this.to=to;
        this.subject=subject;
        this.text=text;
 
        this.sendingHost="smtp.gmail.com";
        this.sendingPort=465;
 
        Properties props = new Properties();
 
        props.put("mail.smtp.host", this.sendingHost);
        props.put("mail.smtp.port", String.valueOf(this.sendingPort));
        props.put("mail.smtp.user", this.userName);
        props.put("mail.smtp.password", this.password);
 
        props.put("mail.smtp.auth", "true");
        Session session1 = Session.getDefaultInstance(props);
        Message simpleMessage = new MimeMessage(session1);
 
        InternetAddress fromAddress = null;
        InternetAddress toAddress = null;
        try {
            fromAddress = new InternetAddress(this.from);
            toAddress = new InternetAddress(this.to);
        } catch (AddressException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Sending email to: " + to + " failed !!!", "Falied to Send!!!", JOptionPane.ERROR_MESSAGE);
        }
        try {
            simpleMessage.setFrom(fromAddress);
            simpleMessage.setRecipient(RecipientType.TO, toAddress);
 
                        // to add CC or BCC use
                        // simpleMessage.setRecipient(RecipientType.CC, new InternetAddress("CC_Recipient@any_mail.com"));
                        // simpleMessage.setRecipient(RecipientType.BCC, new InternetAddress("CBC_Recipient@any_mail.com"));
 
            simpleMessage.setSubject(this.subject);
 
            simpleMessage.setText(this.text);
 
        //sometimes Transport.send(simpleMessage); is used, but for gmail it's different
 
            Transport transport = session1.getTransport("smtps");
 
            transport.connect (this.sendingHost,sendingPort, this.userName, this.password);
 
            transport.sendMessage(simpleMessage, simpleMessage.getAllRecipients());
 
            transport.close();
 
            JOptionPane.showMessageDialog(null, "Mail sent successfully ...","Mail sent",JOptionPane.PLAIN_MESSAGE);
 
        } catch (MessagingException e) {
 
            e.printStackTrace();
 
                       JOptionPane.showMessageDialog(null, "Sending email to: " + to + " failed !!!", "Falied to Send!!!", JOptionPane.ERROR_MESSAGE);
 
        }
 
    }
 
    public ArrayList readGmail(){
        
        ArrayList returnval = new ArrayList();
        this.receivingHost="imap.gmail.com";//for imap protocol
        Properties props2=System.getProperties();
        props2.setProperty("mail.store.protocol", "imaps");
        Session session2=Session.getDefaultInstance(props2, null);
        try {
            Store store=session2.getStore("imaps");
            store.connect(this.receivingHost,this.userName, this.password);
            Folder folder=store.getFolder("INBOX");//get inbox
            folder.open(Folder.READ_ONLY);//open folder only to read
            FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
            Message message[] = folder.search(ft);
            //Message message[]=folder.getMessages();
            int endcount = message.length;
            if(message.length > 20) {
                endcount = 20;
            }
            for(int i=message.length - 1;i>=message.length-endcount;i--){
                Address[] fromAddress = message[i].getFrom();
                from = fromAddress[0].toString();
                //to utf8
                from = MimeUtility.decodeText(from);
                subject = message[i].getSubject();
                String sentDate = message[i].getSentDate().toString();

                String contentType = message[i].getContentType();
                String messageContent = "";
                String attachFiles = "";

                if (contentType.contains("multipart")) {
                    // content may contain attachments
                    Multipart multiPart = (Multipart) message[i].getContent();
                    int numberOfParts = multiPart.getCount();
                    for (int partCount = 0; partCount < numberOfParts; partCount++) {
                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                                // this part is attachment
                            String fileName = part.getFileName();
                            attachFiles += fileName + ", ";
//                                    part.saveFile(saveDirectory + File.separator + fileName);
                        } else {
                            // this part may be the message content
                            messageContent = part.getContent().toString();
                        }
                    }

                    if (attachFiles.length() > 1) {
                        attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
                    }
                } else if (contentType.contains("text/plain")
                        || contentType.contains("text/html")) {
                    Object content = message[i].getContent();
                    if (content != null) {
                        messageContent = content.toString();
                    }
                }

                //System.out.println(""+messageContent.length());
                htmlTagsRemove htmlfilter = new htmlTagsRemove(messageContent);
                    //String msgFilter = htmlfilter.remove();

                    // print out details of each message
//                        System.out.println("Message #" + (i + 1) + ":");
//                        System.out.println("\t From: " + from);
//                        System.out.println("\t Subject: " + subject);
//                        System.out.println("\t Sent Date: " + sentDate);
//                        System.out.println("\t Message: " + htmlfilter.remove());
//                        System.out.println("\t Attachments: " + attachFiles);
//                        System.out.println("-----------end-------------");
                email mail = new email((i+1),from,subject,sentDate,htmlfilter.remove(),attachFiles);
                returnval.add(mail);
            }
                //close connections
            folder.close(true);
            store.close();
        } catch (MessagingException | IOException e) {
                System.out.println(e.toString());
        }
        return returnval;
    }
 
    public static void main(String[] args) {
        //String mailFrom=new String("sender@gmail.com");
        //Sender must be a Gmail Account holder
        //String mailTo=new String("receiver@gmail.com");
        //but here you can send to any type of mail account
        //String senderPassword=new String("actacis1");
        //String senderUserName=new String("hotro@hocvienact.edu.vn");
        //Mention your email subject and content
        //String mailSubject=new String("Testing Mail");
        //String mailText=new String("Have an Nice Day ...........!!!");
        //Create a GmailClient object
        //gmailClient newGmailClient=new gmailClient();
        //Setting up account details
        //newGmailClient.setAccountDetails(senderUserName, senderPassword);
        //Send mail
        //newGmailClient.sendGmail(mailFrom, mailTo, mailSubject, mailText);
        //Receive mails
        //newGmailClient.readGmail();
    }
 
}
