package se.su.inlupp;

import java.io.File;

// PROG2 VT2025, Inlämningsuppgift, del 2
// Grupp 369
// Einar Gurell eigu0369
// Linus Sjöberg lisj4306
// Tigris Lundgren tilu6961

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Gui extends Application {

  private Stage stage;
  private BorderPane root;


  public void start(Stage stage) {
    this.stage = stage;
    root = new BorderPane();

    Graph<String> graph = new ListGraph<String>();
    String javaVersion = System.getProperty("java.version");
    String javafxVersion = System.getProperty("javafx.version");

    Label label = new Label("Hello, JavaFX " + javafxVersion + " UwU, yaho yaho " + javaVersion + ".");

    // Create 3 buttons
    Button FindPathButton = new Button("Find Path");
    Button ShowCoButton = new Button("Show Connection");
    Button NewPlaceButton = new Button("New Place");
    Button NewCoButton = new Button("New Connection");
    Button ChangeCoButton = new Button("Change Connection");

    MenuBar fileBar = new MenuBar();

    Menu fileMenu = new Menu("File");
    MenuItem newMapItem = new MenuItem("New Map");
    //newMapItem.setOnAction(e-> openNewMap());

    MenuItem openMap = new MenuItem("Open");

    MenuItem saveMap = new MenuItem("Save");

    MenuItem saveImageMap = new MenuItem("Save Image");

    MenuItem exitMap = new MenuItem("Exit");

    fileBar.getMenus().add(fileMenu);
    fileMenu.getItems().addAll(newMapItem, openMap, saveMap, saveImageMap, exitMap);

    // Optional: Set actions for the buttons
    FindPathButton.setOnAction(e -> System.out.println("Button One clicked!"));
    ShowCoButton.setOnAction(e -> System.out.println("Button Two clicked!"));
    NewPlaceButton.setOnAction(e -> System.out.println("Button Three clicked!"));
    NewCoButton.setOnAction(e -> System.out.println("Button Two clicked!"));
    ChangeCoButton.setOnAction(e -> System.out.println("Button Three clicked!"));

    HBox navi = new HBox(0, fileBar); // spacing of 10 pixels
    navi.setAlignment(Pos.TOP_LEFT); // center the buttons horizontally

    // Put the buttons in an HBox (a horizontal row)
    HBox buttonRow = new HBox(10, FindPathButton, ShowCoButton, NewPlaceButton, NewCoButton, ChangeCoButton); // spacing of 10 pixels
    buttonRow.setAlignment(Pos.CENTER); // center the buttons horizontally

    // Create a VBox to hold the button row and other content
    VBox topBox = new VBox(5, navi, buttonRow); // spacing of 20 pixels
    //topBox.setAlignment(Pos.TOP_CENTER);  // kommer blin problem senare med detta, root till grid pain

    FlowPane topHud = new FlowPane(topBox);
    root.setTop(topBox);
    
    Scene scene = new Scene(root, 640, 480);
    stage.setScene(scene);
    stage.setTitle("JavaFX with Top Button Row");
    stage.show();
  }


/* 
  private void openNewMap(){
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose File");
    fileChooser.getExtensionFilters().add(
      new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*jepg"));
    File selectedFile = fileChooser.showOpenDialog(stage);
    if (selectedFile != null){

    }
  }
  */

  
  public static void main(String[] args) {
    launch(args);
  }
}