/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.tcpsocket.cauthu;

import java.io.Serializable;

/**
 *
 * @author UNO
 */
public class CauThu implements Serializable {

    private int macauthu;
    private String tencauthu;
    private int namsinh;
    private String vitri;
    private double luong;

    public CauThu() {
    }

    public CauThu(int macauthu, String tencauthu, int namsinh, String vitri, double luong) {
        this.macauthu = macauthu;
        this.tencauthu = tencauthu;
        this.namsinh = namsinh;
        this.vitri = vitri;
        this.luong = luong;
    }

    public int getMacauthu() {
        return macauthu;
    }

    public void setMacauthu(int macauthu) {
        this.macauthu = macauthu;
    }

    public String getTencauthu() {
        return tencauthu;
    }

    public void setTencauthu(String tencauthu) {
        this.tencauthu = tencauthu;
    }

    public int getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(int namsinh) {
        this.namsinh = namsinh;
    }

    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    @Override
    public String toString() {
        return "CauThu{" + "macauthu=" + macauthu + ", tencauthu=" + tencauthu + ", namsinh=" + namsinh + ", vitri=" + vitri + ", luong=" + luong + '}';
    }
}
