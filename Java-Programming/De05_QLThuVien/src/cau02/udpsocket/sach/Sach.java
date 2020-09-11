/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.udpsocket.sach;

import java.io.Serializable;

/**
 *
 * @author UNO
 */
public class Sach implements Serializable {

    private int masach;
    private String tensach;
    private String nhaxb;
    private double soluongtong;
    private double soluongmuon;

    public Sach() {
    }

    public Sach(int masach, String tensach, String nhaxb, double soluongtong, double soluongmuon) {
        this.masach = masach;
        this.tensach = tensach;
        this.nhaxb = nhaxb;
        this.soluongtong = soluongtong;
        this.soluongmuon = soluongmuon;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getNhaxb() {
        return nhaxb;
    }

    public void setNhaxb(String nhaxb) {
        this.nhaxb = nhaxb;
    }

    public double getSoluongtong() {
        return soluongtong;
    }

    public void setSoluongtong(double soluongtong) {
        this.soluongtong = soluongtong;
    }

    public double getSoluongmuon() {
        return soluongmuon;
    }

    public void setSoluongmuon(double soluongmuon) {
        this.soluongmuon = soluongmuon;
    }

    @Override
    public String toString() {
        return "Sach{" + "masach=" + masach + ", tensach=" + tensach + ", nhaxb=" + nhaxb + ", soluongtong=" + soluongtong + ", soluongmuon=" + soluongmuon + '}';
    }
}
