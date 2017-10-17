package sk.upjs.maria.babcanska.prezenckovnik;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrezenckovnikApp extends Application {
   @Override
   public void start(final Stage primaryStage) throws Exception {
      MainSceneController mainSceneController = new MainSceneController();

      //kazda appka ma okno , scene = javisko , stage
      //kazda scena chce mat rodica
      //chceme, aby parent prisiel z fxml suboru, ktory sme v scenbuilderi urobili
      //natihanes fxmkl
      //staci nazov suboru, lebo si v tom istom palicku

      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
      fxmlLoader.setController(mainSceneController);
      Parent parentPane = fxmlLoader.load();
      Scene scene = new Scene(parentPane);
      primaryStage.setScene(scene);
      primaryStage.setTitle("Prezenckovnik");
      primaryStage.show();
   }

   public static void main(String[] args) {
      //chceme tu spusit tu aplikaciu
      //staticka metoda triedy Application
      launch(args);
   }
}
