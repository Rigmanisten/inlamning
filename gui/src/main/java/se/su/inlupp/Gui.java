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
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.Optional;

public class Gui extends Application  {

  //mapView och primaryStage behöver kunna nås från andra metoder och är därför deklarerade utanför start
  private Stage stage;
  private ImageView mapView;
  private Pane drawingPane;
  Graph<Location> graph = new ListGraph<Location>();
   boolean saved = true;

  @Override
  public void start(Stage stage) {
    this.stage = stage;

    //root behöver inte nås i någon annan metod, och skapas därför i start
    BorderPane root = new BorderPane();
    mapView = new ImageView();
    drawingPane = new Pane();
    drawingPane.setPickOnBounds(false);
    StackPane mapStack = new StackPane(mapView, drawingPane);
    root.setCenter(mapStack);
    
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
    //openMap.setOnAction(e-> openMap());
    //saveMapFile.setOnAction(e-> saveFile());
    //saveMapFile.setOnAction(e-> saveImage());
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

    newPlaceButton.setOnAction(e-> {
      mapView.setCursor(Cursor.CROSSHAIR);
      buttonRow.setDisable(true);

      mapView.setOnMouseClicked(mouseEvent -> {
        mapView.setCursor(Cursor.DEFAULT);
        mapView.setOnMouseClicked(null);
        mapPlaceClick(mouseEvent.getX(), mouseEvent.getY());
      });
      buttonRow.setDisable(false);
      saved = false;
    });


    //menu och knappar lägs i en i nav i toppen
    VBox nav = new VBox(menuBar, buttonRow);
    root.setTop(nav);

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

      drawingPane.setMinSize(image.getWidth(), image.getHeight());
      drawingPane.setMaxSize(image.getWidth(), image.getHeight());
      drawingPane.setPrefSize(image.getWidth(), image.getHeight());

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

    private void mapPlaceClick(double x, double y){      
      TextInputDialog dialog = new TextInputDialog();
      dialog.setTitle("Name");
      dialog.setHeaderText(null);
      dialog.setContentText("Name of place:");

      Optional<String> input = dialog.showAndWait();
      input.ifPresent(name -> {
        //creat and add loaction to graph
        Location location = new Location(name, x, y);
        graph.add(location);
        //Draw on map
        Circle place = new Circle(x,y, 12 , Color.BLUE); 
        Text placeName = new Text(x+1, y+21, name);
        placeName.setMouseTransparent(true);
        placeName.setStyle
        ("-fx-font-size: 14px; -fx-fill: black; -fx-font-weight: bold;");
        
        Group PlaceNode = new Group(place, placeName);
        drawingPane.getChildren().add(PlaceNode);
      });
      
      //Alert placeName = new Alert(Alert.AlertType.CONFIRMATION);




    }
  
  private void exit(){
    //boolean saved = true; // intal true //  skulle kunna sätta utanför metoden

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
