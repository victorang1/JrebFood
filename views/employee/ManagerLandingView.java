package views.employee;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.EmployeeHandler;
import controllers.OrderHandler;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import core.View;

public class ManagerLandingView extends View implements ActionListener {

    private JPanel landingPanel, contentPanel;
    private JLabel lblTitle;
    private JButton btnViewFinancialSummary, viewManageEmployeeForm;

    public ManagerLandingView() {
        super(250, 150);
    }

    @Override
    protected void onInitView() {
        landingPanel = new JPanel();
        contentPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        lblTitle = new JLabel("Welcome to Landing Page!");
        
        btnViewFinancialSummary = new JButton("View Financial Summary");
        viewManageEmployeeForm = new JButton("View Manage Employee Form");
    }

    @Override
    protected void onViewCreated() {
        landingPanel.setLayout(new BoxLayout(landingPanel, BoxLayout.PAGE_AXIS));
        landingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        contentPanel.add(btnViewFinancialSummary);
        contentPanel.add(viewManageEmployeeForm);

        lblTitle.setAlignmentX(CENTER_ALIGNMENT);
        landingPanel.add(lblTitle);
        landingPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        landingPanel.add(contentPanel);
        add(landingPanel);
    }

    @Override
    protected void initListener() {
        btnViewFinancialSummary.addActionListener(this);
        viewManageEmployeeForm.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnViewFinancialSummary)) {
            OrderHandler.getInstance().viewProfit();
        }
        else if (e.getSource().equals(viewManageEmployeeForm)) {
            EmployeeHandler.getInstance().viewManageEmployeeForm();
        }
	}
}
