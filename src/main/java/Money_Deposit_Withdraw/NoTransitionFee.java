package Money_Deposit_Withdraw;

import BankAccounts.Account;

public class NoTransitionFee extends DepositWithdraw_State {

    public NoTransitionFee(Account account) {
        super(account);
    }

    public NoTransitionFee(DepositWithdraw_State source) {
        super(source);
    }

    @Override
    DepositWithdraw_State transitionState() {
        double balance = getAccount().getAccountBalance();

        if (balance <0)
            getAccount().setState(new OverDrawn(this));
        else if (balance < this.balance_min)
            getAccount().setState(new TransitionFee(this));

        return getAccount().getState();
    }


    @Override
    public void deposit(double amount) {
        double balance = getAccount().getAccountBalance();

        getAccount().setAccountBalance(balance + amount);
        transitionState();
    }

    @Override
    public void withdraw(double amount) throws Exception {
        double balance = getAccount().getAccountBalance();

        if ((balance - amount) <= this.limit_overdraw)
            throw new Exception("Insufficient funds");
        getAccount().setAccountBalance(balance - amount);
        transitionState();
    }
}