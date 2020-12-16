package views.user;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.UserHandler;
import core.View;

public class RegistrationFormView extends View implements ActionListener {

    private JPanel formPanel, contentPanel, headerPanel;
    private JLabel lblFormTitle, lblErrorMessage;
    private JLabel lblName, lblAddress, lblEmail, lblPhoneNumber, lblPassword;
    private JTextField etName, etAddress, etEmail, etPhoneNumber, etPassword;
    private JButton btnCreate;

    public RegistrationFormView() {
        super(300, 300);
	}

	@Override
    protected void onInitView() {
        formPanel = new JPanel();
        headerPanel = new JPanel();
        contentPanel = new JPanel(new GridLayout(5, 2, 0, 5));

        lblName = new JLabel("Name: ");
        lblAddress = new JLabel("Address: ");
        lblEmail = new JLabel("Email: ");
        lblPhoneNumber = new JLabel("Phone Number: ");
        lblPassword = new JLabel("Password: ");
        lblFormTitle = new JLabel("Registration Form");
        lblErrorMessage = new JLabel("Invalid!");
        
        etName = new JTextField();
        etAddress = new JTextField();
        etEmail = new JTextField();
        etPhoneNumber = new JTextField();
        etPassword = new JTextField();

        btnCreate = new JButton("Register");
    }

    @Override
    protected void onViewCreated() {
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.PAGE_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.LINE_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        contentPanel.add(lblName);
        contentPanel.add(etName);

        contentPanel.add(lblAddress);
        contentPanel.add(etAddress);

        contentPanel.add(lblEmail);
        contentPanel.add(etEmail);

        contentPanel.add(lblPhoneNumber);
        contentPanel.add(etPhoneNumber);

        contentPanel.add(lblPassword);
        contentPanel.add(etPassword);
        
        headerPanel.add(backButton);
        headerPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        headerPanel.add(lblFormTitle);

        formPanel.add(headerPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(contentPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        btnCreate.setAlignmentX(CENTER_ALIGNMENT);
        formPanel.add(btnCreate);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        lblErrorMessage.setForeground(Color.RED);
        lblErrorMessage.setAlignmentX(CENTER_ALIGNMENT);
        lblErrorMessage.setVisible(false);
        formPanel.add(lblErrorMessage);

        add(formPanel);
    }

    @Override
    protected void initListener() {
        btnCreate.addActionListener(this);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnCreate)) {
            register();
        }
        else if (e.getSource().equals(backButton)) {
            UserHandler.getInstance().homeView();
            dispose();
        }
    }

    private void register() {
        String name = etName.getText();
        String address = etAddress.getText();
        String email = etEmail.getText();
        String phoneNumber = etPhoneNumber.getText();
        String password = etPassword.getText();
        UserHandler userHandler = UserHandler.getInstance();
        if (userHandler.validateFields(email, phoneNumber)) {
            lblErrorMessage.setText("All field must be input!");
        }
        else if (userHandler.validateUnique(email, phoneNumber)) {
            lblErrorMessage.setText("Email/Phone Number has been used!");
        }
        else {
            if (userHandler.createAccount(name, address, email, phoneNumber, password)) {
                lblErrorMessage.setText("Register Success");
            }
            else {
                lblErrorMessage.setText("Something went wrong");
            }
        }
        lblErrorMessage.setVisible(true);
    }
}
