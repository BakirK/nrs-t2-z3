package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PretragaController {
    @FXML
    private Button btnSearch;
    @FXML
    private ListView listViewUzorci;
    @FXML
    private TextField fieldUzorak;
    private String putanja;

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
                        System.out.println("Double clicked");
                        putanja = listViewUzorci.getSelectionModel().getSelectedItem().toString();
                        zatvoriProzorPropuhJe();
                    }
                }
            }
        });

    }

    @FXML
    private void clickSearch(ActionEvent actionEvent) {
    }

    public String getPutanja() {
        return putanja;
    }

    private void zatvoriProzorPropuhJe() {
        Stage stage = (Stage) listViewUzorci.getScene().getWindow();
        stage.close();
    }
}
