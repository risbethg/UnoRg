
//juego

package unoflip;
import java.util.*;
import javax.swing.*;
import java.awt.Font;

//import de las secciones a utilizar en este caso, son el lado oscuro y claro de las cartas
import unoflip.LightSide.*;
import unoflip.DarkSide.*;

//  clase juego donde se unen nustras partes del codigo 
public class Juego {
    private int turno;
    private int ronda;
    private Mazo deck;
    private ArrayList<Carta> pila;
    public Entity [] jugadores;
    private boolean sentido;
    public boolean side;
    private Carta cartaActual;
    private final int pid;
    
    private LightValue valorClaroActual;
    private LightColor colorClaroActual;
    private DarkValue valorOscuroActual;
    private DarkColor colorOscuroActual;
    
    // en esta cadse almacenan los jugadores cabe recalcar que el juego solo permite 4 jugadores, la persona y 3 bots 
    public Juego(String[] jugadores) {
        this.jugadores = new Entity[4];
        this.jugadores[0] = new Entity(jugadores[0]);
        this.jugadores[1] = new Entity(jugadores[1]);
        this.jugadores[2] = new Entity(jugadores[2]);
        this.jugadores[3] = new Entity(jugadores[3]);
        this.ronda=1;
        this.turnoInicial();
        this.repartirCartas();
        sentido = true; //antihorario
        side=true; //lado claro
        pid = 0;
        this.inicio();
    }
    
    //  metodo get para tomar la carta actual  
    public Carta getCartaActual() {
        return cartaActual;
    }
    
    
    
    // iniciacion de nuestra partida    
    public void inicio(){
        cartaActual = this.retirarCarta();
        this.colorClaroActual = cartaActual.getLadoClaro().getLightColor();
        this.valorClaroActual = cartaActual.getLadoClaro().getLightValue();
        this.colorOscuroActual = cartaActual.getLadoOscuro().getDarkColor();
        this.valorOscuroActual = cartaActual.getLadoOscuro().getDarkValue();
        
        
        
        if(cartaActual.getLadoClaro().getLightColor() == LightSide.LightColor.BLACK){
            inicio();
        }
        
        if(cartaActual.getLadoClaro().getLightValue() == LightSide.LightValue.CAMBIOMAS2 || cartaActual.getLadoClaro().getLightValue() == LightSide.LightValue.MAS1){
            inicio();
        }
        
        this.turnoInicial();
        
        if(cartaActual.getLadoClaro().getLightValue() == LightSide.LightValue.SKIP){
            JLabel mensaje = new JLabel(this.jugadores[turno].name + "Fue Saltado!");
            mensaje.setFont(new Font("Arial", Font.BOLD, 48));
            JOptionPane.showMessageDialog(null, mensaje);
            
            this.skip();
        }
        
        if(cartaActual.getLadoClaro().getLightValue() == LightSide.LightValue.REVERSE){
            JLabel mensaje = new JLabel("El Sentido Ha Cambiado!");
            mensaje.setFont(new Font("Arial", Font.BOLD, 48));
            JOptionPane.showMessageDialog(null, mensaje);
            
            this.sentido = false;
        }
        
        pila = new ArrayList<>();
        
        pila.add(cartaActual);
        
    }
    
    // si un jugador no tiene para jugar y le toca robar una carta de la mesa 
    public void robarPlayer() throws InvalidTurnException{
        jugadores[0].revisarTurnoDelJugador(turno);
        if(deck.isEmpty()){
            deck.refill(pila);
            deck.shuffle();
        }
        jugadores[0].baraja.add(this.retirarCarta());
        this.skip();
        
    }
    
