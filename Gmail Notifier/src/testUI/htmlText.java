/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 * A complete Java class that demonstrates how to create an HTML viewer with styles,
 * using the JEditorPane, HTMLEditorKit, StyleSheet, and JFrame.
 * 
 * @author alvin alexander, devdaily.com.
 *
 */
public class htmlText
{
  public static void main(String[] args)
  {
    new htmlText();
  }
  
  public htmlText()
  {
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        // create jeditorpane
        JEditorPane jEditorPane = new JEditorPane();
        
        // make it read-only
        jEditorPane.setEditable(false);
        
        // create a scrollpane; modify its attributes as desired
        JScrollPane scrollPane = new JScrollPane(jEditorPane);
        
        // add an html editor kit
        HTMLEditorKit kit = new HTMLEditorKit();
        jEditorPane.setEditorKit(kit);
        
        // add some styles to the html
        StyleSheet styleSheet = kit.getStyleSheet();
//        styleSheet.addRule("body {color:#000; font-family:times; margin: 4px; }");
//        styleSheet.addRule("h1 {color: blue;}");
//        styleSheet.addRule("h2 {color: #ff0000;}");
//        styleSheet.addRule("pre {font : 10px monaco; color : black; background-color : #fafafa; }");

        // create some simple html as a string
        String htmlString = "<html><head><meta http-equiv=\"content-type\" content=\"text/html;charset=UTF-8\" /><title>How to use Gmail with Google Apps</title></head><body style=\"background-color:#e5e5e5; margin:20px 0;\"><br /><div style=\"margin:2%;\"><div style=\"direction:ltr; text-align:left; font-family:'Open sans','Arial',sans-serif; color:#444; background-color:white; padding:1.5em; border-radius:1em; box-shadow:1px -5px 8px 2px #bbb; max-width:580px; margin:2% auto 0 auto;\"><table style=\"background:white;width:100%\"><tr><td><div style=\"width:90px; height:54px; margin:10px auto;\"><img src=\"https://services.google.com/fh/files/emails/google_logo_flat_90_color.png\" alt=\"Google\" width=\"90\" height=\"34\"/></div><div style=\"width:90%; padding-bottom:10px; padding-left:15px\"><p><img alt=\"\" aria-hidden=\"true\" src=\"https://ssl.gstatic.com/ui/v1/icons/mail/images/gmail_logo_large.png\" style=\"display:inline-block; max-height:10px; margin-right:5px;\"/><span style=\"font-family:'Open sans','Arial',sans-serif; font-weight:bold; font-size:small; line-height:1.4em\">Hi Hotro</span></p><p><span style=\"font-family:'Open sans','Arial',sans-serif; font-size:2.08em;\">Work smarter with Gmail and Google Apps</span><br/></p></div><p></p><div style=\"float:left; clear:both; padding:0px 5px 10px 10px;\"><img src=\"https://ssl.gstatic.com/mail/welcome/localized/en/welcome_calendar.png\" alt=\"Calendar\" style=\"display:block;\"width=\"129\"height=\"129\"/></div><div style=\"float:left; vertical-align:middle; padding:10px; max-width:398px; float:left;\"><table style=\"vertical-align:middle;\"><tr><td style=\"font-family:'Open sans','Arial',sans-serif;\"><span style=\"font-size:20px;\">Manage Calendar meetings</span><br/><br/><span style=\"font-size:small; line-height:1.4em\"><a href=\"https://www.google.com/calendar\" style=\"text-decoration:none; color:#15C\">Google Calendar</a> makes scheduling meetings easy with shared calendars. You’ll get invites and reminders and can even join a scheduled video meeting right from your inbox.</span></td></tr></table></div><div style=\"float:left; clear:both; padding:0px 5px 10px 10px;\"><img src=\"https://ssl.gstatic.com/mail/welcome/localized/en/welcome_drive.png\" alt=\"Send Large files\" style=\"display:block;\"width=\"129\"height=\"129\"/></div><div style=\"float:left; vertical-align:middle; padding:10px; max-width:398px; float:left;\"><table style=\"vertical-align:middle;\"><tr><td style=\"font-family:'Open sans','Arial',sans-serif;\"><span style=\"font-size:20px;\">Use Drive to send large files</span><br/><br/><span style=\"font-size:small; line-height:1.4em\"><a href=\"https://support.google.com/mail/answer/2480713?hl=en&amp;utm_source=gmailwelcomeemail&amp;utm_medium=email&amp;utm_campaign=gmailwelcome\" style=\"text-decoration:none; color:#15C\">Send huge files in Gmail</a> (up to 10GB) using <a href=\"https://drive.google.com/\" style=\"text-decoration:none; color:#15C\">Google Drive</a>. Plus, files stored in Drive stay up-to-date automatically so everyone has the most recent version and can access them from anywhere.</span></td></tr></table></div><div style=\"float:left; clear:both; padding:0px 5px 10px 10px;\"><img src=\"https://ssl.gstatic.com/mail/welcome/localized/en/welcome_collaborate.png\" alt=\"Collaborate\" style=\"display:block;\"width=\"129\"height=\"129\"/></div><div style=\"float:left; vertical-align:middle; padding:10px; max-width:398px; float:left;\"><table style=\"vertical-align:middle;\"><tr><td style=\"font-family:'Open sans','Arial',sans-serif;\"><span style=\"font-size:20px;\">Collaborate on documents</span><br/><br/><span style=\"font-size:small; line-height:1.4em\">Get email alerts when someone comments on your shared document in <a href=\"https://docs.google.com/document/\" style=\"text-decoration:none; color:#15C\">Docs</a>, <a href=\"https://docs.google.com/spreadsheet/\" style=\"text-decoration:none; color:#15C\">Sheets</a> and <a href=\"https://docs.google.com/presentation\" style=\"text-decoration:none; color:#15C\">Slides</a>. To respond to the comment, just reply to the email.</span></td></tr></table></div><div style=\"float:left; clear:both; padding:0px 5px 10px 10px;\"><img src=\"https://ssl.gstatic.com/mail/welcome/localized/en/welcome_forms.png\" alt=\"Forms\" style=\"display:block;\"width=\"129\"height=\"129\"/></div><div style=\"float:left; vertical-align:middle; padding:10px; max-width:398px; float:left;\"><table style=\"vertical-align:middle;\"><tr><td style=\"font-family:'Open sans','Arial',sans-serif;\"><span style=\"font-size:20px;\">Get quick answers with Forms</span><br/><br/><span style=\"font-size:small; line-height:1.4em\">Send <a href=\"https://docs.google.com/forms\" style=\"text-decoration:none; color:#15C\">Google Forms</a> via email. Recipients can complete forms and respond right from their inbox.</span></td></tr></table></div><br/><br/>\n" +
"<div style=\"clear:both; padding-left:13px; height:6.8em;\"><table style=\"width:100%; border-collapse:collapse; border:0\"><tr><td style=\"width:68px\"><img alt='Gmail icon' width=\"49\" height=\"37\" src=\"https://ssl.gstatic.com/ui/v1/icons/mail/images/gmail_logo_large.png\" style=\"display:block;\"/></td><td style=\"align:left; font-family:'Open sans','Arial',sans-serif; vertical-align:bottom\"><span style=\"font-size:small\">Happy emailing,<br/></span><span style=\"font-size:x-large; line-height:1\">The Gmail Team</span></td></tr></table></div>\n" +
"</td></tr></table></div>\n" +
"<div style=\"direction:ltr;color:#777; font-size:0.8em; border-radius:1em; padding:1em; margin:0 auto 4% auto; font-family:'Arial','Helvetica',sans-serif; text-align:center;\">© 2015 Google Inc. 1600 Amphitheatre Parkway, Mountain View, CA 94043<br/></div></div></body></html>";
        
        // create a document, set it on the jeditorpane, then add the html
        Document doc = kit.createDefaultDocument();
        jEditorPane.setDocument(doc);
        jEditorPane.setText(htmlString);

        // now add it all to a frame
        JFrame j = new JFrame("HtmlEditorKit Test");
        j.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // make it easy to close the application
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // display the frame
        j.setSize(new Dimension(300,200));
        
        // pack it, if you prefer
        //j.pack();
        
        // center the jframe, then make it visible
        j.setLocationRelativeTo(null);
        j.setVisible(true);
      }
    });
  }
}

