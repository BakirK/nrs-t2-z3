package ba.unsa.etf.rpr;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

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
        //zadatak 2
        /*TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Unesite punu putanju do slike");
        dialog.setTitle("Unos slike");
        dialog.setContentText("Putanja:");
        dialog.setResizable(true);

        Optional<String> result = dialog.showAndWait();
        try {
            //Image image = new Image("file:" + result.get());
            //BufferedImage img = null;
            //img = ImageIO.read(new File(result.get()));
            //Image image = SwingFXUtils.toFXImage(img, null);
            File f = new File(result.get());
            Image imageForFile = new Image(f.toURI().toURL().toExternalForm());
            imageView.setImage(imageForFile);
            path = result.get();
        } catch(Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Belaj");
            alert.setHeaderText("Greška sa pronalaženjem slike!");
            alert.setContentText("Putanja nije ispravna?");
            alert.showAndWait();
        }*/

        //zadatak 3
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pretraga.fxml"));
            PretragaController pretragaController = new PretragaController();
            loader.setController(pretragaController);
            root = loader.load();
            stage.setTitle("Pretraga datoteke");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(true);
            stage.show();

            stage.setOnHiding( event -> {
                //TODO
                try {
                    String s = pretragaController.getPutanja();
                    if(s.trim().isEmpty()) {
                        return;
                    }
                    //Image image = new Image(s);

                    BufferedImage img = null;
                    img = ImageIO.read(new File(s));
                    Image image = SwingFXUtils.toFXImage(img, null);

                    System.out.println(pretragaController.getPutanja());

                    /*File f = new File(pretragaController.getPutanja());
                    Image imageForFile = new Image(f.toURI().toURL().toExternalForm());*/
                    imageView.setImage(image);
                    path = pretragaController.getPutanja();
                } catch(Exception e) {
                    //e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Belaj");
                    alert.setHeaderText("Greška sa pronalaženjem slike!");
                    alert.setContentText("Putanja nije ispravna?");
                    alert.showAndWait();
                }
            } );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void clickSave(ActionEvent actionEvent) {
        if(fieldZnamenitost.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Belaj");
            alert.setHeaderText("Naziv je prazan!");
            alert.setContentText("Unesite naziv znamenitosti.");
            alert.showAndWait();
            return;
        }
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
