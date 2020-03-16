package ba.unsa.etf.nrs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class PretragaController {
    @FXML
    private Button btnSearch;
    @FXML
    private ListView listViewUzorci;
    @FXML
    private TextField fieldUzorak;
    private String putanja;
    Object temp;
    Thread backgroudThread;

    public PretragaController() {
        putanja = "";
    }

    @FXML
    public void initialize() {
        listViewUzorci.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        temp = listViewUzorci.getSelectionModel().getSelectedItem();
                        if(temp != null) {
                            putanja = temp.toString();
                            zatvoriProzorPropuhJe();
                        }
                    }
                }
            }
        });

    }

    @FXML
    private void clickSearch(ActionEvent actionEvent) {
        backgroudThread = new Thread(() -> {
            lockInputs();
            String term = fieldUzorak.getText();
            String target_file;
            File[] listOfFiles;
            Queue<File> folders = new LinkedList<>();
            //dohvati korisnicki home dir (nije testirano na linuxu)
            File homeFolder;
            try {
                homeFolder = new File(System.getProperty("user.home"));
                folders.add(homeFolder);
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Belaj");
                alert.setHeaderText("Greška pri otvaranju home direktorija");
                alert.setContentText("Folder je zaključan?");
                alert.showAndWait();
                return;
            }
            File currentFolder;
            while(!folders.isEmpty() && !Thread.currentThread().isInterrupted()) {
                currentFolder = folders.remove();
                listOfFiles = currentFolder.listFiles();
                if(listOfFiles == null) {
                    continue;
                }
                for (int i = 0; i < listOfFiles.length; i++) {
                    if(listOfFiles[i].isFile()) {
                        target_file = listOfFiles[i].getName();
                        if (target_file.contains(term)) {
                            listViewUzorci.getItems().add(listOfFiles[i].getAbsolutePath());
                        }
                    }
                    else if(listOfFiles[i].isDirectory()) {
                        System.out.println(listOfFiles[i].getAbsolutePath());
                        folders.add(listOfFiles[i]);
                    }
                }
            }
            unlockInputs();
        });
        backgroudThread.start();
    }

    public String getPutanja() {
        return putanja;
    }

    private void zatvoriProzorPropuhJe() {
        if(backgroudThread != null) {
            //.stop() je zastarjelo?
            backgroudThread.interrupt();

            System.out.println("stopped");
        }
        Stage stage = (Stage) listViewUzorci.getScene().getWindow();
        stage.close();
    }

    private void lockInputs() {
        fieldUzorak.setEditable(false);

        //nema arrow+wait cursor za javafx :(
        //btnSearch.getScene().setCursor(Cursor.WAIT);

        //Image image = new Image("cursor.png");  //pass in the image path
        //btnSearch.getScene().setCursor(new ImageCursor(image));

        btnSearch.disableProperty().setValue(true);
    }

    private void unlockInputs() {
        fieldUzorak.setEditable(true);
        //btnSearch.getScene().setCursor(Cursor.DEFAULT);
        btnSearch.disableProperty().setValue(false);
    }
}
