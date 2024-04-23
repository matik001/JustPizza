package dev.justpizza.tests;

import dev.justpizza.shape.circle.Circle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CircleTest {

    @Test
    void fromRadius() {
        assertEquals(1, Circle.fromRadius(1).getRadius());
        assertEquals(Math.PI, Circle.fromRadius(1).getArea());
        assertEquals(Math.PI * 2, Circle.fromRadius(1).getPerimeter());
    }

    @Test
    void fromArea() {
        assertEquals(1, Math.round(Circle.fromArea(Math.PI).getRadius()));
        assertEquals(Math.PI, Circle.fromArea(Math.PI).getArea());
        assertEquals(Math.PI * 2, Circle.fromArea(Math.PI).getPerimeter());
    }

    @Test
    void fromPerimeter() {
        assertEquals(1, Math.round(Circle.fromPerimeter(Math.PI * 2).getRadius()));
        assertEquals(Math.PI, Circle.fromPerimeter(Math.PI * 2).getArea());
        assertEquals(Math.PI * 2, Circle.fromPerimeter(Math.PI * 2).getPerimeter());
    }

    @org.junit.jupiter.api.Test
    void createCircumcircle() {
        var circle = Circle.fromRadius(1);
        assertEquals(1, circle.createCircumcircle().getRadius());
        assertEquals(Math.PI, circle.createCircumcircle().getArea());
        assertEquals(Math.PI * 2, circle.createCircumcircle().getPerimeter());
    }

    @org.junit.jupiter.api.Test
    void doubleArea() {
        var circle = Circle.fromRadius(1);
        assertEquals(Math.sqrt(2), circle.doubleArea().getRadius());
        //assertEquals(Math.PI * 2, circle.doubleArea().getArea()); //TODO: numeric error
        assertEquals(Math.sqrt(2) * 2 * Math.PI, circle.doubleArea().getPerimeter());
    }
}