
package unoflip;
import java.util.* ;

   // clase mazo para crear todas las cartas de nuestro juego 
public class Mazo {
    public Carta[] cartas;
    public int inDeck;

    public Mazo() {
        cartas = new Carta[112];
    }

    public int getInDeck() {
        return inDeck;
    }
    
    
    
    public void reset(){
        
        //Crea todas las caras del lado oscuro
        DarkSide.DarkColor[] dcolors = DarkSide.DarkColor.values();
        int counter = 0;
        DarkSide [] ladoO;
        ladoO = new DarkSide[112];
        
        //usa los indices ya definidos para los colores y los tipos de cartas para crearlas a base de ciclos for
        for(int i =0;i<4; i++){
            DarkSide.DarkColor colorOscuro = dcolors[i];
            for(int j=0; j<13; j++){
                ladoO[counter++] = new DarkSide(colorOscuro, DarkSide.DarkValue.getDvalue(j));
                ladoO[counter++] = new DarkSide(colorOscuro, DarkSide.DarkValue.getDvalue(j));
            }
        }
        
        for(int i=0;i<4;i++){
            ladoO[counter++] = new DarkSide(DarkSide.DarkColor.WHITE, DarkSide.DarkValue.CAMBIOCOLOR);
            ladoO[counter++] = new DarkSide(DarkSide.DarkColor.WHITE, DarkSide.DarkValue.CAMBIODRAW);
        }
        
        //Crea todas las caras del lado claro
        LightSide.LightColor[] lcolors = LightSide.LightColor.values();
        counter = 0;
        LightSide[] ladoC;
        ladoC = new LightSide[112];
        
        //usa los indices ya definidos para los colores y los tipos de cartas para crearlas a base de ciclos for
        for(int i =0;i<lcolors.length-1; i++){
            LightSide.LightColor colorClaro = lcolors[i];
            for(int j=0; j<13; j++){
                ladoC[counter++] = new LightSide(colorClaro, LightSide.LightValue.getLvalue(j));
                ladoC[counter++] = new LightSide(colorClaro, LightSide.LightValue.getLvalue(j));
            }
        }
        
        for(int i=0;i<4;i++){
            ladoC[counter++] = new LightSide(LightSide.LightColor.BLACK, LightSide.LightValue.CAMBIOCOLOR);
            ladoC[counter++] = new LightSide(LightSide.LightColor.BLACK, LightSide.LightValue.CAMBIOMAS2);
        }
        
        
        Random random = new Random();
        
        for(int i=0;i<ladoO.length;i++){
            int randI = random.nextInt(ladoO.length);
            DarkSide aux;
            
            aux = ladoO[i];
            ladoO[i] = ladoO[randI];
            ladoO[randI] = aux;
        }
        
        inDeck=0;
        
        for(int i=0; i<ladoC.length;i++){
            cartas[inDeck++] = new Carta(ladoO[i], ladoC[i]);
        }
        inDeck--;
        
    }
    
    public void shuffle(){
        Random random = new Random();
        int change;
        
        for(int i=0;i<cartas.length;i++){
            change = random.nextInt(cartas.length);
            Carta aux;
            
            aux = cartas[i];
            cartas[i] = cartas[change];
            cartas[change] = aux;
        }
    }
    
    public boolean isEmpty(){
        return inDeck==0;
    }
    
    public void refill(ArrayList<Carta> pila){
        cartas = pila.toArray(Carta[]::new);
    }
}
