/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlers;

import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author kienh_000
 */
public class account {
    private String username;
    private String password;
    private File f;
    private fileHandle file;
    private String[] Content;
    
    public account() {
        this.f = new File("account.theseven");
        file = new fileHandle(f);
        if(!file.fileIsExist()) {
            if(!file.fileCreate()) {
                JOptionPane.showMessageDialog(null, "Tệp tin 'account.theseven' không tồn tại!\nKhông thể tạo file!", "Error", 2);
            }
        }
        else{
            String file_decrypt = file.Decrypt();
            Content = file_decrypt.split("//");
        }
    }
    
    public String getUsername() {
        if(Content.length == 2) {
            return Content[0];
        }
        return null;
    }
    
    public String getPassword() {
        if(Content.length == 2) {
            return Content[1];
        }
        return null;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void writeData() {
        String dataToWrite;
        dataToWrite = this.username+"//"+this.password;
        file.Encrypt(dataToWrite);
    }
}
