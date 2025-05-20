package se.su.inlupp;

import java.io.File;

// PROG2 VT2025, Inlämningsuppgift, del 2
// Grupp 369
// Einar Gurell eigu0369
// Linus Sjöberg lisj4306
// Tigris Lundgren tilu6961

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Gui extends Application {

  //mapView och primaryStage behöver kunna nås från andra metoder och är därför deklarerade utanför start
  private Stage stage;
  private ImageView mapView;

  @Override
  public void start(Stage stage) {
    this.stage = stage;

    //root behöver inte nås i någon annan metod, och skapas därför i start
    BorderPane root = new BorderPane();

    //file menu skapas
    MenuBar menuBar = new MenuBar();
    Menu fileMenu = new Menu("File");
    MenuItem newMap = new MenuItem("New Map");
    MenuItem openMap = new MenuItem("Open");
    MenuItem saveMapFile = new MenuItem("Save");
    MenuItem saveMapImage = new MenuItem("Save Image");
    MenuItem exit = new MenuItem("Exit");

    menuBar.getMenus().add(fileMenu);
    fileMenu.getItems().addAll(newMap, openMap, saveMapFile, saveMapImage, exit);

    //ansluter knapparna till dess hjälpmetoder
    newMap.setOnAction(e -> openNewMap());

    //knappar skapas
    Button findPathButton = new Button("Find Path");
    Button showConnectionButton = new Button("Show Connection");
    Button newPlaceButton = new Button("New Place");
    Button newConnectionButton = new Button("New Connection");
    Button changeConnectionButton = new Button("Change Connection");

    HBox buttonRow = new HBox(5, findPathButton, showConnectionButton, newPlaceButton, newConnectionButton, changeConnectionButton);
    buttonRow.setAlignment(Pos.CENTER);

    //menu och knappar lägs i en i nav i toppen
    VBox nav = new VBox(5, menuBar, buttonRow);
    nav.setPadding(new Insets(5));
    root.setTop(nav);

    mapView = new ImageView();
    root.setCenter(mapView);

    Scene scene = new Scene(root, 640, 480);
    stage.setScene(scene);
    stage.setTitle("PathFinder");
    stage.show();
  }


  private void openNewMap () {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open Map Image");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
    );
    File selectedFile = fileChooser.showOpenDialog(stage);

    if (selectedFile != null) {
      Image image = new Image(selectedFile.toURI().toString());

      mapView.setImage(image);
      mapView.setFitWidth(image.getWidth());
      mapView.setFitHeight(image.getHeight());
    }
    else {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Error Opening File");
      alert.setHeaderText(null);
      alert.setContentText("You have not selected a working file.");
      alert.showAndWait();
    }
  }

  public static void main (String[]args){
    launch(args);
  }
}
