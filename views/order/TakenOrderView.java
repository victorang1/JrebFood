package views.order;

import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import constants.OrderStatus;
import controllers.OrderHandler;
import core.Model;
import core.View;
import models.Session;
import models.order.Order;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class TakenOrderView extends View implements ActionListener {

    private JTable table;
    private JScrollPane sp;
    private JPanel contentPanel, formPanel;
    private JButton btnOrder;
    private JLabel lblTitle, lblOrderId, lblErrorMessage;
    private JTextField etOrderId;
    private Vector<Vector<String>> listTakenOrder;
    private Vector<String> header, detail;

    public TakenOrderView() {
        super(500, 500);
    }

	@Override
	protected void onInitView() {
        contentPanel = new JPanel();
        formPanel = new JPanel();

        table = new JTable();
		sp = new JScrollPane(table);
        lblTitle = new JLabel("List of Taken Order:");
        lblOrderId = new JLabel("Order Id");
        lblErrorMessage = new JLabel("");

        etOrderId = new JTextField();

        btnOrder = new JButton("Order Food");

        loadTakenOrder();
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
        
        lblTitle.setAlignmentX(LEFT_ALIGNMENT);
        contentPanel.add(backButton);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(lblTitle);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(sp);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.setAlignmentX(LEFT_ALIGNMENT);
        contentPanel.add(formPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(btnOrder);
        contentPanel.add(lblErrorMessage);
        
        add(contentPanel);
	}

	@Override
	protected void initListener() {
		btnOrder.addActionListener(this);
	}
    
    @Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnOrder)) {
            lblErrorMessage.setVisible(true);
            Integer orderId = Integer.parseInt(etOrderId.getText());
            if (OrderHandler.getInstance().updateStatus(orderId, OrderStatus.ORDERED)) {
                lblErrorMessage.setText("Order To Restaurant Success");
            }
            else {
                String errMsg = OrderHandler.getInstance().getErrorMessage();
                lblErrorMessage.setText(errMsg);
            }
        }
    }
    
    private void loadTakenOrder() {
        header = new Vector<>();
        listTakenOrder = new Vector<>();
        
        header.add("Order ID");
        header.add("Order Date");
        header.add("User ID");
		header.add("Address");
		header.add("Driver ID");
        header.add("Status");
		
		Vector<Model> takenOrders = OrderHandler.getInstance().viewOrderList(Session.getInstance().getEmployeeUserId());
		
		for(Model model : takenOrders) {
			Order order = (Order) model;
			detail = new Vector<String>();
            
            detail.add(order.getOrderId().toString());
            detail.add(order.getDate().toString());
			detail.add(order.getUserId().toString());
			detail.add(order.getAddress());
			String driverId = order.getDriverId() == 0 ? "-" : order.getDriverId().toString();
			detail.add(driverId);
			detail.add(order.getStatus());
			
			listTakenOrder.add(detail);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(listTakenOrder, header);
		
        table.setModel(dtm);
    }
}
