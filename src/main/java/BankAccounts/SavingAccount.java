package BankAccounts;

public class SavingAccount extends Account{
    public SavingAccount(String ownerName) {
        super(ownerName);
    }

    public SavingAccount(String ownerName, double balance) {
        super(ownerName, balance);
    }

    @Override
    public String getType() {
        return "Save";
    }

    @Override
    public void setRevenue() {
        this.revenue = 6.5;
    }

    @Override
    public void isDepositRevenue() {
        this.deposit_revenue =  false;
    }

    @Override
    public void isWithdrawRevenue() {
        this.withdraw_revenue =  true;
    }
}
