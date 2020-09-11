/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testUI;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kienh_000
 */
public class isotoutf8 {
    public static void main(String[] args) {
        try {
            String abc = "859-1?Q?T=FA_N=F4ng?=";
            String bcd = "=?ISO-8859-1?Q?T=FA_N=F4ng?=";
            byte[] isoBytes = bcd.getBytes();
            System.out.println(new String(isoBytes, "UTF8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(isotoutf8.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
