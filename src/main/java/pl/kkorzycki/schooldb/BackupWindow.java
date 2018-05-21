package pl.kkorzycki.schooldb;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class BackupWindow {

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private void makeBackstoreHandler (MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Zapisz kopię");
        File file = fileChooser.showSaveDialog(GUI.getPrimaryStage());
        if (file != null) {
            String dbname = "szkola";

            String executeCmd = "mysqldump --routines -u "+login.getText()+" -p"+password.getText()
                    +" "+dbname+" -r "+ file.getAbsolutePath();

            try {
                Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                int processComplete = runtimeProcess.waitFor();
                if (processComplete == 0) {
                    System.out.println("Backup taken successfully");
                    GUI.getPrimaryStage().setScene(LoginWindow.getScene());
                    clearTextFields();
                } else {
                    System.out.println("Could not take mysql backup");
                    clearTextFields();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

    private void clearTextFields () {
        login.clear();
        password.clear();
    }

    @FXML
    protected void backToStartingWindowHandler (MouseEvent mouseEvent) {
        GUI.getPrimaryStage().setScene(LoginWindow.getScene());
    }


}
