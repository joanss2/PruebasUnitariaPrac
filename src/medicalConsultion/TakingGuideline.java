package medicalConsultion;

public class TakingGuideline {
    private dayMoment dMoment;
    private float duration;
    private String instructions;
    private Posology posology;

    public TakingGuideline(dayMoment dM, float du, String i, float d, float f, FqUnit u) {
        this.dMoment = dM;
        this.duration = du;
        this.instructions = i;
        this.posology = new Posology(d, f, u);
    }

    // Getters and setters
    public void setdMoment(dayMoment DM) {
        this.dMoment = DM;
    }

    public void setDuration(float du) {
        this.duration = du;
    }

    public void setInstructions(String i) {
        this.instructions = i;
    }

    public void setPosology(Posology po) {
        this.posology = po;
    }

    public dayMoment getdMoment() {
        return this.dMoment;
    }

    public float getDuration() {
        return this.duration;
    }

    public String getInstructions() {
        return this.instructions;
    }

    public Posology getPosology() {
        return this.posology;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TakingGuideline gline1 = (TakingGuideline) o;
        return dMoment.equals(gline1.dMoment) && duration == gline1.duration && instructions.equals(gline1.instructions) && posology.equals(gline1.posology);
    }
}
