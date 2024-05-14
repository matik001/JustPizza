package dev.justpizza.shape;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dev.justpizza.config.AppSettings;
import dev.justpizza.shape.ellipse.Circle;
import dev.justpizza.shape.quadrangle.IsoscelesTrapezium;
import dev.justpizza.shape.quadrangle.Rectangle;
import dev.justpizza.translations.TranslationKey;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
public abstract class Shape {

    private final ZonedDateTime date;

    public Shape() {
        this.date = java.time.ZonedDateTime.now();
    }

    protected Map<String, Object> getProperties() {
        return Map.of(
                "Area", getArea(),
                "Perimeter", getPerimeter(),
                "Date created", getDate()
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
            sb.append(propName)
              .append("=")
              .append(propName.equals("Date created")
                      ? ((ZonedDateTime) propValue).format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss"))
                      : String.format("%.2f", propValue));

            first = false;
        }

        sb.append(" ]");
        return sb.toString();
    }



    @JsonIgnore

    public final String getCharacteristic() {
        return this.toString();
    }

//    public final void printCharacteristic() {
//        try {
//            printCharacteristicToStream(System.out);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public final void printCharacteristicToStream(OutputStream stream) throws IOException {
//        var sw = new OutputStreamWriter(stream);
//        sw.append(getCharacteristic());
//        sw.flush();
//    }

    public abstract double getArea();

    public abstract double getPerimeter();

    public ZonedDateTime getDate() {
        return date;
    }

    public abstract Circle createCircumcircle() throws IllegalShapeException;

    public abstract Shape doubleArea() throws IllegalShapeException;

    protected static String paramError(String shapeName) {
        return String.format(
                AppSettings.global.translations.get(TranslationKey.no_shape_for_parameters),
                shapeName
        );
    }

    protected static String ambiguousError(String shapeName) {
        return String.format(
                AppSettings.global.translations.get(TranslationKey.ambiguous_shape),
                shapeName
        );
    }
}
