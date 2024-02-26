/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package unoflip;

import java.util.*;
import java.util.Scanner.*;


//implementacion del juego UNO con el  main
public class UnoFlip {

    public static void main(String[] args) {
         Scanner op = new Scanner(System.in);
        int caso = 0;
        do {
            System.out.println(" - - Menu - - ");
            System.out.println("1. Jugar");
            System.out.println("2. Ayuda");
            System.out.println("3. Acerca De ");
            System.out.println("4. Salir");
            System.out.print("Ingrese opcion: ");
            caso = op.nextInt();
            switch(caso){
               case 1:
       //Se crea el Juego Uno 
         Entity[]e;
        Juego game;
        Scanner scn = new Scanner(System.in);
        int choice;
        
        e= new Entity[4];
        String []n;
        
       
        
        // se inician los jugadores de la partida 
        n=new String[4];
        n[0] = "jug";
        n[1] = "2";
        n[2] = "3";
        n[3] = "4";
        
        //cantidad de cartas que tiene cada uno    
        game = new Juego(n);
        int i;
        while(true){
            System.out.println(game.jugadores[0]+"tiene "+game.jugadores[0].baraja.size()+" cartas");
            System.out.println(game.jugadores[1]+"tiene "+game.jugadores[1].baraja.size()+" cartas");
            System.out.println(game.jugadores[2]+"tiene "+game.jugadores[2].baraja.size()+" cartas");
            System.out.println(game.jugadores[3]+"tiene "+game.jugadores[3].baraja.size()+" cartas");
            if(game.side)
                
                // se imprime tambien la carta actual para saber que pudes jugar cuando sea tu turno 
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
            
            //En el caso de que sea un bot el que le toque jugar
            else
                game.tirarCartaBot(game.jugadorActual());
        
        break; 
        }
        case 2: 
               Ayuda.ImpAyuda();
        break;
        
        case 3:
              AcercaD.impAcercaDe();
        break;
        
        case 4:
            System.out.println("gracias por jugar");
        break;
        default:
            System.out.println("opcion no valida");
               
                   
        }
        
        }while (caso != 4);
    } 
}
        
    
  
    
            
            
    

                
           
           
   
     
                    
         
           
     
            
    
        