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
    Object temp;

    public PretragaController() {
        putanja = "";
    }

    @FXML
    public void initialize() {
        listViewUzorci.getItems().add("C:\\Users\\Bakir\\Pictures\\ker.png");
        listViewUzorci.getItems().add("C:\\Users\\Bakir\\Pictures\\mouthful.png");
        listViewUzorci.getItems().add("C:\\Users\\Bakir\\Pictures\\f.png");
        listViewUzorci.getItems().add("C:\\Users\\Bakir\\Pictures\\r.png");
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
    }

    public String getPutanja() {
        return putanja;
    }

    private void zatvoriProzorPropuhJe() {
        Stage stage = (Stage) listViewUzorci.getScene().getWindow();
        stage.close();
    }
}
