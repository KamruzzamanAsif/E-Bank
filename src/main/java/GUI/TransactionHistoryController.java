package GUI;

import MoneyTransfer.Transaction;
import Sort.AmountComparator;
import Sort.DateComparator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TransactionHistoryController {

    private User user;
    ArrayList<Transaction> transactions = new ArrayList<>();

    @FXML
    ListView<Transaction> transactionList;

    public void showTransactions(User user) {
        this.user = user;
        storeTransactions();
        populateTransactionList();
    }

    private void storeTransactions(){
        transactions.clear();
        transactions.addAll(user.getTransactions());
    }

    private void populateTransactionList(){
        transactionList.getItems().clear();
        transactionList.getItems().addAll(transactions);
    }

    public void clickOnSortByDate(ActionEvent event){
        Collections.sort(transactions, new DateComparator());
        populateTransactionList();
    }

    public void clickOnSortByAmount(ActionEvent event){
        Collections.sort(transactions, new AmountComparator());
        populateTransactionList();
    }

    public void clickOnBackButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("transfer.fxml"));
        Parent root = loader.load();
        TransferController transferController = loader.getController();
        transferController.transferControl(user);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
