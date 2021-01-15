package services;

import data.HealthCardID;

public interface ScheduledVisitAgenda {

    void add_pacient(HealthCardID nouID);

    HealthCardID getHealthCardID();

}