
package cajero;

import javax.swing.JOptionPane;


public class Cola {
    private Nodo inicioCola, finalCola,cajero;
    private String cola="";

    public Nodo getInicioCola() {
        return inicioCola;
    }

    public void setInicioCola(Nodo inicioCola) {
        this.inicioCola = inicioCola;
    }

    public Nodo getFinalCola() {
        return finalCola;
    }

    public void setFinalCola(Nodo finalCola) {
        this.finalCola = finalCola;
    }

    public Nodo getCajero() {
        return cajero;
    }

    public void setCajero(Nodo cajero) {
        this.cajero = cajero;
    }

    public String getCola() {
        return cola;
    }

    public void setCola(String cola) {
        this.cola = cola;
    }
    
    
    
    public Cola(){
        cajero=new Nodo();
        cajero.setRecibos(-1);
        inicioCola=cajero;
        finalCola=cajero;
    }
    
    //Método para identificar si la cola está vacía
    public boolean colaVacia(){
        if (inicioCola==finalCola) {
            return true;
        } else {
            return false;
        }
    }
    
    //Método para insertar un elemento en la cola
    public void insertar(int recibos){
        Nodo nuevo=new Nodo();
        nuevo.setRecibos(recibos);
        nuevo.setSiguiente(inicioCola);
        
        finalCola.setSiguiente(nuevo);
        finalCola=nuevo;
    }
    
    public void extraer(){
        if (colaVacia()) {
           JOptionPane.showMessageDialog(null,"Sólo está el cajero en la cola");
        } else {
            inicioCola.setSiguiente(inicioCola.getSiguiente().getSiguiente());
          
        }
    }
    
    public String mostrarCola(){
        Nodo recorrido=inicioCola.getSiguiente();
        cola="";
        cola=inicioCola.getRecibos()+"   ||   ";
        if (colaVacia()) {
           return "Sólo está el cajero en la cola";
        }else{
            while(recorrido!=inicioCola){
                cola+=recorrido.getRecibos()+"   /   ";
                recorrido=recorrido.getSiguiente();
            }
           
            return cola;
            
        }
    }
    
}
