/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau01.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author UNO
 */
public interface InterfaceTamGiac extends Remote{
    public boolean checkTriangle(int a, int b, int c) throws RemoteException;
}
