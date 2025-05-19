package se.su.inlupp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui extends Application {

  public void start(Stage stage) {
    Graph<String> graph = new ListGraph<String>();
    String javaVersion = System.getProperty("java.version");
    String javafxVersion = System.getProperty("javafx.version");

    Label label = new Label("Hello, JavaFX " + javafxVersion + " UwU, yaho yaho " + javaVersion + ".");

    // Create 3 buttons
    Button fdnp = new Button("Find Path");
    Button shco = new Button("Show Connection");
    Button nwpl = new Button("New Place");
    Button nwco = new Button("New Connection");
    Button cco = new Button("Change Connection");

    MenuBar fileBar = new MenuBar();

    Menu fileMenu = new Menu("File");
    MenuItem newMapItem = new MenuItem("New Map");
    MenuItem openItem = new MenuItem("Open");
    MenuItem saveItem = new MenuItem("Save");
    MenuItem saveImageItem = new MenuItem("Save Image");
    MenuItem exitItem = new MenuItem("Exit");

    fileBar.getMenus().add(fileMenu);
    fileMenu.getItems().addAll(newMapItem, openItem, saveItem, saveImageItem, exitItem);



    // Optional: Set actions for the buttons
    fdnp.setOnAction(e -> System.out.println("Button One clicked!"));
    shco.setOnAction(e -> System.out.println("Button Two clicked!"));
    nwpl.setOnAction(e -> System.out.println("Button Three clicked!"));
    nwco.setOnAction(e -> System.out.println("Button Two clicked!"));
    cco.setOnAction(e -> System.out.println("Button Three clicked!"));

    HBox navi = new HBox(0, fileBar); // spacing of 10 pixels
    navi.setAlignment(Pos.TOP_LEFT); // center the buttons horizontally

    // Put the buttons in an HBox (a horizontal row)
    HBox buttonRow = new HBox(1, fdnp, shco, nwpl, nwco, cco); // spacing of 10 pixels
    buttonRow.setAlignment(Pos.CENTER); // center the buttons horizontally

    // Create a VBox to hold the button row and other content
    VBox root = new VBox(20, navi, buttonRow, label); // spacing of 20 pixels
    root.setAlignment(Pos.TOP_CENTER);  // kommer blin problem senare med detta, root till grid pain

    Scene scene = new Scene(root, 640, 480);
    stage.setScene(scene);
    stage.setTitle("JavaFX with Top Button Row");
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}