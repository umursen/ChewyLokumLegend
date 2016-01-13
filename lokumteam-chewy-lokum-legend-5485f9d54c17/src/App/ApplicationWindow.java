package App;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ApplicationWindow extends JFrame {
	
	protected static ApplicationWindow instance;

	public ApplicationWindow() {
		super("Chewy Lokum Legend");
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		setLayout(new GridLayout(1,1));
		setVisible(true);
		setResizable(false);
		
		
		
		add(MenuPanel.getInstance());
	}
	
	public static ApplicationWindow getInstance(){
		if(instance==null){
			instance = new ApplicationWindow();
		}
		return instance;
	}
	
	public void resetInstance(){
		instance = null;
	}
	
	public static void addPanel(JPanel panel){
		instance.add(panel);
		instance.pack();
		instance.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);

	}
	
	public static void removePanel(JPanel panel){
		instance.remove(panel);
	}
	
}