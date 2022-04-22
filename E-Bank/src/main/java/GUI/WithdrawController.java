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

public class WithdrawController {

    private User user;

    public void withdrawControl(User user){
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
    private TextField withdrawAmount;
    @FXML
    private Label withdrawSuccessful;
    @FXML
    private Label accountLabel;

    @FXML
    private void clickWithdraw() throws Exception {
        withdraw();
    }

    private void withdraw() throws Exception {
        Account account = accountList.getSelectionModel().getSelectedItem();
        if(account == null){
            accountLabel.setTextFill(Paint.valueOf("RED"));
            return;
        }
        account.withdraw(Double.parseDouble(withdrawAmount.getText()));
        withdrawSuccessful.setText("Withdraw Successful");
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
