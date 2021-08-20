package org.mama.secondProgect;

import org.mama.secondProgect.SwingForms.TraderForm;

import javax.swing.*;

public class Starter {
    public static void main(String[] args) {
        TraderForm form = new TraderForm();
        form.setBounds(500, 200, 900, 400);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setVisible(true);
    }
}