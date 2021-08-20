package org.mama.funckydude;

import javax.swing.*;

public class StartSwing {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        MainForm form = new MainForm();
        form.setBounds(500, 200, 900, 700);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setVisible(true);
    }
}
