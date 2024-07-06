package com.mycompany.vista;

import com.mycompany.modelo.Producto;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    public static ArrayList<Producto> productos;

    public MainFrame() {
        productos = new ArrayList<>();
        productos.add(new Producto("Aguardiente Antioqueño", 30.0, 50));
        productos.add(new Producto("Ron Medellín", 40.0, 30));
        productos.add(new Producto("Whisky Old Parr", 100.0, 20));
        productos.add(new Producto("Vodka Absolut", 50.0, 25));
        productos.add(new Producto("Tequila José Cuervo", 60.0, 15));

        new LoginFrame(productos);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}

