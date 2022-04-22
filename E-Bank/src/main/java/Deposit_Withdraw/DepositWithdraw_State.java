package Deposit_Withdraw;

import BankAccounts.Account;

import java.io.Serializable;

public abstract class DepositWithdraw_State implements Serializable {
    private Account account;
    public double limit_overdraw = -500.00;
    public double balance_min = 0.00;
    public double fee_basic = 5.00;
    public double fee_overdraw = 10.00;

    public DepositWithdraw_State(Account account) {
        setAccount(account);
    }

    public DepositWithdraw_State(DepositWithdraw_State source) {
        setAccount(source.getAccount());
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account newAccount) {
        account = newAccount;
    }

    public static DepositWithdraw_State InitialState(Account account) {
        return new NoTransitionFee(account);
    }


    abstract DepositWithdraw_State transitionState();
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount) throws Exception;
}
