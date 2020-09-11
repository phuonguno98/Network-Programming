/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlers;

import java.io.*;
import org.apache.commons.codec.binary.Base64;
/**
 *
 * @author kienh_000
 */
public class fileHandle {
    
    private File f = null;
    public fileHandle() {
        //do nothing
    }
    
    public boolean fileIsExist() {
        return this.f.exists();
    }
    
    public boolean fileCreate() {
        try {
            return f.createNewFile();
        } catch(IOException e) {
            return false;
        }
    }
    
    public fileHandle(File f) {
        this.f = f;
    }
    
    public String Decrypt() {
        String decoded = "";
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String file_content = "";
            String line;
            while((line = br.readLine()) != null) {
                file_content += line;
            }
            decoded = new String(Base64.decodeBase64(Base64.decodeBase64(file_content.getBytes())));
            
        } catch(IOException e) {
            
        }
        return decoded;
    }
    
    public void Encrypt(String file_content) {
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            //base64 encode
            String base64encoded1 = new String(Base64.encodeBase64(file_content.getBytes()));
            //encode with base64 twice
            String base64encoded2 = new String(Base64.encodeBase64(base64encoded1.getBytes()));
            bw.write(base64encoded2);
            bw.close();
        } catch(IOException e) {
            
        }
    }
}
