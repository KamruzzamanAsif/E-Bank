package BankAccounts;

import Cards.Card;
import Deposit_Withdraw.DepositWithdraw_State;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

public abstract class Account implements Serializable {
    private int accountNumber;
    private double accountBalance;
    private String accountOwnerName;
    double revenue;
    // Is there need to deposit revenue
    boolean deposit_revenue;
    // Is there need to withdraw revenue
    boolean withdraw_revenue;
    private boolean accountActive = true;
    private LinkedList<Card> cards = new LinkedList<Card>();

    private DepositWithdraw_State state; // aggregation


    public Account(String ownerName) {
        initialize(ownerName);
    }
    public Account(String ownerName, double accountBalance)  {
        initialize(ownerName);
        setAccountBalance(accountBalance);
    }

    public final void initialize(String ownerName){
        setAccountOwnerName(ownerName);
        createAccountNumber();
        setState(DepositWithdraw_State.InitialState(this));
        setRevenue();
        isDepositRevenue();
        isWithdrawRevenue();
    }

    public final void setAccountOwnerName(String accountOwnerName) { this.accountOwnerName = accountOwnerName; }
    public final void createAccountNumber(){
        Random rand = new Random();
        this.accountNumber = rand.nextInt(77777-10000);
    }
    public final void setState(DepositWithdraw_State state) { this.state = state; }
    public abstract void setRevenue();
    public abstract void isDepositRevenue();
    public abstract void isWithdrawRevenue();
    public final void setAccountBalance(double accountBalance) { this.accountBalance = accountBalance; }


    private boolean isAccountActive() { return accountActive; }
    public final void closeAccount() { this.accountActive = false; }
    public final int getAccountNumber() { return accountNumber; }
    public final void addCard(Card newCard){ this.cards.add(newCard);}


    public abstract String getType();



    public  void deposit(double amount) throws Exception {
        if(amount <= 0 )
            throw new Exception("Invalid amount");
        //Check if we need to add revenue depends on the account type
        if (deposit_revenue){
            getState().deposit(amount -((amount/12)*revenue));
        }
        else {
            getState().deposit(amount);
        }
    }

    public void withdraw(double amount) throws Exception {
        if(amount <= 0)
            throw new Exception("Invalid amount");
        //Check if we need to add revenue depends on the account type
        if (withdraw_revenue){
            getState().withdraw(amount+revenue);
        }
        else {
            getState().withdraw(amount);
        }
    }

    public double getAccountBalance() { return accountBalance; }
    public LinkedList<Card> getCards() { return cards; }
    public void deleteCard(Card oldCard){ this.cards.remove(oldCard);}
    public String getAccountOwnerName() { return accountOwnerName; }
    public DepositWithdraw_State getState() { return state; }

    @Override
    public String toString() {
        String output =
                "[Account]\n" +
                        "Account#: " + accountNumber + "\n" +
                        "Balance: $" + accountBalance + "\n" +
                        "Owner: " + accountOwnerName + "\n" +
                        "Account status: ";
        if(isAccountActive())
            output += "Open";
        else
            output += "Closed";
        return output;
    }
}