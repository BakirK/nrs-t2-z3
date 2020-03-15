package ba.unsa.etf.rpr;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class GradController {
    public TextField fieldNaziv;
    public TextField fieldBrojStanovnika;
    public TextField fieldPostanskiBroj;
    public ChoiceBox<Drzava> choiceDrzava;
    public ObservableList<Drzava> listDrzave;
    private Grad grad;
    @FXML
    private ListView<Znamenitost> listViewZnamenitosti;
    public ObservableList<Znamenitost> znamenitostiList;
    private GeografijaDAO dao;

    public GradController(Grad grad, ArrayList<Drzava> drzave) {
        this.grad = grad;
        listDrzave = FXCollections.observableArrayList(drzave);
        ArrayList<Znamenitost> znamenitosti = grad.getZnamenitosti();
        if(znamenitosti != null && !znamenitosti.isEmpty()) {
            znamenitostiList = FXCollections.observableArrayList(znamenitosti);
        }
        dao = GeografijaDAO.getInstance();
    }

    @FXML
    public void initialize() {
        choiceDrzava.setItems(listDrzave);
        if (grad != null) {
            fieldNaziv.setText(grad.getNaziv());
            fieldBrojStanovnika.setText(Integer.toString(grad.getBrojStanovnika()));
            fieldPostanskiBroj.setText(Integer.toString(grad.getPostanskiBroj()));
            // choiceDrzava.getSelectionModel().select(grad.getDrzava());
            // ovo ne radi jer grad.getDrzava() nije identički jednak objekat kao član listDrzave
            for (Drzava drzava : listDrzave)
                if (drzava.getId() == grad.getDrzava().getId())
                    choiceDrzava.getSelectionModel().select(drzava);
        } else {
            choiceDrzava.getSelectionModel().selectFirst();
        }
        if(znamenitostiList != null && !znamenitostiList.isEmpty()) {
            listViewZnamenitosti.setItems(znamenitostiList);
        }
    }

    public Grad getGrad() {
        return grad;
    }

    public void clickCancel(ActionEvent actionEvent) {
        grad = null;
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }

    public void clickDodaj(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/znamenitosti.fxml"));
            ZnamenitostiController znamenitostiController = new ZnamenitostiController(grad);
            loader.setController(znamenitostiController);
            root = loader.load();
            stage.setTitle("Znamenitosti");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(true);
            stage.show();

            stage.setOnHiding( event -> {
                refreshListView();
            } );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void refreshListView() {
        ArrayList<Znamenitost> temp = dao.dajZnamenitosti(grad.getId());
        if(temp != null && !temp.isEmpty()) {
            znamenitostiList = FXCollections.observableArrayList(temp);
            listViewZnamenitosti.setItems(znamenitostiList);
        }
    }

    private void lockInputs() {
        //fieldNaziv.disableProperty().setValue(true);
        fieldNaziv.setEditable(false);
        fieldBrojStanovnika.setEditable(false);
        fieldPostanskiBroj.setEditable(false);
        choiceDrzava.disableProperty().setValue(true);
        fieldNaziv.getScene().setCursor(Cursor.WAIT);
    }

    private void unlockInputs() {
        //fieldNaziv.disableProperty().setValue(false);
        fieldNaziv.setEditable(true);
        fieldBrojStanovnika.setEditable(true);
        fieldPostanskiBroj.setEditable(true);
        choiceDrzava.disableProperty().setValue(false);;
        fieldNaziv.getScene().setCursor(Cursor.DEFAULT);
    }

    public void clickOk(ActionEvent actionEvent) {
        lockInputs();
        AtomicBoolean sveOk = new AtomicBoolean(true);

        if (fieldNaziv.getText().trim().isEmpty()) {
            fieldNaziv.getStyleClass().removeAll("poljeIspravno");
            fieldNaziv.getStyleClass().add("poljeNijeIspravno");
            sveOk.set(false);
        } else {
            fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
            fieldNaziv.getStyleClass().add("poljeIspravno");
        }


        int brojStanovnika = 0;
        try {
            brojStanovnika = Integer.parseInt(fieldBrojStanovnika.getText());
        } catch (NumberFormatException e) {
            // ...
        }
        if (brojStanovnika <= 0) {
            fieldBrojStanovnika.getStyleClass().removeAll("poljeIspravno");
            fieldBrojStanovnika.getStyleClass().add("poljeNijeIspravno");
            sveOk.set(false);
        } else {
            fieldBrojStanovnika.getStyleClass().removeAll("poljeNijeIspravno");
            fieldBrojStanovnika.getStyleClass().add("poljeIspravno");
        }


        Thread thread1 = new Thread(() -> {
            String link = "http://c9.etf.unsa.ba/proba/postanskiBroj.php?postanskiBroj=";
            link += fieldPostanskiBroj.getText().trim();
            try {
                URL url = new URL(link);
                BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
                String json = "", line = null;
                while ((line = input.readLine()) != null) {
                    json += line;
                }
                input.close();
                boolean temp = json.equals("OK");
                if(temp) {
                    Platform.runLater(() -> {
                        fieldPostanskiBroj.getStyleClass().removeAll("poljeNijeIspravno");
                        fieldPostanskiBroj.getStyleClass().add("poljeIspravno");
                        if (!sveOk.get()) return;
                        if (grad == null) grad = new Grad();
                        grad.setNaziv(fieldNaziv.getText());
                        grad.setBrojStanovnika(Integer.parseInt(fieldBrojStanovnika.getText().trim()));
                        grad.setDrzava(choiceDrzava.getValue());
                        grad.setPostanskiBroj(Integer.parseInt(fieldPostanskiBroj.getText().trim()));
                        Platform.runLater(() -> {
                            unlockInputs();
                            Stage stage = (Stage) fieldNaziv.getScene().getWindow();
                            stage.close();
                        });
                    });

                } else {
                    Platform.runLater(() -> {
                        fieldPostanskiBroj.getStyleClass().removeAll("poljeIspravno");
                        fieldPostanskiBroj.getStyleClass().add("poljeNijeIspravno");
                        sveOk.set(false);
                        unlockInputs();
                    });
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



        });
        thread1.start();
        /*try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
