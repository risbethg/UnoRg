
package unoflip;

/*  calse para definir el lado oscuro de nuestras cartas    */
public class DarkSide {
    
    private final DarkColor darkColor;
    private final DarkValue darkValue;

    /*  este es el constructor de la clase con valores definidos*/
    public DarkSide(DarkColor darkColor, DarkValue darkValue) {
        this.darkColor = darkColor;
        this.darkValue = darkValue;
    }
    
    /* enumerador para los colores del lado oscuro*/
    public enum DarkColor{
        TURQUOISE, GOLD, PINK, PURPLE, WHITE;
        
        private static final DarkColor[] darkColors = DarkColor.values();
        
        /*  metodo get para tomar los colores por un indice */
        public static DarkColor getDColor(int i){
            return DarkColor.darkColors[i];
        }
        
    }
    
    /*  mismo que el caso anteriro solo que con los numeros y comodines, un enumerador  */
    public enum DarkValue{
        UNO, DOS, TRES, CUATRO, CINCO, SEIS, SIETE, OCHO, NUEVE, EVERYSKIP, MAS5, REVERSE, FLIP, CAMBIOCOLOR, CAMBIODRAW;
        
        private static final DarkValue[] darkValues = DarkValue.values();
        
        /*  metodo get para tomar el valor de la carta por un indice */
        public static DarkValue getDvalue(int i){
            return DarkValue.darkValues[i];
        }
    }
    
    /*  metodo get pra el lado oscuro*/
    public DarkColor getDarkColor() {
        return darkColor;
    }
/*  metodo get pra los valores del lado oscuro*/
    public DarkValue getDarkValue() {
        return darkValue;
    }
    
    /*  sobre escritura por metodo de tostring*/
    @Override
    public String toString(){
        return darkColor + "_"+ darkValue;
    }
    
}
