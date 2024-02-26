
package unoflip;
import java.util.*;

/*  clase entity para casos especificos que pasen con nuestra baraja    */
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
    
    
    /*  FUNCION para el caso que nos toque robar una carta del maso */
    public void robarCarta(Carta carta){
        baraja.add(carta);
    }
    
    /*  valor entero que nos muestra el tabaño de nuestra baraja, en este caso las cartas en posecion */
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
       
    /*  FUNCION void para validar el turno del jugador y arojar una execpion en caso de no ser valido   */
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
