package views;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.UserHandler;
import core.View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class HomeView extends View implements ActionListener {

    private JPanel contentPanel;
    private JLabel lblTitle;
    private JButton btnCreateAccount, btnLogin, btnLoginAsEmployee;

    public HomeView() {
        super(200, 185);
    }

	@Override
	protected void onInitView() {
		contentPanel = new JPanel();
        
        lblTitle = new JLabel("Welcome to JrebFood!");

        btnCreateAccount = new JButton("Create Account");
        btnLogin = new JButton("Login as User");
        btnLoginAsEmployee = new JButton("Login as Employee");
	}

	@Override
	protected void onViewCreated() {
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        lblTitle.setAlignmentX(CENTER_ALIGNMENT);
        btnCreateAccount.setAlignmentX(CENTER_ALIGNMENT);
        btnLogin.setAlignmentX(CENTER_ALIGNMENT);
        btnLoginAsEmployee.setAlignmentX(CENTER_ALIGNMENT);

        contentPanel.add(lblTitle);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(btnCreateAccount);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(btnLogin);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(btnLoginAsEmployee);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        add(contentPanel);
	}

	@Override
	protected void initListener() {
        btnCreateAccount.addActionListener(this);
        btnLogin.addActionListener(this);
        btnLoginAsEmployee.addActionListener(this);
	}
    
    @Override
	public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnCreateAccount)) {
            dispose();
            UserHandler.getInstance().viewRegistrationForm();
        }
        else if (e.getSource().equals(btnLogin)) {
            dispose();
            UserHandler.getInstance().viewLoginForm();
        }
        else if (e.getSource().equals(btnLoginAsEmployee)) {
            dispose();
            UserHandler.getInstance().viewLoginEmployeeFrom();
        }
	}
}
