package BankCards;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;


public abstract class Card implements Serializable {
    private final String ownerName;
    private final int cardNumber;
    private LocalDate make;
    private LocalDate exp;
    private final String spendingLimit;

    public Card(String ownerName) {
        this.ownerName = ownerName;
        Random rand = new Random();
        this.cardNumber = rand.nextInt(9999999-2000000);
        setCardDate();
        this.spendingLimit = getSpendingLimit();
    }
    public String getOwnerName() { return this.ownerName; }

    public void setCardDate() {
        this.make = LocalDate.now();
        Period p = Period.ofYears(2);
        this.exp = make.plus(p);
    }
    public abstract String getSpendingLimit();
    public abstract String getType();

    @Override
    public String toString() {
        return "Card{" +
                "owner Name='" + ownerName + '\'' +
                ", card Number=" + cardNumber +
                ", card date=" + make +
                ", exp date=" + exp +
                ", spendingLimit=" + spendingLimit +
                '}';
    }
}