package BankCards;

public class MasterCard extends Card{
    public MasterCard(String ownerName) {
        super(ownerName);
    }

    @Override
    public String getSpendingLimit() {
        return "The spending limit is 5000 TK";
    }

    @Override
    public String getType() {
        return "Master Card";
    }
}