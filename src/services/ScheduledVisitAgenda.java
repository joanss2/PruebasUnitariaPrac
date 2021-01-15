package services;

import data.HealthCardID;
import data.ProductID;

import java.util.ArrayList;
import java.util.List;

public interface ScheduledVisitAgenda {

    void add_pacient(HealthCardID nouID);

    HealthCardID getHealthCardID();

}