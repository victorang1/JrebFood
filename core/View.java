package core;

import javax.swing.JFrame;

public abstract class View extends JFrame {

    protected int frameWidth;
	protected int frameHeight;

    public void View() {
        onInitView();
        onViewCreated();
        initListener();
    }

    public void showForm(){
		setSize(frameWidth, frameHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
    
    protected abstract void onInitView();
    protected abstract void onViewCreated();
    protected abstract void initListener();
}
