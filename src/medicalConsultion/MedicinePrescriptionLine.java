package medicalConsultion;

import data.ProductID;

public class MedicinePrescriptionLine {

    private final ProductID id;
    private final TakingGuideline line;

    public MedicinePrescriptionLine(ProductID prID, TakingGuideline tkLine) {
        this.id = prID;
        this.line = tkLine;
    }

    public ProductID getId() {
        return this.id;
    }

    public TakingGuideline getLine() {
        return this.line;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicinePrescriptionLine medPLine1 = (MedicinePrescriptionLine) o;
        return id.equals(medPLine1.id) && line.equals(medPLine1.line);
    }
}
