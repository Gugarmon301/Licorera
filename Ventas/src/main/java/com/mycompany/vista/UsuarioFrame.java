package com.mycompany.vista;

import com.mycompany.modelo.Producto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class UsuarioFrame extends JFrame {
    private ArrayList<Producto> productos;
    private JPanel listaPanel;
    private JTextPane reciboTextPane;
    private String rutaImagenes = "C:/Users/DAVID/Desktop/carritodecompra/imagenes/";

    public UsuarioFrame(ArrayList<Producto> productos) {
        this.productos = productos;

        setTitle("Modo Usuario");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 240, 240));

        // Panel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.weightx = 1.0;
        gbcMain.weighty = 1.0;
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;

        // Panel para la lista de productos
        JPanel productosPanel = new JPanel(new BorderLayout());
        productosPanel.setBackground(new Color(240, 240, 240));

        JLabel tituloLabel = new JLabel("Lista de Productos");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 16));
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        productosPanel.add(tituloLabel, BorderLayout.NORTH);

        listaPanel = new JPanel();
        listaPanel.setBackground(new Color(240, 240, 240));
        listaPanel.setLayout(new BoxLayout(listaPanel, BoxLayout.Y_AXIS));
        actualizarListaProductos();
        JScrollPane scrollPane = new JScrollPane(listaPanel);
        productosPanel.add(scrollPane, BorderLayout.CENTER);

        gbcMain.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(productosPanel, gbcMain);

        // Panel para el área de recibo
        JPanel reciboPanel = new JPanel(new BorderLayout());
        reciboPanel.setBackground(new Color(240, 240, 240));

        reciboTextPane = new JTextPane();
        reciboTextPane.setEditable(false);
        reciboTextPane.setBackground(new Color(240, 240, 240));
        JScrollPane reciboScrollPane = new JScrollPane(reciboTextPane);
        reciboPanel.add(reciboScrollPane, BorderLayout.CENTER);

        JButton comprarButton = new JButton("Finalizar Compra");
        comprarButton.setBackground(new Color(144, 238, 144));
        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (reciboTextPane.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(UsuarioFrame.this, "No hay productos en el carrito.");
                } else {
                    JOptionPane.showMessageDialog(UsuarioFrame.this, "¡Compra realizada con éxito!");
                    reciboTextPane.setText(""); // Limpiar el área de recibo después de la compra
                }
            }
        });

        // Botón para borrar la compra del carrito
        JButton borrarButton = new JButton("Borrar Carrito");
        borrarButton.setBackground(new Color(255, 160, 122));
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reciboTextPane.setText(""); // Limpiar el área de recibo
            }
        });

        // Botón para volver al login
        JButton volverLoginButton = new JButton("Volver al Login");
        volverLoginButton.setBackground(new Color(173, 216, 230));
        volverLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame(productos);
                dispose(); // Cierra la ventana actual
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.add(comprarButton);
        buttonPanel.add(borrarButton);
        buttonPanel.add(volverLoginButton);

        reciboPanel.add(buttonPanel, BorderLayout.SOUTH);

        gbcMain.gridy = 1;
        mainPanel.add(reciboPanel, gbcMain);

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void actualizarListaProductos() {
        listaPanel.removeAll();

        for (Producto producto : productos) {
            JPanel productoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            productoPanel.setBackground(new Color(240, 240, 240));

            // Construir la ruta de la imagen
            String imagePath = rutaImagenes + producto.getNombre().toLowerCase().replace(" ", "_") + ".jpg";
            ImageIcon productoIcon = createImageIcon(imagePath);

            if (productoIcon != null) {
                JLabel imagenLabel = new JLabel(productoIcon);
                productoPanel.add(imagenLabel);
            } else {
                JLabel noImagenLabel = new JLabel("");
                productoPanel.add(noImagenLabel);
            }

            JLabel nombreLabel = new JLabel(producto.getNombre());
            productoPanel.add(nombreLabel);

            JTextField cantidadField = new JTextField(3);
            productoPanel.add(new JLabel("Cantidad:"));
            productoPanel.add(cantidadField);

            JButton agregarButton = new JButton("Agregar al Carrito");
            agregarButton.setBackground(new Color(144, 238, 144));
            agregarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int cantidad = Integer.parseInt(cantidadField.getText());
                        if (cantidad <= 0) {
                            JOptionPane.showMessageDialog(UsuarioFrame.this, "La cantidad debe ser mayor que cero.");
                            return;
                        }

                        String reciboTexto = "Producto: " + producto.getNombre() + "\n" +
                                "Precio: $" + producto.getPrecio() + "\n" +
                                "Cantidad: " + cantidad + "\n" +
                                "Total: $" + (producto.getPrecio() * cantidad) + "\n\n";

                        // Agregar al recibo
                        reciboTextPane.setText(reciboTextPane.getText() + reciboTexto);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(UsuarioFrame.this, "Ingrese una cantidad válida.");
                    }
                }
            });
            productoPanel.add(agregarButton);

            listaPanel.add(productoPanel);
        }

        listaPanel.revalidate();
        listaPanel.repaint();
    }

    private ImageIcon createImageIcon(String path) {
        File file = new File(path);
        if (file.exists()) {
            return new ImageIcon(path);
        } else {
            System.err.println(" " + path);
            return null;
        }
    }
    
}