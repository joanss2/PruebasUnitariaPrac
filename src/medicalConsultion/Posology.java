package medicalConsultion;

public class Posology { // A class that represents the posology of a medicine
    private float dose;
    private float freq;
    private FqUnit freqUnit;

    public Posology(float d, float f, FqUnit u) {
        this.dose = d;
        this.freq = f;
        this.freqUnit = u;
    }

    // Getters and setters
    public void setDose(float d) {
        this.dose = d;
    }

    public void setFreq(float f) {
        this.freq = f;
    }

    public void setFreqUnit(FqUnit u) {
        this.freqUnit = u;
    }

    public float getDose() {
        return this.dose;
    }

    public float getFreq() {
        return this.freq;
    }

    public FqUnit getFreqUnit() {
        return this.freqUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posology po1 = (Posology) o;
        return dose == po1.dose && freq == po1.freq && freqUnit.equals(po1.freqUnit);
    }


}