/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau01.rmi;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

/**
 *
 * @author UNO
 */
public class ImplementTamGiac extends UnicastRemoteObject implements InterfaceTamGiac {

    public ImplementTamGiac() throws RemoteException {
    }

    @Override
    public boolean checkTriangle(int a, int b, int c) throws RemoteException {
        if (Math.abs(b - c) < a && a < b + c
                && Math.abs(a - c) < b && b < a + c
                && Math.abs(a - b) < c && c < a + b) {
            return true;
        }
        return false;
    }

}
