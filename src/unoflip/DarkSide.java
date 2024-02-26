
package unoflip;

// clase para el lado oscuro de las cartas
public class DarkSide {
    //atributos
    private final DarkColor darkColor;
    private final DarkValue darkValue;

    // constructor 
    public DarkSide(DarkColor darkColor, DarkValue darkValue) {
        this.darkColor = darkColor;
        this.darkValue = darkValue;
    }
    
    //metodo enumerador para los colores del lado oscuro
    public enum DarkColor{
        TURQUOISE, GOLD, PINK, PURPLE, WHITE;
        
        private static final DarkColor[] darkColors = DarkColor.values();
        
        // metodo get para tomar los colores por un indice 
        public static DarkColor getDColor(int i){
            return DarkColor.darkColors[i];
        }
        
    }
    
    // metodo enumerador para los numeros y comodines 
    public enum DarkValue{
        UNO, DOS, TRES, CUATRO, CINCO, SEIS, SIETE, OCHO, NUEVE, EVERYSKIP, MAS5, REVERSE, FLIP, CAMBIOCOLOR, CAMBIODRAW;
        
        private static final DarkValue[] darkValues = DarkValue.values();
        
        // metodo get para tomar el valor de la carta por un indice 
        public static DarkValue getDvalue(int i){
            return DarkValue.darkValues[i];
        }
    }
    
    // metodo get para el lado oscuro
    public DarkColor getDarkColor() {
        return darkColor;
    }
   //metodo get para los valores del lado oscuro
    public DarkValue getDarkValue() {
        return darkValue;
    }
    
    // otros metodos
    @Override
    public String toString(){
        return darkColor + "_"+ darkValue;
    }
    
}
