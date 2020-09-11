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
public class Sach implements Serializable{

    private int masach;
    private String tensach;
    private String nguoimuon;

    public Sach() {
        this.nguoimuon = "chua muon";
    }

    public Sach(int masach, String tensach, String nguoimuon) {
        this.masach = masach;
        this.tensach = tensach;
        this.nguoimuon = nguoimuon;
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

    public String getNguoimuon() {
        return nguoimuon;
    }

    public void setNguoimuon(String nguoimuon) {
        this.nguoimuon = nguoimuon;
    }

    @Override
    public String toString() {
        return "Sach{" + "masach=" + masach + ", tensach=" + tensach + ", nguoimuon=" + nguoimuon + '}';
    }
}
