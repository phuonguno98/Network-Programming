/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.rmi;

import cau02.rmi.mathang.MatHang;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author UNO
 */
public interface InterfaceMiniGame extends Remote {

    public MatHang chooseMatHangRandom() throws RemoteException;

    public int checkClientAnswer(double clientprice, MatHang mathang) throws RemoteException;
}
