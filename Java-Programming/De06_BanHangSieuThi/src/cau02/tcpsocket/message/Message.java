/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.tcpsocket.message;

import cau02.tcpsocket.mathang.MatHang;
import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author UNO
 */
public class Message implements Serializable{
    public static final int TIM_KIEM = 1;
    public static final int LAP_HOA_DON = 2;
    
    private String message;
    private MatHang mathang;
    Map<Integer, Integer> mapMatHang;
    
    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MatHang getMathang() {
        return mathang;
    }

    public void setMathang(MatHang mathang) {
        this.mathang = mathang;
    }
    
    public Map<Integer, Integer> getMapMatHang() {
        return mapMatHang;
    }

    public void setMapMatHang(Map<Integer, Integer> mapMatHang) {
        this.mapMatHang = mapMatHang;
    }
}
