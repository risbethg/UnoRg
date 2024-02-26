/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unoflip;

/*  unas exepcion en el caso de que no sea tu turno */
public class InvalidTurnException extends Exception {
    public InvalidTurnException(String a){
        super(a + " pibe no es tu turno");
    }
}
