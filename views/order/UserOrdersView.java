package views.order;

import java.awt.Dimension;
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

import controllers.OrderHandler;
import core.Model;
import core.View;
import models.order.Order;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserOrdersView extends View implements ActionListener {

    private JTable table;
    private JScrollPane sp;
    private JPanel contentPanel, formPanel;
    private JLabel lblTitle, lblOrderId, lblErrorMessage;
    private JTextField etOrderId;
    private JButton btnCancel;
    private Vector<Vector<String>> listOrders;
    private Vector<String> header, detail;

    public UserOrdersView() {
        super(500, 300);
    }
    
	@Override
	protected void onInitView() {
		contentPanel = new JPanel();
        formPanel = new JPanel();

        table = new JTable();
		sp = new JScrollPane(table);
        lblTitle = new JLabel("User's Orders:");
        lblOrderId = new JLabel("Order Id");
        lblErrorMessage = new JLabel("");

        etOrderId = new JTextField();

        btnCancel = new JButton("Cancel Order");
        
		loadOrders();
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

        formPanel.add(lblOrderId);
        formPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        formPanel.add(etOrderId);
        formPanel.setAlignmentX(LEFT_ALIGNMENT);
		
        lblTitle.setAlignmentX(LEFT_ALIGNMENT);
        contentPanel.add(backButton);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(lblTitle);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(sp);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(formPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(btnCancel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(lblErrorMessage);

        add(contentPanel);
	}
	@Override
	protected void initListener() {
		btnCancel.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnCancel)) {
            lblErrorMessage.setVisible(true);
            String orderIdToDelete = etOrderId.getText();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this,
                "Are you want to cancel order id " + orderIdToDelete + " ?",
                "Confirmation Dialog",
                dialogButton
            );
            if(dialogResult == 0) {
                OrderHandler.getInstance().removeOrder(Integer.parseInt(orderIdToDelete));
                lblErrorMessage.setText(OrderHandler.getInstance().getErrorMessage());
                loadOrders();
            }
        }
    }

    private void loadOrders() {
        header = new Vector<>();
        listOrders = new Vector<>();
        
        header.add("Order ID");
        header.add("Order Date");
		header.add("Address");
		header.add("Driver ID");
        header.add("Status");
		
		Vector<Model> orders = OrderHandler.getInstance().getUserOrders();
		
		for(Model model : orders) {
			Order order = (Order) model;
			detail = new Vector<String>();
            
            detail.add(order.getOrderId().toString());
            detail.add(order.getDate().toString());
			detail.add(order.getAddress());
			String driverId = order.getDriverId() == 0 ? "-" : order.getDriverId().toString();
			detail.add(driverId);
			detail.add(order.getStatus());
			
			listOrders.add(detail);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(listOrders, header);
		
        table.setModel(dtm);
    }
    
}
