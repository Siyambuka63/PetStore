package za.ac.cput.domain.user;

public class Address {
    private long addressID;
    private String streetAddress;
    private String complexDetail;
    private String suburb;
    private String city;
    private Type type;

    private Address() {}

    private Address(Builder builder) {
        this.addressID = builder.addressID;
        this.streetAddress = builder.streetAddress;
        this.complexDetail = builder.complexDetail;
        this.suburb = builder.suburb;
        this.city = builder.city;
        this.type = builder.type;

    }

    public Long getAddressID() {
        return addressID;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getComplexDetail() {
        return complexDetail;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getCity() {
        return city;
    }

    public String getType() {
        return type;
    }


    @Override
    public String toString() {
        return "Address{" +
                "addressID=" + addressID +
                ", streetAddress='" + streetAddress + '\'' +
                ", complexDetail='" + complexDetail + '\'' +
                ", suburb='" + suburb + '\'' +
                ", city='" + city + '\'' +
                ", type='" + type + '\'' +
                '}';
    }


    public static class Builder {

        private long addressID;
        private String streetAddress;
        private String complexDetail;
        private String suburb;
        private String city;
        private Type type;

        public Builder addressID(Long addressID) {
            this.addressID = addressID;
            return this;
        }

        public Builder streetAddress(String streetAddress) {
            this.streetAddress = streetAddress;
            return this;
        }

        public Builder complexDetail(String complexDetail) {
            this.complexDetail = complwxDetail;
            return this;
        }

        public Builder suburb(String suburb) {
            this.suburb = suburb;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder type(Type type) {
            this.type = type;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
