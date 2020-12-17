package views.user;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.OrderHandler;
import controllers.UserHandler;
import core.View;
import models.user.User;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserInformationView extends View implements ActionListener {

    private JPanel contentPanel, informationPanel;
    private JLabel lblTitle, lblName, lblAddress, lblPhoneNumber;
    private JLabel lblNameValue, lblAddressValue, lblPhoneNumberValue;
    private JButton btnProceed;
    
    public UserInformationView() {
		super(300, 200);
	}

	@Override
    protected void onInitView() {
        contentPanel = new JPanel();
        informationPanel = new JPanel(new GridLayout(0, 2, 5, 5));

        lblTitle = new JLabel("User Information");
        lblName = new JLabel("Name:");
        lblAddress = new JLabel("Address:");
        lblPhoneNumber = new JLabel("Phone Number:");

        lblNameValue = new JLabel();
        lblAddressValue = new JLabel();
        lblPhoneNumberValue = new JLabel();

        btnProceed = new JButton("Proceed");
        loadUserInformation();
    }

    @Override
    protected void onViewCreated() {
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        lblTitle.setAlignmentX(CENTER_ALIGNMENT);
        btnProceed.setAlignmentX(CENTER_ALIGNMENT);

        informationPanel.add(lblName);
        informationPanel.add(lblNameValue);

        informationPanel.add(lblAddress);
        informationPanel.add(lblAddressValue);

        informationPanel.add(lblPhoneNumber);
        informationPanel.add(lblPhoneNumberValue);

        contentPanel.add(lblTitle);
        contentPanel.add(informationPanel);
        contentPanel.add(btnProceed);

        add(contentPanel);
    }

    @Override
    protected void initListener() {
        btnProceed.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnProceed)) {
            OrderHandler.getInstance().lastCheckoutOrderId = UserHandler.getInstance().selectedOrder.getOrderId();
            OrderHandler.getInstance().viewDetails();
        }
    }

    private void loadUserInformation() {
        Integer selectedUserId = UserHandler.getInstance().selectedOrder.getUserId();
        User user = (User) UserHandler.getInstance().getOne(selectedUserId);
        lblNameValue.setText(user.getName());
        lblAddressValue.setText(user.getAddress());
        lblPhoneNumberValue.setText(user.getPhoneNumber());
    }
}
