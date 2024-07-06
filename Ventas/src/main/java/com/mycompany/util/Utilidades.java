package com.mycompany.util;

import javax.swing.*;
import java.awt.*;

public class Utilidades {
    public static void centrarVentana(JFrame frame) {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = frame.getSize();
        frame.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
    }
}

