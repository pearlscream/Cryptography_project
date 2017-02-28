package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.abstraction.CipherService;
import sample.abstraction.DataSource;
import sample.implementation.CaesarCipherService;
import sample.implementation.FileDataSource;

import java.io.File;


public class Controller {
    @FXML
    private TextField textInput;
    @FXML
    private TextField keyInput;
    @FXML
    private AnchorPane ap;


    private CipherService cipherService;


    @FXML
    private void encryptButtonAction(ActionEvent event) {
        try {
            cipherService = new CaesarCipherService(Long.valueOf(keyInput.getText()));
            String encryptedText = cipherService.encryptText(textInput.getText());
            textInput.setText(encryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void decryptButtonAction(ActionEvent event) {
        try {
            cipherService = new CaesarCipherService(Long.valueOf(keyInput.getText()));
            String decryptedText = cipherService.decryptText(textInput.getText());
            textInput.setText(decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void fileUpload(ActionEvent event) {
        Stage stage = (Stage) ap.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File uploadedFile = fileChooser.showOpenDialog(stage);
        DataSource dataSource = new FileDataSource(uploadedFile.getPath());
        textInput.setText(dataSource.getProgramText());
    }
}
