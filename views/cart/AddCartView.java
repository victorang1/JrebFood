package views.cart;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.CartHandler;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Color;

import core.View;
import models.Session;

public class AddCartView extends View implements ActionListener {

    private JPanel formPanel, contentPanel;
    private JLabel lblFoodId, lblQty, lblErrorMessage;
    private JTextField etFoodId, etQty;
    private JButton btnAdd;

    public AddCartView() {
        super(300, 180);
    }
    
    @Override
    protected void onInitView() {
        formPanel = new JPanel();
        contentPanel = new JPanel(new GridLayout(2, 2, 0, 8));

        lblFoodId = new JLabel("Food Id");
        lblQty = new JLabel("Quantity");
        lblErrorMessage = new JLabel("");

        etFoodId = new JTextField();
        etQty = new JTextField();

        btnAdd = new JButton("Add to Cart");
    }

    @Override
    protected void onViewCreated() {
        lblErrorMessage.setVisible(false);
        lblErrorMessage.setForeground(Color.RED);

        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.PAGE_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        contentPanel.setAlignmentX(LEFT_ALIGNMENT);

        contentPanel.add(lblFoodId);
        contentPanel.add(etFoodId);

        contentPanel.add(lblQty);
        contentPanel.add(etQty);
        
        formPanel.add(backButton);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(contentPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(btnAdd);
        formPanel.add(lblErrorMessage);

        add(formPanel);
    }

    @Override
    protected void initListener() {
        btnAdd.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAdd)) {
            lblErrorMessage.setVisible(true);
            Integer userId = Session.getInstance().getUserId();
            Integer foodId = Integer.parseInt(etFoodId.getText());
            Integer qty = Integer.parseInt(etQty.getText());
            if (CartHandler.getInstance().addToCart(userId, foodId, qty)) {
                lblErrorMessage.setText("Success add to cart");
            }
            else {
                String errMessage = CartHandler.getInstance().getErrorMessage();
                lblErrorMessage.setText(errMessage);
            }
            reset();
        }
    }
    
    private void reset() {
        etFoodId.setText("");
        etQty.setText("");
    }
}
