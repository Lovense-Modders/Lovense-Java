package eu.nogitsu.lovense.toys;

import eu.nogitsu.lovense.Toy;

import java.util.Map;

public class Max extends Toy {
    public boolean AirAuto(int speed){
        return Request("AirAuto", Map.of("v", speed));
    }

    public boolean AirIn(){
        return Request("AirIn");
    }

    public boolean AirOut(){
        return Request("AirOut");
    }

    public boolean AAirLevel(int speed, int seconds){
        return Request("AAirLevel", Map.of("a", speed, "sec", seconds));
    }

    public boolean AVibAir(int vibration, int contraction, int seconds){
        return Request("AVibAir", Map.of("v", vibration, "a", contraction, "sec", seconds));
    }
}
