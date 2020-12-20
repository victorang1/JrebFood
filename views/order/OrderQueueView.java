package views.order;

import core.Model;
import core.View;
import models.order.Order;
import util.StringUtil;

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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderQueueView extends View implements ActionListener {

    private JTable table;
    private JScrollPane sp;
    private JPanel contentPanel, formPanel;
    private JButton btnCook;
    private JLabel lblTitle, lblOrderId, lblErrorMessage;
    private JTextField etOrderId;
    private Vector<Vector<String>> listQueue;
    private Vector<String> header, detail;

    public OrderQueueView() {
        super(500, 300);
    }

	@Override
	protected void onInitView() {
        contentPanel = new JPanel();
        formPanel = new JPanel();

        table = new JTable();
		sp = new JScrollPane(table);
        lblTitle = new JLabel("List of Order Queue:");
        lblOrderId = new JLabel("Order Id");
        lblErrorMessage = new JLabel("");

        etOrderId = new JTextField();

        btnCook = new JButton("Cook Selected Order");

        loadOrderQueue();
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
        formPanel.setAlignmentX(LEFT_ALIGNMENT);

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

        contentPanel.add(formPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(btnCook);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(lblErrorMessage);
        
        add(contentPanel);
	}

	@Override
	protected void initListener() {
        btnCook.addActionListener(this);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                String orderId = (String) table.getValueAt(row, 0);
                OrderHandler.getInstance().lastCheckoutOrderId = Integer.parseInt(orderId);
                OrderHandler.getInstance().viewDetails();
            }
        });
	}
    
    @Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnCook)) {
            lblErrorMessage.setVisible(true);
            String orderId = etOrderId.getText();
            if (StringUtil.isNullOrEmpty(orderId)) {
                lblErrorMessage.setText("Order Id cannot be empty!");
            }
            else {
                if (OrderHandler.getInstance().updateStatus(Integer.parseInt(orderId), OrderStatus.COOKED)) {
                    lblErrorMessage.setText("Cook Success");
                    loadOrderQueue();
                }
                else {
                    lblErrorMessage.setText("Cook Failed");
                }
            }
        }
    }
    
    private void loadOrderQueue() {
        header = new Vector<>();
        listQueue = new Vector<>();
        
        header.add("Order ID");
        header.add("Order Date");
        header.add("User ID");
		header.add("Address");
		header.add("Driver ID");
        header.add("Status");
		
		Vector<Model> orderQueues = OrderHandler.getInstance().viewOrderList(OrderStatus.ORDERED);
		
		for(Model model : orderQueues) {
			Order order = (Order) model;
			detail = new Vector<String>();
            
            detail.add(order.getOrderId().toString());
            detail.add(order.getDate().toString());
            detail.add(order.getUserId().toString());
			detail.add(order.getAddress());
			String driverId = order.getDriverId() == 0 ? "-" : order.getDriverId().toString();
			detail.add(driverId);
			detail.add(order.getStatus());
			
			listQueue.add(detail);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(listQueue, header);
		
        table.setModel(dtm);
    }
}
