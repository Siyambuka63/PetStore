package za.ac.cput.domain.user;
//222319437
//Athenkosi Mekana
//Address Domain

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class Address {
    @Id
    private long addressID;
    private String streetAddress;
    private String complexDetail;
    private String suburb;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String city;
    private String postalCode;

    protected Address() {}

    public Address(Builder builder) {
        this.addressID = builder.addressID;
        this.streetAddress = builder.streetAddress;
        this.complexDetail = builder.complexDetail;
        this.suburb = builder.suburb;
        this.type = builder.type;
        this.city = builder.city;
        this.postalCode = builder.postalCode;

    }

    @Override
    public String toString() {
        return "Address{" +
                "addressID=" + addressID +
                ", streetAddress='" + streetAddress + '\'' +
                ", complexDetail='" + complexDetail + '\'' +
                ", suburb='" + suburb + '\'' +
                ", type=" + type +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }

    public long getAddressID() {
        return addressID;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getComplexDetail() {
        return complexDetail;
    }

    public Type getType() {
        return type;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public static class Builder {
        private long addressID;
        private String streetAddress;
        private String complexDetail;
        private String suburb;
        private Type type;
        private String city;
        private String postalCode;

        public Builder setAddressID(long addressID) {
            this.addressID = addressID;
            return this;
        }

        public Builder setStreetAddress(String streetAddress) {
            this.streetAddress = streetAddress;
            return this;
        }

        public Builder setComplexDetail(String complexDetail) {
            this.complexDetail = complexDetail;
            return this;
        }

        public Builder setSuburb(String suburb) {
            this.suburb = suburb;
            return this;
        }

        public Builder setType(Type type) {
            this.type = type;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder copy(Address address) {
            this.addressID = address.addressID;
            this.streetAddress = address.streetAddress;
            this.complexDetail = address.complexDetail;
            this.suburb = address.suburb;
            this.type = address.type;
            this.city = address.city;
            this.postalCode = address.postalCode;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}