/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package unoflip;
import javax.swing.*;
import java.util.*;

public class UnoFlip {

    /**
     * @param args the command line arguments
     */
    
    /*  aqui inicia nuestra funcion main    */
    public static void main(String[] args) {
        Entity[]e;
        Juego game;
        Scanner scn = new Scanner(System.in);
        int choice;
        
        e= new Entity[4];
        String []n;
        
        /*  aca se inician los jugadores de nuestra partida */
        n=new String[4];
        n[0] = "jug";
        n[1] = "2";
        n[2] = "3";
        n[3] = "4";
        
        /*  imporecion de cada jugador y de la cantidad de cartas que tiene cada uno    */
        game = new Juego(n);
        int i;
        while(true){
            System.out.println(game.jugadores[0]+"tiene "+game.jugadores[0].baraja.size()+" cartas");
            System.out.println(game.jugadores[1]+"tiene "+game.jugadores[1].baraja.size()+" cartas");
            System.out.println(game.jugadores[2]+"tiene "+game.jugadores[2].baraja.size()+" cartas");
            System.out.println(game.jugadores[3]+"tiene "+game.jugadores[3].baraja.size()+" cartas");
            if(game.side)
                
                /*  se impirme tambien la carta actual para saber que el lo que pudes jugar cuando sea tu turno */
                System.out.println("carta actual:" + game.getCartaActual().getLadoClaro().toString());
            else
                System.out.println("carta actual:" + game.getCartaActual().getLadoOscuro().toString());
            if(game.jugadorActual()== game.jugadores[0]){
                System.out.println("tira una carta");
                i = 1;
                for(Carta c: game.jugadorActual().getBaraja()){
                    if(game.side)
                        System.out.println(i+"-"+c.getLadoClaro().toString());
                    else
                        System.out.println(i+"-"+c.getLadoOscuro().toString());
                    i++;    
                }
                choice = scn.nextInt();
                if(choice ==0){
                    game.jugadorActual().robarCarta(game.retirarCarta());
                    game.skip();
                }    
                else{
                try{
                    if(game.side)
                        game.tirarCarta(game.jugadorActual(), game.jugadorActual().baraja.get(choice-1), LightSide.LightColor.RED);
                    else
                        game.tirarCarta(game.jugadorActual(), game.jugadorActual().baraja.get(choice-1), DarkSide.DarkColor.PURPLE);
                }
                catch(InvalidColorException|InvalidTurnException|InvalidValueException ex){
                    System.out.println(ex);
                }
                }
                
            }
            
            /*  en el caso de que sea un bot el que le toque jugar*/
            else
                game.tirarCartaBot(game.jugadorActual());
           
        }
        
        
    }
        
}
