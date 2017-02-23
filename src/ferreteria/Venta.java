
package ferreteria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;


/**
 *Clase para generar Objetos de Venta, incluye un ArrayList de productos, 
 * geteres y seteres.
 * @author javr
 */
public class Venta implements Serializable {
    private int claveVenta = 0;
    private double totalVenta = 0;
    Calendar fecha = Calendar.getInstance();
    ArrayList<Producto> productoArrayV = new ArrayList<>(); //POSIBLE añadir static
    int dayW = fecha.get(Calendar.DAY_OF_WEEK);
    int dayM = fecha.get(Calendar.DAY_OF_MONTH);
    int month = fecha.get(Calendar.MONTH);
    int year = fecha.get(Calendar.YEAR);
    
    

    public void setClaveVenta(int claveVenta) {
        this.claveVenta = claveVenta;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }


    public void setProductoArrayV(ArrayList<Producto> productoArrayV) {
        this.productoArrayV = productoArrayV;
    }


    
    public int getClaveVenta() {
        return claveVenta;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public int getYear() {
        return year;
    }

    public ArrayList<Producto> getProductoArrayV() {
        return productoArrayV;
    }
    /**
     * Función para día del mes
     * @return Regresa el número del día
     */
    public int getDayM() {
        return dayM;
    }
    /**
     * Función para sustituir el número de la semana por un String en español.
     * @return Regresa el nombre del día 
     */
    public String getDayWString(){
        switch (this.dayW) {
            case 1:
                return "Domingo";
            case 2:
                return "Lunes";    
            case 3:
                return "Martes";
            case 4:
                return "Miércoles";    
            case 5:
                return "Jueves";
            case 6:
                return "Viernes";    
            case 7:
                return "Sábado";     
        }
        return "?";
    }
    /**
     * Función para el número del mes
     * @return Regresa el número del mes
     */
    public int getMonthInt(){
        return month;
    }
    /**
     * Función para sustituir el número de la semana por un String en español. 
     * @return Regresa el nombre del mes
     */
    public String getMonthString(){
        switch (this.month) {
            case 0:
                return "Enero";
            case 1:
                return "Febrero";    
            case 2:
                return "Marzo";
            case 3:
                return "Abril";    
            case 4:
                return "Mayo";
            case 5:
                return "Junio";    
            case 6:
                return "Julio"; 
            case 7:
                return "Agosto";
            case 8:
                return "Septiembre";    
            case 9:
                return "Octubre";    
            case 10:
                return "Noviembre";
            case 11:
                return "Diciembre";             
        }
        return "?";
    }
}
