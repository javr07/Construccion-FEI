
package ferreteria;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *Clase Archivo, se encarga de guardar y leer los datos
 * @author javr
 */
public class Archivo {
    
    File archivo;
    
    /*public Archivo (String nombre) {
    archivo = new File(nombre);
    }*/
    public Archivo(){}

    /**
     * Método para guardar el ArrayList de productos que se tiene en el inventario,
     * se recibe el Arraylist cada vez que tiene cambios y se sobreescribe.
     * @param productoArr 
     */
    public void guardar (ArrayList<Producto> productoArr) {
        try{
            
            FileOutputStream fos = new FileOutputStream("Productos.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(productoArr);
            oos.close();
            
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    /**
     * Método para guardar el ArrayList de ventas que se realizaron,
     * se recibe el Arraylist cada vez que tiene cambios y se sobreescribe.
     * @param ventasArr 
     */
    public void guardarVentas (ArrayList<Venta> ventasArr) {
        try{
            
            FileOutputStream fos = new FileOutputStream("Ventas.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ventasArr);
            oos.close();
            
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    /**
     * Método para recuperar el ArrayList de productos que se han guardado, regresa
     * el ArrayList almacenado. Método corregido por @ALAN
     * @return 
     */
    public static ArrayList<Producto> entrada() {
    ArrayList<Producto> temp = null;

    try {
      FileInputStream file = new FileInputStream("Productos.txt");
      ObjectInputStream in = new ObjectInputStream(file);
      temp = (ArrayList<Producto>) in.readObject();
      in.close();
        
    } catch (FileNotFoundException ex) {
      System.out.println("Archivo no encontrado");
    } catch (IOException ex) {
      System.out.println("Creando el archivo");
    } catch (ClassNotFoundException z) {
      System.out.println("Error de clase");
    }

    return temp;
    }
    /**
     * Método para recuperar el ArrayList de productos que se han guardado, regresa
     * el ArrayList almacenado. Método corregido por @ALAN
     * @return 
     */
    public static ArrayList<Venta> entradaVentas() {
       ArrayList<Venta> temp = null;

      try {
        FileInputStream file = new FileInputStream("Ventas.txt");
        ObjectInputStream in = new ObjectInputStream(file);
        temp = (ArrayList<Venta>) in.readObject();
        in.close();
      } catch (FileNotFoundException ex) {
        System.out.println("Archivo no encontrado");
      } catch (IOException ex) {
        System.out.println("Creando el archivo");
      } catch (ClassNotFoundException z) {
        System.out.println("Error de clase");
      }

      return temp;
    }
}
