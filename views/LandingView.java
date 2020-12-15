package views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.CartHandler;
import controllers.FoodHandler;
import core.View;

public class LandingView extends View implements ActionListener {

    private JPanel landingPanel, contentPanel;
    private JLabel lblTitle;
    private JButton btnViewCart, btnViewMenu;

    public LandingView() {
        super(300, 180);
    }

    @Override
    protected void onInitView() {
        landingPanel = new JPanel();
        contentPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        lblTitle = new JLabel("Welcome to Landing Page!");
        
        btnViewCart = new JButton("View Cart");
        btnViewMenu = new JButton("View Menu");
    }

    @Override
    protected void onViewCreated() {
        landingPanel.setLayout(new BoxLayout(landingPanel, BoxLayout.PAGE_AXIS));
        landingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        contentPanel.add(btnViewCart);
        contentPanel.add(btnViewMenu);
        contentPanel.add(new JButton("Test"));

        landingPanel.add(lblTitle);
        landingPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        landingPanel.add(contentPanel);
        add(landingPanel);
    }

    @Override
    protected void initListener() {
        btnViewCart.addActionListener(this);
        btnViewMenu.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnViewMenu)) {
            FoodHandler.getInstance().viewMenu();
        }
        else if (e.getSource().equals(btnViewCart)) {
            CartHandler.getInstance().viewManageCartForm();
        }
	}
}
