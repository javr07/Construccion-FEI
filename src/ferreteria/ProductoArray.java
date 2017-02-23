/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferreteria;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


/**
 *La siguiente clase genera el ArrayList de productos que se tienen en la Ferretería
 * también se tienen muchos métodos que son necesarios para la administración.
 * @author javr
 */
public class ProductoArray implements Serializable{
    private static ArrayList<Producto> productoArray = new ArrayList<>();
    
    Iterator<Producto> iteratorProducto = productoArray.iterator();
    
    
    Archivo archP = new Archivo();
    
    Teclado teclado = new Teclado();
    Producto prodc = new Producto();
    //int clave = 0;
    int claveEditar;
    int existenciaEditar;
    
    
    /**
     * El siguiente método permite guardar un producto por primera vez, finaliza 
     * añadiendo el objeto al ArrayList y guardando el ArrayList en el archivo.
     */
    public void agregarProducto(){
        Producto nuevoP = new Producto();
        double precioVenta = 0;
        int existencia = 1;
        System.out.println("Escribe el nombre del producto");
        nuevoP.setNombre(teclado.leerString()); 
        System.out.println("Escribe la descripción del producto");
        nuevoP.setDescripcion(teclado.leerString());
        System.out.println("Escribe el precio de compra del producto");
        nuevoP.setPrecioCompra(teclado.leerDoble());
        teclado.leerString();
        System.out.println("Escribe el tipo de unidad del producto (kg, L, mL, m, pieza)");
        nuevoP.setTipoUnidad(teclado.leerString());
        
        int clave = asignarClave();
        nuevoP.setClave(clave);
        precioVenta = (nuevoP.getPrecioCompra()*1.50);
        nuevoP.setPrecioVenta(precioVenta);
        nuevoP.setExistencia(existencia);
        productoArray.add(nuevoP);
        archP.guardar(productoArray);
        
    }
    /**
     * El sigueinte método permite guardar el producto en el ArrayList después de
     * alguna modificación. Termina añadiendo el producto y guardando el ArrayList.
     */
    public void reagregarProducto(){
        Producto nuevoP = new Producto();
        double precioVenta = 0;
        System.out.println("Escribe el nombre del producto");
        nuevoP.setNombre(teclado.leerString()); 
        System.out.println("Escribe la descripción del producto");
        nuevoP.setDescripcion(teclado.leerString());
        System.out.println("Escribe el precio de compra del producto");
        nuevoP.setPrecioCompra(teclado.leerDoble());
        teclado.leerString();
        System.out.println("Escribe el tipo de unidad del producto (kg, L, mL, m, pieza)");
        nuevoP.setTipoUnidad(teclado.leerString());
        nuevoP.setClave(claveEditar);
        nuevoP.setExistencia(existenciaEditar);
        precioVenta = (nuevoP.getPrecioCompra()*1.50);
        nuevoP.setPrecioVenta(precioVenta);
        productoArray.add(nuevoP);
        archP.guardar(productoArray);
    }
    /**
     * El siguiente método función permite añadir número de elementos de un producto.
     * @param posicion Del Producto a aumentar la cantidad
     * @param nuevaCant Cantidad a aumentar
     */
    public void aumentarProducto(int posicion, int nuevaCant){
        Producto nuevoP = new Producto();
        nuevoP = productoArray.get(posicion);
        int viejaCant = nuevoP.getExistencia();
        nuevoP.setExistencia(viejaCant + nuevaCant);
        
    }
    /**
     * El siguiente método permite eliminar un producto del ArrayList y termina
     * guardando los cambios.
     * @param posicion Del Producto en el ArrayList
     */
    public void eliminarProducto(int posicion){
        productoArray.remove(posicion);
        archP.guardar(productoArray);
    }
    /**
     * El siguiente método permite editar un producto ya registrado del ArrayList
     * @param posicion Del Producto en el ArrayList
     */
    public void editarProducto(int posicion){
       eliminarProducto(posicion);
       reagregarProducto();
    }
    /**
     * El siguiente método ordena los productos del ArrayList por orden alfabético
     */
    public void ordenarNombre(){
        Producto nuevoP = new Producto();
        iteratorProducto = productoArray.iterator();
        ArrayList<String> nombresArray = new ArrayList<String>();
        for (int i = 0; i < productoArray.size(); i++) {
            nuevoP = iteratorProducto.next();
            nombresArray.add(nuevoP.getNombre());   
        }
        Collections.sort(nombresArray);
        for(String nombre : nombresArray){
            buscarNombre(nombre);
        }
    }
    
