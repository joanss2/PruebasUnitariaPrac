package services;

import data.HealthCardID;
import data.ProductID;

import java.util.ArrayList;
import java.util.List;

public class ScheduledVisitAgenda {
    private List<HealthCardID> llista_pacients;

    public ScheduledVisitAgenda(){
        this.llista_pacients = new ArrayList<>();
    }

    public void add_pacient(HealthCardID nouid) {
        llista_pacients.add(nouid);
    }

    public HealthCardID getHealthCardID(){//throws HealthCardException{
        return llista_pacients.remove(0);
    }
}
