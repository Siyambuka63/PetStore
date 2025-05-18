package za.ac.cput.domain.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Flavour {
    @Id
    private int flavourID;
    private String flavourName;

    protected Flavour() {}

    private Flavour(Flavour.Builder builder) {
        this.flavourID = builder.flavourID;
        this.flavourName = builder.flavourName;
    }

    public int getFlavourID() {
        return flavourID;
    }

    public String getflavourName() {
        return flavourName;
    }

    @Override
    public String toString() {
        return "Flavour{" +
                "flavourID=" + flavourID +
                ", flavourName='" + flavourName + '\'' +
                '}';
    }

    public static class Builder {
        private int flavourID;
        private String flavourName;

        public Builder setFlavourID(int flavourID) {
            this.flavourID = flavourID;
            return this;
        }

        public Builder setFlavourName(String FlavourName) {
            this.flavourName = flavourName;
            return this;
        }

        public Flavour copy(Flavour flavour) {
            this.flavourID = flavour.flavourID;
            this.flavourName = flavour.flavourName;
            return this.build();
        }

        public Flavour build() {
            return new Flavour(this);
        }
    }
}
