package core;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public abstract class View extends JFrame {

    protected JButton backButton;
    protected Integer frameWidth;
	protected Integer frameHeight;

    public View(Integer frameWidth, Integer frameHeight) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        setTitle("JrebFood");
        initBackButton();
        onInitView();
        onViewCreated();
        initListener();
        showView();
    }

    private void showView(){
		setSize(frameWidth, frameHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
    }
    
    private void initBackButton() {
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    protected abstract void onInitView();
    protected abstract void onViewCreated();
    protected abstract void initListener();
}