    // cada que se tiene una carta, se elimine una de su mazo y el cambio de jugador
    public void tirarCarta(Entity player, Carta carta, LightSide.LightColor colorDeclarado)
            throws InvalidTurnException, InvalidColorException, InvalidValueException{
        player.revisarTurnoDelJugador(turno);
        ArrayList<Carta> pCards = player.getBaraja();
        
        if(!this.validLightCardPlay(carta)&&!(carta.getLadoClaro().getLightColor() == LightSide.LightColor.BLACK)){
            if(carta.getLadoClaro().getLightColor() != this.colorClaroActual){
                JLabel mensaje1 = new JLabel("jugada invalida, color no coincide");
                mensaje1.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, mensaje1);
                throw new InvalidColorException("jugada invalida, color no coincide", carta.getLadoClaro().getLightColor(), this.colorClaroActual);
            }
            if(!this.validLightCardPlay(carta)&&!(carta.getLadoClaro().getLightValue() == LightSide.LightValue.CAMBIOCOLOR)){
                JLabel mensaje1 = new JLabel("jugada invalida, valor no coincide");
                mensaje1.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, mensaje1);
                throw new InvalidValueException("jugada invalida, valor no coincide", carta.getLadoClaro().getLightValue(), this.valorClaroActual);
            }
            
            
        }
        
        player.baraja.remove(carta);
        
        if(player.withoutCards()){
            JLabel mensaje2 = new JLabel("Has Ganado!");
            mensaje2.setFont(new Font("Arial", Font.BOLD, 48));
            JOptionPane.showMessageDialog(null, mensaje2);
            System.exit(0);
        }
        
        this.colorClaroActual = carta.getLadoClaro().getLightColor();
        this.valorClaroActual = carta.getLadoClaro().getLightValue();
        this.cartaActual = carta;
        
        if(carta.getLadoClaro().getLightValue() == LightSide.LightValue.REVERSE){
            this.sentido^=true;
            System.out.println("cambio el sentido");
        }
        
        this.cambiaTurno();
        
        if(carta.getLadoClaro().getLightValue() == LightSide.LightValue.MAS1){
            this.jugadorActual().baraja.add(this.retirarCarta());
            this.skip();
            System.out.println("mas1");
        }
        
        if(carta.getLadoClaro().getLightValue() == LightSide.LightValue.SKIP){
            this.skip();
            System.out.println("skip");
        }
        
        if(carta.getLadoClaro().getLightValue() == LightSide.LightValue.CAMBIOMAS2){
            this.colorClaroActual = colorDeclarado;
            this.jugadorActual().baraja.add(this.retirarCarta());
            this.jugadorActual().baraja.add(this.retirarCarta());
            this.skip();
            System.out.println("cambio el color mas 2");
        }
        
        if(carta.getLadoClaro().getLightValue() == LightSide.LightValue.CAMBIOCOLOR){
            this.colorClaroActual = colorDeclarado;
            System.out.println("cambio el color a " +colorDeclarado);
        }
        
        if(carta.getLadoClaro().getLightValue() == LightSide.LightValue.FLIP){
            this.side ^= true;
            System.out.println("flip");
        }
    }
    
    
    public void tirarCarta(Entity player, Carta carta, DarkSide.DarkColor colorDeclarado)
            throws InvalidTurnException, InvalidColorException, InvalidValueException{
        player.revisarTurnoDelJugador(turno);
        
        if(!this.validDarkCardPlay(carta)&&!(carta.getLadoOscuro().getDarkColor() == DarkSide.DarkColor.WHITE)){
            if(carta.getLadoOscuro().getDarkColor() != this.colorOscuroActual){
                JLabel mensaje1 = new JLabel("jugada invalida, color no coincide");
                mensaje1.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, mensaje1);
                throw new InvalidColorException("jugada invalida, color no coincide", carta.getLadoOscuro().getDarkColor(), this.colorOscuroActual);
            }
            else{
                JLabel mensaje1 = new JLabel("jugada invalida, valor no coincide");
                mensaje1.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, mensaje1);
                throw new InvalidValueException("jugada invalida, valor no coincide", carta.getLadoOscuro().getDarkValue(), this.valorOscuroActual);
            }
            
            
        }
        
        player.baraja.remove(carta);
        
        if(player.withoutCards()){
            JLabel mensaje2 = new JLabel("Has Ganado!");
            mensaje2.setFont(new Font("Arial", Font.BOLD, 48));
            JOptionPane.showMessageDialog(null, mensaje2);
            System.exit(0);
        }
        
        this.colorOscuroActual = carta.getLadoOscuro().getDarkColor();
        this.valorOscuroActual = carta.getLadoOscuro().getDarkValue();
        this.cartaActual = carta;
        
        if(carta.getLadoOscuro().getDarkValue() == DarkSide.DarkValue.REVERSE){
            this.sentido^=true;
        }
        
        this.cambiaTurno();
        
        if(carta.getLadoOscuro().getDarkValue() == DarkSide.DarkValue.EVERYSKIP){
            while(this.jugadorActual()!= player){
            this.skip();
            }
        }
        
        
        if(carta.getLadoOscuro().getDarkValue() == DarkSide.DarkValue.MAS5){
            this.jugadorActual().baraja.add(this.retirarCarta());
            this.jugadorActual().baraja.add(this.retirarCarta());
            this.jugadorActual().baraja.add(this.retirarCarta());
            this.jugadorActual().baraja.add(this.retirarCarta());
            this.jugadorActual().baraja.add(this.retirarCarta());
            this.skip();
        }
        
        
        if(carta.getLadoOscuro().getDarkValue() == DarkSide.DarkValue.CAMBIODRAW){
            this.colorOscuroActual = colorDeclarado;
            do{
                this.jugadorActual().baraja.add(this.retirarCarta());
            }while(this.verCarta().getLadoOscuro().getDarkColor() == colorOscuroActual);
            
            this.skip();
        }
        
        if(carta.getLadoClaro().getLightValue() == LightSide.LightValue.CAMBIOCOLOR){
            this.colorOscuroActual = colorDeclarado;
        }
        
        if(carta.getLadoOscuro().getDarkValue() == DarkSide.DarkValue.FLIP){
            this.side ^= true;
        }
    }
    
    //las funciones que cumple el bot para arojar las cartas
    public void tirarCartaBot(Entity bot){
        ArrayList<Carta>temp = bot.getBaraja();
        boolean found = false;
        Carta carta=null;
        if(this.side){
            for(Carta c : temp){
                if((c.getLadoClaro().getLightColor() == this.colorClaroActual || c.getLadoClaro().getLightValue() == this.valorClaroActual)&& c!=null){
                    carta = c;
                    found = true;
                    break;
                }
                
            }
            
            if(found){
                this.colorClaroActual = carta.getLadoClaro().getLightColor();
                this.valorClaroActual = carta.getLadoClaro().getLightValue();
                this.cartaActual = carta;
                
                bot.baraja.remove(carta);
                
                if(bot.baraja.isEmpty()){
                    JLabel mensaje2 = new JLabel(bot.name+ " Ha Ganado!");
                    mensaje2.setFont(new Font("Arial", Font.BOLD, 48));
                    JOptionPane.showMessageDialog(null, mensaje2);
                    System.exit(0);
                }
                
                if(carta.getLadoClaro().getLightValue() == LightSide.LightValue.REVERSE){
                    this.sentido^=true;
                    System.out.println("reversa");
                }
        
                this.cambiaTurno();
        
                if(carta.getLadoClaro().getLightValue() == LightSide.LightValue.MAS1){
                    this.jugadorActual().baraja.add(this.retirarCarta());
                    this.skip();
                    System.out.println("mas 1");
                }
        
                if(carta.getLadoClaro().getLightValue() == LightSide.LightValue.SKIP){
                this.skip();
                    System.out.println("skip");
                }
        
                if(carta.getLadoClaro().getLightValue() == LightSide.LightValue.CAMBIOMAS2){
                    Random r = new Random();
                    this.colorClaroActual = LightSide.LightColor.getLColor(r.nextInt(0, 4));
                    this.jugadorActual().baraja.add(this.retirarCarta());
                    this.jugadorActual().baraja.add(this.retirarCarta());
                    this.skip();
                    System.out.println("cambio mas 2");
                }
        
                if(carta.getLadoClaro().getLightValue() == LightSide.LightValue.CAMBIOCOLOR){
                    Random r = new Random();
                    this.colorClaroActual = LightSide.LightColor.getLColor(r.nextInt(0, 4));
                    System.out.println("cambio color a "+colorClaroActual);
                }
        
                if(carta.getLadoClaro().getLightValue() == LightSide.LightValue.FLIP){
                    this.side ^= true;
                    System.out.println("flip");
                }
            }
            else
                bot.baraja.add(this.retirarCarta());
        }
        else{
            for(Carta c : temp){
                if((c.getLadoOscuro().getDarkColor() == this.colorOscuroActual || c.getLadoOscuro().getDarkValue() == this.valorOscuroActual)&& c!=null){
                    carta = c;
                    found = true;
                    break;
                }
                
            }
            
            if(found){
                this.colorOscuroActual = carta.getLadoOscuro().getDarkColor();
                this.valorOscuroActual = carta.getLadoOscuro().getDarkValue();
                this.cartaActual = carta;
                
                bot.baraja.remove(carta);
                
                if(carta.getLadoOscuro().getDarkValue() == DarkSide.DarkValue.REVERSE){
                    this.sentido^=true;
                    System.out.println("reversa");
                }
        
                this.cambiaTurno();
        
                if(carta.getLadoOscuro().getDarkValue() == DarkSide.DarkValue.EVERYSKIP){
                    while(this.jugadorActual()!= bot){
                    this.skip();
                     
                    }
                    System.out.println("everyskip");
                }
        
        
                if(carta.getLadoOscuro().getDarkValue() == DarkSide.DarkValue.MAS5){
                this.jugadorActual().baraja.add(this.retirarCarta());
                this.jugadorActual().baraja.add(this.retirarCarta());
                this.jugadorActual().baraja.add(this.retirarCarta());
                this.jugadorActual().baraja.add(this.retirarCarta());
                this.jugadorActual().baraja.add(this.retirarCarta());
                this.skip();
                    System.out.println("mas 5");
                }
        
        
                if(carta.getLadoOscuro().getDarkValue() == DarkSide.DarkValue.CAMBIODRAW){
                    Random r = new Random();
                    this.colorOscuroActual = DarkSide.DarkColor.getDColor(r.nextInt(0, 4));
                    do{
                        this.jugadorActual().baraja.add(this.retirarCarta());
                    }while(this.verCarta().getLadoOscuro().getDarkColor() == colorOscuroActual);

                    this.skip();
                    System.out.println("cambio draw");
                }

                if(carta.getLadoOscuro().getDarkValue() ==DarkSide.DarkValue.CAMBIOCOLOR){
                    Random r = new Random();
                    this.colorOscuroActual = DarkSide.DarkColor.getDColor(r.nextInt(0, 4));
                    System.out.println("cambio color a "+ colorOscuroActual);
                }

                if(carta.getLadoOscuro().getDarkValue() == DarkSide.DarkValue.FLIP){
                    this.side ^= true;
                    System.out.println("flip");
                }
            }
            else
                bot.baraja.add(this.retirarCarta());
        }
    }
    
      
    //valor booleano para validar la jugado con el lado claro de la carta
    public boolean validLightCardPlay(Carta carta){
        return (carta.getLadoClaro().getLightColor() == this.colorClaroActual || carta.getLadoClaro().getLightValue() == this.valorClaroActual);
    }
    
    //valor booleano para validar la jugado con el lado oscuro de la carta
    public boolean validDarkCardPlay(Carta carta){
        return (carta.getLadoOscuro().getDarkColor() == this.colorOscuroActual || carta.getLadoOscuro().getDarkValue() == this.valorOscuroActual);
    }
    
    
    public void turnoInicial(){
        Random r = new Random();
        this.turno = r.nextInt(0, jugadores.length);
    }
    
    // funcion que se usa al principio de las partidas para darle las 7 cartas a cada jugador
    public void repartirCartas(){
        this.deck = new Mazo();
        this.deck.reset();
        this.deck.shuffle();
        
        for(Entity e: jugadores){
            for(int i=0; i<7; i++){
                e.robarCarta(this.retirarCarta());
            }
        }
        
    
    }
    
    // metodo get para la ronda
    public int getRonda(){
        return this.ronda;
    }
    
    //entity para el jugador que se encuentra jugando ahora mismo  
    public Entity jugadorActual(){
        return this.jugadores[this.turno];
    }
    
    // numero de cartas en posicion del jugar que esta jugando 
    public int nroCartasJugadorActual(){
        return this.jugadorActual().nroCartas();
    }
    
    // metodo que se usa para cada que se usa la carta que cambia el sentido de la partida  
    public void cambiaTurno(){
        if(this.sentido && this.turno == this.jugadores.length-1){
            turno = 0;
        } else if(!this.sentido && turno == 0){
            this.turno = this.jugadores.length-1;
        }
        else{
            if(this.sentido){
                turno++;
            }
            else{
                turno--;
            }
        }
    }
    
    // ganador de la partida 
    public Entity ganador(){
        Entity j = null;
        for(int i =0; i<this.jugadores.length;i++){
            if(this.jugadores[i].withoutCards()){
                j= this.jugadores[i];
            }
        }
        return j;
    }
    //valor boleano para ser retornado en el caso de que termine nuestra partida
    public boolean finPartida(){
        return this.ganador() != null;
    }
    
    // el skip es para cuando alguien usa una carta y le hace saltar el turno al siguiente jugador 
    public void skip(){
        this.cambiaTurno();
    }
    
    //metodo para paso de turno, y asi saltar al siguiente participante   
    public Entity siguienteJugador(){
        if(this.sentido){
            if(this.turno == this.jugadores.length){
                return this.jugadores[0];
            }
            else{
                return this.jugadores[this.turno+1];
            }
        }
        else{
            if(this.turno == 0){
                return this.jugadores[this.jugadores.length-1];
            }
            else{
                return this.jugadores[this.turno-1];
            }
        }
    }
    
    //metodo para retirar una de las cartas de nuestro mazo  
    public Carta retirarCarta(){
        return deck.cartas[deck.inDeck--];
    }
    /*  metodo para mostras las cartas  */
    public Carta verCarta(){
        return deck.cartas[deck.inDeck];
    }
    
    // metodo set para el color claro actual  
    public void setColorClaroActual(LightColor colorClaroActual) {
        this.colorClaroActual = colorClaroActual;
    }
    
    // metodo set para el color oscuro actual   
    public void setColorOscuroActual(DarkColor colorOscuroActual) {
        this.colorOscuroActual = colorOscuroActual;
    }
    
    
    
}
        
    


