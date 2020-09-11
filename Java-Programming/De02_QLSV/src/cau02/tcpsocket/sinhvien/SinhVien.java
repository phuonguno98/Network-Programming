/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.tcpsocket.sinhvien;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author UNO
 */
public class SinhVien implements Serializable{
    private String tensv;
    private String masv;
    private Date ngaysinh;
    private String quequan;

    public SinhVien() {
    }

    public SinhVien(String tensv, String masv, Date ngaysinh, String quequan) {
        this.tensv = tensv;
        this.masv = masv;
        this.ngaysinh = ngaysinh;
        this.quequan = quequan;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    @Override
    public String toString() {
        return "SinhVien{" + "tensv=" + tensv + ", masv=" + masv + ", ngaysinh=" + ngaysinh + ", quequan=" + quequan + '}';
    }
}
