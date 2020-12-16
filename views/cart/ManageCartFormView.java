package views.cart;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.CartHandler;
import controllers.OrderHandler;
import core.Model;
import core.View;
import models.Session;
import models.cart.Cart;
import util.CollectionUtil;
import util.StringUtil;

public class ManageCartFormView extends View implements ActionListener {

    private JTable table;
    private JScrollPane sp;
    private JPanel contentPanel, formPanel, actionPanel;
    private JButton btnDelete, btnOrder;
    private JLabel lblTitle, lblCartId, lblErrorMessage;
    private JTextField etCartId;
    private Vector<Vector<String>> listCart;
    private Vector<String> header, detail;
    private Integer currentUserId;

    public ManageCartFormView() {
        super(500, 300);
    }
    
    @Override
    protected void onInitView() {
        currentUserId = Session.getInstance().getUserId();
        contentPanel = new JPanel();
        formPanel = new JPanel();
        actionPanel = new JPanel();

        table = new JTable();
		sp = new JScrollPane(table);
        lblTitle = new JLabel("List of Cart:");
        lblCartId = new JLabel("Food Id");
        lblErrorMessage = new JLabel("");

        etCartId = new JTextField();

        btnDelete = new JButton("Delete from Cart");
        btnOrder = new JButton("Checkout");

        loadCart();
    }

    @Override
    protected void onViewCreated() {
        lblErrorMessage.setVisible(false);
        lblErrorMessage.setForeground(Color.RED);
        sp.setAlignmentX(LEFT_ALIGNMENT);

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.LINE_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.LINE_AXIS));
        actionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(lblCartId);
        formPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        formPanel.add(etCartId);

        actionPanel.add(btnDelete);
        actionPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        actionPanel.add(btnOrder);
        
        lblTitle.setAlignmentX(LEFT_ALIGNMENT);
        contentPanel.add(backButton);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(lblTitle);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(sp);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.setAlignmentX(LEFT_ALIGNMENT);
        actionPanel.setAlignmentX(LEFT_ALIGNMENT);
        contentPanel.add(formPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(actionPanel);
        actionPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(lblErrorMessage);
        
        add(contentPanel);
    }
    
    @Override
    protected void initListener() {
        btnDelete.addActionListener(this);
        btnOrder.addActionListener(this);
    }

    public void loadCart() {
        header = new Vector<>();
        listCart = new Vector<>();
        
        header.add("Cart ID");
        header.add("Food ID");
		header.add("Food Name");
		header.add("Food Price");
        header.add("Food Description");
        header.add("Quantity");
		
		Vector<Model> carts = CartHandler.getInstance().viewAll(currentUserId);
		
		for(Model model : carts) {
			Cart cart = (Cart) model;
			detail = new Vector<String>();
            
            detail.add(cart.getCartId().toString());
            detail.add(cart.getFood().getFoodId().toString());
			detail.add(cart.getFood().getName());
			detail.add(cart.getFood().getPrice().toString());
			detail.add(cart.getFood().getDescription());
			detail.add(cart.getQty().toString());
			
			listCart.add(detail);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(listCart, header);
		
        table.setModel(dtm);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnDelete)) {
            lblErrorMessage.setVisible(true);
            String foodId = etCartId.getText();
            if (StringUtil.isNullOrEmpty(foodId)) {
                lblErrorMessage.setText("Food Id cannot be empty!");
            }
            else {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to remove cart with food id =" + foodId,
                    "Confirmation Dialog",
                    dialogButton
                );
                if(dialogResult == 0) {
                    removeFromCart(foodId);
                }
            }
        }
        else if (e.getSource().equals(btnOrder)) {
            lblErrorMessage.setVisible(true);
            if (!CollectionUtil.isNullOrEmpty(listCart)) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(this,
                    "Are you want to checkout?",
                    "Confirmation Dialog",
                    dialogButton
                );
                if(dialogResult == 0) {
                    if (OrderHandler.getInstance().addOrder(Session.getInstance().getUser())) {
                        lblErrorMessage.setText("Order Success!");
                    }
                    else {
                        lblErrorMessage.setText("Something wrong occured while checkout");
                    }
                }
            }
            else {
                lblErrorMessage.setText("Cannot order. Cart is empty!");
            }
        }
    }

    private void removeFromCart(String foodId) {
        if(!CartHandler.getInstance().removeFromCart(currentUserId, Integer.parseInt(foodId))) {
            lblErrorMessage.setText("Remove from cart failed!");
        }
        else {
            lblErrorMessage.setText("Success remove item from cart");
            loadCart();
        }
    }
}
