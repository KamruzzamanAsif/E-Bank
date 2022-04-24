package GUI;

import BankAccounts.Account;
import BankAccounts.CreateAccount;
import Sort.AccountComparator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class UserAccountController{
    private User user;
    ArrayList<Account> accounts = new ArrayList<>();

    public void control(User user){
        this.user = user;
        getAccounts();
        populateListView();
    }

    private void getAccounts(){
        accounts.clear();
        accounts.addAll(user.getAccounts());
    }

    @FXML
    private TextField ownerName;
    @FXML
    private TextField accountType;
    Account account;

    public void clickAddAccount() throws Exception {
        String name = ownerName.getText();
        String type = accountType.getText();
        account = CreateAccount.makeAccount(name, type);
        user.setUsername(name);
        user.addAccount(account);
        getAccounts();
        populateListView();
    }

    @FXML
    private TextField accountSelection;

    public void clickDeleteAccount() {
        account = accountList.getSelectionModel().getSelectedItem();
        System.out.println(account);
        accountSelection.setText(String.valueOf(account.getAccountNumber()));
        user.setUsername(user.getUsername());
        user.deleteAccount(account);
        user.updateUserInformation();
        getAccounts();
        populateListView();
    }

    @FXML
    ListView<Account> accountList;
    private void populateListView(){
        accountList.getItems().clear();
        accountList.getItems().addAll(accounts);
    }

    public void clickOnSortButton(ActionEvent event){
        Collections.sort(accounts, new AccountComparator());
        populateListView();
    }

    public void clickOnBackButton(ActionEvent event) throws IOException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userMenu.fxml"));
        Parent root = loader.load();
        UserMenu userMenu = loader.getController();
        userMenu.showUserInterface(user.getUsername());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
