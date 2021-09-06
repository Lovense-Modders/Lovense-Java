package eu.nogitsu.lovense.toys;

import eu.nogitsu.lovense.Toy;

import java.util.Map;

public class Nora extends Toy {
    public boolean Rotate(int speed){
        return Request("Rotate", Map.of("v", speed));
    }

    public boolean RotateAntiClockwise(int speed){
        return Request("RotateAntiClockwise", Map.of("v", speed));
    }

    public boolean RotateClockwise(int speed){
        return Request("RotateClockwise", Map.of("v", speed));
    }

    public boolean RotateChange(){
        return Request("RotateChange");
    }

    public boolean ARotate(int speed, int seconds){
        return Request("AAirLevel", Map.of("r", speed, "sec", seconds));
    }

    public boolean AVibRotate(int vibration, int rotation, int seconds){
        return Request("AVibRotate", Map.of("v", vibration, "r", rotation, "sec", seconds));
    }
}
