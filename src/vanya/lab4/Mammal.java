package vanya.lab4;

import java.io.Serializable;

public class Mammal implements Serializable {
     private String name,family;
     private float maxSpeed;

    public Mammal(String name, String family, float naxSpeed) {
        this.name = name;
        this.family = family;
        this.maxSpeed = naxSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", maxSpeed=" + maxSpeed;
    }
}
