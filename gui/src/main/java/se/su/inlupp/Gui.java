package se.su.inlupp;

// PROG2 VT2025, Inlämningsuppgift, del 2
// Grupp 369
// Einar Gurell eigu0369
// Linus Sjöberg lisj4306
// Tigris Lundgren tilu6961

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.Optional;

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
    exit.setOnAction(e -> exit());

    //knappar skapas
    Button findPathButton = new Button("Find Path");
    Button showConnectionButton = new Button("Show Connection");
    Button newPlaceButton = new Button("New Place");
    Button newConnectionButton = new Button("New Connection");
    Button changeConnectionButton = new Button("Change Connection");

    HBox buttonRow = new HBox(5, findPathButton, showConnectionButton, newPlaceButton, newConnectionButton, changeConnectionButton);
    buttonRow.setDisable(true);
    buttonRow.setPadding(new Insets(5));
    buttonRow.setAlignment(Pos.CENTER);

    //menu och knappar lägs i en i nav i toppen
    VBox nav = new VBox(menuBar, buttonRow);

    root.setTop(nav);

    mapView = new ImageView();
    root.setCenter(mapView);

    //skapar en listener som aktiverar knapparna, om bilden ändras
    mapView.imageProperty().addListener((obs, oldImage, newImage) -> {
      if (newImage != null) {
        buttonRow.setDisable(false);
      }
    });

    //scene skapas
    Scene scene = new Scene(root, 550, 70);
    stage.setScene(scene);
    stage.setTitle("PathFinder");
    stage.show();

    stage.setOnCloseRequest(e -> exit());
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

      //sätter en fast storlek på inladdade bilden
      mapView.setImage(image);
      mapView.setFitWidth(image.getWidth());
      mapView.setFitHeight(image.getHeight());

      //ändrar scene storlek efter den inladdade bildens storlek
      changeSceneSize(image);
    }
    else {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Error Opening File");
      alert.setHeaderText(null);
      alert.setContentText("You have not selected a working file.");
      alert.showAndWait();
    }
  }

  private void changeSceneSize (Image image) {
    //om bild mindre bred än nav, gör inte scene storlek mindre
    if(image.getWidth() < 550) {
      stage.setWidth(550);
    }
    else {
      stage.setWidth(image.getWidth() + 80);
    }
    stage.setHeight(image.getHeight() + 110);
  }


  
  private void exit(){
    boolean saved = false; 

    // lisener som kollar om något ändrats mellan sparingar, ska ändra saved
    if(!saved){
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Warning!");
      alert.setHeaderText(null);
      alert.setContentText("Unsaved changes, exit anyway?");
      Optional<ButtonType> answer = alert.showAndWait();
      if (answer.isPresent() && answer.get() == ButtonType.OK){
        Platform.exit();
      }
      // Användaren klickade på OK
    }else{
    // exit program
    Platform.exit();
    }
  }

  public static void main (String[]args){
    launch(args);
  }
}
