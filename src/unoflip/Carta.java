
package unoflip;

/*  clase carta donde se maneja el lado oscuro y claro de las cartas    */
public class Carta {
    //atributos
    private final DarkSide ladoOscuro;
    private final LightSide ladoClaro;

    // constructor  
    public Carta(DarkSide ladoOscuro, LightSide ladoClaro) {
        this.ladoOscuro = ladoOscuro;
        this.ladoClaro = ladoClaro;
    }

    // metodo get para el lado oscuro de las cartas
    public DarkSide getLadoOscuro() {
        return ladoOscuro;
    }
    //  metodo get para el lado claro de las cartas
    public LightSide getLadoClaro() {
        return ladoClaro;
    }

    
    // otros metodos
    @Override
    public String toString(){
        return ladoClaro.toString() +" " +ladoOscuro.toString();
    }
    
}
