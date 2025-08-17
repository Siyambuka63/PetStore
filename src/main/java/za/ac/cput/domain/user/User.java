package za.ac.cput.domain.user;

import jakarta.persistence.*;
import za.ac.cput.domain.review.Review;
import za.ac.cput.domain.Product;

import java.util.List;

@Entity
public class User {
    @Id
    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String password;
    @ManyToMany(mappedBy = "users")
    private List<Product> wishlistItems;
    @OneToMany
    private List<Review> reviews;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
    private Card card;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address shippingAddress;
    @JoinColumn(name = "address_id")
    private Address billingAddress;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    protected User(){}

    private User(Builder builder){
        id = builder.userID;
        firstName = builder.firstName;
        middleName = builder.middleName;
        lastName = builder.lastName;
        password = builder.password;
        wishlistItems = builder.wishlistItems;
        reviews = builder.reviews;
        card = builder.card;
        shippingAddress = builder.shippingAddress;
        billingAddress = builder.billingAddress;
        contact = builder.contact;
    }

    public long getId() {
        return id;
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

    public List<Product> getWishlistItems() {
        return wishlistItems;
    }

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

    public Contact getContact() {
        return contact;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", wishlistItems=" + wishlistItems +
                ", reviews=" + reviews +
                ", card=" + card +
                ", shippingAddress=" + shippingAddress +
                ", billingAddress=" + billingAddress +
                ", contact=" + contact +
                '}';
    }

    public static class Builder {
        private long userID;
        private String firstName;
        private String middleName;
        private String lastName;
        private String password;
        private List<Product> wishlistItems;
        private List<Review> reviews;
        private Card card;
        private Address shippingAddress;
        private Address billingAddress;
        private Contact contact;

        public Builder setUserID(long userID) {
            this.userID = userID;
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

        public Builder setWishlistItems(List<Product> wishlistItems) {
            this.wishlistItems = wishlistItems;
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

        public Builder setContact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public Builder copy(User user){
            this.userID = user.id;
            this.firstName = user.firstName;
            this.middleName = user.middleName;
            this.lastName = user.lastName;
            this.password = user.password;
            this.wishlistItems = user.wishlistItems;
            this.reviews = user.reviews;
            this.card = user.card;
            this.shippingAddress = user.shippingAddress;
            this.billingAddress = user.billingAddress;
            this.contact = user.contact;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
