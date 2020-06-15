package cajero;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Vista extends JFrame implements ActionListener, Runnable {

    JPanel panelNorte;
    JPanel panelCentro;
    JButton nuevoCliente;
    JButton abrirCajero;
    JTextField recibos;
    Thread hilo = new Thread(this);
    Cola cola;

    public Vista() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 150);
        setLayout(new BorderLayout());
        setTitle("Práctica Cajero");

        panelNorte = new JPanel();
        panelCentro = new JPanel();
        panelCentro.setLayout(null);

        cola = new Cola();

        nuevoCliente = new JButton("Agregar cliente");
        nuevoCliente.addActionListener(this);
        recibos = new JTextField();
        abrirCajero = new JButton("Abrir cajero");
        abrirCajero.addActionListener(this);
        JLabel etiqueta = new JLabel(" Ingrese la cantidad de recibos: ");

        recibos.setText("Recibos");

        panelNorte.add(etiqueta);
        panelNorte.add(recibos);
        panelNorte.add(nuevoCliente);
        panelNorte.add(abrirCajero);

        add(panelNorte, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nuevoCliente) {
            System.out.println("Nuevo Cliente");
            cola.insertar(Integer.parseInt(recibos.getText()));
            recibos.requestFocus();
            recibos.setText("");
        } else if (e.getSource() == abrirCajero) {
            hilo.start();
        }
    }

    @Override
    public void run() {

        while (!cola.getCola().equalsIgnoreCase("-1")) {
            
            JLabel etiqueta=new JLabel();
            etiqueta.setText(cola.mostrarCola());
            panelCentro.removeAll();
            panelCentro.add(etiqueta);
            etiqueta.setBounds(95,5,500,50);
            System.out.println(cola.mostrarCola());
 
            if (cola.getInicioCola().getSiguiente().getRecibos() <= 5) {
                cola.extraer();
            } else {
                cola.insertar(cola.getInicioCola().getSiguiente().getRecibos() - 5);
                cola.extraer();

            }
          
            try {
                hilo.sleep(1500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            

        }
        
        System.out.println("Cajero vacío");
    }
}
