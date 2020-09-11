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
public interface DictionaryInterface extends Remote {

    public String searchVieBasedOnEng(String word_eng) throws RemoteException;

    public String searchEngBasedOnVie(String word_vie) throws RemoteException;

    public String meaningEngWord(String word_eng) throws RemoteException;
}
