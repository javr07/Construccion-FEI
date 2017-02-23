
package ferreteria;

import java.io.Serializable;

/**
 *La siguiente clase crea la estructura del objeto Producto que se utiliza en el
 * programa, se incluye constructor y geteres y seteres.
 * @author javr
 */

public class Producto implements Serializable {
    private int clave;
    private String nombre;
    private String descripcion;
    private double precioCompra;
    private int existencia; //Tal vez innecesario
    private String tipoUnidad;
    private double precioVenta;
    

    public Producto(){ };
    
    public Producto(int clave, String nombre, String descripcion, double precioCompra,
        String tipoValor, int existencia, double precioVenta) {
        this.clave = clave;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.tipoUnidad = tipoValor;
        this.existencia = existencia;
        this.precioVenta = precioVenta;
    }

    

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }
    

    
    public int getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public int getExistencia() {
        return existencia;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }
    public double getPrecioVenta() {
        return precioVenta;
    }
    
}
