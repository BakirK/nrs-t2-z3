package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;

public class ZnamenitostiController {
    @FXML
    private TextField fieldZnamenitost;
    private ImageView imageView;
    private GeografijaDAO dao;

    public Grad getGrad() {
        return grad;
    }

    private void setGrad(Grad grad) {
        this.grad = grad;
    }

    private Grad grad;

    public ZnamenitostiController(Grad grad) {
        this.grad = grad;
        this.dao = GeografijaDAO.getInstance();
    }

    @FXML
    private void clickOdaberi(ActionEvent actionEvent) {
    }

    @FXML
    private void clickSave(ActionEvent actionEvent) {
        Znamenitost z = new Znamenitost(-1, fieldZnamenitost.getText(), imageView.getImageURL().toString(), grad.getId());
        grad.dodajZnamenitost(z);
        dao.dodajZnamenitost(z);
        zatvoriProzorPropuhJe();
    }

    private void zatvoriProzorPropuhJe() {
        Stage stage = (Stage) fieldZnamenitost.getScene().getWindow();
        stage.close();
    }
}
