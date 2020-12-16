package views.order;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.View;

import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class DetailsView extends View implements ActionListener {

    private JPanel contentPanel, headerPanel;
    private JButton btnCancel;
    private JLabel lblOrderId, lblDate, lblAddress, lblDriver, lblStatus;
    private JLabel lblOrderIdValue, lblDateValue, lblAddressValue, lblDriverValue, lblStatusValue;
    
    public DetailsView() {
        super(300, 300);
    }

	@Override
	protected void onInitView() {
        contentPanel = new JPanel();
        headerPanel = new JPanel(new GridLayout(5, 2, 3, 0));

        lblOrderId = new JLabel("Order Id:");
        lblDate = new JLabel("Order Date:");
        lblAddress = new JLabel("Address:");
        lblDriver = new JLabel("Driver:");
        lblStatus = new JLabel("Status:");

        lblOrderIdValue = new JLabel();
        lblDateValue = new JLabel();
        lblAddressValue = new JLabel();
        lblDriverValue = new JLabel();
        lblStatusValue = new JLabel();
        
        btnCancel = new JButton("Cancel Order");
        loadOrderData();
	}

	@Override
	protected void onViewCreated() {
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        headerPanel.add(lblOrderId);
        headerPanel.add(lblOrderIdValue);
        headerPanel.add(lblDate);
        headerPanel.add(lblDateValue);
        headerPanel.add(lblAddress);
        headerPanel.add(lblAddressValue);
        headerPanel.add(lblDriver);
        headerPanel.add(lblDriverValue);
        headerPanel.add(lblStatus);
        headerPanel.add(lblStatusValue);

        contentPanel.add(headerPanel);
	}

	@Override
	protected void initListener() {
		btnCancel.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnCancel)) {

        }
    }

    private void loadOrderData() {

    }
}
