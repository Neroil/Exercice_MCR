package org.example;

public class ShapeFactory {

    private ShapeFactory(){}

    static IShape create(String name){
        return switch (name){
            case "triangle" -> new Triangle();
            case "circle" -> new Circle();
            default -> throw new IllegalArgumentException("This shape does not exist");
        };
    }
}
