package Deposit_Withdraw;

public class OverDrawn extends DepositWithdraw_State {
    public OverDrawn(DepositWithdraw_State source) {
        super(source);
    }

    @Override
    DepositWithdraw_State transitionState() {
        double balance = getAccount().getAccountBalance();
        if (balance >= this.balance_min)
            getAccount().setState(new NoTransitionFee(this));
        else if (balance >= 0)
            getAccount().setState(new TransitionFee(this));

        return getAccount().getState();
    }


    @Override
    public void deposit(double amount) {
        double balance = getAccount().getAccountBalance();

        getAccount().setAccountBalance(balance - this.fee_overdraw);
        alertAccountHolder();
        System.out.println("A transaction fee of $" + this.fee_overdraw+ " was charged due to account status (Overdrawn)");
        getAccount().setAccountBalance(balance + amount);
        transitionState();
    }

    @Override
    public void withdraw(double amount) throws Exception {
        double balance = getAccount().getAccountBalance();

        if ((balance - this.fee_overdraw -	amount) > this.limit_overdraw)
            throw new Exception("Overdraw limit has been exceeded");

        getAccount().setAccountBalance(balance - this.fee_overdraw);
        alertAccountHolder();
        System.out.println("A transaction fee of $" + this.fee_overdraw + " was charged due to account status (Overdrawn)");
        getAccount().setAccountBalance(balance - amount);
        transitionState();
    }

    public void alertAccountHolder() {
        String alert = "Attention: Your Account is Overdrawn";
        System.out.println(alert);
    }
}