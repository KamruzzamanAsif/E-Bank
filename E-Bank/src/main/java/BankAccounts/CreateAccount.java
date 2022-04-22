package BankAccounts;

public class CreateAccount {
    public static Account makeAccount(String accountOwnerName, String accountType)throws Exception{
        Account account;
        if (accountType.equalsIgnoreCase("Basic")) {
            account = new BasicAccount(accountOwnerName);
            System.out.println("A Basic account has been created.");
        }
        else if  (accountType.equalsIgnoreCase("Saving")) {
            account = new SavingAccount(accountOwnerName);
            System.out.println("A Save account has been created.");
        }
        else if  (accountType.equalsIgnoreCase("Recurring Deposit")) {
            account = new RecurringDepositAccount(accountOwnerName);
            System.out.println("A Recurring Deposit account has been created.");
        }
        else
            throw new Exception("Invalid type of account provided");
        return account;
    }
}
