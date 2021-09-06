package eu.nogitsu.test;

import eu.nogitsu.lovense.Lovense;
import eu.nogitsu.lovense.Toy;

public class Main {

    public static void main(String[] args) {
        Toy[] toys = Lovense.GetToys();

        for(Toy toy : toys){
            System.out.println(toy.name);
            System.out.println(toy.Battery());
            System.out.println(toy.status);
        }
    }
}
