package Deposit_Withdraw;

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
        double balance = getContext().getAccountBalance();

        if (balance <0)
            getContext().setState(new OverDrawn(this));
        else if (balance < this.balance_min)
            getContext().setState(new TransFee(this));

        return getContext().getState();
    }


    @Override
    public void deposit(double amount) {
        double balance = getContext().getAccountBalance();

        getContext().setAccountBalance(balance + amount);
        transitionState();
    }

    @Override
    public void withdraw(double amount) throws Exception {
        double balance = getContext().getAccountBalance();

        if ((balance - amount) <= this.limit_overdraw)
            throw new Exception("Insufficient funds");
        getContext().setAccountBalance(balance - amount);
        transitionState();
    }
}