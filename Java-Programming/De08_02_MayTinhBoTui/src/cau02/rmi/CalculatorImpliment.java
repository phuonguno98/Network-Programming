/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.rmi;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author UNO
 */
public class CalculatorImpliment extends UnicastRemoteObject implements CalculatorInterface {

    public CalculatorImpliment() throws RemoteException {
    }

    @Override
    public float add(float a, float b) throws RemoteException {
        return a + b;
    }

    @Override
    public float subtract(float a, float b) throws RemoteException {
        return a - b;
    }

    @Override
    public float multiple(float a, float b) throws RemoteException {
        return a * b;
    }

    @Override
    public float divide(float a, float b) throws RemoteException {
        return a / b;
    }
}
