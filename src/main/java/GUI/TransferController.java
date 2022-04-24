package GUI;

import BankAccounts.Account;
import MoneyTransfer.Transaction;
import javafx.animation.Transition;
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

public class TransferController {

    private User user;

    public void transferControl(User user){
        this.user = user;
        populateListView();
    }

    @FXML
    ListView<Account> senderList;
    @FXML
    ListView<Account> receiverList;
    private void populateListView(){
        senderList.getItems().clear();
        senderList.getItems().addAll(user.getAccounts());
        receiverList.getItems().clear();
        receiverList.getItems().addAll(user.getAccounts());
    }

    @FXML
    private TextField amount;
    @FXML
    Label senderLabel;
    @FXML
    Label receiverLabel;
    @FXML
    Label transferSuccessful;

    public void clickOnTransferButton(ActionEvent event) throws Exception {
        Account sender = senderList.getSelectionModel().getSelectedItem();
        Account receiver = receiverList.getSelectionModel().getSelectedItem();
        if(sender == null || receiver == null){
            senderLabel.setTextFill(Paint.valueOf("RED"));
            receiverLabel.setTextFill(Paint.valueOf("RED"));
            return;
        }
        Transaction transaction = new Transaction(sender,receiver,Double.parseDouble(amount.getText()));
        transaction.execute();
        transferSuccessful.setText("Transition Successful");
        populateListView();
        user.transactions.add(transaction);
        user.updateUserInformation();
    }

    public void clickOnShowPreviousTransactions(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("transactionHistory.fxml"));
        Parent root = loader.load();
        TransactionHistoryController transactionHistoryController = loader.getController();
        transactionHistoryController.showTransactions(this.user);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
