package sk.upjs.maria.babcanska.prezenckovnik;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class MainSceneController {
   //initiazlized sa spustit vtredy ked sa vytvori cela scena

   private final RiadokDao riadokDao = RiadokDaoFactory.INSTANCE.getRiadokDao();

   @FXML
   private ResourceBundle resources;

   @FXML
   private URL location;

   @FXML
   //chceme aby zobrazovalo riadky
   // akeho typu budu tie veci zadat
   //editovatelny list - stringy radsej
   private ListView<Riadok> riadkyListView;

   @FXML
   private Button vlozRiadokButton;

   //2. sposob vkladanie riadku cez stlacenie tlacitka
   //1. sposob bol na prednaske
   @FXML
   void vlozRiadokButtonAction(ActionEvent event) {
      riadokDao.pridajRiadok(new Riadok(99l, LocalDateTime.now(), "Evka", "paza", 30l));

   }

   @FXML
   void initialize() {
      //iba na testovanie
      //assert riadkyListView != null : "fx:id=\"riadkyListView\" was not injected: check your FXML file 'Untitled'.";
      List<Riadok> riadoks = riadokDao.getAll();
      riadkyListView.setItems(FXCollections.observableArrayList(riadoks));
   }
}
