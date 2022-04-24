package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;


public class Login {

    @FXML
    TextField userNameText;
    @FXML
    TextField passwordText;
    @FXML
    Label errorLogin;
    public void clickOnLogin(ActionEvent event) throws IOException, ClassNotFoundException {

        String userName = userNameText.getText();
        String password = passwordText.getText();
        LoginChecker loginChecker = new LoginChecker();

        if(loginChecker.isUserExist(userName, password)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userMenu.fxml"));
            Parent root = loader.load();
            UserMenu userMenu = loader.getController();
            userMenu.showUserInterface(userName);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            errorLogin.setText("Invalid UserName and Password");
            errorLogin.setTextFill(Paint.valueOf("RED"));
        }
    }

}
