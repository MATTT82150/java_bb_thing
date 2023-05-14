import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.geom.CubicCurve2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.util.ArrayList;
import java.lang.Math;



public class Frame1 {

	static JFrame frmGuiDemo;
	static JFrame selectorPane;
	private static JLabel labelMessage;
	private static Point.Double pos = new Point2D.Double();
	private static Point.Double previousMousePos = new Point2D.Double();
	private static ArrayList<movingBox> boxes = new ArrayList<movingBox>();
	static ArrayList<CubicCurve2D.Double> curves = new ArrayList<CubicCurve2D.Double>();
	private static double zoom = 1;
	HandlerClass handler = new HandlerClass();
	private static int blockSelected = -1;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//selectedBlock = -1;
					//Frame1 window = new Frame1();
					//window.frmGuiDemo.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				


			}
		});
	}


	/*private static int selectBlock(int x, int y) {
		for (int i = 0; i < boxes.size(); i++) {
			if (boxes.get(i).checkIfClicked(x, y)) {
				return i;
			}
		}
		return -1;
	}*/
	
	public static void boxClicked(movingBox boxSelected) {
		blockSelected = -1;
		for (int i = 0; i < boxes.size(); i++) {
			movingBox box = boxes.get(i);
			box.deselect();
			if (box.equals(boxSelected)) {
				blockSelected = i;
				Frame2.loadBlockData(box);
			}
		}
	}
	
	public static movingBox getSelectedBox() {
		return boxes.get(blockSelected);
	}
	
	public static double getZoom() {
		//System.out.println("Zoom of " + zoom + " returned");
		return zoom;
	}
	
	public static void shiftView(Point2D.Double p1, Point2D.Double p2) {
		pos.x += p1.x;
		pos.y += p1.y;
		previousMousePos.x = p2.x;
		previousMousePos.y = p2.y;
		updateBlocks();
		//System.out.println(pos);
	}
	
	public static void zoomView(double z) {
		zoom *= z;
		pos.x -= frmGuiDemo.getWidth()/2;
		pos.y -= frmGuiDemo.getHeight()/2;
		pos.x *= z;
		pos.y *= z;
		pos.x += frmGuiDemo.getWidth()/2;
		pos.y += frmGuiDemo.getHeight()/2;
		updateBlocks();
		//System.out.println("Zoomed by a factor of " + z);
	}
	
	public static Point2D.Double getPreviousMousePos() {
		return previousMousePos;
	}
	
	/*private static void moveSelectedBlock(int x, int y) {
		boxes.get(selectedBlock).moveBlock(x,y);
	}*/

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public Frame1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		frmGuiDemo = new JFrame("thing 1");
	
		frmGuiDemo.getContentPane().add(new JFrameShapes());
		frmGuiDemo.pack();
		
		
		
		frmGuiDemo.addMouseListener(handler);
		frmGuiDemo.addMouseMotionListener(handler);
		frmGuiDemo.addMouseWheelListener(handler);
		frmGuiDemo.setIconImage(new ImageIcon("bread.jpg").getImage());
		frmGuiDemo.setResizable(true);
		frmGuiDemo.setTitle("GUI demo");
		frmGuiDemo.setBounds(100, 100, 500, 500);
		frmGuiDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGuiDemo.getContentPane().setLayout(null);
	
		
		/*JButton btnNewButton = new JButton("Show Message");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "Hello World!");
				labelMessage.setText("Hello, World!");
			}
		});
		btnNewButton.setBounds(148, 104, 125, 23);
		frmGuiDemo.getContentPane().add(btnNewButton);
		
		labelMessage = new JLabel("Message");
		labelMessage.setBounds(94, 35, 173, 43);
		frmGuiDemo.getContentPane().add(labelMessage);*/
		
		/*JPanel blockContainer = new JPanel();
		blockContainer.setToolTipText("");
		blockContainer.setBounds(54, 132, 73, 23);
		frmGuiDemo.getContentPane().add(blockContainer);
		
		JLabel blockText = new JLabel("New label");
		blockContainer.add(blockText);
		blockText.setBackground(Color.GRAY);
		blockText.setHorizontalAlignment(SwingConstants.CENTER);*/
		
		/*boxes.add(new movingBox(0, 0, 100, 30, "yuh1"));
		boxes.add(new movingBox(120, 0, 100, 30, "yuh2"));
		boxes.add(new movingBox(240, 0, 100, 30, "yuh3"));*/
		for (int i = 0; i < 500; i++) {
			//boxes.add(new movingBox(/*120*i*/(int)(Math.random()*50000)-25000, (int)(Math.random()*50000)-25000, 100, 30, "yuh" + i));
			boxes.add(new movingBox(120*(i%(int)Math.sqrt(500)), 120*(i/(int)Math.sqrt(500)), 100, 30, "yuh" + i));
		}
		generateConnectionCurves();
		updateBlocks();
	}
	
	public static void generateConnectionCurves() {
		curves = new ArrayList<CubicCurve2D.Double>();
		for (int i = 0; i < boxes.size(); i++) {
			for (int j = i+1; j < boxes.size(); j++) {
				addCurve(boxes.get(i), boxes.get(j), 0, 0);
			}
		}
	}
	
	public static void addCurve(movingBox a, movingBox b, int aSlot, int bSlot) {
		curves.addAll(generateCurve(
		new Point2D.Double(
				a.getX()+a.getWidth(), 
				a.getY()+a.getHeight()*(0.2*aSlot+0.1)),
		new Point2D.Double( 
				b.getX(),
				b.getY()+b.getHeight()*(0.2*bSlot+0.1))));
	}
	
	public static ArrayList<CubicCurve2D.Double> generateCurve(Point2D.Double a, Point2D.Double b) {
		Point2D.Double p1;
		Point2D.Double p2;
		Point2D.Double p3;
		Point2D.Double p4;
		Point2D.Double p5;
		Point2D.Double p6;
		Point2D.Double p7;
		Point2D.Double p8;
		ArrayList<CubicCurve2D.Double> list = new ArrayList<CubicCurve2D.Double>();
		if (a.x < b.x) {
			p1 = new Point2D.Double(a.x,a.y);
			p2 = new Point2D.Double((a.x+b.x)*0.5, a.y);
			p3 = new Point2D.Double((a.x+b.x)*0.5, b.y);
			p4 = new Point2D.Double(b.x,b.y);
			list.add(new CubicCurve2D.Double(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y, p4.x, p4.y));
		} else {
			p1 = new Point2D.Double(a.x,a.y);
			p2 = new Point2D.Double(a.x + Math.abs(a.y-b.y)*0.1, a.y);
			p3 = new Point2D.Double(a.x + Math.abs(a.y-b.y)*0.1, (a.y+b.y)*0.5);
			p4 = new Point2D.Double(a.x,(a.y+b.y)*0.5);
			p5 = new Point2D.Double(b.x,(a.y+b.y)*0.5);
			p6 = new Point2D.Double(b.x - Math.abs(a.y-b.y)*0.1, (a.y+b.y)*0.5);
			p7 = new Point2D.Double(b.x - Math.abs(a.y-b.y)*0.1, b.y);
			p8 = new Point2D.Double(b.x,b.y);
			
			list.add(new CubicCurve2D.Double(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y, p4.x, p4.y));
			list.add(new CubicCurve2D.Double(p4.x, p4.y, p4.x, p4.y, p5.x, p5.y, p5.x, p5.y));
			list.add(new CubicCurve2D.Double(p5.x, p5.y, p6.x, p6.y, p7.x, p7.y, p8.x, p8.y));
		}
		
		return list;
	}
	
	public static Point2D.Double transform(Point2D.Double point) {
		return new Point2D.Double(point.x*zoom + pos.x, point.y*zoom + pos.y);
	}
	
	/*public static CubicCurve2D.Double transform(CubicCurve2D.Double curve) {
		CubicCurve2D.Double output = new CubicCurve2D.Double(
				curve.x1 + 0*pos.x,
				curve.y1 + 0*pos.y,
				curve.ctrlx1 + 0*pos.x,
				curve.ctrly1 + 0*pos.y,
				curve.ctrlx2 + 0*pos.x,
				curve.ctrly2 + 0*pos.y,
				curve.x2 + 0*pos.x,
				curve.y2 + 0*pos.y);
		//System.out.println("Curve transformed: x from " + curve.x1 + " to " + output.x1);
		return output;
	}*/
	
	public static void printBoxes() {
		for (movingBox box : boxes) {
			//System.out.println(box.getPos());
		}
	}
	
	public static double getBlockWidthForTextLength(String str) {
		return str.length() * 8;
	}
	
	/*public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		drawCurves(g2);
	}
	
	public void drawCurves(Graphics2D g2) {
		for (CubicCurve2D.Double curve : curves) {
			g2.draw(curve);
			System.out.println("Printing");
		}
	}*/
	
	public static void updateBlocks() {
		//blockContainers = new ArrayList<JPanelClickable>();
		//ArrayList<JLabel> blockContainersText = new ArrayList<JLabel>();
		for (movingBox box : boxes) {
			
			box.setToolTipText("");
			int xp = (int)(box.getPos().x*zoom+pos.x);
			int yp = (int)(box.getPos().y*zoom+pos.y);
			int xd = (int)(Math.max(box.getDimensions().x*zoom,  getBlockWidthForTextLength(box.getText())*zoom  ));
			int yd = (int)(box.getDimensions().y*zoom);
			if (!(xp+xd < 0 || xp > frmGuiDemo.getWidth() || yp+yd < 0 || yp > frmGuiDemo.getHeight())) {
				box.setBounds(
					xp,
					yp,
					xd,
					yd
				);
				//box.setFont(new Font("Dialog", Font.BOLD, (int)(120*zoom)));
				box.setLabelFontSize((int)(12*zoom));
				if (box.isSelected()) {
					box.setBackground(Color.GRAY);
				} else {
					box.setBackground(Color.LIGHT_GRAY);
				}
				frmGuiDemo.getContentPane().add(box);
			} else {
				//box.setLocation(frmGuiDemo.getWidth(), 0);
				box.setLocation(xp, yp);
			}

		}
		
		frmGuiDemo.repaint();
		generateConnectionCurves();
		printBoxes();
		
	}

}

