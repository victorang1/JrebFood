package views.food;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controllers.CartHandler;
import controllers.FoodHandler;
import core.Model;
import core.View;
import models.food.Food;
import views.cart.AddCartView;

public class MenuView extends View implements ActionListener {

    private JTable table;
    private JScrollPane sp;
    private JPanel contentPanel;
    private JLabel lblTitle;
    private JButton btnAddToCart;
    private Vector<Vector<String>> menu;
    private Vector<String> header, detail;

    public MenuView() {
        super(500, 300);
    }

    @Override
    protected void onInitView() {
        contentPanel = new JPanel();
        table = new JTable();
		sp = new JScrollPane(table);
        lblTitle = new JLabel("List of Menu:");

        btnAddToCart = new JButton("Add To Cart");
        loadMenu();
    }

    @Override
    protected void onViewCreated() {
        sp.setAlignmentX(LEFT_ALIGNMENT);

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        lblTitle.setAlignmentX(LEFT_ALIGNMENT);
        contentPanel.add(backButton);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(lblTitle);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(sp);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(btnAddToCart);
        
        add(contentPanel);
    }

    @Override
    protected void initListener() {
        btnAddToCart.addActionListener(this);
    }

    @Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAddToCart)) {
            CartHandler.getInstance().viewAddCart();
        }
	}

    public void loadMenu() {
        header = new Vector<>();
        menu = new Vector<>();
        
		header.add("Food Name");
		header.add("Food Price");
        header.add("Food Description");
        header.add("Food Status");
		
		Vector<Model> listMenu = FoodHandler.getInstance().viewAll();
		
		for(Model model : listMenu) {
			Food food = (Food) model;
			detail = new Vector<>();
			
			detail.add(food.getName());
			detail.add(food.getPrice().toString());
			detail.add(food.getDescription());
			detail.add(food.getStatus());
			
			menu.add(detail);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(menu, header);
		
		table.setModel(dtm);
	}
}
