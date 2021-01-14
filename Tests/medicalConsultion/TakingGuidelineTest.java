package medicalConsultion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TakingGuidelineTest {


    TakingGuideline instruc1;
    TakingGuideline instruc2;

    @BeforeEach
    public void setUp(){
        instruc1=new TakingGuideline(dayMoment.AFTERLUNCH,40f,"Montserrat, cuidado",2f,4f,FqUnit.DAY);
        instruc2=new TakingGuideline(dayMoment.BEFOREDINNER,69f,"tu lo que ere una ladrona",2f,5f,FqUnit.MONTH);
    }

    @Test
    void setdMoment() {
        instruc1.setdMoment(dayMoment.DURINGLUNCH);
        assertEquals(instruc1.getdMoment(),dayMoment.DURINGLUNCH);
        assertNotEquals(instruc2.getdMoment(),dayMoment.DURINGLUNCH);
    }

    @Test
    void setDuration() {
        instruc1.setDuration(15f);
        assertEquals(instruc1.getDuration(),15f);
        assertNotEquals(instruc2.getDuration(),4f);
    }

    @Test
    void setInstructions() {
        instruc1.setInstructions("QUIEN CREO EL TRAP ANUEL");
        assertNotEquals(instruc1.getInstructions(),"tu lo que ere una ladrona");
        assertEquals(instruc2.getInstructions(),"tu lo que ere una ladrona");
    }

    @Test
    void setPosology() {
        instruc2.setPosology(new Posology(3f,4f,FqUnit.MONTH));
        assertTrue(instruc2.getPosology().equals(new Posology(3f,4f,FqUnit.MONTH)));
        assertNotEquals(instruc1.getPosology(),new Posology(1f,3,FqUnit.HOUR));
    }

    @Test
    void getdMoment() {
        assertEquals(instruc1.getdMoment(),dayMoment.AFTERLUNCH);
        assertEquals(instruc2.getdMoment(),dayMoment.BEFOREDINNER);
    }

    @Test
    void getDuration() {
        assertEquals(instruc1.getDuration(),40f);
        assertEquals(instruc2.getDuration(),69f);
    }

    @Test
    void getInstructions() {
        assertEquals(instruc1.getInstructions(),"Montserrat, cuidado");
        assertEquals(instruc2.getInstructions(),"tu lo que ere una ladrona");
    }

    @Test
    void getPosology() {
        assertTrue(instruc1.getPosology().equals(new Posology(2f,4f,FqUnit.DAY)));
        assertTrue(instruc2.getPosology().equals(new Posology(2f,5f,FqUnit.MONTH)));

    }

}