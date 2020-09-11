/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testUI;

import java.io.IOException;
import java.util.Calendar;

/**
 *
 * @author kienh_000
 */
public class testtime {
    
    public static void main(String[] args) throws IOException {
        Calendar c = Calendar.getInstance();
            String file_content = c.getTime() + "//0//1//1//1//300";
            System.out.println(file_content);
    }
}
