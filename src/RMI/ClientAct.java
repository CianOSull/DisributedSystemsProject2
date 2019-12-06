package RMI;

import Factory.flyHeroFactory;
import PowerPeople.flyHero;
import PowerPeople.flyVillain;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ClientAct extends UnicastRemoteObject implements Client {
    private flyHeroFactory factory = new flyHeroFactory();
    private Search access;

    public ClientAct(Search access) throws RemoteException {
        super();
        this.access = access;
    }

    public void alert() throws RemoteException {
        flyVillain result = access.sendVillain();

        System.out.println("Name of the villain: " + result.getName());

        ArrayList<Object> peopleList = new ArrayList<Object>();
        flyHero her = factory.getHero();
        peopleList.add(result);
        peopleList.add(her);

        access.sendBattle(peopleList);
    }
}
