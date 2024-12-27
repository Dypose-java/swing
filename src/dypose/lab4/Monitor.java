package dypose.lab4;

import java.io.Serializable;

public class Monitor implements Serializable {
    private String model,variety;
    private float diagonal;

    public Monitor(String model, String variety, float diagonal) {
        this.model = model;
        this.variety = variety;
        this.diagonal = diagonal;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public float getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(float diagonal) {
        this.diagonal = diagonal;
    }

    @Override
    public String toString() {
        return
                "model='" + model + '\'' +
                ", variety='" + variety + '\'' +
                ", diagonal=" + diagonal ;
    }
}