class HandlerClass implements MouseListener, MouseMotionListener, MouseWheelListener  {

	public void mouseDragged(MouseEvent evt) {
		//System.out.println("Stage is dragged");
		//System.out.println(pos);
		double posX = evt.getLocationOnScreen().x - Frame1.getPreviousMousePos().x;
		double posY = evt.getLocationOnScreen().y - Frame1.getPreviousMousePos().y;
		int prevMousePosX = evt.getLocationOnScreen().x;
		int prevMousePosY = evt.getLocationOnScreen().y;
		Frame1.shiftView(new Point2D.Double(posX, posY), new Point2D.Double(prevMousePosX, prevMousePosY));
	}

	public void mouseMoved(MouseEvent evt) {
    	int prevMousePosX = evt.getLocationOnScreen().x;
		int prevMousePosY = evt.getLocationOnScreen().y;
		Frame1.shiftView(new Point2D.Double(), new Point2D.Double(prevMousePosX, prevMousePosY));
		
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseWheelMoved(MouseWheelEvent evt) {
		// TODO Auto-generated method stub
		Frame1.zoomView(1 + 0.01*evt.getScrollAmount()*evt.getUnitsToScroll());
		
	}
	
}

class JFrameShapes extends JPanel {
	
	/*ArrayList<CubicCurve2D.Double> curvesTransformed;
	
	public void getCurves(ArrayList<CubicCurve2D.Double> input) {
		curvesTransformed = input;
	}*/
	
    public Dimension getPreferredSize() {
        return new Dimension(10000,10000);
    }

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;
		//g.drawLine(100, 100, 200, 100);
		drawCurves(g2);
	}
	
	public void drawCurves(Graphics2D g2) {
		for (CubicCurve2D.Double curve : Frame1.curves) {
			CubicCurve2D.Double c = curve;
			g2.draw(c);
			//g2.draw(new Line2D.Double(c.getP1(), c.getP2()));
			/*System.out.println("Printing curve: " + 
					"(" + c.getX1() + ", "+ c.getY1() + "); " + 
					"(" + c.getCtrlX1() + ", "+ c.getCtrlY1() + "); " + 
					"(" + c.getCtrlX2() + ", "+ c.getCtrlY2() + "); " + 
					"(" + c.getX2() + ", "+ c.getY2() + "); " + 
					" at " + System.currentTimeMillis());*/
		}
	}
	
}