/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailTest;

/**
 *
 * @author kienh_000
 */
public class email {
    public int STT;
    public String from;
    public String subject;
    public String receiveDate;
    public String message;
    public String attachments;
    
    public email(int stt, String from, String subject, String receivedate, String message, String attach) {
        this.STT = stt;
        this.from = from;
        this.subject = subject;
        this.receiveDate = receivedate;
        this.message = message;
        this.attachments = attach;
    }
    
    public int get_stt() {
        return this.STT;
    }
    
    public String get_sender() {
        return this.from;
    }
    
    public String get_subject() {
        return this.subject;
    }
    
    public String get_receiveDate() {
        return this.receiveDate;
    }
    
    public String get_message() {
        return this.message;
    }
    
    public String get_attachment() {
        return this.attachments;
    }
}
