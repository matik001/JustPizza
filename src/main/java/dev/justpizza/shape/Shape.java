package dev.justpizza.shape;

import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.circle.Circle;
import dev.justpizza.translations.TranslationKey;

import java.util.Map;

public abstract class Shape {
    protected Map<String, Object> getProperties() {
        return Map.of(
                "Area", getArea(),
                "Perimeter", getPerimeter()
        );
    }

    protected String getShapeName() {
        return getClass().getSimpleName();
    }

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

    public abstract double getArea();

    public abstract double getPerimeter();

    public abstract Circle createCircumcircle() throws IllegalShapeException;

    public abstract Shape doubleArea() throws IllegalShapeException;

    protected static String paramError(String shapeName) {
        return String.format(
                AppSettings.global.translations.get(TranslationKey.no_shape_for_parameters),
                shapeName
        );
    }
}
