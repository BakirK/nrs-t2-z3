package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;

public class ZnamenitostiController {
    @FXML
    private TextField fieldZnamenitost;
    private ImageView imageView;
    private GeografijaDAO dao;
    private Grad grad;

    public Grad getGrad() {
        return grad;
    }

    private void setGrad(Grad grad) {
        this.grad = grad;
    }


    public ZnamenitostiController(Grad grad) {
        this.grad = grad;
        this.dao = GeografijaDAO.getInstance();
    }

    @FXML
    private void clickOdaberi(ActionEvent actionEvent) {

    }

    @FXML
    private void clickSave(ActionEvent actionEvent) {
        Znamenitost z = new Znamenitost(-1, fieldZnamenitost.getText(), "C:\\Users\\Bakir\\Pictures\\ker.png", grad.getId());
        if(grad.getZnamenitosti()==null) {
            grad.setZnamenitosti(new ArrayList<>());
        }
        grad.dodajZnamenitost(z);
        dao.dodajZnamenitost(z);
        zatvoriProzorPropuhJe();
    }

    private void zatvoriProzorPropuhJe() {
        Stage stage = (Stage) fieldZnamenitost.getScene().getWindow();
        stage.close();
    }
}
