package sk.upjs.maria.babcanska.prezenckovnik;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainSceneController {
   //initiazlized sa spustit vtredy ked sa vytvori cela scena

   private final RiadokDao riadokDao = RiadokDaoFactory.INSTANCE.getRiadokDao();
   private final RiadokFxModel editovanyRiadok = new RiadokFxModel();

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
   private ObservableList<Riadok> riadkyModel;

   //2. sposob vkladanie riadku cez stlacenie tlacitka
   //1. sposob bol na prednaske
   @FXML
   void vlozRiadokButtonAction(ActionEvent event) {
      try {
         riadokDao.pridajRiadok(editovanyRiadok.getRiadok());
         riadkyModel.setAll(riadokDao.getAll());
      } catch (DaoException daoException) {
         System.err.println("Nepodarilo sa vložiť riadok. Príčina: " +
               daoException.getMessage());
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Prezenčkovník: Chybové hlásenie");
         alert.setHeaderText("Nepodarilo sa vložiť riadok!");
         alert.setContentText("Príčina: " + daoException.getMessage());
         alert.showAndWait();
         System.out.println("Chybové okno zatvorené");
      }
   }

   @FXML
   private TextField ucastnikTextField;

   @FXML
   private TextField poradieTextField;

   @FXML
   private TextField udalostTextField;

   @FXML
   void initialize() {
      //iba na testovanie
      //assert riadkyListView != null : "fx:id=\"riadkyListView\" was not injected: check your FXML file 'Untitled'.";
      setvlozRiadokButtonDisable();

      udalostTextField.textProperty().bindBidirectional(
            editovanyRiadok.udalostProperty());

      udalostTextField.textProperty().addListener(new ChangeListener<String>() {
         @Override
         public void changed(final ObservableValue<? extends String> observable, final String oldValue,
               final String newValue) {
            if (!goodTextValue(oldValue)) {
               editovanyRiadok.setPocetChyb(editovanyRiadok.getPocetChyb() - 1);
            }
            if (goodTextValue(newValue)) {
               udalostTextField.setStyle("-fx-background-color: white;");
            } else {
               udalostTextField.setStyle("-fx-background-color: lightcoral;");
               editovanyRiadok.setPocetChyb(editovanyRiadok.getPocetChyb() + 1);
            }
            setvlozRiadokButtonDisable();
         }
      });

      poradieTextField.textProperty().bindBidirectional(
            editovanyRiadok.poradieProperty());

      poradieTextField.textProperty().addListener(new ChangeListener<String>() {
         @Override
         public void changed(final ObservableValue<? extends String> observable, final String oldValue,
               final String newValue) {
            if (!goodLongValueText(oldValue)) {
               editovanyRiadok.setPocetChyb(editovanyRiadok.getPocetChyb() - 1);
            }
            if (goodLongValueText(newValue)) {
               poradieTextField.setStyle("-fx-background-color: white;");
            } else {
               poradieTextField.setStyle("-fx-background-color: lightcoral;");
               editovanyRiadok.setPocetChyb(editovanyRiadok.getPocetChyb() + 1);
            }
            setvlozRiadokButtonDisable();
         }
      });

      ucastnikTextField.textProperty().bindBidirectional(
            editovanyRiadok.ucastnikProperty());

      ucastnikTextField.textProperty().addListener(new ChangeListener<String>() {
         @Override
         public void changed(final ObservableValue<? extends String> observable, final String oldValue,
               final String newValue) {
            if (!goodTextValue(oldValue)) {
               editovanyRiadok.setPocetChyb(editovanyRiadok.getPocetChyb() - 1);
            }
            if (goodTextValue(newValue)) {
               ucastnikTextField.setStyle("-fx-background-color: white;");
            } else {
               ucastnikTextField.setStyle("-fx-background-color: lightcoral;");
               editovanyRiadok.setPocetChyb(editovanyRiadok.getPocetChyb() + 1);
            }
            setvlozRiadokButtonDisable();
         }
      });

      List<Riadok> riadoks = riadokDao.getAll();

      riadkyModel = FXCollections.observableArrayList(riadoks);
      riadkyListView.setItems(riadkyModel);
   }

   private boolean goodTextValue(String value) {
      return value != null && !value.trim().isEmpty();
   }

   private boolean goodLongValueText(String value) {
      try {
         Long.parseLong(value);
         return true;
      } catch (NumberFormatException numberFormatException) {
         return false;
      }
   }

   private void setvlozRiadokButtonDisable() {
      if (editovanyRiadok.getPocetChyb() == 0) {
         vlozRiadokButton.setDisable(false);
      } else {
         vlozRiadokButton.setDisable(true);
      }
   }

}
