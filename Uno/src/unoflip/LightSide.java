
package unoflip;

/*  calse para definir el lado claro de nuestras cartas    */
public class LightSide {
    private final LightColor lightColor;
    private final LightValue lightValue;

     /*  este es el constructor de la clase con valores definidos*/
    public LightSide(LightColor lightColor, LightValue lightValue) {
        this.lightColor = lightColor;
        this.lightValue = lightValue;
    }

    
    
    /* enumerador para los colores del lado claro*/
    public enum LightColor{
        RED, BLUE, YELLOW, GREEN, BLACK;
        
        private static final LightColor[] lightColors = LightColor.values();
        
        /*  metodo get para tomar los colores por un indice */
        public static LightColor getLColor(int i){
            return LightColor.lightColors[i];
        }
        
    }
    
    /*  mismo que el caso anteriro solo que con los numeros y comodines, un enumerador  */
    public enum LightValue{
        UNO, DOS, TRES, CUATRO, CINCO, SEIS, SIETE, OCHO, NUEVE, SKIP, MAS1, REVERSE, FLIP, CAMBIOCOLOR, CAMBIOMAS2;
        
        private static final LightValue[] LightValues = LightValue.values();
        
        /*  metodo get para tomar el valor de la carta por un indice */
        public static LightValue getLvalue(int i){
            return LightValue.LightValues[i];
        }
    }

    /*  metodo get pra el lado claro*/
    public LightColor getLightColor() {
        return lightColor;
    }

    /*  metodo get pra los valores del lado claro*/
    public LightValue getLightValue() {
        return lightValue;
    }
    
    
    /*  sobre escritura por metodo de tostring*/
     @Override
    public String toString(){
        return lightColor + "_" +lightValue;
    }

    
}
