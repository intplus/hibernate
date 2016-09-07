package view;

import model.Product;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Map;

public class ShopUI {
    private JFrame f;

    public ShopUI(Map order) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        f = new JFrame("Best Shop2");
        f.setMinimumSize(new Dimension(1200, 600));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setLayout(new BorderLayout());

        ShopPanel shopPanel = new ShopPanel(order);
        f.add(shopPanel, BorderLayout.CENTER);
        shopPanel.init();


        f.setVisible(true);
        f.pack();
    }

}
