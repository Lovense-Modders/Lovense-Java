package eu.nogitsu.lovense.toys;

import eu.nogitsu.lovense.Toy;

import java.util.Map;

public class Domi extends Toy {
    public boolean Preset(int pattern){
        return Request("Preset", Map.of("v", pattern));
    }
}
