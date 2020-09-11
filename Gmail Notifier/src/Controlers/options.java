/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlers;
import java.io.*;
import java.util.Calendar;
/**
 *
 * @author kienh_000
 */
public class options {
    private String LastCheckDate;
    private boolean StartWithWindows;
    private boolean OpenMainFormAtStartUp;
    private boolean AutoNotify;
    private boolean AutoCheck;
    private int SecondToCheck;
    
    private File f;
    private fileHandle fh;
    
    public options() {
        f = new File("setting.theseven");
        fh = new fileHandle(f);
        if(!fh.fileIsExist()) {
            fh.fileCreate();
            //initialize for first time use
            Calendar c = Calendar.getInstance();
            String file_content = c.getTime() + "//0//1//1//1//300";
            fh.Encrypt(file_content);
        }
    }
    
    public String getLastCheckDate() {
        return this.LastCheckDate;
    }
    
    public void setLastCheckDate(String date) {
        this.LastCheckDate = date;
    }
    
    public boolean getStartWithWindows() {
        return this.StartWithWindows;
    }
    
    public void setStartWithWindows(boolean start) {
        this.StartWithWindows = start;
    }
    
    public boolean getOpenMainFormAtStartUp() {
        return this.OpenMainFormAtStartUp;
    }
    
    public void setOpenMainFormAtStartUp(boolean open) {
        this.OpenMainFormAtStartUp = open;
    }
    
    public boolean getAutoNotify() {
        return this.AutoNotify;
    }
    
    public void setAutoNotify(boolean auto) {
        this.AutoNotify = auto;
    }
    
    public boolean getAutoCheck() {
        return this.AutoCheck;
    }
    
    public void setAutoCheck(boolean auto) {
        this.AutoCheck = auto;
    }
    
    public int getSecondToCheck() {
        return this.SecondToCheck;
    }
    
    public void setSecondToCheck(int second) {
        this.SecondToCheck = second;
    }
    
    public void getData() {
        //get data from file
        String content = fh.Decrypt();
        String[] split = content.split("//");
        this.LastCheckDate = split[0];
        this.StartWithWindows = split[1].equals("1");
        this.OpenMainFormAtStartUp = split[2].equals("1");
        this.AutoNotify = split[3].equals("1");
        this.AutoCheck = split[4].equals("1");
        this.SecondToCheck = Integer.parseInt(split[5]);
    }
    
    public void setData() {
        String file_content = this.LastCheckDate + "//";
        file_content += (this.StartWithWindows?"1":"0") + "//";
        file_content += (this.OpenMainFormAtStartUp?"1":"0") + "//";
        file_content += (this.AutoNotify?"1":"0") + "//";
        file_content += (this.AutoCheck?"1":"0") + "//";
        file_content += this.SecondToCheck;
        fh.Encrypt(file_content);
    }
}
