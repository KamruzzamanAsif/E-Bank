package GUI;

import Service.Client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class UserMenu {
    String userName;
    @FXML
    Label userWelcomeText;
    User user;

    public void showUserInterface(String userName) throws IOException, ClassNotFoundException {
        this.userName = userName;
        userWelcomeText.setText("Welcome " + userName);
        user = getUser(userName);
        if(user == null){
            user = new User();
        }
    }

    public User getUser(String name) throws IOException, ClassNotFoundException {
        String path = "src/main/resources/userAccounts/"+ name +".txt";
        File f = new File(path);
        if(f.exists() && !f.isDirectory()){
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
            User user = (User) in.readObject();
            return user;
        }
        return null;
    }

    @FXML
    Button accounts;
    public void clickAccountsButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("accounts.fxml"));
        Parent root = loader.load();
        UserAccountController userAccountController = loader.getController();
        userAccountController.control(user);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    Button depositMoney;
    public void clickDepositButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("deposit.fxml"));
        Parent root = loader.load();
        DepositController depositController = loader.getController();
        depositController.depositControl(user);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    Button withdrawMoney;
    public void clickWithdrawButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("withdraw.fxml"));
        Parent root = loader.load();
        WithdrawController withdrawController = loader.getController();
        withdrawController.withdrawControl(user);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    Button transferMoney;
    public void clickTransferButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("transfer.fxml"));
        Parent root = loader.load();
        TransferController transferController = loader.getController();
        transferController.transferControl(user);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void clickCardButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("bankCard.fxml"));
        Parent root = loader.load();
        BankCardController bankCardController = loader.getController();
        bankCardController.cardControl(user);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void clickHelpButton(ActionEvent event) throws IOException {
        Client client = new Client();
        client.clientRun();
    }
}
