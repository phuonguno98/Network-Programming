/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau01.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author UNO
 */
public class StringProcessImpliment extends UnicastRemoteObject implements StringProcessInterface {
    
    private static final long serialVersionUID = 1L;

    public StringProcessImpliment() throws RemoteException{
    }

    @Override
    public int lengthOfString(String str) {
        int length = str.length();
        return length;
    }
}
