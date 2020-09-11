/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.tcpsocket.mathang;

import java.io.Serializable;

/**
 *
 * @author UNO
 */
public class MatHang implements Serializable {
    private int mahang;
    private String tenhang;
    private double giaban;
    private int soluongtonkho;
    public static final String INFO_PRODUCT_FORMAT = String.format("%7s %25s %10s %15s", "Mã Hàng", "Tên Hàng", "Số Lượng", "Đơn Giá");

    public MatHang() {

    }

    public MatHang(int mahang, String tenhang, double giaban, int soluongtonkho) {
        this.mahang = mahang;
        this.tenhang = tenhang;
        this.giaban = giaban;
        this.soluongtonkho = soluongtonkho;
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

    public double getGiaban() {
        return giaban;
    }

    public void setGiaban(double giaban) {
        this.giaban = giaban;
    }

    public int getSoluongtonkho() {
        return soluongtonkho;
    }

    public void setSoluongtonkho(int soluongtonkho) {
        this.soluongtonkho = soluongtonkho;
    }

    @Override
    public String toString() {
        return "MatHang{" + "mahang=" + mahang + ", tenhang=" + tenhang + ", giaban=" + giaban + ", soluongtonkho=" + soluongtonkho + '}';
    }
}
