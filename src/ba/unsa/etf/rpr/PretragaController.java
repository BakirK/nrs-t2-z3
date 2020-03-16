package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PretragaController {
    @FXML
    private TextField fieldUzorak;
    @FXML
    private Button btnSearch;
    @FXML
    private ListView listViewUzorci;
    private String putanja = "";

    @FXML
    private void clickSearch(ActionEvent actionEvent) {
    }

    public String getPutanja() {
        return putanja;
    }

    private void setPutanja(String putanja) {
        this.putanja = putanja;
    }
}
