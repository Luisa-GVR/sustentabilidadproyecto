package com.sustentabilidad.sust.principal;

import com.sustentabilidad.sust.model.Compuestos;
import com.sustentabilidad.sust.repository.CompuestosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

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
                    Principal frame = SpringApplicationContext.getBean(Principal.class);
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
                Compuestos compuestoNuevo =mostrarFormularioAñadirCompuestos();

                if (compuestoNuevo != null) {
                    // Guardar en la base de datos
                    compuestosRepository.save(compuestoNuevo);
                    JOptionPane.showMessageDialog(Principal.this, "Compuesto añadido exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(Principal.this, "Operación cancelada.");
                }


            }
        });

        contentPane.add(btnAñadirCompuestos);

        // Botón Ver información de compuestos
        JButton btnVerInfoCompuestos = createMenuButton("Ver información de compuestos");
        // Botón Ver información de compuestos
        btnVerInfoCompuestos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear la ventana de información de compuestos
                JFrame frameTabla = new JFrame("Información de Compuestos");
                frameTabla.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameTabla.setSize(800, 400);  // Ajusta el tamaño según sea necesario

                // Obtener datos de la base de datos
                List<Compuestos> listaCompuestos = compuestosRepository.findAll(); // Usa tu repositorio
                String[] columnNames = {
                        "Nombre del compuesto", "Orgánico/Inorgánico", "Peso",
                        "Explosivo", "Inflamable", "Carburante", "Presión",
                        "Corrosión", "Toxicidad", "Químico nocivo",
                        "Medio ambiente", "Peligro salud", "Descripción"
                };

                // Inicializar el arreglo de datos con el tamaño de la lista de compuestos
                Object[][] data = new Object[listaCompuestos.size()][13];

                // Llenar los datos con la información de cada compuesto
                for (int i = 0; i < listaCompuestos.size(); i++) {
                    Compuestos compuesto = listaCompuestos.get(i);
                    data[i][0] = compuesto.getNombreCompuesto();
                    data[i][1] = compuesto.isOrganico() ? "Orgánico" : "Inorgánico";
                    data[i][2] = compuesto.getPeso();
                    data[i][3] = compuesto.isExplosivo() ? "Sí" : "No";
                    data[i][4] = compuesto.isInflamable() ? "Sí" : "No";
                    data[i][5] = compuesto.isCarburante() ? "Sí" : "No";
                    data[i][6] = compuesto.isPresion() ? "Sí" : "No";
                    data[i][7] = compuesto.isCorrosion() ? "Sí" : "No";
                    data[i][8] = compuesto.isToxicidad() ? "Sí" : "No";
                    data[i][9] = compuesto.isQuimicoNocivo() ? "Sí" : "No";
                    data[i][10] = compuesto.isMedioAmbiente() ? "Sí" : "No";
                    data[i][11] = compuesto.isPeligroSalud() ? "Sí" : "No";
                    data[i][12] = compuesto.getDescripcion();
                }

                // Crear la tabla
                JTable table = new JTable(data, columnNames);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                table.setFillsViewportHeight(true);

                // Agregar la tabla a un JScrollPane
                JScrollPane scrollPane = new JScrollPane(table);
                frameTabla.add(scrollPane);

                // Hacer visible la ventana
                frameTabla.setVisible(true);
            }
        });

        contentPane.add(btnVerInfoCompuestos);

        // Botón Entrada de compuestos
        JButton btnEntradaCompuestos = createMenuButton("Entrada de compuestos");
        btnEntradaCompuestos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción para la entrada de compuestos
                // lista de compuestos
                List<Compuestos> listaCompuestos = compuestosRepository.findAll();

                // muestra lal lista
                String[] compuestosNombres = new String[listaCompuestos.size()];
                for (int i = 0; i < listaCompuestos.size(); i++) {
                    compuestosNombres[i] = listaCompuestos.get(i).getNombreCompuesto();
                }

                // elegir compuesto
                String compuestoSeleccionado = (String) JOptionPane.showInputDialog(
                        Principal.this,
                        "Selecciona el compuesto",
                        "Selección de compuesto",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        compuestosNombres,
                        compuestosNombres[0]
                );
                if (compuestoSeleccionado == null) {
                    return;
                }

                Compuestos compuesto = null;
                for (Compuestos c : listaCompuestos) {
                    if (c.getNombreCompuesto().equals(compuestoSeleccionado)) {
                        compuesto = c;
                        break;
                    }
                }

                String cantidadStr = JOptionPane.showInputDialog(Principal.this,
                        "¿Cuántos kg/lt se desea agregar del compuesto " + compuesto.getNombreCompuesto() + "?");

                if (cantidadStr == null || cantidadStr.isEmpty()) {
                    return;
                }

                try {
                    double cantidadAgregada = Double.parseDouble(cantidadStr);
                    double nuevoPeso = compuesto.getPeso() + cantidadAgregada;

                    // Verificar que el nuevo peso no sea negativo
                    if (nuevoPeso < 0) {
                        return;
                    }

                    // actualizar peso
                    compuesto.setPeso(nuevoPeso);
                    compuestosRepository.save(compuesto);
                    JOptionPane.showMessageDialog(Principal.this,
                            "La entrada de compuestos se ha registrado correctamente.");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Principal.this,
                            "Por favor, ingresa un número válido.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }            }
        });
        contentPane.add(btnEntradaCompuestos);

        // Botón Salida de compuestos
        JButton btnSalidaCompuestos = createMenuButton("Salida de compuestos");
        btnSalidaCompuestos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // lista de compuestos
                List<Compuestos> listaCompuestos = compuestosRepository.findAll();

                // muestra lal lista
                String[] compuestosNombres = new String[listaCompuestos.size()];
                for (int i = 0; i < listaCompuestos.size(); i++) {
                    compuestosNombres[i] = listaCompuestos.get(i).getNombreCompuesto();
                }

                // elegir compuesto
                String compuestoSeleccionado = (String) JOptionPane.showInputDialog(
                        Principal.this,
                        "Selecciona el compuesto",
                        "Selección de compuesto",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        compuestosNombres,
                        compuestosNombres[0]
                );
                if (compuestoSeleccionado == null) {
                    return;
                }

                Compuestos compuesto = null;
                for (Compuestos c : listaCompuestos) {
                    if (c.getNombreCompuesto().equals(compuestoSeleccionado)) {
                        compuesto = c;
                        break;
                    }
                }

                String cantidadStr = JOptionPane.showInputDialog(Principal.this,
                        "¿Cuántos kg/lt se desea sacar del compuesto " + compuesto.getNombreCompuesto() + "?");

                if (cantidadStr == null || cantidadStr.isEmpty()) {
                    return;
                }

                try {
                    double cantidadSacada = Double.parseDouble(cantidadStr);
                    double nuevoPeso = compuesto.getPeso() - cantidadSacada;

                    // Verificar que el nuevo peso no sea negativo
                    if (nuevoPeso < 0) {
                        JOptionPane.showMessageDialog(Principal.this,
                                "No se puede sacar más peso de lo que hay disponible.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // actualizar peso
                    compuesto.setPeso(nuevoPeso);
                    compuestosRepository.save(compuesto);
                    JOptionPane.showMessageDialog(Principal.this,
                            "La salida de compuestos se ha registrado correctamente.");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Principal.this,
                            "Por favor, ingresa un número válido.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
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

    private Compuestos mostrarFormularioAñadirCompuestos() {
        // Crear el panel principal del formulario
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Disposición vertical

        // Campo de texto para el nombre del compuesto
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("Nombre del compuesto:");
        JTextField nameField = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        panel.add(namePanel);

        // Checkboxes para las propiedades
        JCheckBox organico = new JCheckBox("Orgánico");
        JCheckBox explosivo = new JCheckBox("Peligro de explosivos");
        JCheckBox inflamable = new JCheckBox("Inflamable");
        JCheckBox carburante = new JCheckBox("Carburante");
        JCheckBox presion = new JCheckBox("Gases bajo presión");
        JCheckBox corrosion = new JCheckBox("Corrosión");
        JCheckBox toxicidad = new JCheckBox("Toxicidad");
        JCheckBox quimicoNocivo = new JCheckBox("Químico nocivo");
        JCheckBox medioAmbiente = new JCheckBox("Daño al medio ambiente");
        JCheckBox peligroSalud = new JCheckBox("Peligro para la salud");

        // Añadir los checkboxes al panel
        panel.add(organico);
        panel.add(explosivo);
        panel.add(inflamable);
        panel.add(carburante);
        panel.add(presion);
        panel.add(corrosion);
        panel.add(toxicidad);
        panel.add(quimicoNocivo);
        panel.add(medioAmbiente);
        panel.add(peligroSalud);

        // Campo de texto para las notas adicionales
        JPanel descPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel descLabel = new JLabel("Notas extra:");
        JTextArea descArea = new JTextArea(5, 20);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descArea);
        descPanel.add(descLabel);
        descPanel.add(scrollPane);
        panel.add(descPanel);

        // Mostrar el formulario en un cuadro de diálogo
        int result = JOptionPane.showConfirmDialog(this, panel,
                "Añadir Compuesto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String nombre = nameField.getText();
            boolean isOrganico = organico.isSelected();
            boolean isExplosivo = explosivo.isSelected();
            boolean isInflamable = inflamable.isSelected();
            boolean isCarburante = carburante.isSelected();
            boolean isPresion = presion.isSelected();
            boolean isCorrosion = corrosion.isSelected();
            boolean isToxicidad = toxicidad.isSelected();
            boolean isQuimicoNocivo = quimicoNocivo.isSelected();
            boolean isMedioAmbiente = medioAmbiente.isSelected();
            boolean isPeligroSalud = peligroSalud.isSelected();
            String descripcion = descArea.getText();

            // Crear y retornar un objeto Compuesto
            return new Compuestos(nombre, isOrganico, 0, isExplosivo, isInflamable, isCarburante,
                    isPresion, isCorrosion, isToxicidad, isQuimicoNocivo, isMedioAmbiente,
                    isPeligroSalud, descripcion);
        }

        return null;
    }



}