    /**
     * El siguiente método muestra los detalles del Producto almacenado en el ArrayList
     * @param nuevoP Recibe el Producto
     */
    public void mostrarProducto(Producto nuevoP){
        System.out.println("\tDetalles del producto "+nuevoP.getClave());
        System.out.println("Nombre: "+ nuevoP.getNombre());
        System.out.println("Descripción: "+ nuevoP.getDescripcion());
        System.out.println("Precio: "+ nuevoP.getPrecioCompra());
        System.out.println("Cantidad: "+ nuevoP.getExistencia());
        
    }
    /**
     * El siguiente método muestra los detalles del producto almacenado en el ArrayList.
     * Se utiliza para la clase venta porque no describe todo el producto.
     */
    public void mostrarProductosPVenta(){
        Producto nuevoP = new Producto();
        iteratorProducto = productoArray.iterator();
        for (int i = 0; i < productoArray.size(); i++) {
            nuevoP = iteratorProducto.next();
            System.out.println("\t\033[34mCódigo del producto: "+nuevoP.getClave());
            System.out.println("Nombre: "+ nuevoP.getNombre());
            System.out.println("Descripción: "+ nuevoP.getDescripcion());
            System.out.println("Precio: "+ nuevoP.getPrecioCompra());
            System.out.println("-------------------");
        }   
    }
    /**
     * Realiza una suma de los activos de productos que se encuentran en el almacén
     */
    public void sumaActivos(){
        Producto nuevoP = new Producto();
        iteratorProducto = productoArray.iterator();
        int suma = 0;
        double precio;
        for (int i = 0; i < productoArray.size(); i++) {
           nuevoP = iteratorProducto.next();
           precio = nuevoP.getPrecioCompra();
           suma += precio;
        }
        System.out.println("La suma total de los activos en mercancía es: "+suma);
    }
    /**
     * La siguiente función facilita la asignación de claves de Productos
     * @return Regresa la clave
     */
    public int asignarClave(){
        Producto nuevoP = new Producto();
        int nElementos = productoArray.size();
        if (nElementos != 0) {
            nuevoP = productoArray.get(nElementos-1);
            
            return (nuevoP.getClave() + 1);
        }
        return 1;
    }
    /**
     * El siguiente método busca por medio de la clave el Producto almacenado en
     * el ArrayList.
     * @param busClave Clave del producto a buscar
     */
    public void buscarClave(int busClave){
        Producto nuevoP = new Producto();
        iteratorProducto = productoArray.iterator();
        int bClave;
        boolean flag = false;
        while (iteratorProducto.hasNext()){
            nuevoP = iteratorProducto.next();
            bClave = nuevoP.getClave();
            if (busClave == bClave) {
                mostrarProducto(nuevoP);
                flag = true;
               break;
            }   
        }
        if (flag == false) {  
            System.out.println("No se encuentra el producto :(");
        }
    }
    /**
     * El siguiente método busca por medio del nombre del Producto almacenado en
     * el ArrayList.
     * @param busNombre Nombre del producto a buscar
     */
    public void buscarNombre(String busNombre){
        Producto nuevoP = new Producto();
        iteratorProducto = productoArray.iterator();
        String nombre;
        boolean flag = false;
        while (iteratorProducto.hasNext()){
            nuevoP = iteratorProducto.next();
            nombre = nuevoP.getNombre();
            if (busNombre.equals(nombre)) {
                mostrarProducto(nuevoP);
                flag = true;
                break;
            }   
        }
        if (flag == false) {  
            System.out.println("No se encuentra el producto :(");
        }
    }
    /**
     * El siguiente método busca por medio de una descripción o palabra clave un 
     * Producto almacenado en el ArrayList.
     * @param busDescripcion Desripción a buscar coincidencias
     */
    public void buscarDescripcion(String busDescripcion){
        Producto nuevoP = new Producto();
        iteratorProducto = productoArray.iterator();
        String descripcion;
        int found = -1;
        boolean flag = false;
        while (iteratorProducto.hasNext()){
            nuevoP = iteratorProducto.next();
            descripcion = nuevoP.getDescripcion();
            found = descripcion.indexOf(busDescripcion);
            if (found != -1) {
               mostrarProducto(nuevoP);
               flag = true;
               break;
            }    
        }
        if (flag == false) {  
            System.out.println("No se encuentra el producto :(");
        }
    }
    /**
     * El siguiente método busca la posición del producto en el ArrayList
     * @param eClave Clave del producto a buscar
     * @return Regresa la posición en el ArrayList
     */
    public int buscarPosicion(int eClave){
        Producto nuevoP = new Producto();
        iteratorProducto = productoArray.iterator();
        int bClave;
        for (int i = 0; i < productoArray.size(); i++) {
           nuevoP = iteratorProducto.next();
           bClave = nuevoP.getClave();
           if (eClave == bClave) {
                return i; 
            }
        }
        return 0;
    }
    /**
     * El siguiente método busca la posición del producto en el ArrayList, especial
     * para ventas.
     * @param posicion Posición del Producto en el ArrayList 
     * @return  Regresa el Producto
     */
    public Producto buscarProductoPVenta(int posicion){
        Producto nuevoP = new Producto();
        return nuevoP = productoArray.get(posicion);
    }
    
