package views.employee;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.EmployeeHandler;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Color;
import core.View;
import util.DateUtil;

import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class ManageEmployeeFormView extends View implements ActionListener {

	private JPanel landingPanel, hirePanel, firePanel, hireFormPanel, fireFormPanel;
    private JLabel lblTitle, lblName, lblRole, lblEmail, lblPassword, lblDob, lblEmployeeId, lblErrorMessage;
	private JButton btnHireEmployee, btnFireEmployee;
    private JTextField etName, etRole, etEmail, etPassword, etDob, etEmployeeId;

    public ManageEmployeeFormView() {
        super(400, 500);
    }

	@Override
	protected void onInitView() {
		landingPanel = new JPanel();
		hirePanel = new JPanel();
		firePanel = new JPanel();
		hireFormPanel = new JPanel(new GridLayout(5, 2, 0, 5));
		fireFormPanel = new JPanel(new GridLayout(0, 2, 0, 5));
		lblTitle = new JLabel("Manage Employee Form Page!");
		
		lblName = new JLabel("Name: ");
        lblRole = new JLabel("Role Id/Name: ");
        lblEmail = new JLabel("Email: ");
        lblPassword = new JLabel("Password: ");
		lblErrorMessage = new JLabel();
		lblDob = new JLabel("Date of Birth:");
		lblEmployeeId = new JLabel("Employee Id To Be Deleted:");
        
        etName = new JTextField();
        etRole = new JTextField();
        etEmail = new JTextField();
		etPassword = new JTextField();
		etDob = new JTextField();
		etEmployeeId = new JTextField();

		btnHireEmployee = new JButton("Hire Employee");
		btnFireEmployee = new JButton("Fire Employee");
	}

	@Override
	protected void onViewCreated() {
		lblErrorMessage.setForeground(Color.RED);
        lblErrorMessage.setAlignmentX(CENTER_ALIGNMENT);
		lblErrorMessage.setVisible(false);
		
		landingPanel.setLayout(new BoxLayout(landingPanel, BoxLayout.PAGE_AXIS));
		landingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblTitle.setAlignmentX(CENTER_ALIGNMENT);
        landingPanel.add(lblTitle);
        landingPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		hirePanel.setLayout(new BoxLayout(hirePanel, BoxLayout.PAGE_AXIS));
		hirePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		hireFormPanel.add(lblName);
        hireFormPanel.add(etName);

        hireFormPanel.add(lblRole);
        hireFormPanel.add(etRole);

        hireFormPanel.add(lblEmail);
        hireFormPanel.add(etEmail);

        hireFormPanel.add(lblDob);
        hireFormPanel.add(etDob);

        hireFormPanel.add(lblPassword);
		hireFormPanel.add(etPassword);
		
		btnHireEmployee.setAlignmentX(CENTER_ALIGNMENT);
		hirePanel.add(hireFormPanel);
		hirePanel.add(Box.createRigidArea(new Dimension(0, 10)));
		hirePanel.add(btnHireEmployee);
		hirePanel.add(Box.createRigidArea(new Dimension(0, 5)));
		hirePanel.add(lblErrorMessage);

		// Fire Section
		firePanel.setLayout(new BoxLayout(firePanel, BoxLayout.PAGE_AXIS));
		firePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		fireFormPanel.add(lblEmployeeId);
		fireFormPanel.add(etEmployeeId);
		etEmployeeId.setSize(new Dimension(200, 50));
		etEmployeeId.setPreferredSize(new Dimension(200, 50));

		btnFireEmployee.setAlignmentX(CENTER_ALIGNMENT);
		firePanel.add(fireFormPanel);
		firePanel.add(Box.createRigidArea(new Dimension(0, 10)));
		firePanel.add(btnFireEmployee);

		landingPanel.add(hirePanel);
		landingPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		landingPanel.add(firePanel);

		add(landingPanel);
	}

	@Override
	protected void initListener() {
		btnHireEmployee.addActionListener(this);
		btnFireEmployee.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnHireEmployee)) {
			lblErrorMessage.setVisible(true);
			Integer roleId;
			if (etRole.getText().equalsIgnoreCase("driver") || etRole.getText().equalsIgnoreCase("chef")) {
				roleId = etRole.getText().equalsIgnoreCase("driver") ? 1 : 2;
			}
			else if(etRole.getText().equals("1") || etRole.getText().equals("2")) {
				roleId = etRole.getText().equalsIgnoreCase("1") ? 1 : 2;
			}
			else {
				lblErrorMessage.setText("Invalid Role. Must be 1(Driver)/2(Chef)");
				return;
			}
			String name = etName.getText();
			String password = etPassword.getText();
			String email = etEmail.getText();
			Date dob = DateUtil.parseDate(etDob.getText());
			if (dob != null) {
				EmployeeHandler.getInstance().createEmployee(roleId, name, dob, email, password, "hired");
				lblErrorMessage.setText("Hire Success");
			}
			else {
				lblErrorMessage.setText("Invalid Birthdate. Correct format yyyy-mm-dd");
			}
		}
		else if (e.getSource().equals(btnFireEmployee)) {
			String employeeId = etEmployeeId.getText();
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this,
				"Are you want to fire employee with id " + employeeId + "?",
				"Confirmation Dialog",
				dialogButton
			);
			if(dialogResult == 0) {
				if (EmployeeHandler.getInstance().changeStatus(Integer.parseInt(employeeId))) {
					lblErrorMessage.setText("Fired Success");
				}
				else {
					lblErrorMessage.setText("Something error occured while firing the employee");
				}
			}
		}
	}
}
