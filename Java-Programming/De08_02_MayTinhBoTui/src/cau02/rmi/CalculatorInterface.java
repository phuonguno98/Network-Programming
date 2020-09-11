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
public interface CalculatorInterface extends Remote{
    public float add(float a, float b) throws RemoteException;
    public float subtract(float a, float b) throws RemoteException;
    public float multiple(float a, float b) throws RemoteException;
    public float divide(float a, float b) throws RemoteException;
}
