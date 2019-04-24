package HumaineGroup.StatusResultNew;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;

public class StatusDetails extends JFrame {

	private JPanel contentPane;
	private JTextField storyIdtxt;
	private JTextField fnNametxt;
	String screenType = "Master";
	String screenType2 = "Master";
	String screenType3 = "Master";
	private JComboBox fnNameCmb2;
	private JComboBox empCmbBox;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	App app = new App();
	private JTextField storyIdtxt2;
	private JTextField subSystxt2;
	private JTextField moduleTxt2;
	private int selectedEmpId = 1487;
	private final Map<String,Integer> empDetailsMap = new HashMap<String, Integer>();
	boolean allowAction = true;
	boolean allowAction3 = true;
	private JTable table;
	Map<String,List<String>> moduleMap = new HashMap<String, List<String>>();
	List<String> functionList = new ArrayList<String>();
	List<String> functionList3 = new ArrayList<String>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatusDetails frame = new StatusDetails();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StatusDetails() {
		empDetailsMap.put("Thamilarasu",1487);
		empDetailsMap.put("Arunprasad",1113);
		empDetailsMap.put("Logesh",1145);
		empDetailsMap.put("Kalaimathi",1158);
		empDetailsMap.put("Pavithra",970);
		empDetailsMap.put("Vinothkumar",1400);
		empDetailsMap.put("Manikandan",1401);
		
		functionList.add("Supplier Category");
		functionList.add("Payment Term");
		functionList.add("Revenue Hierarchy");
		functionList.add("Match Condition Rule");
		moduleMap.put("Account Payable", functionList);
		
		functionList = new ArrayList<>();
		
		functionList.add("Payment Term2");
		functionList.add("Revenue Hierarchy2");
		functionList.add("Match Condition Rule2");
		moduleMap.put("Account Receivable", functionList);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		tabbedPane.setBounds(10, 0, 480, 440);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("INSERT", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblEnterStoryId = new JLabel("StoryId");
		lblEnterStoryId.setBounds(10, 11, 67, 21);
		panel.add(lblEnterStoryId);
		
		storyIdtxt = new JTextField();
		storyIdtxt.setBounds(77, 11, 124, 20);
		panel.add(storyIdtxt);
		storyIdtxt.setColumns(10);
		
		JLabel lblEnterXmlPath = new JLabel("Subsys");
		lblEnterXmlPath.setBounds(10, 43, 67, 21);
		panel.add(lblEnterXmlPath);
		
		final JComboBox subsysCmb = new JComboBox();
		subsysCmb.setBounds(77, 43, 124, 20);
		subsysCmb.addItem("finance");
		subsysCmb.addItem("asset");
		panel.add(subsysCmb);
		
		JLabel lblModule = new JLabel("Module");
		lblModule.setBounds(10, 75, 67, 21);
		panel.add(lblModule);
		
		final JComboBox moduleCmb = new JComboBox();
		moduleCmb.setBounds(77, 75, 124, 20);
		moduleCmb.addItem("Account Payable");
		moduleCmb.addItem("Account Receivable");
		moduleCmb.addItem("Procurement");
		panel.add(moduleCmb);
		
		JLabel lblFnname = new JLabel("FnName");
		lblFnname.setBounds(10, 107, 67, 17);
		panel.add(lblFnname);
		
		fnNametxt = new JTextField();
		fnNametxt.setColumns(10);
		fnNametxt.setBounds(77, 104, 124, 20);
		panel.add(fnNametxt);
		
		JLabel lblStartdate = new JLabel("StartDate");
		lblStartdate.setBounds(10, 135, 67, 21);
		panel.add(lblStartdate);
		
		final JDateChooser startDate = new JDateChooser();
		startDate.setBounds(77, 135, 124, 21);
		panel.add(startDate);
		
		JLabel lblEnddate = new JLabel("EndDate");
		lblEnddate.setBounds(10, 167, 67, 21);
		panel.add(lblEnddate);
		
		final JDateChooser endDate = new JDateChooser();
		endDate.setBounds(77, 167, 124, 21);
		panel.add(endDate);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 199, 67, 21);
		panel.add(lblStatus);
		
		final JComboBox statusCmb = new JComboBox();
		statusCmb.setBounds(77, 199, 124, 20);
		statusCmb.addItem("New");
		statusCmb.addItem("InProgress");
		statusCmb.addItem("Resolved");
		panel.add(statusCmb);
		
		JLabel lblInittable = new JLabel("InitTable");
		lblInittable.setBounds(10, 230, 67, 21);
		panel.add(lblInittable);
		
		final JComboBox initTableCmb = new JComboBox();
		initTableCmb.setBounds(77, 230, 124, 20);
		initTableCmb.addItem("dev");
		initTableCmb.addItem("prod");
		initTableCmb.addItem("live");
		panel.add(initTableCmb);
		
		JLabel lblInitsreen = new JLabel("InitScreen");
		lblInitsreen.setBounds(10, 260, 67, 21);
		panel.add(lblInitsreen);
		
		final JComboBox initScreenCmb = new JComboBox();
		initScreenCmb.setBounds(77, 261, 124, 20);
		initScreenCmb.addItem("dev");
		initScreenCmb.addItem("prod");
		initScreenCmb.addItem("live");
		panel.add(initScreenCmb);
		
		JLabel lblCurrtable = new JLabel("CurrTable");
		lblCurrtable.setBounds(10, 292, 67, 21);
		panel.add(lblCurrtable);
		
		final JComboBox currTableCmb = new JComboBox();
		currTableCmb.setBounds(77, 292, 124, 20);
		currTableCmb.addItem("dev");
		currTableCmb.addItem("prod");
		currTableCmb.addItem("live");
		panel.add(currTableCmb);
		
		JLabel lblCurrscreen = new JLabel("CurrScreen");
		lblCurrscreen.setBounds(10, 323, 67, 21);
		panel.add(lblCurrscreen);
		
		final JComboBox currScreenCmb = new JComboBox();
		currScreenCmb.setBounds(77, 323, 124, 20);
		currScreenCmb.addItem("dev");
		currScreenCmb.addItem("prod");
		currScreenCmb.addItem("live");
		panel.add(currScreenCmb);
		
		JLabel lblDone = new JLabel("% Done");
		lblDone.setBounds(10, 354, 67, 21);
		panel.add(lblDone);
		
		final JComboBox doneCmb = new JComboBox();
		doneCmb.setBounds(77, 354, 124, 20);
		doneCmb.addItem("10");
		doneCmb.addItem("20");
		doneCmb.addItem("30");
		doneCmb.addItem("40");
		doneCmb.addItem("50");
		doneCmb.addItem("60");
		doneCmb.addItem("70");
		doneCmb.addItem("80");
		doneCmb.addItem("90");
		doneCmb.addItem("100");
		panel.add(doneCmb);
		
		JLabel lblAppgenIssues = new JLabel("AppGen Issues");
		lblAppgenIssues.setBounds(207, 0, 130, 21);
		panel.add(lblAppgenIssues);
		
		final TextArea appGenArea = new TextArea();
		appGenArea.setBounds(207, 21, 260, 75);
		panel.add(appGenArea);
		
		JLabel lblCrIssues = new JLabel("CR Issues");
		lblCrIssues.setBounds(207, 95, 130, 17);
		panel.add(lblCrIssues);
		
		final TextArea changeReqArea = new TextArea();
		changeReqArea.setBounds(207, 113, 260, 75);
		panel.add(changeReqArea);
		
		JLabel lblTableissues = new JLabel("TableIssues");
		lblTableissues.setBounds(207, 190, 130, 14);
		panel.add(lblTableissues);

		final TextArea tableArea = new TextArea();
		tableArea.setBounds(207, 204, 260, 75);
		panel.add(tableArea);

		JLabel lblRemarks = new JLabel("Remarks");
		lblRemarks.setBounds(207, 285, 130, 14);
		panel.add(lblRemarks);

		final TextArea remarksArea = new TextArea();
		remarksArea.setBounds(207, 299, 260, 75);
		panel.add(remarksArea);
		
		Button button = new Button("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.insertData(Integer.parseInt(storyIdtxt.getText().split("/")[1]),screenType.toString(),
						storyIdtxt.getText().split("/")[0],
						subsysCmb.getSelectedItem().toString(),
						moduleCmb.getSelectedItem().toString(),
						fnNametxt.getText(),dateFormat.format(startDate.getDate()),
						dateFormat.format(endDate.getDate()),
						statusCmb.getSelectedItem().toString(),
						initTableCmb.getSelectedItem().toString(),
						initScreenCmb.getSelectedItem().toString(),
						currTableCmb.getSelectedItem().toString(),
						currScreenCmb.getSelectedItem().toString(),
						doneCmb.getSelectedItem().toString(),
						appGenArea.getText(),
						changeReqArea.getText(),
						tableArea.getText(),
						remarksArea.getText()
						);
			}
		});
		button.setBounds(286, 380, 70, 22);
		panel.add(button);
		
		final JRadioButton rdbtnMaster = new JRadioButton("Master");
		rdbtnMaster.setBounds(10, 382, 67, 23);
		rdbtnMaster.setSelected(true);
		panel.add(rdbtnMaster);
		
		final JRadioButton rdbtnTransaction = new JRadioButton("Transaction");
		rdbtnTransaction.setBounds(77, 381, 109, 23);
		panel.add(rdbtnTransaction);
		
		rdbtnMaster.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					rdbtnTransaction.setSelected(false);
					screenType = "Master";
			    }
			    else if (e.getStateChange() == ItemEvent.DESELECTED) {
			    	rdbtnTransaction.setSelected(true);
			    	screenType = "Transaction";
			    }
			}
		});
		rdbtnTransaction.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					rdbtnMaster.setSelected(false);
					screenType = "Transaction";
			    }
			    else if (e.getStateChange() == ItemEvent.DESELECTED) {
			    	rdbtnMaster.setSelected(true);
			    	screenType = "Master";
			    }
			}
		});
		
		tabbedPane.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		        if(tabbedPane.getSelectedIndex() == 1) {
		        	List<String> fnNameList = app.loadFunctionNameCmb(selectedEmpId);
		        	if(!fnNameList.isEmpty()) {
		        		for(int i=0;i<fnNameList.size();i++) {
		        			fnNameCmb2.addItem(fnNameList.get(i));
		        		}
		        	}
		        }
		        // Prints the string 3 times if there are 3 tabs etc
		    }
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("UPDATE", null, panel_1, null);
		
		
		JLabel label_2 = new JLabel("EmpId");
		label_2.setBounds(10, 11, 67, 21);
		panel_1.add(label_2);
		
		empCmbBox = new JComboBox();
		empCmbBox.setBounds(77, 11, 124, 20);
