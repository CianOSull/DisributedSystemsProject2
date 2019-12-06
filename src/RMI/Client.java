package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote {
    private void serverPoll( Search access){};

    public void alert() throws RemoteException;
}
