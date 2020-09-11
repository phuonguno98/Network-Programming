/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlers;

/**
 *
 * @author kienh_000
 */
public class date {
    
    public int day;
    public int month;
    public int year;
    public String time;
    public int hour;
    public int minute;
    public int second;
    public String thu;
    public int thu_int;
    
    public date() {
        
    }
    
    public date(String input) {
        String[] explode = input.split(" ");
        /* explode[0] = thu
         * explode[1] = month
         * explode[2] = day
         * explode[3] = time
         * explode[4] = 'ICT'
         * explode[5] = year
         */
        String thu = explode[0];
        this.thu = thu;
        switch(thu) {
            case "Mon": this.thu_int = 2; break;
            case "Tue": this.thu_int = 3; break;
            case "Wed": this.thu_int = 4; break;
            case "Thu": this.thu_int = 5; break;
            case "Fri": this.thu_int = 6; break;
            case "Sat": this.thu_int = 7; break;
            case "Sun": this.thu_int = 8; break;
        }
        String month = explode[1];
        switch(month) {
            case "Jan": this.month = 1; break;
            case "Feb": this.month = 2; break;
            case "Mar": this.month = 3; break;
            case "Apr": this.month = 4; break;
            case "May": this.month = 5; break;
            case "Jun": this.month = 6; break;
            case "Jul": this.month = 7; break;
            case "Aug": this.month = 8; break;
            case "Sep": this.month = 9; break;
            case "Oct": this.month = 10; break;
            case "Nov": this.month = 11; break;
            case "Dec": this.month = 12; break;
        }
        int day = Integer.parseInt(explode[2]);
        this.day = day;
        String time = explode[3];
        this.time = time;
        String[] time_explode = time.split(":");
        this.hour = Integer.parseInt(time_explode[0]);
        this.minute = Integer.parseInt(time_explode[1]);
        this.second = Integer.parseInt(time_explode[2]);
        this.year = Integer.parseInt(explode[5]);
    }
    
    public String getdate() {
        return this.thu + " " + this.day + "/" + this.month + "/" + this.year + " " + this.time;
    }
    
    /**
     * Compare two date: DDD, MMM DD HH:MM:SS YYYY 
     * @param date2
     * @return integer value
     */
    public int equal(date date2) {
        if(this.year<date2.year) {
            return -1; //this < date2
        }
        else if(this.year > date2.year) {
            return 1; //this > date2
        }
        else {
            //year is equal
            if(this.month<date2.month) {
                return -1;
            }
            else if(this.month > date2.month) {
                return 1;
            }
            else {
                if(this.day < date2.day) {
                    return -1;
                }
                else if(this.day > date2.day) {
                    return 1;
                }
                else {
                    if(this.hour < date2.hour) {
                        return -1;
                    }
                    else if(this.hour > date2.hour) {
                        return 1;
                    }
                    else {
                        if(this.minute < date2.minute) {
                            return -1;
                        }
                        else if(this.minute > date2.minute) {
                            return 1;
                        }
                        else {
                            if(this.second < date2.second) {
                                return -1;
                            }
                            else if(this.second > date2.second) {
                                return 1;
                            }
                            else {
                                return 0;
                            }
                        }
                    }
                }
            }
        }
    }
}
