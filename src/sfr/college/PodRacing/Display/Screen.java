package sfr.college.PodRacing.Display;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
public class Screen {
	private JFrame frame;
	private Canvas canvas;
	private String title;
	private int length;

	
	public Screen(int l, String title){
		this.title = title;
		this.length=l;
		
		createDisplay();
	}
	private void createDisplay(){
		frame = new JFrame(title);
		frame.setSize(length,length);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.requestFocusInWindow();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(length,length));
		canvas.setMaximumSize(new Dimension(length,length));
		canvas.setMinimumSize(new Dimension(length,length));
                canvas.setFocusable(false);
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
                
                
	}
	public Canvas getCanvas(){
		return canvas;
	}
        public JFrame getFrame(){
            return frame;
        }

}
