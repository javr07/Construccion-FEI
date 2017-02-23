/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferreteria;

import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author javr
 */
public class Teclado implements Serializable {
    Scanner sc = new Scanner(System.in);
    
    /**
     * Regresa un entero leido desde teclado
     * @return valor de tipo entero
     */
    public int leerEnteros(){
        return sc.nextInt();
    }
    /**
     * Regresa un doble leído desde teclado
     * @return valor de tipo double
     */
    public double leerDoble(){
        return sc.nextDouble();
    }
    /**
     * Regresa un doble leído desde teclado
     * @return valor de tipo double
     */
    public String leerString(){
        return sc.nextLine();
    }
}
