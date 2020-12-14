package core;

import javax.swing.JFrame;

public abstract class View extends JFrame {

    protected Integer frameWidth;
	protected Integer frameHeight;

    public View(Integer frameWidth, Integer frameHeight) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
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
    
    protected abstract void onInitView();
    protected abstract void onViewCreated();
    protected abstract void initListener();
}
