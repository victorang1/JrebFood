package views.employee;

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

import controllers.EmployeeHandler;
import controllers.FoodHandler;
import controllers.OrderHandler;
import core.View;

public class ChefLandingView extends View implements ActionListener {
	
	private JPanel landingPanel, contentPanel;
    private JLabel lblTitle;
    private JButton btnViewQueue, viewFoodAvailability,btnFoodForm;

    public ChefLandingView() {
        super(300, 200);
    }

	@Override
	protected void onInitView() {
		landingPanel = new JPanel();
        contentPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        lblTitle = new JLabel("Welcome to Chef Page!");
        
        viewFoodAvailability = new JButton("Update Food Availability");
        btnFoodForm = new JButton("Add or Remove Food From Menu");
        btnViewQueue = new JButton("View Order Queue");
		
	}

	@Override
	protected void onViewCreated() {
		landingPanel.setLayout(new BoxLayout(landingPanel, BoxLayout.PAGE_AXIS));
        landingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        contentPanel.add(viewFoodAvailability);
        contentPanel.add(btnViewQueue);
        contentPanel.add(btnFoodForm);

        lblTitle.setAlignmentX(CENTER_ALIGNMENT);
        landingPanel.add(lblTitle);
        landingPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        landingPanel.add(contentPanel);
        add(landingPanel);
		
	}

	@Override
	protected void initListener() {
		btnFoodForm.addActionListener(this);
		btnViewQueue.addActionListener(this);
		viewFoodAvailability.addActionListener(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(viewFoodAvailability)) {
            FoodHandler.getInstance().viewManageFoodForm();
        }
        else if (e.getSource().equals(btnFoodForm)) {
            FoodHandler.getInstance().viewManageFoodForm();
        }
        else if (e.getSource().equals(btnViewQueue)) {
        	OrderHandler.getInstance().viewOrderQueue();
        }
	}
}
