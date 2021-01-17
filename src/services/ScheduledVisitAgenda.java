package services;

import data.HealthCardID;

public interface ScheduledVisitAgenda {                         // All of this methods are implemented in  medicalConsultionTest

    boolean emptyAgenda();                                     // Method that comprove if the agenda is empty

    void addPacient(HealthCardID nouID);                       // Method to add patients

    HealthCardID getHealthCardID();                             // Method that return the firts pacient to enter to the consultion

}
