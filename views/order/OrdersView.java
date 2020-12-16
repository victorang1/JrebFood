package views.order;

import core.View;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controllers.OrderHandler;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class OrdersView extends View implements ActionListener {

    private JPanel contentPanel;
    private JButton btnViewActiveOrder, btnViewOrderHistory;

    public OrdersView() {
        super(330, 100);
    }

	@Override
	protected void onInitView() {
        contentPanel = new JPanel();
        
        btnViewActiveOrder = new JButton("View Active Order");
        btnViewOrderHistory = new JButton("View Order History");
	}

	@Override
	protected void onViewCreated() {
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.LINE_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        contentPanel.add(btnViewActiveOrder);
        contentPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        contentPanel.add(btnViewOrderHistory);

        add(contentPanel);
	}

	@Override
	protected void initListener() {
		btnViewActiveOrder.addActionListener(this);
		btnViewOrderHistory.addActionListener(this);
    }
    
    @Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnViewActiveOrder)) {
            OrderHandler.getInstance().viewUserOrders();
        }
        else if (e.getSource().equals(btnViewOrderHistory)) {
            OrderHandler.getInstance().viewHistory();
        }
	}
}
