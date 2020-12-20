package views.food;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.FoodHandler;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;

import core.View;

import java.awt.event.ActionEvent;

public class ManageFoodFormView extends View implements ActionListener {
    
    private JPanel landingPanel, addPanel, removePanel, addFormPanel, removeFormPanel, bottomPanel;
    private JLabel lblTitle, lblName, lblPrice,lblDescription, lblFoodId,lblErrorMessage;
	private JButton btnAddMenu, btnRemoveMenu, btnChange;
    private JTextField etName, etPrice, etDescription, etFoodId;

	public ManageFoodFormView() {
		super(400, 500);
	}

	@Override
	protected void onInitView() {
		landingPanel = new JPanel();
		addPanel = new JPanel();
		removePanel = new JPanel();
		bottomPanel = new JPanel();
		addFormPanel = new JPanel(new GridLayout(5, 2, 0, 5));
		removeFormPanel = new JPanel(new GridLayout(0, 2, 0, 5));
		lblTitle = new JLabel("Add or Remove Menu");
		
		lblName = new JLabel("Name: ");
        lblPrice = new JLabel("Price: ");
        lblDescription = new JLabel("Description: ");
		lblFoodId = new JLabel("Food Id To Be Deleted:");
		lblErrorMessage = new JLabel();
        
        etName = new JTextField();
        etPrice = new JTextField();
        etDescription = new JTextField();
        etFoodId = new JTextField();

		btnAddMenu = new JButton("Add Menu");
		btnRemoveMenu = new JButton("Remove Menu");
		btnChange = new JButton("Change Food Availibility");
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
		addPanel.add(btnAddMenu);
		addPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		addPanel.add(lblErrorMessage);

		// Remove Section
		removePanel.setLayout(new BoxLayout(removePanel, BoxLayout.PAGE_AXIS));
		removePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		bottomPanel.add(btnRemoveMenu);
		bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		bottomPanel.add(btnChange);

		removeFormPanel.add(lblFoodId);
		removeFormPanel.add(etFoodId);
		etFoodId.setSize(new Dimension(200, 50));
		etFoodId.setPreferredSize(new Dimension(200, 50));

		btnRemoveMenu.setAlignmentX(CENTER_ALIGNMENT);
		removePanel.add(removeFormPanel);
		removePanel.add(Box.createRigidArea(new Dimension(0, 10)));
		removePanel.add(bottomPanel);

		landingPanel.add(backButton);
		landingPanel.add(addPanel);
		landingPanel.add(removePanel);

		add(landingPanel);
		
	}

	@Override
	protected void initListener() {
		btnAddMenu.addActionListener(this);
		btnRemoveMenu.addActionListener(this);
		btnChange.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAddMenu)) {
            lblErrorMessage.setVisible(true);
            String name = etName.getText();
            String price = etPrice.getText();
            String description = etDescription.getText();
            if (FoodHandler.getInstance().addFood(name, Integer.parseInt(price), description)) {
                lblErrorMessage.setText("Add Food Success!");
            }
            else {
                lblErrorMessage.setText(FoodHandler.getInstance().getErrorMessage());
            }
        }
        else if (e.getSource().equals(btnRemoveMenu)) {
            lblErrorMessage.setVisible(true);
			String foodId = etFoodId.getText();
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this,
				"Are you sure you want to remove this menu with foodId= " + foodId + "?",
				"Confirmation Dialog",
				dialogButton
			);
			if(dialogResult == 0) {
				if (FoodHandler.getInstance().deleteFood(Integer.parseInt(foodId))) {
					lblErrorMessage.setText("Remove Food Success!");
				}
				else {
					lblErrorMessage.setText("Remove Food Failed!");
				}
			}
		}
		else if (e.getSource().equals(btnChange)) {
			lblErrorMessage.setVisible(true);
			String foodId = etFoodId.getText();
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this,
				"Are you sure you want to change food availibty with foodId= " + foodId + "?",
				"Confirmation Dialog",
				dialogButton
			);
			if(dialogResult == 0) {
				if (FoodHandler.getInstance().changeStatus(Integer.parseInt(foodId))) {
					lblErrorMessage.setText("Change Food Availibility Success!");
				}
				else {
					lblErrorMessage.setText("Change Food Availibility Failed!");
				}
			}
		}
	}
}
