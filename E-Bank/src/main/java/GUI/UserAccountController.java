package GUI;

import BankAccounts.Account;
import BankAccounts.CreateAccount;
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

public class UserAccountController{
    User user;
    public void control(User user){
        this.user = user;
        populateListView();
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
        populateListView();
    }

    @FXML
    ListView<Account> accountList;
    private void populateListView(){
        accountList.getItems().clear();
        accountList.getItems().addAll(user.getAccounts());
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
