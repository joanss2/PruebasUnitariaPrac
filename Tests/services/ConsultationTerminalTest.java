package services;

import data.HealthCardID;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConsultationTerminalTest {

    private static class ScheduledAgendaDoble implements ScheduledVisitAgenda{

        List<HealthCardID> listPacients = new ArrayList();

        @Override
        public void add_pacient(HealthCardID nouID) {
            listPacients.add(nouID);
        }

        @Override
        public HealthCardID getHealthCardID() {
            return listPacients.remove(0);
        }
    }

    @Test
    void initRevision() {
    }

    @Test
    void initPrescriptionEdition() {
    }

    @Test
    void searchForProducts() {
    }

    @Test
    void selectProduct() {
    }

    @Test
    void enterMedicineGuidelines() {
    }

    @Test
    void enterTreatmentEndingDate() {
    }

    @Test
    void sendePrescription() {
    }

    @Test
    void printePresc() {
    }
}