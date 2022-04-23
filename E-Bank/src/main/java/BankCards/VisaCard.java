package BankCards;

public class VisaCard extends Card {
    public VisaCard(String ownerName) {
        super(ownerName);
    }

    @Override
    public String getSpendingLimit() {
        return "The spending limit is 4500 TK";
    }

    @Override
    public String getType() {
        return "Visa Card";
    }
}