//		empCmbBox.addItem("1487");
//		empCmbBox.addItem("1113");//Arun
//		empCmbBox.addItem("1145");//Logesh
//		empCmbBox.addItem("1158");//Kalai
//		empCmbBox.addItem("970");//Pavi
		empCmbBox.addItem("Thamilarasu");
		empCmbBox.addItem("Arunprasad");
		empCmbBox.addItem("Logesh");
		empCmbBox.addItem("Kalaimathi");
		empCmbBox.addItem("Pavithra");
		empCmbBox.addItem("Vinothkumar");
		empCmbBox.addItem("Manikandan");
		panel_1.add(empCmbBox);
		
		storyIdtxt2 = new JTextField();
		storyIdtxt2.setColumns(10);
		storyIdtxt2.setBounds(173, 385, 109, 20);
		storyIdtxt2.setEditable(false);
		panel_1.add(storyIdtxt2);
		
		JLabel label_3 = new JLabel("Subsys");
		label_3.setBounds(10, 43, 67, 21);
		panel_1.add(label_3);
		
		subSystxt2 = new JTextField();
		subSystxt2.setColumns(10);
		subSystxt2.setBounds(77, 43, 124, 20);
		subSystxt2.setEditable(false);
		panel_1.add(subSystxt2);
		
		JLabel label_4 = new JLabel("Module");
		label_4.setBounds(10, 75, 67, 21);
		panel_1.add(label_4);
		
		moduleTxt2 = new JTextField();
		moduleTxt2.setColumns(10);
		moduleTxt2.setBounds(77, 75, 124, 20);
		moduleTxt2.setEditable(false);
		panel_1.add(moduleTxt2);
		
		JLabel label_5 = new JLabel("FnName");
		label_5.setBounds(10, 107, 67, 17);
		panel_1.add(label_5);
		
		fnNameCmb2 = new JComboBox();
		fnNameCmb2.setBounds(77, 106, 124, 20);
		panel_1.add(fnNameCmb2);
		
		JLabel label_6 = new JLabel("StartDate");
		label_6.setBounds(10, 135, 67, 21);
		panel_1.add(label_6);
		
		final JDateChooser startDate2 = new JDateChooser();
		startDate2.setBounds(77, 135, 124, 21);
		panel_1.add(startDate2);
		
		JLabel label_7 = new JLabel("EndDate");
		label_7.setBounds(10, 167, 67, 21);
		panel_1.add(label_7);
		
		final JDateChooser endDate2 = new JDateChooser();
		endDate2.setBounds(77, 167, 124, 21);
		panel_1.add(endDate2);
		
		JLabel label_8 = new JLabel("Status");
		label_8.setBounds(10, 199, 67, 21);
		panel_1.add(label_8);
		
		final JComboBox statusCmb2 = new JComboBox();
		statusCmb2.setBounds(77, 199, 124, 20);
		statusCmb2.addItem("New");
		statusCmb2.addItem("InProgress");
		statusCmb2.addItem("Resolved");
		panel_1.add(statusCmb2);
		
		JLabel label_9 = new JLabel("InitTable");
		label_9.setBounds(10, 230, 67, 21);
		panel_1.add(label_9);
		
		final JComboBox initTableCmb2 = new JComboBox();
		initTableCmb2.setBounds(77, 230, 124, 20);
		initTableCmb2.addItem("dev");
		initTableCmb2.addItem("prod");
		initTableCmb2.addItem("live");
		panel_1.add(initTableCmb2);
		
		JLabel label_24 = new JLabel("InitScreen");
		label_24.setBounds(10, 260, 67, 21);
		panel_1.add(label_24);
		
		final JComboBox initScreenCmb2 = new JComboBox();
		initScreenCmb2.setBounds(77, 261, 124, 20);
		initScreenCmb2.addItem("dev");
		initScreenCmb2.addItem("prod");
		initScreenCmb2.addItem("live");
		panel_1.add(initScreenCmb2);
		
		JLabel label_25 = new JLabel("CurrTable");
		label_25.setBounds(10, 292, 67, 21);
		panel_1.add(label_25);
		
		final JComboBox currTableCmb2 = new JComboBox();
		currTableCmb2.setBounds(77, 292, 124, 20);
		currTableCmb2.addItem("dev");
		currTableCmb2.addItem("prod");
		currTableCmb2.addItem("live");
		panel_1.add(currTableCmb2);
		
		JLabel label_26 = new JLabel("CurrScreen");
		label_26.setBounds(10, 323, 67, 21);
		panel_1.add(label_26);
		
		final JComboBox currScreenCmb2 = new JComboBox();
		currScreenCmb2.setBounds(77, 323, 124, 20);
		currScreenCmb2.addItem("dev");
		currScreenCmb2.addItem("prod");
		currScreenCmb2.addItem("live");
		panel_1.add(currScreenCmb2);
		
		JLabel label_27 = new JLabel("% Done");
		label_27.setBounds(10, 354, 67, 21);
		panel_1.add(label_27);
		
		final JComboBox doneCmb2 = new JComboBox();
		doneCmb2.setBounds(77, 354, 124, 20);
		doneCmb2.addItem("10");
		doneCmb2.addItem("20");
		doneCmb2.addItem("30");
		doneCmb2.addItem("40");
		doneCmb2.addItem("50");
		doneCmb2.addItem("60");
		doneCmb2.addItem("70");
		doneCmb2.addItem("80");
		doneCmb2.addItem("90");
		doneCmb2.addItem("100");
		panel_1.add(doneCmb2);
		
		JLabel label_28 = new JLabel("AppGen Issues");
		label_28.setBounds(207, 0, 130, 21);
		panel_1.add(label_28);
		
		final TextArea appGenArea2 = new TextArea();
		appGenArea2.setBounds(207, 21, 260, 75);
		panel_1.add(appGenArea2);
		
		JLabel label_29 = new JLabel("CR Issues");
		label_29.setBounds(207, 95, 130, 17);
		panel_1.add(label_29);
		
		final TextArea changeReqArea2 = new TextArea();
		changeReqArea2.setBounds(207, 113, 260, 75);
		panel_1.add(changeReqArea2);
		
		JLabel label_30 = new JLabel("TableIssues");
		label_30.setBounds(207, 190, 130, 14);
		panel_1.add(label_30);
		
		final TextArea tableArea2 = new TextArea();
		tableArea2.setBounds(207, 204, 260, 75);
		panel_1.add(tableArea2);
		
		JLabel label_31 = new JLabel("Remarks");
		label_31.setBounds(207, 285, 130, 14);
		panel_1.add(label_31);
		
		final TextArea remarksArea2 = new TextArea();
		remarksArea2.setBounds(207, 299, 260, 75);
		panel_1.add(remarksArea2);
		
		Button button_2 = new Button("Submit");
		button_2.setBounds(303, 380, 70, 22);
		panel_1.add(button_2);
		
		final JRadioButton rdbtnMaster2 = new JRadioButton("Master");
		rdbtnMaster2.setBounds(6, 383, 67, 23);
		rdbtnMaster2.setSelected(true);
		panel_1.add(rdbtnMaster2);
		
		final JRadioButton rdbtnTransaction2 = new JRadioButton("Transaction");
		rdbtnTransaction2.setBounds(73, 382, 109, 23);
		panel_1.add(rdbtnTransaction2);
		//-------------------------------------------------------------------------------------------------------------
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		tabbedPane.addTab("STATUS", null, panel_3, null);
		
		JLabel lblDeleteFunction = new JLabel("Delete Function Entry");
		lblDeleteFunction.setBounds(10, 43, 124, 21);
		panel_3.add(lblDeleteFunction);
		
		JLabel lblTeamStatusReport = new JLabel("Individual Report");
		lblTeamStatusReport.setBounds(10, 121, 124, 17);
		panel_3.add(lblTeamStatusReport);
		
		JComboBox empIdCmb3 = new JComboBox();
		empIdCmb3.setBounds(158, 119, 124, 20);
		panel_3.add(empIdCmb3);
		
		JLabel lblStatusReport = new JLabel("Status Report");
		lblStatusReport.setBounds(183, 11, 89, 21);
		panel_3.add(lblStatusReport);
		
		final JRadioButton rdbtnMaster3 = new JRadioButton("Master");
		rdbtnMaster3.setSelected(true);
		rdbtnMaster3.setBounds(158, 75, 67, 23);
		panel_3.add(rdbtnMaster3);
		
		final JRadioButton rdbtnTransaction3 = new JRadioButton("Transaction");
		rdbtnTransaction3.setBounds(248, 75, 109, 23);
		panel_3.add(rdbtnTransaction3);
		
		rdbtnMaster3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					rdbtnTransaction3.setSelected(false);
					screenType3 = "Master";
			    }
			    else if (e.getStateChange() == ItemEvent.DESELECTED) {
			    	rdbtnTransaction3.setSelected(true);
			    	screenType3 = "Transaction";
			    }
			}
		});
		rdbtnTransaction3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					rdbtnMaster3.setSelected(false);
					screenType3 = "Transaction";
			    }
			    else if (e.getStateChange() == ItemEvent.DESELECTED) {
			    	rdbtnMaster3.setSelected(true);
			    	screenType3 = "Master";
			    }
			}
		});
		
		final JComboBox moduleCmb3 = new JComboBox();
		moduleCmb3.setBounds(158, 43, 124, 20);
		panel_3.add(moduleCmb3);
		
		moduleMap.keySet().forEach(action -> {
			moduleCmb3.addItem(action);
		});
		moduleCmb3.setSelectedItem("Account Payable");
