package GUI;

import BankAccounts.Account;
import BankCards.Card;
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

import static BankCards.CreateCard.createCard;

public class BankCardController {

    private User user;

    ArrayList<Account> accounts = new ArrayList<>();

    public void cardControl(User user){
        this.user = user;
        populateAccountList();
        populateCardList();
        getAccounts();
    }

    @FXML
    ListView<Account> accountList;
    private void populateAccountList(){
        accountList.getItems().clear();
        accountList.getItems().addAll(user.getAccounts());
    }

    @FXML
    ListView<Card> bankCards;
    private void populateCardList(){
        Account account = accountList.getSelectionModel().getSelectedItem();
        if(account == null)
            return;
        bankCards.getItems().clear();
        bankCards.getItems().addAll(account.getCards());
    }

    private void getAccounts(){
        accounts.addAll(user.getAccounts());
    }

    @FXML
    private TextField cardName;

    public void clickOnAddCardButton(ActionEvent event){
        Account account = accountList.getSelectionModel().getSelectedItem();
        if(account == null)
            return;
        bankCards.getItems().clear();
        account.addCard(createCard(cardName.getText(), user.getUsername()));
        bankCards.getItems().addAll(account.getCards());
        // update card info in user
        int index = accounts.indexOf(account);
        user.updateAccount(index,account);
        populateAccountList();
        populateCardList();
    }

    public void clickOnShowCards(){
        populateCardList();
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
