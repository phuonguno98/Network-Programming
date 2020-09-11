/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlers;
import java.util.regex.*;
/**
 *
 * @author kienh_000
 */
public class htmlTagsRemove {
    String htmlString;

    public htmlTagsRemove(String htmlString) {
        this.htmlString = htmlString;
    }
    
    public String remove() {
        String temp = this.htmlString;
        String[] patterns = {"<style>[^>]*</style>","<[^>]*>","<[^>]*/>","</[^>]*>"};
        for (String pattern : patterns) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(temp);
            temp = m.replaceAll("");
        }
        temp = temp.replaceAll("\\s+", " ");
        return temp;
    }
    
}
