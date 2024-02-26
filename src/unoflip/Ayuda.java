/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unoflip;
import java.util.Scanner;

public class Ayuda {
    
    public static void ImpAyuda(){
        
        Scanner pause = new Scanner (System.in);
        System.out.println("--AYUDA--");
        System.out.println(" como jugar y reglas del juego");
        System.out.println("Como en el Uno original, el objetivo de Uno Flip! es ser el primero en jugar todas las cartas de la mano, sumando puntos por las cartas que todavía tienen los demás.");
        System.out.println("Todas las tarjetas tienen dos caras, el lado \"claro\"  y el lado \"oscuro\" Solo un lado está en juego en un momento dado, comenzando con el lado claro al comienzo de cada nueva mano.");
        System.out.println("Cada lado tiene su propio juego de cuatro colores, cartas de acción y comodines, y cartas de números del 1 al 9.");
        System.out.println("Cada vez que se juega un Flip, tanto la pila de reserva como la pila de descarte se giran inmediatamente y todos los jugadores deben girar sus manos para jugar el otro lado de sus cartas.");
        System.out.println("--REGLAS--");
        System.out.println("El juego uno flip se juega igual al uno normal, pero ahora las cartas cuentan con dos caras");
        System.out.println(" hay una carta específica llamada FlIP, exiten 4 cartas de estás, 2 de cada lado, se usa para cambiar el lado a jugar.");
        System.out.println("Hay que tener en cuenta que todas las cartas deben de estar apuntando al lado a jugar. ");
        System.out.println("El lado oscuro: tiene color turquesa, dorado, rosado, púrpura y blanco, tiene cartas del 1 al 9, un +5 que hace cargar 5 cartas, un saltar todos, que hace que ninguno pueda jugar una ronda, reversa, cambio de color, flip.");
        System.out.println("El lado claro: tiene color rojo, azul, amarillo, verde y negro, tiene cartas del 1 al 9, saltar, que salta el turno de un jugador, reversa, +2 para cargarse dos cartas, cambio de color, flip, carta +1 y carta +2");
        System.out.println("Cuando sea tu turno tienes que hacer coincidir una de las cartas de tu mano con la carta que está en la mesa, ya sea en número o color");
        System.out.println("Si no tienes ninguna que coincida te tienes que cargar");
       
    }
           
             
}
