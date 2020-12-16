package views.employee;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.View;

public class ChefMenuFormView extends View implements ActionListener {
	
	private JPanel landingPanel, addPanel, removePanel, addFormPanel, removeFormPanel;
    private JLabel lblTitle, lblName, lblPrice,lblDescription, lblFoodId,lblErrorMessage;
	private JButton btnAddMenu,btnRemoveMenu;
    private JTextField etName, etPrice, etDescription, etFoodId;

	public ChefMenuFormView() {
		super(400, 500);
	}


	@Override
	protected void onInitView() {
		landingPanel = new JPanel();
		addPanel = new JPanel();
		removePanel = new JPanel();
		addFormPanel = new JPanel(new GridLayout(5, 2, 0, 5));
		removeFormPanel = new JPanel(new GridLayout(0, 2, 0, 5));
		lblTitle = new JLabel("Add or Remove Menu");
		
		lblName = new JLabel("Name: ");
        lblPrice = new JLabel("Prce: ");
        lblDescription = new JLabel("Description: ");
        lblFoodId = new JLabel("Food Id To Be Deleted:");
        
        etName = new JTextField();
        etPrice = new JTextField();
        etDescription = new JTextField();
        etFoodId = new JTextField();

		btnAddMenu = new JButton("Add Menu");
		btnRemoveMenu = new JButton("Remove Menu");
	}

	@Override
	protected void onViewCreated() {
//		lblErrorMessage.setForeground(Color.RED);
//        lblErrorMessage.setAlignmentX(CENTER_ALIGNMENT);
//		lblErrorMessage.setVisible(false);
//		
		landingPanel.setLayout(new BoxLayout(landingPanel, BoxLayout.PAGE_AXIS));
		landingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblTitle.setAlignmentX(CENTER_ALIGNMENT);
        landingPanel.add(lblTitle);
        landingPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.PAGE_AXIS));
        addPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		addFormPanel.add(lblName);
		addFormPanel.add(etName);

		addFormPanel.add(lblPrice);
		addFormPanel.add(etPrice);

		addFormPanel.add(lblDescription);
		addFormPanel.add(etDescription);

		
		btnAddMenu.setAlignmentX(CENTER_ALIGNMENT);
		addPanel.add(addFormPanel);
		addPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		addPanel.add(btnAddMenu);
		addPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		addPanel.add(lblErrorMessage);

		// Remove Section
		removePanel.setLayout(new BoxLayout(removePanel, BoxLayout.PAGE_AXIS));
		removePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		removeFormPanel.add(lblFoodId);
		removeFormPanel.add(etFoodId);
		etFoodId.setSize(new Dimension(200, 50));
		etFoodId.setPreferredSize(new Dimension(200, 50));

		btnRemoveMenu.setAlignmentX(CENTER_ALIGNMENT);
		removePanel.add(removeFormPanel);
		removePanel.add(Box.createRigidArea(new Dimension(0, 10)));
		removePanel.add(btnRemoveMenu);

		landingPanel.add(addPanel);
		landingPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		landingPanel.add(removePanel);

		add(landingPanel);
		
	}

	@Override
	protected void initListener() {
		btnAddMenu.addActionListener(this);
		btnRemoveMenu.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
