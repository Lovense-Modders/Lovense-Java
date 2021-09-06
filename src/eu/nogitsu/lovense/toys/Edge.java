package eu.nogitsu.lovense.toys;

import eu.nogitsu.lovense.Toy;

import java.util.Map;

public class Edge extends Toy {
    public boolean Preset(int pattern){
        return Request("Preset", Map.of("v", pattern));
    }

    public boolean Vibrate1(int speed){
        return Request("AVibrate1",Map.of("v", speed));
    }

    public boolean AVibrate1(int speed, double seconds){
        return Request("AVibrate1",Map.of("v", speed, "sec", seconds));
    }

    public boolean Vibrate2(int speed){
        return Request("AVibrate1",Map.of("v", speed));
    }

    public boolean AVibrate2(int speed, double seconds){
        return Request("AVibrate1",Map.of("v", speed, "sec", seconds));
    }
}
