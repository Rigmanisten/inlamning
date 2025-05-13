package se.su.inlupp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    Button fdnp = new Button("Find path");
    Button shco = new Button("show connection");
    Button nwpl = new Button("new place");
    Button nwco = new Button("new connection");
    Button cco = new Button("Change Connection");
    Button fileb = new Button("file");

    // Optional: Set actions for the buttons
    fdnp.setOnAction(e -> System.out.println("Button One clicked!"));
    shco.setOnAction(e -> System.out.println("Button Two clicked!"));
    nwpl.setOnAction(e -> System.out.println("Button Three clicked!"));
    nwco.setOnAction(e -> System.out.println("Button Two clicked!"));
    cco.setOnAction(e -> System.out.println("Button Three clicked!"));

    HBox navi = new HBox(10, fileb); // spacing of 10 pixels
    navi.setAlignment(Pos.TOP_LEFT); // center the buttons horizontally

    // Put the buttons in an HBox (a horizontal row)
    HBox buttonRow = new HBox(10, fdnp, shco, nwpl, nwco, cco); // spacing of 10 pixels
    buttonRow.setAlignment(Pos.CENTER); // center the buttons horizontally

    // Create a VBox to hold the button row and other content
    VBox root = new VBox(20, navi, buttonRow, label); // spacing of 20 pixels
    root.setAlignment(Pos.TOP_CENTER);

    Scene scene = new Scene(root, 640, 480);
    stage.setScene(scene);
    stage.setTitle("JavaFX with Top Button Row");
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}