    /**
     * Método auxiliar para describir el producto [mostrarProducto()] y guardar 
     * la posición que tiene en el arreglo.
     * @param busDescripcion Recibe la descripción del producto para identificar 
     * @return Regresa la posición del producto
     */
    public int buscarDescripcionAux(String busDescripcion){
        Producto nuevoP = new Producto();
        iteratorProducto = productoArray.iterator();
        String descripcion;
        int found = -1;
        for (int i = 0; i < productoArray.size(); i++) {
           nuevoP = iteratorProducto.next();
           descripcion = nuevoP.getDescripcion();
           found = descripcion.indexOf(busDescripcion);
            if (found != -1) {
               mostrarProducto(nuevoP);
               existenciaEditar = nuevoP.getExistencia();
               return i; 
            } 
        }
        return -1;
    }
    /**
     * Método auxiliar para describir el producto [mostrarProducto()] y guardar 
     * la posición que tiene en el arreglo, además guarda la clave y existencia 
     * para editar postereriormente. 
     * @param busClave Clave a buscar en el ArrayList
     * @return  Regresa la posición del Producto en el ArrayList
     */
    public int buscarClaveAux(int busClave){
        Producto nuevoP = new Producto();
        iteratorProducto = productoArray.iterator();
        int bClave;
        for (int i = 0; i < productoArray.size(); i++) {
           nuevoP = iteratorProducto.next();
           bClave = nuevoP.getClave();
           if (busClave == bClave) {
               mostrarProducto(nuevoP);
               claveEditar = nuevoP.getClave();
               existenciaEditar = nuevoP.getExistencia();
               return i; 
            }
        }
        return -1;
    }
    /**
     * Método auxiliar para mostrar el nombre del producto y guardar 
     * la posición que tiene en el arreglo.
     * @param busClave Clave del producto en el ArrayList
     * @return  Regresa la posición del Producto
     */
    public int buscarClaveAuxPVenta(int busClave){
        Producto nuevoP = new Producto();
        iteratorProducto = productoArray.iterator();
        int bClave;
        for (int i = 0; i < productoArray.size(); i++) {
           nuevoP = iteratorProducto.next();
           bClave = nuevoP.getClave();
           if (busClave == bClave) {
               System.out.println("Producto: "+nuevoP.getNombre());
               return i; 
            }
        }
        return -1; 
    }
    /**
     * Función para disminuir el producto en el inventario en caso de venta.
     * @param posicion Del producto en el ArrayList
     * @return  Regresa si se logró reducir
     */
    public boolean disminuir(int posicion){
        Producto nuevoP = new Producto();
        nuevoP = productoArray.get(posicion);
        if (nuevoP.getExistencia() > 0) {
            nuevoP.setExistencia((nuevoP.getExistencia() - 1));
            return true;
        } else {
            return false;
        }
    }
    /**
     * Método par guardar el ArrayList cuando no se ha guardado en alguno de los
     * métodos anteriores.
     */
    public void guardarCasoEsp(){
        archP.guardar(productoArray);
    }
    /**
     * Método para cargar (leer) los datos del ArrayList.
     */
    public void cargar(){
        productoArray = archP.entrada();
        if (productoArray.isEmpty()) {
            System.out.println("Limpio :(");
        }

    }
    
    

}
