package com.scanner;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.scanner.ui.ScannerFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        System.setProperty("flatlaf.uiScale.allowScaleDown", "true");

        try {
            FlatRobotoFont.install();
            FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
            FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
            FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);

            JFrame.setDefaultLookAndFeelDecorated(true);
            System.setProperty("flatlaf.useWindowDecorations", "true"); 
            System.setProperty("flatlaf.menuBarEmbedded", "true");

            FlatDarkLaf.setup();
            
            UIManager.put("Component.arc", 16);
            UIManager.put("Component.arrowType", "chevron");
            UIManager.put("Button.arc", 16);
            UIManager.put("TextComponent.arc", 16);
            
            UIManager.put("Panel.background", new Color(18, 18, 24));
            UIManager.put("TextField.background", new Color(30, 30, 38));
            UIManager.put("Table.background", new Color(18, 18, 24));
            UIManager.put("Table.gridColor", new Color(40, 40, 50));
            UIManager.put("TableHeader.background", new Color(18, 18, 24));
            UIManager.put("TableHeader.separatorColor", new Color(40, 40, 50));
            UIManager.put("TableHeader.bottomSeparatorColor", new Color(40, 40, 50));

        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            ScannerFrame frame = new ScannerFrame();
            frame.setVisible(true);
        });
    }
}