package views.order;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.OrderHandler;

import java.awt.Dimension;

import core.Model;
import core.View;
import models.Session;
import models.order.Order;

public class HistoryView extends View {

	private JTable table;
    private JScrollPane sp;
    private JPanel contentPanel;
    private JLabel lblTitle;
    private Vector<Vector<String>> listHistory;
    private Vector<String> header, detail;

    public HistoryView() {
        super(500, 300);
    }

	@Override
	protected void onInitView() {
        contentPanel = new JPanel();

        table = new JTable();
		sp = new JScrollPane(table);
		lblTitle = new JLabel("History Orders:");
		loadHistory();
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
        
        add(contentPanel);
	}

	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		
	}
	
	private void loadHistory() {
		header = new Vector<>();
        listHistory = new Vector<>();
        
        header.add("Order ID");
        header.add("Order Date");
		header.add("Address");
		header.add("Driver ID");
        header.add("Status");
		
		Vector<Model> histories = OrderHandler.getInstance().viewAllHistory(Session.getInstance().getUserId());
		
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
