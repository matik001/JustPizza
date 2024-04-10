package dev.justpizza.shape;

import java.util.Dictionary;
import java.util.Map;

public abstract class Shape {
    protected abstract Map<String, Object> getProperties();

    protected abstract String getShapeName();

    @Override
    public String toString() {
        var sb = new StringBuffer();
        var props = getProperties();
        var shapeName = getShapeName();
        sb.append(shapeName);
        sb.append(" [ ");

        var first = true;
        for (var prop : props.entrySet()){
            var propName = prop.getKey();
            var propValue = prop.getValue();
            if(!first)
                sb.append(", ");

            sb.append(propName).append("=").append(propValue);


            first = false;
        }

        sb.append(" ] ");
        return sb.toString();
    }

    public void printCharacteristic(){
        System.out.println(this.toString());
    }
    public abstract double calcArea();
}
