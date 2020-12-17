package views.employee;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.OrderHandler;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import core.View;

public class DriverLandingView extends View implements ActionListener {

	private JPanel landingPanel, contentPanel;
    private JLabel lblTitle;
    private JButton btnTakeOrder, btnOrderToRestaurant, btnViewOrderHistory, btnViewOrderList;

    public DriverLandingView() {
        super(350, 200);
    }

	@Override
	protected void onInitView() {
		landingPanel = new JPanel();
        contentPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        lblTitle = new JLabel("Welcome to Landing Page!");
        
        btnTakeOrder = new JButton("Take Order");
        btnOrderToRestaurant = new JButton("Order to Restaurant");
        btnViewOrderHistory = new JButton("View Order History");
        btnViewOrderList = new JButton("View Order List");
	}

	@Override
	protected void onViewCreated() {
		landingPanel.setLayout(new BoxLayout(landingPanel, BoxLayout.PAGE_AXIS));
        landingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        contentPanel.add(btnTakeOrder);
		contentPanel.add(btnOrderToRestaurant);
		contentPanel.add(btnViewOrderList);
		contentPanel.add(btnViewOrderHistory);

        lblTitle.setAlignmentX(CENTER_ALIGNMENT);
        landingPanel.add(lblTitle);
        landingPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        landingPanel.add(contentPanel);
        add(landingPanel);
	}

	@Override
	protected void initListener() {
		btnTakeOrder.addActionListener(this);
		btnOrderToRestaurant.addActionListener(this);
		btnViewOrderList.addActionListener(this);
		btnViewOrderHistory.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnTakeOrder)) {
			OrderHandler.getInstance().viewAvailableOrder();
		}
		else if (e.getSource().equals(btnOrderToRestaurant)) {
			OrderHandler.getInstance().viewTakenOrder();
		}
		else if (e.getSource().equals(btnViewOrderList)) {
			OrderHandler.getInstance().viewTakenOrder();
		}
		else if (e.getSource().equals(btnViewOrderHistory)) {
			OrderHandler.getInstance().viewHistory();
		}
	}
}
