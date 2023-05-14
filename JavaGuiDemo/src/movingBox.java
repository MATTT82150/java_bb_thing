import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class movingBox extends JPanel implements MouseListener, MouseMotionListener {

	private final int NODE_WIDTH = 15;
	private final int NODE_HEIGHT = 15;
	private final double DETAIL_ZOOM_LIMIT = 0.3;
	
	
	private Point2D.Double pos;
	private Point2D.Double size;
	private boolean selected;
	private String blockType;
	private Point2D.Double prevMousePos;
	private int prevMousePosX;
	private int prevMousePosY;
	private int inputNodes;
	private int outputNodes;
	private ArrayList<JPanel> inputNodePanels;
	private ArrayList<JPanel> outputNodePanels;
	JPanel panel;
	JLabel label;
	
	public movingBox(int x, int y, int sx, int sy, String t) {
		pos = new Point2D.Double(x,y);
		size = new Point2D.Double(sx, sy);
		panel = this;
		label = new JLabel(t);
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		panel.add(label);
		panel.setBorder(new LineBorder(new Color(128, 128, 128)));
		label.setBackground(Color.GRAY);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setVerticalTextPosition(SwingConstants.CENTER);
		updateNodes();
	}
	
	/*public movingBox(int x. int y, int sx, int sy, String t, int iN, int oN) {
		this(x,y,sx,sy,t);
		inputNodes = iN;
		outputNodes = oN;
	}*/
	
	public void deselect() {
		selected = false;
	}
	
	
	/*public boolean posClicked(Point2D.Double p) {
		if((p.x > pos.x && p.x < pos.x+size.x) && (p.y > pos.y && p.y < pos.y+size.y)) {
			System.out.println("Pos:      " + pos);
			System.out.println("Size:     " + size);
			System.out.println("Mouse Pos:" + p);
			return true;
		}
		return false;
		
	}*/
	
	/*public void mouseClicked(MouseEvent event) {
		Frame1.boxClicked();
		selected = true;
		System.out.println("Clicked!");
		Frame1.updateBlocks();
	}*/
	
	public void setBounds(int x1, int y1, int x2, int y2) {
		super.setBounds(x1, y1, x2, y2);
		updateNodes();
	}
	
	public void addInputNodes(int nodes) {
		inputNodes += nodes;
		updateNodes();
	}
	
	public void addOutputNodes(int nodes) {
		outputNodes += nodes;
		updateNodes();
	}
	
	public void setInputNodes(int nodes) {
		inputNodes = nodes;
		updateNodes();
	}
	
	public void setOutputNodes(int nodes) {
		outputNodes = nodes;
		updateNodes();
	}
	
	public void updateNodes() {
		if (Frame1.getZoom() < DETAIL_ZOOM_LIMIT) {
			//System.out.println("Zoom limit hit! Zoom = " + Frame1.getZoom());
			return;
		}
		int nodeWidth = (int)(NODE_WIDTH*Frame1.getZoom());
		int nodeHeight = (int)(NODE_HEIGHT*Frame1.getZoom());
		inputNodePanels = new ArrayList<JPanel>();
		outputNodePanels = new ArrayList<JPanel>();
		if (inputNodes < 1) {
			inputNodes = 1;
		}
		if (outputNodes < 1) {
			outputNodes = 1;
		}
		for (int i = 0; i < inputNodes; i++) {
			JPanel j = new JPanel();
			j.setBounds((int)(pos.x - nodeWidth), (int)(pos.y + i*(size.y-nodeHeight) - nodeHeight*0.5), nodeWidth, nodeHeight);
			j.setBorder(new LineBorder(new Color(128, 128, 128)));
			inputNodePanels.add(j);
			//System.out.println("nodes updated: input node " + i + " at " + j.getX() + ", " + j.getY());
		}
		for (int i = 0; i < outputNodes; i++) {
			JPanel j = new JPanel();
			j.setBounds((int)(pos.x + size.x + nodeWidth), (int)(pos.y + i*(size.y-nodeHeight) - nodeHeight*0.5), nodeWidth, nodeHeight);
			j.setBorder(new LineBorder(new Color(128, 128, 128)));
			outputNodePanels.add(j);
			//System.out.println("nodes updated: output node " + i + " at " + j.getX() + ", " + j.getY());
		}
		
	}
	
	public void setLabelFontSize(int size) {
		//label.setFont();
		label.setFont(new Font("Dialog", Font.BOLD, size));
	}
	
	public void moveBlock(int x, int y) {
		pos = new Point2D.Double(x,y);
	}
	
	public Point2D.Double getPos() {
		return pos;
	}
	
	public Point2D.Double getDimensions() {
		return size;
	}
	
	public String getText() {
		return label.getText();
	}
	
	public void setText(String str) {
		label.setText(str);
		updateNodes();
	}
	
	public String getType() {
		return blockType;
	}
	
	public void setType(String str) {
		blockType = str;
	}
	
	public boolean isSelected() {
		return selected;
	}

    public void mouseMoved   (MouseEvent evt){
    	prevMousePosX = evt.getLocationOnScreen().x;
		prevMousePosY = evt.getLocationOnScreen().y;
    	Frame1.updateBlocks();
    }
    
    public void mouseClicked (MouseEvent evt){
    	//System.out.println("ID " + 1 + " is clicked");
    }
    
    public void mouseEntered (MouseEvent evt){
    	//System.out.println("ID " + 1 + " is entering");
    }
    
    public void mouseExited  (MouseEvent evt){
    	//System.out.println("ID " + 1 + " is exiting");
    }
    
    public void mousePressed (MouseEvent evt){
    	//System.out.println("ID " + 1 + " is pressed");
    	if(SwingUtilities.isLeftMouseButton(evt)) {
    		Frame1.boxClicked(this);
    		selected = true;
    	}
    	Frame1.updateBlocks();
    		
    }
    public void mouseReleased(MouseEvent evt){
    	//System.out.println("ID " + 1 + " is released");
    }
	public void mouseDragged (MouseEvent evt){
		//System.out.println("ID " + 1 + " is dragged");
		//System.out.println(pos);
		if(SwingUtilities.isLeftMouseButton(evt)) {
			pos.x += (evt.getLocationOnScreen().x - prevMousePosX)/Frame1.getZoom();
			pos.y += (evt.getLocationOnScreen().y - prevMousePosY)/Frame1.getZoom();
			prevMousePosX = evt.getLocationOnScreen().x;
			prevMousePosY = evt.getLocationOnScreen().y;
		}
		Frame1.updateBlocks();
	}
}
