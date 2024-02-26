/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unoflip;

/*  clase para las exepciones de color de las cartar*/
public class InvalidColorException extends Exception {
    private LightSide.LightColor esperadoL;
    private LightSide.LightColor actualL;
        
    private DarkSide.DarkColor esperadoD;
    private DarkSide.DarkColor actualD;
        
       /*   constru para la execcion en el caso de ser el lado claro de la carta    */
    public InvalidColorException(String mensaje, LightSide.LightColor esperado, LightSide.LightColor actual ){
        this.actualL = actual;
        this.esperadoL = esperado;
    }
        
     /*   constru para la execcion en el caso de ser el lado oscuro de la carta    */
    public InvalidColorException(String mensaje, DarkSide.DarkColor esperado, DarkSide.DarkColor actual ){
        this.actualD = actual;
        this.esperadoD = esperado;
    }
}
