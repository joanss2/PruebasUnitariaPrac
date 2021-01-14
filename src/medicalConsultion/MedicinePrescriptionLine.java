package medicalConsultion;

import data.ProductID;

public class MedicinePrescriptionLine {

    private ProductID id;
    private TakingGuideline line;

    public MedicinePrescriptionLine(ProductID prID, TakingGuideline tkLine){
        this.id = prID;
        this.line = tkLine;
    }

    public ProductID getId(){
        return this.id;
    }
    public TakingGuideline getLine(){
        return this.line;
    }

}
