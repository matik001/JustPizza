package dev.justpizza.shape;

import dev.justpizza.shape.circle.Circle;

import java.util.Map;

public abstract class Shape {
    protected abstract Map<String, Object> getProperties();

    protected abstract String getShapeName();

    @Override
    public String toString() {
        var sb = new StringBuilder();
        var props = getProperties();
        var shapeName = getShapeName();
        sb.append(shapeName);
        sb.append(" [ ");

        var first = true;
        for (var prop : props.entrySet()) {
            var propName = prop.getKey();
            var propValue = prop.getValue();
            if (!first)
                sb.append(", ");

            sb.append(propName).append("=").append(propValue);


            first = false;
        }

        sb.append(" ] ");
        return sb.toString();
    }

    public final void printCharacteristic() {
        System.out.println(this);
    }

    public abstract double calcArea();

//    public abstract double getPerimeter();

    public abstract Circle createCircumcircle() throws IllegalShapeException;
}
