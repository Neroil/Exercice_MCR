package org.example;

public class FactoryPatternDemo
{
    public static void main(String[] args)
    {
        IShape triangle = ShapeFactory.create("triangle");
        triangle.draw();

        IShape circle = ShapeFactory.create("circle");
        circle.draw();
    }
}