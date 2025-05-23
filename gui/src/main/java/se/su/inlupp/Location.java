package se.su.inlupp;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Location {
    private final String name;
    private final double x;
    private final double y;
    private Circle circle;
    private Text label;
    //ska kanske vara transient

    public Location(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;

        this.circle = new Circle(x,y, 12 , Color.BLUE);
        this.circle.setStroke(Color.BLACK);

        this.label = new Text(x+1, y+21, name);
        this.label.setMouseTransparent(true);
        this.label.setStyle("-fx-font-size: 14px; -fx-fill: black; -fx-font-weight: bold;");
    }

    public void setClickHandler(EventHandler<MouseEvent> handler) {
        circle.setOnMouseClicked(handler);
    }

    public Group getGraphics() {
        return new Group(circle, label);
    }

    public Circle getCircle() {
        return circle;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return name + " (" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Location other = (Location) obj;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}