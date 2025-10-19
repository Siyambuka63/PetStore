package za.ac.cput.domain.user;

import jakarta.persistence.*;
import lombok.Setter;
import za.ac.cput.domain.review.Review;
import za.ac.cput.domain.user.Role;

import java.util.List;

@Entity
@Setter
public class User {
    @Id
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String password;
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_email"),
            inverseJoinColumns = @JoinColumn(name = "roles_description")
    )
    private List<Role> roles;

    @OneToMany
    private List<Review> reviews;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
    private Card card;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_id")
    private Address shippingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_id")
    private Address billingAddress;

    protected User(){}

    private User(Builder builder){
        email = builder.email;
        firstName = builder.firstName;
        middleName = builder.middleName;
        lastName = builder.lastName;
        password = builder.password;
        phoneNumber = builder.phoneNumber;
        reviews = builder.reviews;
        card = builder.card;
        shippingAddress = builder.shippingAddress;
        billingAddress = builder.billingAddress;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Role> getRoles() {return roles;}

    public List<Review> getReviews() {
        return reviews;
    }

    public Card getCard() {
        return card;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", roles=" + roles +
                ", reviews=" + reviews +
                ", card=" + card +
                ", shippingAddress=" + shippingAddress +
                ", billingAddress=" + billingAddress +
                '}';
    }

    public static class Builder {
        private String email;
        private String firstName;
        private String middleName;
        private String lastName;
        private String password;
        private String phoneNumber;
        private List<Role> roles;
        private List<Review> reviews;
        private Card card;
        private Address shippingAddress;
        private Address billingAddress;

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setRoles(List<Role> roles) {
            this.roles = roles;
            return this;
        }

        public Builder setReviews(List<Review> reviews) {
            this.reviews = reviews;
            return this;
        }

        public Builder setCard(Card card) {
            this.card = card;
            return this;
        }

        public Builder setShippingAddress(Address shippingAddress) {
            this.shippingAddress = shippingAddress;
            return this;
        }

        public Builder setBillingAddress(Address billingAddress) {
            this.billingAddress = billingAddress;
            return this;
        }

        public Builder copy(User user){
            this.email = user.email;
            this.firstName = user.firstName;
            this.middleName = user.middleName;
            this.lastName = user.lastName;
            this.password = user.password;
            this.phoneNumber = user.phoneNumber;
            this.roles = user.roles;
            this.reviews = user.reviews;
            this.card = user.card;
            this.shippingAddress = user.shippingAddress;
            this.billingAddress = user.billingAddress;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}