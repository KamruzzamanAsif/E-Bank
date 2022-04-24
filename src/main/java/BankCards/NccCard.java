package BankCards;

public class NccCard extends Card {
    public NccCard(String ownerName) {
        super(ownerName);
    }

    @Override
    public String getSpendingLimit() {
        return "The spending limit is 10000 TK";
    }

    @Override
    public String getType() {
        return "Ncc Card";
    }
}
