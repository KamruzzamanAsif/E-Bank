package Sort;

import BankAccounts.Account;

import java.util.Comparator;

public class AccountComparator implements Comparator<Account> {
    @Override
    public int compare(Account o1, Account o2) {
        if(o1.getAccountBalance() < o2.getAccountBalance()){
            return 1;
        } else if (o1.getAccountBalance() > o2.getAccountBalance()){
            return -1;
        } else {
            return 0;
        }
    }
}
