package za.ac.cput.domain;

public class Contact {
    private String email;
    private String phone;

    private Contact(){}

    private Contact(Builder builder){
        this.email = builder.email;
        this.phone = builder.phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static class Builder {
        private String email;
        private String phone;

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder copy(Contact contact){
            this.email = contact.email;
            this.phone = contact.phone;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }
    }
}
