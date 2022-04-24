package Money_Deposit_Withdraw;

public class TransitionFee extends DepositWithdraw_State {

    public TransitionFee(DepositWithdraw_State source) {
        super(source);
    }

    @Override
    DepositWithdraw_State transitionState() {
        double balance = getAccount().getAccountBalance();

        if (balance < 0)
            getAccount().setState(new OverDrawn(this));

        else if (balance >= this.balance_min)
            getAccount().setState(new NoTransitionFee(this));

        return getAccount().getState();
    }


    @Override
    public void deposit(double amount) {
        double balance = getAccount().getAccountBalance();

        getAccount().setAccountBalance(balance - this.fee_basic);
        System.out.println("A transaction fee of $" + this.fee_basic + " was charged due to account status (Less than minimum balance)");
        getAccount().setAccountBalance(balance + amount);
        transitionState();
    }

    @Override
    public void withdraw(double amount) throws Exception {
        double balance = getAccount().getAccountBalance();

        if ((balance - this.fee_basic - amount) >this.limit_overdraw)
            throw new Exception("Overdraw limit exceeded");

        getAccount().setAccountBalance(balance - this.fee_basic);
        System.out.println("A transaction fee of $" + this.fee_basic + " was charged due to account status (Less than minimum balance)");

        getAccount().setAccountBalance(balance - amount);
        transitionState();
    }
}
