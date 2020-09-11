/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.udpsocket.message;

import cau02.udpsocket.sach.Sach;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author UNO
 */
public class Message implements Serializable {

    public static final int HIEN_THI_THONG_TIN_SACH = 1;
    public static final int MUON_SACH = 2;
    public static final int TRA_SACH = 3;
    private ArrayList<Sach> listSachs;
    private Sach sach;
    private int chucnang;

    public ArrayList<Sach> getListSachs() {
        return listSachs;
    }

    public void setListSachs(ArrayList<Sach> listSachs) {
        this.listSachs = listSachs;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public int getChucnang() {
        return chucnang;
    }

    public void setChucnang(int chucnang) {
        this.chucnang = chucnang;
    }
}
