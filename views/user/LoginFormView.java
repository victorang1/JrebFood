package views.user;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.FoodHandler;
import controllers.UserHandler;
import core.View;

public class LoginFormView extends View implements ActionListener {

    private JPanel formPanel, contentPanel;
    private JLabel lblFormTitle, lblErrorMessage;
    private JLabel lblEmail, lblPassword;
    private JTextField etEmail, etPassword;
    private JButton btnLogin;

    public LoginFormView() {
        super(250, 220);
	}

	@Override
    protected void onInitView() {
        formPanel = new JPanel();
        contentPanel = new JPanel(new GridLayout(2, 2, 0, 8));

        lblEmail = new JLabel("Email: ");
        lblPassword = new JLabel("Password: ");
        lblFormTitle = new JLabel("Login Form");
        lblErrorMessage = new JLabel();
        
        etEmail = new JTextField();
        etPassword = new JTextField();

        btnLogin= new JButton("Login");
    }

    @Override
    protected void onViewCreated() {
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        contentPanel.add(lblEmail);
        contentPanel.add(etEmail);

        contentPanel.add(lblPassword);
        contentPanel.add(etPassword);

        lblFormTitle.setAlignmentX(CENTER_ALIGNMENT);
        formPanel.add(backButton);
        formPanel.add(lblFormTitle);

        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(contentPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        btnLogin.setAlignmentX(CENTER_ALIGNMENT);
        formPanel.add(btnLogin);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        lblErrorMessage.setForeground(Color.RED);
        lblErrorMessage.setAlignmentX(CENTER_ALIGNMENT);
        lblErrorMessage.setVisible(false);
        formPanel.add(lblErrorMessage);

        add(formPanel);
    }

    @Override
    protected void initListener() {
        btnLogin.addActionListener(this);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnLogin)) {
            login();
        }
        else if (e.getSource().equals(backButton)) {
            dispose();
            UserHandler.getInstance().viewRegistrationForm();
        }
    }

    private void login() {
        String email = etEmail.getText();
        String password = etPassword.getText();
        UserHandler userHandler = UserHandler.getInstance();
        if (userHandler.validateFields(email, password)) {
            lblErrorMessage.setText("All field must be input!");
        }
        else {
            if (userHandler.login(email, password)) {
                lblErrorMessage.setText("Login Success, redirecting to menu page...");
                try {
                    Thread.sleep(1000);
                    dispose();
                    UserHandler.getInstance().viewLandingView();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                lblErrorMessage.setText("Incorrect username/password");
            }
        }
        lblErrorMessage.setVisible(true);
    }
}
