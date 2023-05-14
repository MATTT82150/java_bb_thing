import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import javax.swing.ListModel;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.*;  
import java.awt.event.*;

public class Frame2 {

	JFrame frame;
	private JPanel blockSettingsPanel;
	private JPanel blockSelectorPanel;
	private JLabel blockSettingsLabel;
	private static JPanel blockTextPanel;
	private static JLabel blockTextLabel;
	private static JPanel panel;
	private static JTextArea textArea;
	private static JPanel blockSettingsPanel2;
	private static JButton settingsButton1;
	private static JButton settingsButton2;
	private static JButton settingsButton3;
	private static JButton settingsButton4;
	private static JButton settingsButton5;
	private static JButton settingsButton6;
	private static JPanel blockSelectorInputsPanel;
	private static JLabel blockSelectorInputsLabel;
	private static JPanel blockSelectorInputsPanel2;
	private static JList inputsList;
	private static JScrollPane inputsScrollList;
	private static JPanel blockSelectorOutputsPanel;
	private static JLabel blockSelectorOutputsLabel;
	private static JPanel blockSelectorOutputsPanel2;
	private static JList outputsList;
	private static JScrollPane outputsScrollList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame2 window = new Frame2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(225, 225, 225));
		frame.setBounds(600, 100, 320, 500);
		frame.setMinimumSize(new Dimension(320, 500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[280px:280px:400px,grow]", "[60px][100px:100px:100px][100px:n,grow]"));
		
		blockTextPanel = new JPanel();
		blockTextPanel.setBorder(new LineBorder(new Color(128, 128, 128)));
		frame.getContentPane().add(blockTextPanel, "cell 0 0,grow");
		blockTextPanel.setLayout(new MigLayout("", "[270px:270px:270px,grow,left]", "[10px][28px,grow]"));
		
		blockTextLabel = new JLabel("Current block:");
		blockTextPanel.add(blockTextLabel, "cell 0 0,grow");
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128)));
		blockTextPanel.add(panel, "cell 0 1,growx,aligny baseline");
		panel.setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setRows(1);
		textArea.getDocument().addDocumentListener(new myDocumentListener());;
		textArea.getDocument().putProperty("name", "Text Area");
		panel.add(textArea);
		
		blockSettingsPanel = new JPanel();
		blockSettingsPanel.setBorder(new LineBorder(new Color(128, 128, 128)));
		frame.getContentPane().add(blockSettingsPanel, "cell 0 1,grow");
		SpringLayout sl_blockSettingsPanel = new SpringLayout();
		blockSettingsPanel.setLayout(sl_blockSettingsPanel);
		
		blockSelectorPanel = new JPanel();
		blockSelectorPanel.setBorder(new LineBorder(new Color(128, 128, 128)));
		
		blockSettingsLabel = new JLabel("Options");
		sl_blockSettingsPanel.putConstraint(SpringLayout.NORTH, blockSettingsLabel, 10, SpringLayout.NORTH, blockSettingsPanel);
		sl_blockSettingsPanel.putConstraint(SpringLayout.WEST, blockSettingsLabel, 10, SpringLayout.WEST, blockSettingsPanel);
		sl_blockSettingsPanel.putConstraint(SpringLayout.SOUTH, blockSettingsLabel, 20, SpringLayout.NORTH, blockSettingsPanel);
		blockSettingsPanel.add(blockSettingsLabel);
		
		blockSettingsPanel2 = new JPanel();
		sl_blockSettingsPanel.putConstraint(SpringLayout.NORTH, blockSettingsPanel2, 10, SpringLayout.SOUTH, blockSettingsLabel);
		sl_blockSettingsPanel.putConstraint(SpringLayout.WEST, blockSettingsPanel2, 10, SpringLayout.WEST, blockSettingsPanel);
		sl_blockSettingsPanel.putConstraint(SpringLayout.SOUTH, blockSettingsPanel2, 68, SpringLayout.SOUTH, blockSettingsLabel);
		sl_blockSettingsPanel.putConstraint(SpringLayout.EAST, blockSettingsPanel2, -10, SpringLayout.EAST, blockSettingsPanel);
		blockSettingsPanel.add(blockSettingsPanel2);
		blockSettingsPanel2.setLayout(new GridLayout(0, 3, 0, 0));
		
		settingsButton1 = new JButton("Add Block");
		settingsButton1.setBackground(new Color(225, 225, 225));
		settingsButton1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		blockSettingsPanel2.add(settingsButton1);
		
		settingsButton2 = new JButton("Add Input");
		settingsButton2.setBackground(new Color(225, 225, 225));
		settingsButton2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		blockSettingsPanel2.add(settingsButton2);
		
		settingsButton3 = new JButton("Add Output");
		settingsButton3.setBackground(new Color(225, 225, 225));
		settingsButton3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		blockSettingsPanel2.add(settingsButton3);
		
		settingsButton4 = new JButton("Delete Block");
		settingsButton4.setBackground(new Color(225, 225, 225));
		settingsButton4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		blockSettingsPanel2.add(settingsButton4);
		
		settingsButton5 = new JButton("Delete In");
		settingsButton5.setBackground(new Color(225, 225, 225));
		settingsButton5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		blockSettingsPanel2.add(settingsButton5);
		
		settingsButton6 = new JButton("Delete Out");
		settingsButton6.setBackground(new Color(225, 225, 225));
		settingsButton6.setFont(new Font("Tahoma", Font.PLAIN, 10));
		blockSettingsPanel2.add(settingsButton6);
		
		frame.getContentPane().add(blockSelectorPanel, "cell 0 2,grow");
		blockSelectorPanel.setLayout(new MigLayout("", "[270px:284px,grow]", "[120px,grow 120][160px,grow 160]"));
		
		blockSelectorInputsPanel = new JPanel();
		blockSelectorPanel.add(blockSelectorInputsPanel, "cell 0 0,grow");
		SpringLayout sl_blockSelectorInputsPanel = new SpringLayout();
		blockSelectorInputsPanel.setLayout(sl_blockSelectorInputsPanel);
		
		blockSelectorInputsLabel = new JLabel("Inputs");
		sl_blockSelectorInputsPanel.putConstraint(SpringLayout.NORTH, blockSelectorInputsLabel, 10, SpringLayout.NORTH, blockSelectorInputsPanel);
		sl_blockSelectorInputsPanel.putConstraint(SpringLayout.WEST, blockSelectorInputsLabel, 10, SpringLayout.WEST, blockSelectorInputsPanel);
		blockSelectorInputsPanel.add(blockSelectorInputsLabel);
		
		blockSelectorInputsPanel2 = new JPanel();
		sl_blockSelectorInputsPanel.putConstraint(SpringLayout.NORTH, blockSelectorInputsPanel2, 10, SpringLayout.SOUTH, blockSelectorInputsLabel);
		sl_blockSelectorInputsPanel.putConstraint(SpringLayout.WEST, blockSelectorInputsPanel2, 10, SpringLayout.WEST, blockSelectorInputsPanel);
		sl_blockSelectorInputsPanel.putConstraint(SpringLayout.SOUTH, blockSelectorInputsPanel2, -10, SpringLayout.SOUTH, blockSelectorInputsPanel);
		sl_blockSelectorInputsPanel.putConstraint(SpringLayout.EAST, blockSelectorInputsPanel2, -10, SpringLayout.EAST, blockSelectorInputsPanel);
		blockSelectorInputsPanel.add(blockSelectorInputsPanel2);
		blockSelectorInputsPanel2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JList inputsList = new JList(new AbstractListModel() {
			String[] values = new String[] {"Constant", "Random number", "Altitude", "Position", "Speed", "Stability", "Orientation", "Rotation", "Time", "Propulsion", "Complex Control", "Flagship info", "Event input", "Custom propulsion out", "Generic block getter", "Primary target info", "Steering point info"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		inputsList.setBorder(new LineBorder(new Color(128, 128, 128)));
		inputsList.setVisibleRowCount(0);
		inputsList.setFont(new Font("Tahoma", Font.PLAIN, 11));
		inputsList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		inputsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		inputsList.setFixedCellWidth(110);
		inputsList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 1) {
					JList target = (JList)me.getSource();
					int index = target.locationToIndex(me.getPoint());
					if (index >= 0) {
						Object item = target.getModel().getElementAt(index);
						Frame2.listClicked(item.toString());
					}
				}
			}
		});
		//blockSelectorInputsPanel2.add(inputsList);
		
		inputsScrollList = new JScrollPane(inputsList);
		blockSelectorInputsPanel2.add(inputsScrollList);

		blockSelectorOutputsPanel = new JPanel();
		blockSelectorPanel.add(blockSelectorOutputsPanel, "cell 0 1,grow");
		SpringLayout sl_blockSelectorOutputsPanel = new SpringLayout();
		blockSelectorOutputsPanel.setLayout(sl_blockSelectorOutputsPanel);
		
		blockSelectorOutputsLabel = new JLabel("Outputs");
		sl_blockSelectorOutputsPanel.putConstraint(SpringLayout.NORTH, blockSelectorOutputsLabel, 10, SpringLayout.NORTH, blockSelectorOutputsPanel);
		sl_blockSelectorOutputsPanel.putConstraint(SpringLayout.WEST, blockSelectorOutputsLabel, 10, SpringLayout.WEST, blockSelectorOutputsPanel);
		blockSelectorOutputsPanel.add(blockSelectorOutputsLabel);
		
		blockSelectorOutputsPanel2 = new JPanel();
		sl_blockSelectorOutputsPanel.putConstraint(SpringLayout.NORTH, blockSelectorOutputsPanel2, 10, SpringLayout.SOUTH, blockSelectorOutputsLabel);
		sl_blockSelectorOutputsPanel.putConstraint(SpringLayout.WEST, blockSelectorOutputsPanel2, 10, SpringLayout.WEST, blockSelectorOutputsPanel);
		sl_blockSelectorOutputsPanel.putConstraint(SpringLayout.SOUTH, blockSelectorOutputsPanel2, -10, SpringLayout.SOUTH, blockSelectorOutputsPanel);
		sl_blockSelectorOutputsPanel.putConstraint(SpringLayout.EAST, blockSelectorOutputsPanel2, -10, SpringLayout.EAST, blockSelectorOutputsPanel);
		blockSelectorOutputsPanel.add(blockSelectorOutputsPanel2);
		
		blockSelectorOutputsPanel2.setLayout(new GridLayout(0, 1, 0, 0));
		outputsList = new JList(new AbstractListModel() {
			String[] values = new String[] {"Multiply", "Printer", "Switch", "Sum", "PID Controller", "Clamp", "Maths Evaluator", "Logic Gate", "Comment", "Differencer", "Accumulator", "Threshold", "One shot", "Drives", "Propulsion", "Playback", "Custom propulsion in", "Generic block setter", "Anim. control", "Anim. position", "Anim. IK position", "Anim. IK rotation", "Anim. body look", "Anim. LINK edit", "LINK subobject", "LINK limb", "Behavior selector", "Maneuver selector"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		outputsList.setBorder(new LineBorder(new Color(128, 128, 128)));
		outputsList.setVisibleRowCount(0);
		outputsList.setFont(new Font("Tahoma", Font.PLAIN, 11));
		outputsList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		outputsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		outputsList.setFixedCellWidth(110);
		outputsList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 1) {
					JList target = (JList)me.getSource();
					int index = target.locationToIndex(me.getPoint());
					if (index >= 0) {
						Object item = target.getModel().getElementAt(index);
						Frame2.listClicked(item.toString());
					}
				}
			}
		});
		//blockSelectorOutputsPanel2.add(outputsList);
		
		outputsScrollList = new JScrollPane(outputsList);
		blockSelectorOutputsPanel2.add(outputsScrollList);
		
		
	}

	public static void loadBlockData(movingBox block) {
		blockTextLabel.setText("Current block: " + block.getType());
		textArea.setText(block.getText());
	}
	
	public static void textUpdated(DocumentEvent e) {
		Frame1.getSelectedBox().setText(textArea.getText());
		Frame1.updateBlocks();
	}
	
	public static void listClicked(String str) {
		Frame1.getSelectedBox().setType(str);
		loadBlockData(Frame1.getSelectedBox());
	}
}

class myDocumentListener implements DocumentListener {

	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		Frame2.textUpdated(e);
	}

	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		Frame2.textUpdated(e);
	}

	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
