package services;

import data.HealthCardID;

public interface ScheduledVisitAgenda {

    boolean empty_agenda();

    void add_pacient(HealthCardID nouID);

    HealthCardID getHealthCardID();

}