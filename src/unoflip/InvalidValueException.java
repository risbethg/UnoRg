/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unoflip;

// excepcion en los casos que el valor no sea el valido   
public class InvalidValueException extends Exception{
    private LightSide.LightValue esperadoL;
    private LightSide.LightValue actualL;
        
    private DarkSide.DarkValue esperadoD;
    private DarkSide.DarkValue actualD;
        
    
     // constructor para la excepcion en el caso de ser el lado claro de la carta    
    public InvalidValueException(String mensaje, LightSide.LightValue esperado, LightSide.LightValue actual ){
        this.actualL = actual;
        this.esperadoL = esperado;
    }
     
     // constructor para la excepcion en el caso de ser el lado oscuro de la carta    
    public InvalidValueException(String mensaje, DarkSide.DarkValue esperado, DarkSide.DarkValue actual ){
        this.actualD = actual;
        this.esperadoD = esperado;
    }
}
