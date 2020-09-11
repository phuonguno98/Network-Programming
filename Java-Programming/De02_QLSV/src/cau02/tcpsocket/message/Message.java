/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.tcpsocket.message;

import cau02.tcpsocket.sinhvien.SinhVien;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author UNO
 */
public class Message implements Serializable{
    public static final int XEM_DS_SINH_VIEN = 1;
    public static final int THEM_SINH_VIEN = 2;
    public static final int XEM_THONG_TIN_SV = 3;
    public static final int LOC_SINH_VIEN = 4;
    
    String message;
    SinhVien sinhvien;
    ArrayList<SinhVien> listSinhViens;
    String typekeyword;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SinhVien getSinhvien() {
        return sinhvien;
    }

    public void setSinhvien(SinhVien sinhvien) {
        this.sinhvien = sinhvien;
    }

    public ArrayList<SinhVien> getListSinhViens() {
        return listSinhViens;
    }

    public void setListSinhViens(ArrayList<SinhVien> listSinhViens) {
        this.listSinhViens = listSinhViens;
    }

    public String getTypekeyword() {
        return typekeyword;
    }

    public void setTypekeyword(String typekeyword) {
        this.typekeyword = typekeyword;
    }
}
