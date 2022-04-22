package BankAccounts;

import java.io.Serializable;

public class BasicAccount extends Account {

    public BasicAccount(String ownerName) {
        super(ownerName);
    }

    public BasicAccount(String ownerName, double balance) {
        super(ownerName, balance);
    }

    @Override
    public String getType() {
        return "Basic";
    }

    @Override
    public void setRevenue() {
        this.revenue = 0;
    }

    @Override
    public void isDepositRevenue() {
        this.deposit_revenue =  false;
    }

    @Override
    public void isWithdrawRevenue() {
        this.withdraw_revenue =  false;
    }
}