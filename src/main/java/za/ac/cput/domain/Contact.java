package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Contact {
    @Id
    private int id;
    private String email;
    private String phone;

    protected Contact(){}

    private Contact(Builder builder){
        this.id = builder.id;
        this.email = builder.email;
        this.phone = builder.phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getId() { return id; }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static class Builder {
        private int id;
        private String email;
        private String phone;

        public Builder setId(int id){
            this.id = id;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder copy(Contact contact){
            this.id = contact.getId();
            this.email = contact.email;
            this.phone = contact.phone;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }
    }
}
