package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.abstraction.CipherService;
import sample.abstraction.DataSource;
import sample.implementation.CaesarCipherService;
import sample.implementation.FileDataSource;
import sample.implementation.TritemiumCipherService;
import sample.tritemius_key_strategies.TritemiusKeyCalculator;
import sample.tritemius_key_strategies.TritemiusLinearKeyCalculator;
import sample.tritemius_key_strategies.TritemiusQuadraticKeyCalculator;
import sample.tritemius_key_strategies.TritemiusWordKeyCalculator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Controller {
    @FXML
    private TextField textInput;
    @FXML
    private TextField keyInput;
    @FXML
    private AnchorPane ap;
    @FXML
    private Text errorText;
    @FXML
    private TextField textInputTritemius;
    @FXML
    private TabPane tabs;
    @FXML
    private ToggleGroup cipherMethod;

    @FXML
    private TextField wordKeyInput;
    @FXML
    private TextField linearFirst;
    @FXML
    private TextField linearSecond;
    @FXML
    private TextField quadraticFirst;
    @FXML
    private TextField quadraticSecond;
    @FXML
    private TextField quadraticThird;

    private CipherService cipherService;


    @FXML
    private void encryptButtonAction(ActionEvent event) {
        fillData();
        String encryptedText = cipherService.encryptText(textInput.getText());
        textInput.setText(encryptedText);
    }

    @FXML
    private void decryptButtonAction(ActionEvent event) {
        fillData();
        String decryptedText = cipherService.decryptText(textInput.getText());
        textInput.setText(decryptedText);
    }

    @FXML
    private void tritemiusEncrypt(ActionEvent event) {
        RadioButton selectedToggle = (RadioButton)(cipherMethod.getSelectedToggle());
        TritemiusKeyCalculator tritemiusKeyCalculator = getKeyCalculationStrategy(selectedToggle.getId());
        CipherService cipherService = new TritemiumCipherService(tritemiusKeyCalculator);
        textInputTritemius.setText(cipherService.encryptText(textInputTritemius.getText()));
    }

    @FXML
    private void tritemuisDecrypt(ActionEvent event) {
        RadioButton selectedToggle = (RadioButton)(cipherMethod.getSelectedToggle());
        TritemiusKeyCalculator tritemiusKeyCalculator = getKeyCalculationStrategy(selectedToggle.getId());
        CipherService cipherService = new TritemiumCipherService(tritemiusKeyCalculator);
        textInputTritemius.setText(cipherService.decryptText(textInputTritemius.getText()));
    }

    private TritemiusKeyCalculator getKeyCalculationStrategy(String radioButtonId) {
        TritemiusKeyCalculator tritemiusKeyCalculator = null;
        switch (radioButtonId) {
            case "keyWord" : tritemiusKeyCalculator = new TritemiusWordKeyCalculator(wordKeyInput.getText()); break;
            case "keyLinear" : tritemiusKeyCalculator = new TritemiusLinearKeyCalculator(Long.valueOf(linearFirst.getText()),Long.valueOf(linearSecond.getText())); break;
            case "keyQuadratic" : tritemiusKeyCalculator = new TritemiusQuadraticKeyCalculator(Long.valueOf(quadraticFirst.getText()),Long.valueOf(quadraticSecond.getText()),Long.valueOf(quadraticThird.getText())); break;
        }
        return tritemiusKeyCalculator;
    }

    private void fillData() {
        try {
            errorText.setVisible(false);
            Long key = Long.valueOf(keyInput.getText());
            cipherService = new CaesarCipherService(key);
            if(textInput.getText().equals("")) {
                errorText.setText("Введіть текст");
                errorText.setVisible(true);
            }
        } catch (NumberFormatException e) {
            errorText.setText("Ключ має бути числом");
            errorText.setVisible(true);
            throw new NumberFormatException();
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
        textInputTritemius.setText(dataSource.getProgramText());
    }

    public void saveToFile(ActionEvent event) {
        Stage stage = (Stage) ap.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save to file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try {
                FileWriter fileWriter = new FileWriter(file);
                switch (tabs.getSelectionModel().getSelectedIndex()) {
                    case 0 : fileWriter.write(textInput.getText()); break;
                    case 1: fileWriter.write(textInputTritemius.getText()); break;
                }
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
