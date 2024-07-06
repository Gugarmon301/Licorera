package com.mycompany.vista;

import com.mycompany.modelo.Producto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginFrame extends JFrame {
    private ArrayList<Producto> productos;

    public LoginFrame(ArrayList<Producto> productos) {
        this.productos = productos;

        setTitle("Login");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(44, 62, 80));  // Fondo oscuro

        // Agregar imagen de fondo
        JLabel backgroundLabel = new JLabel(new ImageIcon("C:/Users/DAVID/Desktop/carritodecompra/imagenes/login.jpg"));
        backgroundLabel.setLayout(new BorderLayout());
        mainPanel.add(backgroundLabel, BorderLayout.CENTER);

        // Panel para el formulario de login
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);  // Hacer transparente el fondo
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(new EmptyBorder(20, 40, 20, 40));  // Añadir margen

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setForeground(Color.BLACK);  // Texto en negro
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JTextField userField = new JTextField();
        userField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        userField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setForeground(Color.BLACK);  // Texto en negro
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JPasswordField passField = new JPasswordField();
        passField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        passField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(52, 152, 219));  // Azul claro
        loginButton.setForeground(Color.WHITE);  // Texto en blanco
        loginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulación de lógica de autenticación
                String username = userField.getText();
                char[] password = passField.getPassword();
                
                // Aquí puedes implementar la lógica real de autenticación
                if (esUsuarioAdministrador(username, password)) {
                    new AdminFrame(productos); // Abre la ventana de administrador
                } else if (esUsuarioNormal(username, password)) {
                    new UsuarioFrame(productos); // Abre la ventana de usuario normal
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Credenciales incorrectas. Inténtelo de nuevo.");
                    // Limpia los campos de usuario y contraseña
                    userField.setText("");
                    passField.setText("");
                    userField.requestFocus();
                }
                dispose(); // Cierra el login
            }
        });

        // Centrar y agregar espaciado a los componentes
        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(userLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(userField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(passLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(passField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        formPanel.add(loginButton);

        backgroundLabel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(backgroundLabel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private boolean esUsuarioAdministrador(String username, char[] password) {
        // Lógica para determinar si el usuario es administrador
        // Por ejemplo, verificar credenciales en una base de datos o archivo de configuración
        return username.equals("admin") && String.valueOf(password).equals("admin"); // Ejemplo básico
    }

    private boolean esUsuarioNormal(String username, char[] password) {
        // Lógica para determinar si el usuario es un usuario normal
        // Por ejemplo, verificar credenciales en una base de datos o archivo de configuración
        return username.equals("usuario") && String.valueOf(password).equals("usuario"); // Ejemplo básico
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArrayList<Producto> productos = new ArrayList<>();
            // Agrega algunos productos de ejemplo
            productos.add(new Producto("Aguardiente Antioqueño", 30.0, 50));
            productos.add(new Producto("Ron Medellín", 40.0, 30));
            productos.add(new Producto("Whisky Old Parr", 100.0, 20));
            productos.add(new Producto("Vodka Absolut", 50.0, 25));
            productos.add(new Producto("Tequila José Cuervo", 60.0, 15));

            new LoginFrame(productos); // Inicia la aplicación mostrando el login
        });
    }
}
