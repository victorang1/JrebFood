package views.cart;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.GridLayout;

import core.View;

public class AddCartView extends View implements ActionListener {

    private JPanel formPanel, contentPanel;
    private JLabel lblFoodId, lblQty;
    private JTextField etFoodId, etQty;
    private JButton btnAdd;

    public AddCartView() {
        super(300, 170);
    }
    
    @Override
    protected void onInitView() {
        formPanel = new JPanel();
        contentPanel = new JPanel(new GridLayout(2, 2, 0, 8));

        lblFoodId = new JLabel("Food Id");
        lblQty = new JLabel("Quantity");

        etFoodId = new JTextField();
        etQty = new JTextField();

        btnAdd = new JButton("Add to Cart");
    }

    @Override
    protected void onViewCreated() {
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.PAGE_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        contentPanel.setAlignmentX(LEFT_ALIGNMENT);

        contentPanel.add(lblFoodId);
        contentPanel.add(etFoodId);

        contentPanel.add(lblQty);
        contentPanel.add(etQty);
        
        formPanel.add(backButton);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(contentPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(btnAdd);

        add(formPanel);
    }

    @Override
    protected void initListener() {
        btnAdd.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAdd)) {
            
        }
	}
}
