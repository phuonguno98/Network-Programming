/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.tcpsocket.khachhang;

import java.io.Serializable;

/**
 *
 * @author UNO
 */
public class KhachHang implements Serializable {

    private int makhachhang;
    private String tenkhachhang;
    private String loaiphong;

    public KhachHang() {
    }

    public KhachHang(int makhachhang, String tenkhachhang, String loaiphong) {
        this.makhachhang = makhachhang;
        this.tenkhachhang = tenkhachhang;
        this.loaiphong = loaiphong;
    }

    public int getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(int makhachhang) {
        this.makhachhang = makhachhang;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public String getLoaiphong() {
        return loaiphong;
    }

    public void setLoaiphong(String loaiphong) {
        this.loaiphong = loaiphong;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "makhachhang=" + makhachhang + ", tenkhachhang=" + tenkhachhang + ", loaiphong=" + loaiphong + '}';
    }

}
