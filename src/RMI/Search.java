package RMI;

// Creating a Search interface

import PowerPeople.flyVillain;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Search extends Remote {
    public void query(int poll) throws RemoteException;

    public flyVillain sendVillain() throws RemoteException;

    public void sendBattle(ArrayList<Object> battleArray) throws RemoteException;

    public void addClient(String rmiCon, String rmiName) throws RemoteException, MalformedURLException, NotBoundException;
}

