/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.rmi;

import java.rmi.*;

/**
 *
 * @author UNO
 */
public interface WordInterface extends Remote {

    public String convertAcronymToFull(String acronym) throws RemoteException;

    public String convertFullToAcronym(String word_full) throws RemoteException;
}
