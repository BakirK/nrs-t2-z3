package ba.unsa.etf.rpr;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

public class ZnamenitostiController {
    @FXML
    private TextField fieldZnamenitost;
    @FXML
    private ImageView imageView;
    private GeografijaDAO dao;
    private Grad grad;
    private String path;

    public Grad getGrad() {
        return grad;
    }

    private void setGrad(Grad grad) {
        this.grad = grad;
    }


    public ZnamenitostiController(Grad grad) {
        this.grad = grad;
        this.dao = GeografijaDAO.getInstance();
        path = "";
    }

    @FXML
    private void clickOdaberi(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Unesite punu putanju do slike");
        dialog.setTitle("Unos slike");
        dialog.setContentText("Putanja:");
        dialog.setResizable(true);

        Optional<String> result = dialog.showAndWait();
        try {
            System.out.println(result.get());
            //Image image = new Image("file:" + result.get());
            //BufferedImage img = null;
            //img = ImageIO.read(new File(result.get()));
            //Image image = SwingFXUtils.toFXImage(img, null);
            File f = new File(result.get());
            Image imageForFile = new Image(f.toURI().toURL().toExternalForm());
            if(imageForFile == null) {
                System.out.println("img nullasdadasdasdasd");
            }
            imageView.setImage(imageForFile);
            path = result.get();
        } catch(Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Greška sa pronalaženjem slike!");
            alert.setContentText("Putanja nije ispravna?");
            alert.showAndWait();
        }

    }

    @FXML
    private void clickSave(ActionEvent actionEvent) {
        Znamenitost z = new Znamenitost(-1, fieldZnamenitost.getText(), path, grad.getId());
        if(grad.getZnamenitosti() == null) {
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
