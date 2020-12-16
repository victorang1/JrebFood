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
import core.Model;
import core.View;
import models.orderdetail.OrderDetail;

import java.awt.Dimension;

public class DetailsView extends View {

    private JTable table;
    private JScrollPane sp;
    private JPanel contentPanel;
    private JLabel lblTitle;
    private Vector<Vector<String>> listDetail;
    private Vector<String> header, detail;
    
    public DetailsView() {
        super(500, 300);
    }

	@Override
	protected void onInitView() {
        contentPanel = new JPanel();

        table = new JTable();
		sp = new JScrollPane(table);
		lblTitle = new JLabel("History Orders:");
		loadDetail();
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
		
    }

    private void loadDetail() {
        header = new Vector<>();
        listDetail = new Vector<>();
        
        header.add("Food Name");
        header.add("Food Description");
        header.add("Food Price");
		header.add("Quantity");
        
        Integer orderId = OrderHandler.getInstance().lastCheckoutOrderId;
		Vector<Model> details = OrderHandler.getInstance().viewDetailById(orderId);
		
		for(Model model : details) {
			OrderDetail orderDetail = (OrderDetail) model;
			detail = new Vector<String>();
            
            detail.add(orderDetail.getFood().getName());
            detail.add(orderDetail.getFood().getDescription());
            detail.add(orderDetail.getFood().getPrice().toString());
			detail.add(orderDetail.getQty().toString());
			
			listDetail.add(detail);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(listDetail, header);
		
        table.setModel(dtm);
    }
}
