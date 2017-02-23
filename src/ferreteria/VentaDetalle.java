
package ferreteria;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 *Clase para generar el ArrayList de Ventas que contiene objetos Venta.
 * @author javr
 */
public class VentaDetalle implements Serializable {
   
   private static ArrayList<Venta> nuevaVentasArray = new ArrayList<>();
    Iterator<Venta> iteratorVentasArray = nuevaVentasArray.iterator();
  
   Archivo archV = new Archivo();
   Teclado teclado = new Teclado();
   ProductoArray proArray = new ProductoArray();
   Venta nuevaVentaD = new Venta();

   int nArticulos = 0;
   
   /**
    * Método para registrar una venta, registra productos de la venta por medio
    * de su código en un ArrayList de Venta para después mostrar el detalle de
    * la venta [mostrarResumenVenta()] y añadir el objeto Venta al ArrayList. 
    */  
    public void registrarVenta() {
        Venta nuevaVenta = new Venta();
        int cod;
        int posicion;
        double precio;
        boolean flag = true;
        Calendar fecha = Calendar.getInstance();
        nuevaVenta.setFecha(fecha);
        nuevaVenta.setClaveVenta(asignarClaveV());
        while (flag == true) {
            System.out.println("Escribe el código del producto: (0 para finalizar nuevaVenta)");
            cod = teclado.leerEnteros();
            if (cod != 0) {
                posicion = proArray.buscarClaveAuxPVenta(cod);
                if (posicion != -1) {
                    if (proArray.disminuir(posicion) == true) {
                       nuevaVenta.productoArrayV.add(proArray.buscarProductoPVenta(posicion));
                       precio = proArray.buscarProductoPVenta(posicion).getPrecioVenta();
                       nuevaVenta.setTotalVenta(nuevaVenta.getTotalVenta() + precio);
                    } else {
                        System.out.println("Fuera de stock");
                    }
                } else {
                    System.out.println("Código erróneo");
                }
            } else {
                flag = false;
            }
        }
        //nuevaVenta.sumarTotal(); 
        mostrarResumenVenta(nuevaVenta.getProductoArrayV());
        System.out.println("_____________________");
        System.out.println("Código de compra: "+nuevaVenta.getClaveVenta());
        System.out.println(nuevaVenta.getDayWString()+" "+ nuevaVenta.getDayM()+
            " "+nuevaVenta.getMonthString()+ " "+ nuevaVenta.getYear());
        System.out.println("\033[34mTOTAL: $"+(nuevaVenta.getTotalVenta()*1.16));

        nuevaVentasArray.add(nuevaVenta);
        archV.guardarVentas(nuevaVentasArray);
    }
    /**
     * Método para mostrar los detalles de los productos que se encuentran el ArrayList.
     * @param arrProdc ArrayList de Producto a describir
     */
    public void mostrarResumenVenta(ArrayList<Producto> arrProdc){
    Producto nuevoP = new Producto();
    //iteratorProductoVenta = arrProdc.iterator();
    Iterator<Producto> iteratorArrProdc = arrProdc.iterator();
        while (iteratorArrProdc.hasNext()) {
            
            nuevoP = iteratorArrProdc.next();
            System.out.println("Producto "+nuevoP.getClave());
            System.out.println("Nombre: "+ nuevoP.getNombre());
            System.out.println("Precio: $"+ nuevoP.getPrecioVenta());
            System.out.println("IVA: $"+ (nuevoP.getPrecioVenta())*0.16);
            System.out.println("-----------------");

            nArticulos += 1;
        } 
         
    }
    /**
     * Método para mostrar las Ventas realizadas el día actual.
     */
    public void detalleDia(){
        iteratorVentasArray = nuevaVentasArray.iterator();
        Venta nuevaVenta = new Venta();
        Calendar fecha = Calendar.getInstance();
        double totalVentas = 0;
        nArticulos = 0;
        int hoy = fecha.get(Calendar.DAY_OF_MONTH);
        int dia;
        for (int i = 0; i < nuevaVentasArray.size(); i++) {
            nuevaVenta = iteratorVentasArray.next();
            dia = nuevaVenta.getDayM();
            if (dia == hoy) {
                totalVentas += mostrarRegistroVenta(nuevaVenta);
                
            } else {
                System.out.println("Ninguna venta para mostrar");
            }   
        }
        System.out.println("\nTotal de artículos vendidos: " + nArticulos);
        System.out.println("Total de ventas del día "+ hoy +" $" + totalVentas);
    }
    /**
     * Método para mostrar las Ventas realizadas el día anterior al actual.
     */
    public void detalleAyer(){
        iteratorVentasArray = nuevaVentasArray.iterator();
        Venta nuevaVenta = new Venta();
        Calendar fecha = Calendar.getInstance();
        double totalVentas = 0;
        nArticulos = 0;
        int ayer = (fecha.get(Calendar.DAY_OF_MONTH) - 1);
        int dia;
        for (int i = 0; i < nuevaVentasArray.size(); i++) {
            nuevaVenta = iteratorVentasArray.next();
            dia = nuevaVenta.getDayM();
            if (dia == ayer) {
                totalVentas += mostrarRegistroVenta(nuevaVenta);
                
            } else {
                System.out.println("Ninguna venta para mostrar");
            }   
        }
        System.out.println("\nTotal de artículos vendidos: " + nArticulos);
        System.out.println("Total de ventas del día "+ ayer +" $" + totalVentas);
    }
    /**
     * Método para mostrar las Ventas realizadas en un mes.
     * @param mesBusc Número del mes a buscar coincidencias
     */
    public void detalleMes(int mesBusc){
        iteratorVentasArray = nuevaVentasArray.iterator();
        Venta nuevaVenta = new Venta();
       
        double totalVentas = 0;
        nArticulos = 0;
        
        int mes;
        for (int i = 0; i < nuevaVentasArray.size(); i++) {
            nuevaVenta = iteratorVentasArray.next();
            mes = nuevaVenta.getMonthInt();
            if (mes == mesBusc) {
                totalVentas += mostrarRegistroVenta(nuevaVenta);
                
            } else {
                System.out.println("Ninguna venta para mostrar");
            }   
        }
        System.out.println("\nTotal de artículos vendidos: " + nArticulos);
        System.out.println("Total de ventas del mes "+ nuevaVenta.getMonthString() +" $" + totalVentas);
    }
    /**
     * Método para describir los detalles (Productos) de la Venta
     * @param nuevaVenta Recibe la Venta
     * @return  Regresa el total de esa venta 
     */
    public double mostrarRegistroVenta(Venta nuevaVenta){
        
        ArrayList<Producto> productoArrayVentaAux = new ArrayList<>();
        productoArrayVentaAux = nuevaVenta.getProductoArrayV();
        //iteratorVentasArray = nuevaVentasArray.iterator();
        if (productoArrayVentaAux.isEmpty()) {
            System.out.println("CLEAR");
        }
        
        System.out.println("__________________________");
        System.out.println("Código de compra: "+nuevaVenta.getClaveVenta());
        System.out.println(nuevaVenta.getDayWString()+" "+ nuevaVenta.getDayM()+
            " "+nuevaVenta.getMonthString()+ " "+ nuevaVenta.getYear());
        System.out.println("-----------------");
        mostrarResumenVenta(productoArrayVentaAux);
        System.out.println("Total de venta: $" + (nuevaVenta.getTotalVenta()*1.16));
        return (nuevaVenta.getTotalVenta()*1.16);   
       
    }
    /**
     * Función para consultar códigos de los productos, en caso de desconocer
     * antes de realizar venta.
     * @throws InterruptedException Aplicado para no tener problemas en el detalle 
     * gráfico de los puntos (diseño).
     */
    public void consultarCodigos() throws InterruptedException{
        System.out.println("1)Desplegar lista de codigos de todos los articulos "
            + "2)Buscar por descripción");
        System.out.println("\033[33m0)Regresar");
        System.out.println("Escribe el número de la opcion");
        int opc = teclado.leerEnteros();
        switch (opc) {
            case 1:
                proArray.mostrarProductosPVenta();
                break;
            case 2:
                System.out.println("Escribe una palabra clave del producto");
                String busDescripcion = teclado.leerString();
                proArray.buscarDescripcion(busDescripcion);
                break;
            case 0:
                System.out.print("Regresando");
                Thread.sleep(700);
                System.out.print(".");
                Thread.sleep(700);
                System.out.print(".");
                Thread.sleep(700);
                System.out.println(".");
                break;
            default:
                System.out.println("No es una opción válida");
                break;
             
        }
        
    }
    public void cargarVentas(){
        nuevaVentasArray = archV.entradaVentas();
        if (nuevaVentasArray.isEmpty()) {
            System.out.println("Limpio :(");
        }
    }
    
    public int asignarClaveV(){
        Venta nuevaVenta = new Venta();
        int nElementos = nuevaVentasArray.size();
        if (nElementos != 0) {
           nuevaVenta = nuevaVentasArray.get(nElementos-1);
            
            return (nuevaVenta.getClaveVenta() + 1);
        }
        return 1;
    }
      
}
