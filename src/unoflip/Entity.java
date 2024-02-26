
package unoflip;
import java.util.*;

// clase entidad, jugar o la propia consola
public class Entity {
   
    public String name;
    protected ArrayList<Carta> baraja;

    public Entity(String name) {
        this.name = name;
        this.baraja = new ArrayList<>();
    }

    public ArrayList<Carta> getBaraja() {
        return baraja;
    }
    
    
    //metodo para robar carta del mazo 
    public void robarCarta(Carta carta){
        baraja.add(carta);
    }
    
    //valor entero que nos muestra el tamaño de nuestra baraja 
    public int nroCartas(){
        return baraja.size();
    }
    
    public Carta getCartaIn(int pos){
        return baraja.get(pos);
    }
    
    public void removeCartaIn(int pos){
        baraja.remove(pos);
    }
    
    public boolean withoutCards(){
        return baraja.isEmpty();
    }
       
   //metodo para validar el turno del jugador y arrojar una excepcion en caso de no ser valido
    public void revisarTurnoDelJugador(int turno) throws InvalidTurnException{
        if(turno != 0){
            throw new InvalidTurnException(this.name);
        }
    }
    
    public boolean validCardPlay(Carta carta, LightSide.LightColor colorClaroActual, LightSide.LightValue valorClaroActual){
        return (carta.getLadoClaro().getLightColor() == colorClaroActual || carta.getLadoClaro().getLightValue() == valorClaroActual);
    }
    
    public boolean validCardPlay(Carta carta, DarkSide.DarkColor colorOscuroActual, DarkSide.DarkValue valorOscuroActual){
        return (carta.getLadoOscuro().getDarkColor() == colorOscuroActual || carta.getLadoOscuro().getDarkValue() == valorOscuroActual);
    }
    
    
}
