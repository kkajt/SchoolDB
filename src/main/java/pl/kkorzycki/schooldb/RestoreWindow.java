package pl.kkorzycki.schooldb;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class RestoreWindow {

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private void makeRestoreHandler (MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Otwórz kopię");
        File file = fileChooser.showOpenDialog(GUI.getPrimaryStage());
        String dbname = "szkola";
        if (file != null) {
            String[] executeCmd = new String[]{"mysql", "--user=" + login.getText(),
                    "--password=" + password.getText(), dbname,"-e", " source "+file.getAbsolutePath()};
            try {
                Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                int processComplete = runtimeProcess.waitFor();
                if (processComplete == 0) {
                    System.out.println("Restore success!");
                    clearTextFields();
                    GUI.getPrimaryStage().setScene(LoginWindow.getScene());
                } else {
                    System.out.println("Restore failure.");
                }
            }
            catch(Exception ex) {
                System.err.println(ex.toString());
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
