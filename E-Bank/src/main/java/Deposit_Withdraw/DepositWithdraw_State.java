package Deposit_Withdraw;

import BankAccounts.Account;

public abstract class DepositWithdraw_State {
    private Account context;
    public double limit_overdraw = -1000.00;
    public double balance_min = 0.00;
    public double fee_basic = 5.75;
    public double fee_overdraw = 8.20;

    public DepositWithdraw_State(Account account) {
        setContext(account);
    }

    public DepositWithdraw_State(DepositWithdraw_State source) {
        setContext(source.getContext());
    }

    public Account getContext() {
        return context;
    }

    public void setContext(Account newAccount) {
        context = newAccount;
    }

    public static DepositWithdraw_State InitialState(Account account) {
        return new NoTransitionFee(account);
    }


    abstract DepositWithdraw_State transitionState();
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount) throws Exception;
}
