package Deposit_Withdraw;

import BankAccounts.Account;

public class OverDrawn extends DepositWithdraw_State {
    private String alert = "Attention: Your Account is Overdrawn";


    public OverDrawn(Account account) {
        super(account);
    }

    public OverDrawn(DepositWithdraw_State source) {
        super(source);
    }

    @Override
    DepositWithdraw_State transitionState() {
        double balance = getContext().getAccountBalance();
        if (balance >= this.balance_min)
            getContext().setState(new NoTransitionFee(this));
        else if (balance >= 0)
            getContext().setState(new TransFee(this));

        return getContext().getState();
    }


    @Override
    public void deposit(double amount) {
        double balance = getContext().getAccountBalance();

        getContext().setAccountBalance(balance - this.fee_overdraw);
        alertAccountHolder();
        System.out.println("A transaction fee of $" + this.fee_overdraw+ " was charged due to account status (Overdrawn)");
        getContext().setAccountBalance(balance + amount);
        transitionState();
    }

    @Override
    public void withdraw(double amount) throws Exception {
        double balance = getContext().getAccountBalance();

        if ((balance - this.fee_overdraw -	amount) > this.limit_overdraw)
            throw new Exception("Overdraw limit has been exceeded");

        getContext().setAccountBalance(balance - this.fee_overdraw);
        alertAccountHolder();
        System.out.println("A transaction fee of $" + this.fee_overdraw + " was charged due to account status (Overdrawn)");
        getContext().setAccountBalance(balance - amount);
        transitionState();
    }

    public void alertAccountHolder() {
        System.out.println(alert);
    }
}