package RMI;

// Creating a Search interface

import PowerPeople.flyVillain;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Search extends Remote {
    public boolean query(String search) throws RemoteException;

    public flyVillain sendVillain() throws RemoteException;

    public void sendBattle(ArrayList<Object> battleArray) throws RemoteException;
}

