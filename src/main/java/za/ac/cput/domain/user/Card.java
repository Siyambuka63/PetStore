package za.ac.cput.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;


public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cardId;
    private String provider;
    private String token;
    private String lastFourDigits;
    private String brand;


    protected Card() {
    }

    public Card(Builder builder) {
        this.cardId = builder.cardId;
        this.provider = builder.provider;
        this.token = builder.token;
        this.lastFourDigits = builder.lastFourDigits;
        this.brand = builder.brand;
    }

    public long getCardId() {
        return cardId;
    }

    public String getProvider() {
        return provider;
    }

    public String getToken() {
        return token;
    }

    public String getLastFourDigits() {
        return lastFourDigits;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", provider='" + provider + '\'' +
                ", token='" + token + '\'' +
                ", lastFourDigits='" + lastFourDigits + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }




    public static class Builder {
        private long cardId;
        private String provider;
        private String token;
        private String lastFourDigits;
        private String brand;

        public Builder setCardID(long cardId) {
            this.cardId = cardId;
            return this;
        }
        public Builder setProvider(String provider) {
            this.provider = provider;
            return this;
        }
        public Builder setToken(String token) {
            this.token = token;
            return this;
        }
        public Builder setLastFourDigits(String lastFourDigits) {
            this.lastFourDigits = lastFourDigits;
            return this;
        }
        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }
        public Card build() {
            return new Card(this);
        }
    }

}