package com.mycompany.vista;

import com.mycompany.modelo.Producto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminFrame extends JFrame {
    private ArrayList<Producto> productos;
    private JPanel panel;
    private JScrollPane scrollPane;
    private JLabel label;
    private JTextField nombreField, precioField, cantidadField;

    public AdminFrame(ArrayList<Producto> productos) {
        this.productos = productos;

        setTitle("Modo Administrador");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);  // Centra la ventana
        getContentPane().setBackground(new Color(240, 240, 240));

        panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel tituloLabel = new JLabel("Administrar Productos");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(tituloLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        nombreField = new JTextField(15);
        panel.add(nombreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Precio:"), gbc);
        gbc.gridx = 1;
        precioField = new JTextField(15);
        panel.add(precioField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Cantidad:"), gbc);
        gbc.gridx = 1;
        cantidadField = new JTextField(15);
        panel.add(cantidadField, gbc);

        // Botón para agregar producto
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton agregarButton = new JButton("Agregar Producto");
        agregarButton.setBackground(new Color(144, 238, 144));
        panel.add(agregarButton, gbc);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                double precio = Double.parseDouble(precioField.getText());
                int cantidad = Integer.parseInt(cantidadField.getText());

                productos.add(new Producto(nombre, precio, cantidad));
                nombreField.setText("");
                precioField.setText("");
                cantidadField.setText("");
                actualizarListaProductos();
            }
        });

        // Botón para eliminar producto seleccionado
        gbc.gridx = 0;
        gbc.gridy = 5;
        JButton eliminarButton = new JButton("Eliminar Producto");
        eliminarButton.setBackground(new Color(255, 160, 122));
        panel.add(eliminarButton, gbc);

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSeleccionado = scrollPane.getVerticalScrollBar().getValue() / 16; // Asumiendo altura promedio de 16 por ítem
                if (indiceSeleccionado >= 0 && indiceSeleccionado < productos.size()) {
                    productos.remove(indiceSeleccionado);
                    actualizarListaProductos();
                }
            }
        });

        // Botón para volver al login
        gbc.gridx = 0;
        gbc.gridy = 6;
        JButton volverLoginButton = new JButton("Volver al Login");
        volverLoginButton.setBackground(new Color(173, 216, 230));
        panel.add(volverLoginButton, gbc);

        volverLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame(productos);
                dispose(); // Cierra la ventana actual
            }
        });

        scrollPane = new JScrollPane();
        actualizarListaProductos();

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private void actualizarListaProductos() {
        JPanel listaPanel = new JPanel();
        listaPanel.setBackground(new Color(240, 240, 240));
        listaPanel.setLayout(new BoxLayout(listaPanel, BoxLayout.Y_AXIS));

        for (Producto producto : productos) {
            listaPanel.add(new JLabel(producto.toString()));
        }

        scrollPane.setViewportView(listaPanel);
    }
}
