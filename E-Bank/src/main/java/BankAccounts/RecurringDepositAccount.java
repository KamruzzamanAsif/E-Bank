package BankAccounts;

public class RecurringDepositAccount extends Account{
    public RecurringDepositAccount(String ownerName) {
        super(ownerName);
    }

    @Override
    public String getType() {
        return "Recurring Deposit";
    }

    @Override
    public void setRevenue() {
        this.revenue = 0.5;
    }

    @Override
    public void isDepositRevenue() {
        this.deposit_revenue =  true;
    }

    @Override
    public void isWithdrawRevenue() {
        this.withdraw_revenue =  false;
    }
}
