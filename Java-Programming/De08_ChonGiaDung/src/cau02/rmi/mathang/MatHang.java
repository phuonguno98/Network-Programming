/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.rmi.mathang;

import java.io.Serializable;

/**
 *
 * @author UNO
 */
public class MatHang implements Serializable {

    private static final long serialVersionUID = 1L;
    private int mahang;
    private String tenhang;
    private double trigia;

    public MatHang() {
    }

    public MatHang(int mahang, String tenhang, double trigia) {
        this.mahang = mahang;
        this.tenhang = tenhang;
        this.trigia = trigia;
    }

    public int getMahang() {
        return mahang;
    }

    public void setMahang(int mahang) {
        this.mahang = mahang;
    }

    public String getTenhang() {
        return tenhang;
    }

    public void setTenhang(String tenhang) {
        this.tenhang = tenhang;
    }

    public double getTrigia() {
        return trigia;
    }

    public void setTrigia(double trigia) {
        this.trigia = trigia;
    }

    @Override
    public String toString() {
        return "MatHang{" + "mahang=" + mahang + ", tenhang=" + tenhang + ", trigia=" + trigia + '}';
    }
}
