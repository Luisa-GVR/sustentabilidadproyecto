package com.sustentabilidad.sust.principal;

import com.sustentabilidad.sust.repository.CompuestosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class Principal extends  JFrame implements CommandLineRunner {
    private JPanel contentPane;
    private CompuestosRepository compuestosRepository;

    @Override
    public void run(String... arg0) throws Exception {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal frame = new Principal(compuestosRepository);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Autowired
    public Principal(CompuestosRepository compuestosRepository) {
        // Configuración del frame
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400); // Tamaño del frame
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaciado
        contentPane.setLayout(new GridLayout(6, 1, 10, 10)); // Configuración de la rejilla
        setContentPane(contentPane);

        // Título
        JLabel titleLabel = new JLabel("Menú Principal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        contentPane.add(titleLabel);

        // Botón Añadir compuestos
        JButton btnAñadirCompuestos = createMenuButton("Añadir compuestos");
        btnAñadirCompuestos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción para añadir compuestos
                JOptionPane.showMessageDialog(Principal.this, "Añadir compuestos");
            }
        });
        contentPane.add(btnAñadirCompuestos);

        // Botón Ver información de compuestos
        JButton btnVerInfoCompuestos = createMenuButton("Ver información de compuestos");
        btnVerInfoCompuestos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción para ver información de compuestos
                JOptionPane.showMessageDialog(Principal.this, "Ver información de compuestos");
            }
        });
        contentPane.add(btnVerInfoCompuestos);

        // Botón Entrada de compuestos
        JButton btnEntradaCompuestos = createMenuButton("Entrada de compuestos");
        btnEntradaCompuestos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción para la entrada de compuestos
                JOptionPane.showMessageDialog(Principal.this, "Entrada de compuestos");
            }
        });
        contentPane.add(btnEntradaCompuestos);

        // Botón Salida de compuestos
        JButton btnSalidaCompuestos = createMenuButton("Salida de compuestos");
        btnSalidaCompuestos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción para la salida de compuestos
                JOptionPane.showMessageDialog(Principal.this, "Salida de compuestos");
            }
        });
        contentPane.add(btnSalidaCompuestos);

        // Botón Salir
        JButton btnSalir = createMenuButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Cierra la aplicación
            }
        });
        contentPane.add(btnSalir);
    }

    // Método para crear un botón con un estilo uniforme
    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente
        button.setBackground(new Color(30, 144, 255)); // Color de fondo
        button.setForeground(Color.WHITE); // Color del texto
        button.setFocusPainted(false); // Quitar el borde cuando se hace clic
        button.setBorder(BorderFactory.createRaisedBevelBorder()); // Estilo de borde
        return button;
    }




}
