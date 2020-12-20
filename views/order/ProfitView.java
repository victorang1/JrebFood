package views.order;

import java.awt.Dimension;
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

import controllers.OrderHandler;
import core.Model;
import core.View;
import models.Session;
import models.order.Order;
import util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ProfitView extends View implements ActionListener {
	
	private JTable table;
    private JScrollPane sp;
    private JPanel contentPanel, formPanel;
	private JLabel lblTitle, lblFitlerDriverId, lblErrorMessage;
	private JTextField etDriverId;
	private JButton btnFilter;
    private Vector<Vector<String>> listHistory;
    private Vector<String> header, detail;

    public ProfitView() {
        super(500, 300);
    }

	@Override
	protected void onInitView() {
		contentPanel = new JPanel();
		formPanel = new JPanel();

        table = new JTable();
		sp = new JScrollPane(table);
		lblTitle = new JLabel("History Orders:");
		lblFitlerDriverId = new JLabel("Filter By Driver Id: ");
		lblErrorMessage = new JLabel();
		etDriverId = new JTextField();
		btnFilter = new JButton("Filter By Driver Id");
		
		loadHistory(-1);
	}

	@Override
	protected void onViewCreated() {
		lblErrorMessage.setForeground(Color.RED);
        lblErrorMessage.setAlignmentX(CENTER_ALIGNMENT);
		lblErrorMessage.setVisible(false);
		
		sp.setAlignmentX(LEFT_ALIGNMENT);

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
		contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.LINE_AXIS));
		formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		formPanel.add(lblFitlerDriverId);
        formPanel.add(Box.createRigidArea(new Dimension(15, 0)));
		formPanel.add(etDriverId);

		lblTitle.setAlignmentX(LEFT_ALIGNMENT);
        contentPanel.add(backButton);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(lblTitle);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(sp);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		contentPanel.add(formPanel);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		contentPanel.add(btnFilter);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		contentPanel.add(lblErrorMessage);
        
        add(contentPanel);
	}

	@Override
	protected void initListener() {
		btnFilter.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnFilter)) {
			lblErrorMessage.setVisible(true);
			String driverId = etDriverId.getText();
			if (StringUtil.isNullOrEmpty(driverId)) {
				driverId = "-1";
			}
			loadHistory(Integer.parseInt(driverId));
		}
	}

	private void loadHistory(Integer id) {
		header = new Vector<>();
        listHistory = new Vector<>();
        
        header.add("Order ID");
        header.add("Order Date");
		header.add("Address");
		header.add("Driver ID");
		header.add("Status");
		
		Vector<Model> histories = OrderHandler.getInstance().viewAllHistory(id);
		
		for(Model model : histories) {
			Order order = (Order) model;
			detail = new Vector<String>();
            
            detail.add(order.getOrderId().toString());
            detail.add(order.getDate().toString());
			detail.add(order.getAddress());
			String driverId = order.getDriverId() == 0 ? "-" : order.getDriverId().toString();
			detail.add(driverId);
			detail.add(order.getStatus());
			
			listHistory.add(detail);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(listHistory, header);
		
        table.setModel(dtm);
	}
}
