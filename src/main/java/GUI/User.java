package GUI;
import BankAccounts.Account;
import MoneyTransfer.Transaction;

import java.io.*;
import java.util.ArrayList;

public class User implements Serializable {
    String username;

    ArrayList<Account> accounts = new ArrayList<>();

    ArrayList<Transaction> transactions = new ArrayList<>();


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<Transaction> getTransactions(){
        return transactions;
    }

    public void updateAccount(int index, Account account){
        accounts.set(index,account);
        updateUserInformation();
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
        updateUserInformation();
    }

    public void deleteAccount(Account account){
        this.accounts.remove(account);
        updateUserInformation();
    }

    public void updateUserInformation(){
        try {
            String path = "src/main/resources/userAccounts/"+username+".txt";

            // clear the file
            FileWriter fwOb = new FileWriter(path, false);
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();

            // now update
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(this);
            out.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
