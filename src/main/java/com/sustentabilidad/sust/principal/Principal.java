package com.sustentabilidad.sust.principal;

import com.sustentabilidad.sust.model.Compuestos;
import com.sustentabilidad.sust.model.Movimientos;
import com.sustentabilidad.sust.repository.CompuestosRepository;
import com.sustentabilidad.sust.repository.MovimientosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@Component
public class Principal extends  JFrame implements CommandLineRunner {
    private JPanel contentPane;
    private CompuestosRepository compuestosRepository;
    private MovimientosRepository movimientosRepository;


    @Override
    public void run(String... arg0) throws Exception {
        try {
            // Cambiar el "look and feel" a Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

            // Personalizar propiedades globales
            UIManager.put("control", new Color(37, 37, 37)); // Fondo general
            UIManager.put("info", new Color(50, 50, 50)); // Fondo de tooltips
            UIManager.put("nimbusBase", new Color(127, 123, 130)); // Color base de Nimbus
            UIManager.put("nimbusFocus", new Color(115, 135, 123)); // Color de foco
            UIManager.put("nimbusLightBackground", new Color(37, 37, 37)); // Fondo de cuadros de texto
            UIManager.put("nimbusSelectionBackground", new Color(30, 144, 255)); // Texto seleccionado
            UIManager.put("nimbusSelectedText", new Color(60, 59, 61)); // Fondo de selección
            UIManager.put("text", new Color(127, 123, 130)); // Texto general
            UIManager.put("defaultFont", new Font("Arial", Font.PLAIN, 14));

            UIManager.put("Button.background", new Color(37, 37, 37)); // Fondo del botón

            UIManager.put("TableHeader.background", new Color(60, 59, 61)); // Fondo del encabezado
            UIManager.put("TableHeader.foreground", new Color(0,0,0)); // Texto del encabezado
            UIManager.put("TableHeader.font", new Font("Arial", Font.BOLD, 14)); // Fuente del encabezado








        } catch (Exception e) {
            e.printStackTrace();
        }

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
    public Principal(CompuestosRepository compuestosRepository, MovimientosRepository movimientosRepository) {

        // Configuración del frame
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); // Tamaño del frame
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Espaciado
        contentPane.setLayout(new GridLayout(7, 1, 10, 10)); // Configuración de la rejilla
        setContentPane(contentPane);
        contentPane.setBackground(new Color(37,37,37));

        // Título
        JLabel titleLabel = new JLabel("Menú Principal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(251,254,249));
        contentPane.add(titleLabel);


        // Botón Añadir compuestos
        JButton btnAñadirCompuestos = createMenuButton("Añadir compuestos");
        btnAñadirCompuestos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Compuestos compuestoNuevo =mostrarFormularioAñadirCompuestos();

                if (compuestoNuevo != null) {
                    // Guardar en la base de datos
                    compuestosRepository.save(compuestoNuevo);
                    LocalDateTime fecha = LocalDateTime.now();
                    String movimiento = "Se ha agregado un nuevo compuesto a la base de datos; " + compuestoNuevo.getNombreCompuesto();
                    Movimientos nuevoMovimiento = new Movimientos(fecha, movimiento);
                    movimientosRepository.save(nuevoMovimiento);


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
                frameTabla.setSize(1200, 400);  // Ajusta el tamaño según sea necesario
                frameTabla.setLocationRelativeTo(null);

                // Obtener datos de la base de datos
                List<Compuestos> listaCompuestos = compuestosRepository.findAll(); // Usa tu repositorio
                if (listaCompuestos.isEmpty()){
                    JOptionPane.showMessageDialog(Principal.this,
                            "La tabla de compuestos está vacía");
                    return;
                }
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
                    if (listaCompuestos.isEmpty()){
                        JOptionPane.showMessageDialog(Principal.this,
                                "La tabla de compuestos está vacía");
                        return;
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

                    LocalDateTime fecha = LocalDateTime.now();
                    String movimiento = "Se han añadido " + cantidadAgregada + " kg/lts del compuesto de nombre: " + compuestoSeleccionado +
                            ". En total se tiene " + nuevoPeso + " kg/lts del compuesto.";
                    Movimientos nuevoMovimiento = new Movimientos(fecha, movimiento);
                    movimientosRepository.save(nuevoMovimiento);


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

                    LocalDateTime fecha = LocalDateTime.now();
                    String movimiento = "Se han extraído " + cantidadSacada + " kg/lts del compuesto de nombre: " + compuestoSeleccionado +
                            ". En total se tiene " + nuevoPeso + " kg/lts del compuesto.";
                    Movimientos nuevoMovimiento = new Movimientos(fecha, movimiento);
                    movimientosRepository.save(nuevoMovimiento);


                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Principal.this,
                            "Por favor, ingresa un número válido.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(btnSalidaCompuestos);


        // Botón Historial de movimientos
        JButton btnHistorialMovimientos = createMenuButton("Historial de movimientos");
        btnHistorialMovimientos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Recuperar los datos de la base de datos
                List<Movimientos> movimientos = movimientosRepository.findAll();

                if (movimientos.isEmpty()) {
                    JOptionPane.showMessageDialog(Principal.this,
                            "La tabla de movimientos está vacía");
                    return;
                }

                // Crear los encabezados de la tabla
                String[] columnNames = {"Fecha", "Movimiento"};

                // Convertir los datos en un formato adecuado para JTable
                Object[][] data = new Object[movimientos.size()][2];
                for (int i = 0; i < movimientos.size(); i++) {
                    Movimientos movimiento = movimientos.get(i);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    data[i][0] = movimiento.getFecha().format(formatter);
                    data[i][1] = movimiento.getMovimiento();
                }

                // Crear un JFrame para mostrar la tabla
                JFrame frameTabla = new JFrame("Historial de Movimientos");
                frameTabla.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameTabla.setSize(800, 400); // Tamaño inicial del JFrame
                frameTabla.setLocationRelativeTo(null); // Centrar la ventana

                // Crear la JTable y agregarla a un JScrollPane
                JTable table = new JTable(data, columnNames);
                JScrollPane scrollPane = new JScrollPane(table);

                // Configurar el JFrame
                frameTabla.add(scrollPane);
                frameTabla.setVisible(true); // Hacer visible la ventana
            }
        });


        contentPane.add(btnHistorialMovimientos);

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
        Icon organicoSelectedIcon = new ImageIcon("src/main/java/com/sustentabilidad/sust/principal/iconos/organico.png");
        Image scaledImage = ((ImageIcon) organicoSelectedIcon).getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        Icon resizedIconOrganico = new ImageIcon(scaledImage);
        JCheckBox organico = new JCheckBox("Orgánico");
        organico.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Márgenes internos
        organico.setMaximumSize(new Dimension(Integer.MAX_VALUE, organico.getPreferredSize().height));
        organico.setBorder(new EmptyBorder(0, 0, 5, 0)); // Margen alrededor del JCheckBox
        organico.setSelectedIcon(resizedIconOrganico);

        Icon explosivoSelectedIcon = new ImageIcon("src/main/java/com/sustentabilidad/sust/principal/iconos/explosivo.png");
        Image scaledImage1 = ((ImageIcon) explosivoSelectedIcon).getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        Icon resizedIconExplosivo = new ImageIcon(scaledImage1);
        JCheckBox explosivo = new JCheckBox("Peligro de explosivos");
        explosivo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Márgenes internos
        explosivo.setMaximumSize(new Dimension(Integer.MAX_VALUE, organico.getPreferredSize().height));
        explosivo.setBorder(new EmptyBorder(0, 0, 5, 0)); // Margen alrededor del JCheckBox
        explosivo.setSelectedIcon(resizedIconExplosivo);

        Icon inflamableSelectedIcon = new ImageIcon("src/main/java/com/sustentabilidad/sust/principal/iconos/inflamable.png");
        Image scaledImage2 = ((ImageIcon) inflamableSelectedIcon).getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        Icon resizedIconInflamable = new ImageIcon(scaledImage2);
        JCheckBox inflamable = new JCheckBox("Inflamable");
        inflamable.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Márgenes internos
        inflamable.setMaximumSize(new Dimension(Integer.MAX_VALUE, organico.getPreferredSize().height));
        inflamable.setBorder(new EmptyBorder(0, 0, 5, 0)); // Margen alrededor del JCheckBox
        inflamable.setSelectedIcon(resizedIconInflamable);

        Icon carburanteSelectedIcon = new ImageIcon("src/main/java/com/sustentabilidad/sust/principal/iconos/carburante.png");
        Image scaledImage3 = ((ImageIcon)  carburanteSelectedIcon).getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        Icon resizedIconCarburante = new ImageIcon(scaledImage3);
        JCheckBox carburante = new JCheckBox("Carburante");
        carburante.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Márgenes internos
        carburante.setMaximumSize(new Dimension(Integer.MAX_VALUE, organico.getPreferredSize().height));
        carburante.setBorder(new EmptyBorder(0, 0, 5, 0)); // Margen alrededor del JCheckBox
        carburante.setSelectedIcon(resizedIconCarburante);


        Icon presionSelectedIcon = new ImageIcon("src/main/java/com/sustentabilidad/sust/principal/iconos/presion.png");
        Image scaledImage4 = ((ImageIcon) presionSelectedIcon).getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        Icon resizedIconPresion = new ImageIcon(scaledImage4);
        JCheckBox presion = new JCheckBox("Gases bajo presión");
        presion.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Márgenes internos
        presion.setMaximumSize(new Dimension(Integer.MAX_VALUE, organico.getPreferredSize().height));
        presion.setBorder(new EmptyBorder(0, 0, 5, 0)); // Margen alrededor del JCheckBox
        presion.setSelectedIcon(resizedIconPresion);

        Icon corrosionSelectedIcon = new ImageIcon("src/main/java/com/sustentabilidad/sust/principal/iconos/corrosion.png");
        Image scaledImage5 = ((ImageIcon) corrosionSelectedIcon).getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        Icon resizedIconCorrosion = new ImageIcon(scaledImage5);
        JCheckBox corrosion = new JCheckBox("Corrosión");
        corrosion.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Márgenes internos
        corrosion.setMaximumSize(new Dimension(Integer.MAX_VALUE, organico.getPreferredSize().height));
        corrosion.setBorder(new EmptyBorder(0, 0, 5, 0)); // Margen alrededor del JCheckBox
        corrosion.setSelectedIcon(resizedIconCorrosion);

        Icon toxicidadSelectedIcon = new ImageIcon("src/main/java/com/sustentabilidad/sust/principal/iconos/toxicidad.png");
        Image scaledImage6 = ((ImageIcon) toxicidadSelectedIcon).getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        Icon resizedIconToxicidad = new ImageIcon(scaledImage6);
        JCheckBox toxicidad = new JCheckBox("Toxicidad");
        toxicidad.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Márgenes internos
        toxicidad.setMaximumSize(new Dimension(Integer.MAX_VALUE, organico.getPreferredSize().height));
        toxicidad.setBorder(new EmptyBorder(0, 0, 5, 0)); // Margen alrededor del JCheckBox
        toxicidad.setSelectedIcon(resizedIconToxicidad);

        Icon nocivoSelectedIcon = new ImageIcon("src/main/java/com/sustentabilidad/sust/principal/iconos/nocivo.png");
        Image scaledImage7 = ((ImageIcon) nocivoSelectedIcon).getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        Icon resizedIconNocivo = new ImageIcon(scaledImage7);
        JCheckBox quimicoNocivo = new JCheckBox("Químico nocivo");
        quimicoNocivo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Márgenes internos
        quimicoNocivo.setMaximumSize(new Dimension(Integer.MAX_VALUE, organico.getPreferredSize().height));
        quimicoNocivo.setBorder(new EmptyBorder(0, 0, 5, 0)); // Margen alrededor del JCheckBox
        quimicoNocivo.setSelectedIcon(resizedIconNocivo);

        Icon ambienteSelectedIcon = new ImageIcon("src/main/java/com/sustentabilidad/sust/principal/iconos/ambiente.png");
        Image scaledImage8 = ((ImageIcon) ambienteSelectedIcon).getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        Icon resizedIconAmbiente = new ImageIcon(scaledImage8);
        JCheckBox medioAmbiente = new JCheckBox("Daño al medio ambiente");
        medioAmbiente.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Márgenes internos
        medioAmbiente.setMaximumSize(new Dimension(Integer.MAX_VALUE, organico.getPreferredSize().height));
        medioAmbiente.setBorder(new EmptyBorder(0, 0, 5, 0)); // Margen alrededor del JCheckBox
        medioAmbiente.setSelectedIcon(resizedIconAmbiente);

        Icon peligroSelectedIcon = new ImageIcon("src/main/java/com/sustentabilidad/sust/principal/iconos/peligro.png");
        Image scaledImage9 = ((ImageIcon) peligroSelectedIcon).getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        Icon resizedIconPeligro = new ImageIcon(scaledImage9);
        JCheckBox peligroSalud = new JCheckBox("Peligro para la salud");
        peligroSalud.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Márgenes internos
        peligroSalud.setMaximumSize(new Dimension(Integer.MAX_VALUE, organico.getPreferredSize().height));
        peligroSalud.setBorder(new EmptyBorder(0, 0, 5, 0)); // Margen alrededor del JCheckBox
        peligroSalud.setSelectedIcon(resizedIconPeligro);


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
        JLabel descLabel = new JLabel("Descripción:");
        JTextArea descArea = new JTextArea(5, 26);
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

