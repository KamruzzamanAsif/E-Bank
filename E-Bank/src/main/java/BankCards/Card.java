package BankCards;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;


public abstract class Card implements Serializable, Date {
    private final String ownerName;
    private final int cardNumber;
    private LocalDate creationDate;
    private LocalDate expiryDate;
    private final String spendingLimit;
    private final String cardType;

    public Card(String ownerName) {
        this.ownerName = ownerName;
        Random rand = new Random();
        this.cardNumber = rand.nextInt(9999999-2000000);
        setDate();
        this.spendingLimit = getSpendingLimit();
        this.cardType = getType();
    }
    public String getOwnerName() { return this.ownerName; }

    @Override
    public void setDate() {
        this.creationDate = LocalDate.now();
        Period p = Period.ofYears(3);
        this.expiryDate = creationDate.plus(p);
    }
    public abstract String getSpendingLimit();
    public abstract String getType();

    @Override
    public String toString() {
        return "Card{" +
                "owner Name='" + ownerName + '\'' +
                ", card Number=" + cardNumber +
                ", card Type=" + cardType +
                ", card date=" + creationDate +
                ", exp date=" + expiryDate +
                ", spendingLimit=" + spendingLimit +
                '}';
    }
}