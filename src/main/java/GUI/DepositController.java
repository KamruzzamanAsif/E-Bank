package GUI;

import BankAccounts.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DepositController {

    private User user;

    public void depositControl(User user){
        this.user = user;
        populateListView();
    }

    @FXML
    ListView<Account> accountList;
    private void populateListView(){
        accountList.getItems().clear();
        accountList.getItems().addAll(user.getAccounts());
    }

    @FXML
    TextField depositText;
    @FXML
    Label accountLabel;
    @FXML
    Label depositSuccessful;

    @FXML
    private void clickDeposit(ActionEvent event) throws Exception {
        deposit();
    }

    private void deposit() throws Exception {
        Account account = accountList.getSelectionModel().getSelectedItem();
        if(account == null){
            accountLabel.setTextFill(Paint.valueOf("RED"));
            return;
        }
        account.deposit(Double.parseDouble(depositText.getText()));
        depositSuccessful.setText("Deposit Successful");
        populateListView();
        user.updateUserInformation();
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
