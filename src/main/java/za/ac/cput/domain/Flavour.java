package za.ac.cput.domain;

public class Flavour {
  private String flavourID;
  private String flavourName;

    private Flavour() {

    }

    private Flavour(Flavour.Builder builder){

    }

    public String getFlavourID() {
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
        private String flavourID;
        private String flavourName;

        public void setFlavourID(String flavourID) {
            this.flavourID = flavourID;
        }

        public void setFlavourName(String FlavourName) {
            this.flavourName = flavourName;
        }

        public Flavour copy(Flavour flavour){
            this.flavourID = flavour.flavourID;
            this.flavourName = flavour.flavourName;
            return this.build();
        }

        public Flavour build(){return new Flavour(this);}
    }
}
