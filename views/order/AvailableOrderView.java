package views.order;

import core.Model;
import core.View;
import models.Session;
import models.order.Order;

import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;

import constants.OrderStatus;
import controllers.OrderHandler;
import controllers.UserHandler;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class AvailableOrderView extends View implements ActionListener {

    private JTable table;
    private JScrollPane sp;
    private JPanel contentPanel;
    private JLabel lblTitle, lblErrorMessage;
    private Vector<Vector<String>> listAvailableOrder;
    private Vector<String> header, detail;

    public AvailableOrderView() {
        super(500, 300);
    }

	@Override
	protected void onInitView() {
		contentPanel = new JPanel();

        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
		sp = new JScrollPane(table);
        lblTitle = new JLabel("History Orders:");
        lblErrorMessage = new JLabel();
        loadAvailableOrder();
    }

	@Override
	protected void onViewCreated() {
        lblErrorMessage.setForeground(Color.RED);
        lblErrorMessage.setAlignmentX(CENTER_ALIGNMENT);
		lblErrorMessage.setVisible(false);
		sp.setAlignmentX(LEFT_ALIGNMENT);

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
		contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
        lblTitle.setAlignmentX(LEFT_ALIGNMENT);
        lblErrorMessage.setAlignmentX(LEFT_ALIGNMENT);
        contentPanel.add(backButton);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(lblTitle);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(sp);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(lblErrorMessage);
        
        add(contentPanel);
	}

	@Override
	protected void initListener() {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                String orderId = (String) table.getValueAt(row, 0);
                String userId = (String) table.getValueAt(row, 1);
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(AvailableOrderView.this,
                    "Are you want to take order with id " + orderId + "?",
                    "Confirmation Dialog",
                    dialogButton
                );
                if(dialogResult == 0) {
                    if (OrderHandler.getInstance().takeOrder(Integer.parseInt(orderId), Session.getInstance().getEmployeeUserId())) {
                        lblErrorMessage.setVisible(true);
                        lblErrorMessage.setText("Take Order Success");
                        UserHandler.getInstance().setSelectedOrder(new Order().setOrderId(Integer.parseInt(orderId)).setUserId(Integer.parseInt(userId)));
                        UserHandler.getInstance().viewUserInformation();
                    }
                }
            }
        });
	}
    
    @Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
    }
    
    private void loadAvailableOrder() {
        header = new Vector<>();
        listAvailableOrder = new Vector<>();
        
        header.add("Order ID");
        header.add("User ID");
        header.add("Order Date");
		header.add("Address");
		header.add("Driver ID");
        header.add("Status");
		
		Vector<Model> availableOrder = OrderHandler.getInstance().viewOrderList(OrderStatus.NOT_ACCEPTED);
		
		for(Model model : availableOrder) {
			Order order = (Order) model;
			detail = new Vector<String>();
            
            detail.add(order.getOrderId().toString());
            detail.add(order.getUserId().toString());
            detail.add(order.getDate().toString());
			detail.add(order.getAddress());
			String driverId = order.getDriverId() == 0 ? "-" : order.getDriverId().toString();
			detail.add(driverId);
			detail.add(order.getStatus());
			
			listAvailableOrder.add(detail);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(listAvailableOrder, header);
		
        table.setModel(dtm);
    }
}
