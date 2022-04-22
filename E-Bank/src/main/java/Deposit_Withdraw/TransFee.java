package Deposit_Withdraw;

import BankAccounts.Account;

public class TransFee extends DepositWithdraw_State {

    public TransFee(Account account) {
        super(account);
    }

    public TransFee(DepositWithdraw_State source) {
        super(source);
    }

    @Override
    DepositWithdraw_State transitionState() {
        double balance = getContext().getAccountBalance();

        if (balance < 0)
            getContext().setState(new OverDrawn(this));

        else if (balance >= this.balance_min)
            getContext().setState(new NoTransitionFee(this));

        return getContext().getState();
    }


    @Override
    public void deposit(double amount) {
        double balance = getContext().getAccountBalance();

        getContext().setAccountBalance(balance - this.fee_basic);
        System.out.println("A transaction fee of $" + this.fee_basic + " was charged due to account status (Less than minimum balance)");
        getContext().setAccountBalance(balance + amount);
        transitionState();
    }

    @Override
    public void withdraw(double amount) throws Exception {
        double balance = getContext().getAccountBalance();

        if ((balance - this.fee_basic - amount) >this.limit_overdraw)
            throw new Exception("Overdraw limit exceeded");

        getContext().setAccountBalance(balance - this.fee_basic);
        System.out.println("A transaction fee of $" + this.fee_basic + " was charged due to account status (Less than minimum balance)");

        getContext().setAccountBalance(balance - amount);
        transitionState();
    }
}
