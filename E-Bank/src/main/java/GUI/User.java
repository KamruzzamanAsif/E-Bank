package GUI;
import BankAccounts.Account;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    String username;

    ArrayList<Account> accounts = new ArrayList<>();


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void updateAccount(int index, Account account){
        accounts.set(index,account);
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
        updateUserInformation();
    }

    public void updateUserInformation(){
        try {
            String path = "src/main/resources/userAccounts/"+username+".txt";
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(this);
            out.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