//		for(int j=0;j<moduleMap.keySet().size()-1;j++) {
//			moduleCmb3.addItem(moduleMap.keySet().);
//		}
		
		final JComboBox fnNameCmb3 = new JComboBox();
		fnNameCmb3.setBounds(305, 43, 124, 20);
		panel_3.add(fnNameCmb3);

		functionList3 = moduleMap.get("Account Payable");
		allowAction3 = false;
		fnNameCmb3.removeAllItems();
		allowAction3 = true;
		for(int i=0;i<functionList3.size();i++) {
			fnNameCmb3.addItem(functionList3.get(i));
		}
		
		moduleCmb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(allowAction3) {
					functionList3 = moduleMap.get(moduleCmb3.getSelectedItem());
					allowAction3 = false;
					fnNameCmb3.removeAllItems();
					allowAction3 = true;
					for(int i=0;i<functionList3.size();i++) {
						fnNameCmb3.addItem(functionList3.get(i));
					}
				}
			}
		});
		
		Button deleteBtn3 = new Button("Delete");
		deleteBtn3.setBounds(363, 76, 70, 22);
		panel_3.add(deleteBtn3);
		
		table = new JTable();
		table.setBounds(27, 157, 419, 164);
		panel_3.add(table);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(429, 157, 17, 164);
		panel_3.add(scrollBar);
		
		JButton btnDownload = new JButton("Team Status Download");
		btnDownload.setBounds(152, 358, 182, 23);
		panel_3.add(btnDownload);
		
		JButton btnViewInGrid = new JButton("View in Grid");
		btnViewInGrid.setBounds(305, 118, 109, 23);
		panel_3.add(btnViewInGrid);
		
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.downloadDetails();
			}
		});
		
		deleteBtn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				app.deleteRecord(fnNameCmb3.getSelectedItem(),screenType3);
			}
		});
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.updateData(Integer.parseInt(storyIdtxt2.getText().split("/")[1]),screenType2.toString(),
						storyIdtxt2.getText().split("/")[0],
						subSystxt2.getText(),
						moduleTxt2.getText(),
						fnNameCmb2.getSelectedItem().toString(),
						dateFormat.format(startDate2.getDate()),
						dateFormat.format(endDate2.getDate()),
						statusCmb2.getSelectedItem().toString(),
						initTableCmb2.getSelectedItem().toString(),
						initScreenCmb2.getSelectedItem().toString(),
						currTableCmb2.getSelectedItem().toString(),
						currScreenCmb2.getSelectedItem().toString(),
						doneCmb2.getSelectedItem().toString(),
						appGenArea2.getText(),
						changeReqArea2.getText(),
						tableArea2.getText(),
						remarksArea2.getText()
						);
			}
		});
		
		rdbtnMaster2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					rdbtnTransaction2.setSelected(false);
					rdbtnMaster2.setSelected(true);
					screenType2 = "Master";
					//=========change in future
					Map<String, String> detailsMap = app.loadDetails(fnNameCmb2.getSelectedItem(),screenType2);
					if(!detailsMap.isEmpty()) {
						if(detailsMap.get("screenType").equals("Master")){
							rdbtnMaster2.setSelected(true);
							rdbtnTransaction2.setSelected(false);
						}else {
							rdbtnMaster2.setSelected(false);
							rdbtnTransaction2.setSelected(true);
						}
						storyIdtxt2.setText(detailsMap.get("storyIdtxt2"));
						subSystxt2.setText(detailsMap.get("subSystxt2"));
						moduleTxt2.setText(detailsMap.get("moduleTxt2"));
						
						Calendar cal;
						try {
						    cal = Calendar.getInstance();
						    cal.setTime(dateFormat.parse(detailsMap.get("startDate2")));
						    startDate2.setCalendar(cal);
						    cal = Calendar.getInstance();
						    cal.setTime(dateFormat.parse(detailsMap.get("endDate2")));
						    endDate2.setCalendar(cal);
						} catch (ParseException exception) {
							exception.printStackTrace();
						}
						
//						startDate2.setCalendar(arg0);Text(detailsMap.get("storyIdCmb2"));
//						endDate2.setText(detailsMap.get("storyIdCmb2"));
						statusCmb2.setSelectedItem(detailsMap.get("statusCmb2"));
						initTableCmb2.setSelectedItem(detailsMap.get("initTableCmb2"));
						initScreenCmb2.setSelectedItem(detailsMap.get("initScreenCmb2"));
						currTableCmb2.setSelectedItem(detailsMap.get("currTableCmb2"));
						currScreenCmb2.setSelectedItem(detailsMap.get("currScreenCmb2"));
						doneCmb2.setSelectedItem(detailsMap.get("doneCmb2"));
						appGenArea2.setText(detailsMap.get("appGenArea2"));
						changeReqArea2.setText(detailsMap.get("changeReqArea2"));
						tableArea2.setText(detailsMap.get("tableArea2"));
						remarksArea2.setText(detailsMap.get("remarksArea2"));
						
					}else {
						if(rdbtnMaster2.isSelected()) {
							rdbtnTransaction2.setSelected(true);
							screenType2 = "Transaction";
						}else {
							rdbtnMaster2.setSelected(true);
							screenType2 = "Master";
						}
//						JOptionPane.showMessageDialog(null, "No Data Found!!! Change fnName or screenType");
					}
					//=========change in future
			    }
			    /*else if (e.getStateChange() == ItemEvent.DESELECTED) {
			    	rdbtnTransaction2.setSelected(true);
			    	screenType2 = "Transaction";
			    }*/
			}
		});
		rdbtnTransaction2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					rdbtnMaster2.setSelected(false);
					rdbtnTransaction2.setSelected(true);
					screenType2 = "Transaction";
					//=========change in future
					Map<String, String> detailsMap = app.loadDetails(fnNameCmb2.getSelectedItem(),screenType2);
					if(!detailsMap.isEmpty()) {
						if(detailsMap.get("screenType").equals("Master")){
							rdbtnMaster2.setSelected(true);
							rdbtnTransaction2.setSelected(false);
						}else {
							rdbtnMaster2.setSelected(false);
							rdbtnTransaction2.setSelected(true);
						}
						storyIdtxt2.setText(detailsMap.get("storyIdtxt2"));
						subSystxt2.setText(detailsMap.get("subSystxt2"));
						moduleTxt2.setText(detailsMap.get("moduleTxt2"));
						
						Calendar cal;
						try {
						    cal = Calendar.getInstance();
						    cal.setTime(dateFormat.parse(detailsMap.get("startDate2")));
						    startDate2.setCalendar(cal);
						    cal = Calendar.getInstance();
						    cal.setTime(dateFormat.parse(detailsMap.get("endDate2")));
						    endDate2.setCalendar(cal);
						} catch (ParseException exception1) {
							exception1.printStackTrace();
						}
						
//						startDate2.setCalendar(arg0);Text(detailsMap.get("storyIdCmb2"));
//						endDate2.setText(detailsMap.get("storyIdCmb2"));
						statusCmb2.setSelectedItem(detailsMap.get("statusCmb2"));
						initTableCmb2.setSelectedItem(detailsMap.get("initTableCmb2"));
						initScreenCmb2.setSelectedItem(detailsMap.get("initScreenCmb2"));
						currTableCmb2.setSelectedItem(detailsMap.get("currTableCmb2"));
						currScreenCmb2.setSelectedItem(detailsMap.get("currScreenCmb2"));
						doneCmb2.setSelectedItem(detailsMap.get("doneCmb2"));
						appGenArea2.setText(detailsMap.get("appGenArea2"));
						changeReqArea2.setText(detailsMap.get("changeReqArea2"));
						tableArea2.setText(detailsMap.get("tableArea2"));
						remarksArea2.setText(detailsMap.get("remarksArea2"));
					}else {
						if(rdbtnMaster2.isSelected()) {
							rdbtnTransaction2.setSelected(true);
							screenType2 = "Transaction";
						}else {
							rdbtnMaster2.setSelected(true);
							screenType2 = "Master";
						}
//						JOptionPane.showMessageDialog(null, "No Data Found!!! Change fnName or screenType");
					}
					//=========change in future
			    }
			    /*else if (e.getStateChange() == ItemEvent.DESELECTED) {
			    	rdbtnMaster2.setSelected(true);
			    	screenType2 = "Master";
			    }*/
			}
		});
		
		empCmbBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedEmpId = empDetailsMap.get(empCmbBox.getSelectedItem());
				allowAction = false;
				fnNameCmb2.removeAllItems();
				allowAction = true;
				List<String> fnNameList = app.loadFunctionNameCmb(selectedEmpId);
	        	if(!fnNameList.isEmpty()) {
	        		for(int i=0;i<fnNameList.size();i++) {
	        			fnNameCmb2.addItem(fnNameList.get(i));
	        		}
	        	}
			}
		});

		fnNameCmb2.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent event) {
		    	if(allowAction) {
		    		Map<String, String> detailsMap = app.loadDetails(fnNameCmb2.getSelectedItem(),screenType2);
					if(!detailsMap.isEmpty()) {
						if(detailsMap.get("screenType").equals("Master")){
							rdbtnMaster2.setSelected(true);
							rdbtnTransaction2.setSelected(false);
						}else {
							rdbtnMaster2.setSelected(false);
							rdbtnTransaction2.setSelected(true);
						}
						storyIdtxt2.setText(detailsMap.get("storyIdtxt2"));
						subSystxt2.setText(detailsMap.get("subSystxt2"));
						moduleTxt2.setText(detailsMap.get("moduleTxt2"));
						
						Calendar cal;
						try {
						    cal = Calendar.getInstance();
						    cal.setTime(dateFormat.parse(detailsMap.get("startDate2")));
						    startDate2.setCalendar(cal);
						    cal = Calendar.getInstance();
						    cal.setTime(dateFormat.parse(detailsMap.get("endDate2")));
						    endDate2.setCalendar(cal);
						} catch (ParseException e) {
						    e.printStackTrace();
						}
						
//						startDate2.setCalendar(arg0);Text(detailsMap.get("storyIdCmb2"));
//						endDate2.setText(detailsMap.get("storyIdCmb2"));
						statusCmb2.setSelectedItem(detailsMap.get("statusCmb2"));
						initTableCmb2.setSelectedItem(detailsMap.get("initTableCmb2"));
						initScreenCmb2.setSelectedItem(detailsMap.get("initScreenCmb2"));
						currTableCmb2.setSelectedItem(detailsMap.get("currTableCmb2"));
						currScreenCmb2.setSelectedItem(detailsMap.get("currScreenCmb2"));
						doneCmb2.setSelectedItem(detailsMap.get("doneCmb2"));
						appGenArea2.setText(detailsMap.get("appGenArea2"));
						changeReqArea2.setText(detailsMap.get("changeReqArea2"));
						tableArea2.setText(detailsMap.get("tableArea2"));
						remarksArea2.setText(detailsMap.get("remarksArea2"));
					}else {
						if(rdbtnMaster2.isSelected()) {
							rdbtnTransaction2.setSelected(true);
							screenType2 = "Transaction";
						}else {
							rdbtnMaster2.setSelected(true);
							screenType2 = "Master";
						}
//						JOptionPane.showMessageDialog(null, "No Data Found!!! Change fnName or screenType");
					}
		    	}
			}
		});
	}
}
