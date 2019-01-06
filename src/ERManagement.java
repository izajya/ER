import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import DataStructures.DoublyLinkedList;

/**
 * This class combines view (GUI) and controller
 * 
 * @author Ching Ching Huang, Yvonne Tran
 *
 */
public class ERManagement implements ActionListener, KeyListener {

	private JFrame frmEmergencyRoomManagement;
	// four main functions of the management system: add, discharge, search, and
	// change
	private final JButton btnAdd = new JButton("Add");
	private final JButton btnDischarge = new JButton("Discharge");
	private final JButton btnSearch = new JButton("Search");
	private final JButton btnChange = new JButton("Update");

	// Label to show the current pateint
	private JLabel lblCurrentPatient = new JLabel("Current Patient: ");

	// Components in ADD
	// LABELS to display information
	private JLabel lblFirstName_Add = new JLabel("First Name:");
	private JLabel lblLastName_Add = new JLabel("Last Name: ");
	private JLabel lblSex_Add = new JLabel("Sex: ");
	private JLabel lblAge_Add = new JLabel("Age: ");
	private JLabel lblEnterReason_Add = new JLabel("Reason of Entering: ");
	private JLabel lblLevel_Add = new JLabel("Level of Injury:");

	// OTHER COMPONENTS
	private JTextField firstNameTF = new JTextField();
	private JTextField lastNameTF = new JTextField();
	private JComboBox<String> sexComboBox_Add = new JComboBox<String>();
	private JComboBox<Integer> ageComboBox_Add = new JComboBox<Integer>();
	private JComboBox<String> comboBox_Add = new JComboBox<String>();
	private JComboBox<Integer> levelComboBox_Add = new JComboBox<Integer>();
	private JButton btnConfirm_Add = new JButton("Confirm");
	private JButton btnCancel_Add = new JButton("Cancel");

	// Components in change level
	private JComboBox<String> arrivalPatient_ComboBox = new JComboBox<String>();
	private JButton btnConfirm_change = new JButton("Confirm");
	private JComboBox<Integer> changeLevelComboBox = new JComboBox<Integer>();
	private JLabel lblChangeInjuryLevel = new JLabel("Patient name:");
	private JLabel lblNewLevelOf = new JLabel("New Level of Injury:");
	private final JButton btnCancel_change = new JButton("Cancel");

	// discharge
	// Labels to display information in discharge
	private JLabel lblDischargePatient = new JLabel("Discharge Patient: ");
	private JLabel lblReasonDischarge_Discharge = new JLabel("Reason for Discharge: ");
	// other components
	private JComboBox<String> comboBox_Discharge = new JComboBox<String>();
	private JButton btnConfirm_Discharge = new JButton("Confirm");

	// search
	// LABELS
	private JLabel lblSearchPatient = new JLabel("Search Patient: ");
	private JLabel lblSex_Search = new JLabel("Sex: ");
	private JLabel lblAge_Search = new JLabel("Age: ");
	private JLabel lblReasonEntrance_Search = new JLabel("Reason of Entering: ");
	private JLabel lblReasonDischarge_Search = new JLabel("Reason of Discharge: ");
	// other components
	private JComboBox<String> comboBox_Search = new JComboBox<String>();

	// the logic of the management system
	private Logic logic = new Logic();
	private int count = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ERManagement window = new ERManagement();
					window.frmEmergencyRoomManagement.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ERManagement() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		createFrame();
		createAdd();
		createUpdate();
		createDischarge();
		createSearch();
	}
	
	/**
	 * Adds a patient to the priority queue.
	 */
	private void addPatient() {
		if (firstNameTF.getText() != "" || lastNameTF.getText() != "") {
			String name = firstNameTF.getText() + " " + lastNameTF.getText();
			String sex = (String) sexComboBox_Add.getSelectedItem();
			int age = (int) ageComboBox_Add.getSelectedItem();
			int level = (int) levelComboBox_Add.getSelectedItem();
			String reason = (String) comboBox_Add.getSelectedItem();

			// inserts the patient into the priority queue
			// from the method in the logic class.
			Patient newPateint = logic.insertPatient(name, sex, age, level, reason, count);
			// add name in the arrival patient combo box to search
			arrivalPatient_ComboBox.addItem(newPateint.getName());

			// increment the count that represents the
			// patient number (which is used in calculating
			// first-come first-serve.)
			count++;
		}

		// display the current patient at the top.
		showCurrentPatient();
	}

	/**
	 * Show the current patient at the top of the application
	 */
	private void showCurrentPatient() {
		Patient currPatient = logic.getCurrentPatient();
		if (currPatient != null) {
			lblCurrentPatient.setText("Current Patient: " + currPatient.getName());
			lblDischargePatient.setText("Discharge Patient: " + currPatient.getName());
		} else {
			// if there are no more patients to treat,
			// reset the current patient.
			lblCurrentPatient.setText("Current Patient: ");
			lblDischargePatient.setText("Discharge Patient: ");
		}
	}

	/**
	 * Adds a patient that has been treated to the combo box for searching.
	 */
	private void addDischargePatient() {
		Patient patient = logic.getCurrentPatient();
		if (patient != null) {
			// add the patient to the combo boc
			comboBox_Search.addItem(patient.getName());
			//remove the pateint from change combo box
			arrivalPatient_ComboBox.removeItem(patient.getName());
			// set the reason for discharge.
			String reason = (String) comboBox_Discharge.getSelectedItem();
			logic.getCurrentPatient().setDischrage(reason);
		}
	}

	/**
	 * Search a patient through a linked list and display the patient information.
	 */
	private void searchPatient() {
		for (int j = 0; j < logic.dischargeList.size(); j++) {
			if (comboBox_Search.getSelectedItem().toString().equals(logic.dischargeList.get(j).getName().toString())) {
				lblSex_Search.setText("Sex: " + (String) logic.getDischarge().get(j).getSex());
				lblAge_Search.setText("Age: " + logic.getDischarge().get(j).getAge());
				lblReasonEntrance_Search.setText("Reason of Entrance: " + logic.getDischarge().get(j).getEnter());
				lblReasonDischarge_Search.setText("Reason of Discharge: " + logic.getDischarge().get(j).getDischarge());
			}
		}
	}

	/**
	 * Get the new injury level of a patient and update the information
	 */
	private void changeLevel() {
		//get the patient's name
		String name = (String) arrivalPatient_ComboBox.getSelectedItem();
		//get the list of all patients in the ER
		DoublyLinkedList<Patient> arrivalList = logic.getArrival();
		//search the patient and update the injury level
		for (int i = 0; i < arrivalList.size(); i++) {
			if (name.equals(arrivalList.get(i).getName())) {
				int level = Integer.parseInt((String) changeLevelComboBox.getSelectedItem());
				logic.changeLevel(arrivalList.get(i), level);
			}
		}
	}
	
	// below is mostly GUI work

	/**
	 * A method that makes the components in the add-a-patient section editable and
	 * makes the text blue.
	 */
	public void setAdd() {
		lblLastName_Add.setForeground(Color.BLUE);
		lblFirstName_Add.setForeground(Color.BLUE);
		lblSex_Add.setForeground(Color.BLUE);
		lblAge_Add.setForeground(Color.BLUE);
		lblEnterReason_Add.setForeground(Color.BLUE);
		lblLevel_Add.setForeground(Color.BLUE);

		firstNameTF.setBackground(Color.WHITE);
		lastNameTF.setBackground(Color.WHITE);
		firstNameTF.setEnabled(true);
		lastNameTF.setEnabled(true);
		sexComboBox_Add.setEnabled(true);
		ageComboBox_Add.setEnabled(true);
		comboBox_Add.setEnabled(true);
		levelComboBox_Add.setEnabled(true);
		btnConfirm_Add.setEnabled(true);
		btnCancel_Add.setEnabled(true);

		// the cursor should automatically start
		// in the "first name" j text field once
		// the user clicks on or presses enter on
		// the "Add" button.
		firstNameTF.requestFocusInWindow();
	}

	/**
	 * Makes components un-editable and text gray for the add section.
	 */
	public void desetAdd() {
		lblLastName_Add.setForeground(Color.LIGHT_GRAY);
		lblFirstName_Add.setForeground(Color.LIGHT_GRAY);
		lblSex_Add.setForeground(Color.LIGHT_GRAY);
		lblAge_Add.setForeground(Color.LIGHT_GRAY);
		lblEnterReason_Add.setForeground(Color.LIGHT_GRAY);
		lblLevel_Add.setForeground(Color.LIGHT_GRAY);

		firstNameTF.setBackground(Color.LIGHT_GRAY);
		lastNameTF.setBackground(Color.LIGHT_GRAY);
		firstNameTF.setEnabled(false);
		lastNameTF.setEnabled(false);
		sexComboBox_Add.setEnabled(false);
		ageComboBox_Add.setEnabled(false);
		comboBox_Add.setEnabled(false);
		levelComboBox_Add.setEnabled(false);
		btnConfirm_Add.setEnabled(false);
		btnCancel_Add.setEnabled(false);

		// all components should reset
		firstNameTF.setText("");
		lastNameTF.setText("");
		sexComboBox_Add.setSelectedIndex(0);
		ageComboBox_Add.setSelectedIndex(0);
		levelComboBox_Add.setSelectedIndex(0);
		comboBox_Add.setSelectedIndex(0);
	}

	/**
	 * Enable change section
	 */
	private void setUpdate() {
		// display change section for the users to interact with
		lblChangeInjuryLevel.setForeground(Color.BLUE);
		lblNewLevelOf.setForeground(Color.BLUE);
		arrivalPatient_ComboBox.setEnabled(true);
		changeLevelComboBox.setEnabled(true);

		if (logic.getCurrentPatient() != null){
			btnCancel_change.setEnabled(true);
			btnConfirm_change.setEnabled(true);
		}

		arrivalPatient_ComboBox.requestFocusInWindow();
	}

	/**
	 * Make this section un-editable
	 */
	private void desetUpdate() {
		lblChangeInjuryLevel.setForeground(Color.LIGHT_GRAY);
		lblNewLevelOf.setForeground(Color.LIGHT_GRAY);
		arrivalPatient_ComboBox.setSelectedIndex(0);
		arrivalPatient_ComboBox.setEnabled(false);
		changeLevelComboBox.setSelectedIndex(0);
		changeLevelComboBox.setEnabled(false);
		btnCancel_change.setEnabled(false);
		btnConfirm_change.setEnabled(false);
	}

	/**
	 * A method that makes the components in the discharge-a-patient section
	 * editable and makes the text blue.
	 */
	public void setDischarge() {
		lblDischargePatient.setForeground(Color.BLUE);
		lblReasonDischarge_Discharge.setForeground(Color.BLUE);

		comboBox_Discharge.setEnabled(true);

		if (logic.getCurrentPatient() != null){
			btnConfirm_Discharge.setEnabled(true);
		}

		// the cursor should automatically be
		// focused on the combo box of the
		// discharge section once a user clicks
		// or presses enter on the "Discharge" button.
		comboBox_Discharge.requestFocusInWindow();
	}

	/**
	 * Makes components un-editable and text gray for the discharge section.
	 */
	public void desetDischarge() {
		lblDischargePatient.setForeground(Color.LIGHT_GRAY);
		lblReasonDischarge_Discharge.setForeground(Color.LIGHT_GRAY);
		comboBox_Discharge.setEnabled(false);
		btnConfirm_Discharge.setEnabled(false);
	}

	/**
	 * A method that makes the components in the search-a-patient section editable
	 * and makes the text blue.
	 */
	public void setSearch() {
		lblSearchPatient.setForeground(Color.BLUE);
		lblSex_Search.setForeground(Color.BLUE);
		lblAge_Search.setForeground(Color.BLUE);
		lblReasonEntrance_Search.setForeground(Color.BLUE);
		lblReasonDischarge_Search.setForeground(Color.BLUE);

		comboBox_Search.setEnabled(true);
		comboBox_Search.requestFocusInWindow();
	}

	/**
	 * Makes components un-editable and text gray for the search section.
	 */
	public void desetSearch() {
		lblSearchPatient.setForeground(Color.LIGHT_GRAY);
		lblSex_Search.setForeground(Color.LIGHT_GRAY);
		lblAge_Search.setForeground(Color.LIGHT_GRAY);
		lblReasonEntrance_Search.setForeground(Color.LIGHT_GRAY);
		lblReasonDischarge_Search.setForeground(Color.LIGHT_GRAY);

		comboBox_Search.setEnabled(false);
	}

	/**
	 * Makes the confirm pop up that is made after pressing the confirmation button
	 * in the add-a-patient section.
	 */
	private void makeConfirmPopup() {
		// if the user didn't put names, pop up a warning message
		if (firstNameTF.getText().equals(null) || firstNameTF.getText().equals("") 
				|| lastNameTF.getText().equals(null) || lastNameTF.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter valid name", "Warning", 
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		String message = "Confirm " + firstNameTF.getText() + " " + lastNameTF.getText() + " needs treatment for "
				+ comboBox_Add.getSelectedItem() + "?";

		// chocies:
		int choice = JOptionPane.showOptionDialog(null, message, "No", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);

		// if the user clicks "no" then
		// they will be able to go back
		// to the application and
		// edit their input.
		if (choice != JOptionPane.NO_OPTION) {
			// adds a patient to the priority queue
			addPatient();
			desetAdd();
		}
	}

	/**
	 * a pop up window in change section to double check the information is correct
	 */
	private void confirmWindowForChange() {
		//if user didn't select a patient, show pop up window to warn the user
		if(arrivalPatient_ComboBox.getSelectedItem().equals("")) {
			//pop up window warning
			JOptionPane.showMessageDialog(null, "Please select a patient", "Warning", 
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		//display message on the pop up window
		String message = "Confirm " + arrivalPatient_ComboBox.getSelectedItem() + "'s updated injury level to "
				+ changeLevelComboBox.getSelectedItem() + "?";

		// chocies:
		int choice = JOptionPane.showOptionDialog(null, message, "No", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);

		//if user didn't click no, update the information
		if (choice != JOptionPane.NO_OPTION) {
			// adds a patient to the priority queue
			changeLevel();
			desetUpdate();
		}
	}
	
	/**
	 * Creates the frame, 3 main buttons, and the JLabel for the current patient.
	 */
	public void createFrame() {
		// GUI - - - - - - - - - - - - - - - - - - - - -
		frmEmergencyRoomManagement = new JFrame();
		frmEmergencyRoomManagement.setTitle("Emergency Room Management System");
		frmEmergencyRoomManagement.setBounds(100, 100, 550, 560);
		frmEmergencyRoomManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEmergencyRoomManagement.getContentPane().setLayout(null);
		lblCurrentPatient.setForeground(Color.BLUE);
		lblCurrentPatient.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		// add, delete, and search buttons
		lblCurrentPatient.setBounds(6, 3, 462, 26);
		frmEmergencyRoomManagement.getContentPane().add(lblCurrentPatient);
		btnAdd.setBounds(6, 34, 117, 29);
		frmEmergencyRoomManagement.getContentPane().add(btnAdd);
		btnDischarge.setBounds(283, 34, 117, 29);
		frmEmergencyRoomManagement.getContentPane().add(btnDischarge);
		btnSearch.setBounds(427, 34, 117, 29);
		frmEmergencyRoomManagement.getContentPane().add(btnSearch);
		// - - - - - - - - - - - - - - - - - - - - - GUI

		// Add button and key listeners
		btnAdd.addActionListener(this);
		btnChange.addActionListener(this);
		btnDischarge.addActionListener(this);
		btnSearch.addActionListener(this);

		btnAdd.addKeyListener(this);
		btnChange.addKeyListener(this);
		btnDischarge.addKeyListener(this);
		btnSearch.addKeyListener(this);
	}

	/**
	 * Creates the add-a-patient section.
	 */
	public void createAdd() {
		// GUI - - - - - - - - - - - - - - - - - - - - -

		// first name label and text field
		lblFirstName_Add.setBounds(6, 86, 90, 16);
		frmEmergencyRoomManagement.getContentPane().add(lblFirstName_Add);
		firstNameTF.setBounds(93, 81, 163, 26);
		frmEmergencyRoomManagement.getContentPane().add(firstNameTF);
		firstNameTF.setColumns(10);
		// last name label and text field
		lblLastName_Add.setBounds(275, 86, 74, 16);
		frmEmergencyRoomManagement.getContentPane().add(lblLastName_Add);
		lastNameTF = new JTextField();
		lastNameTF.setBounds(361, 81, 163, 26);
		frmEmergencyRoomManagement.getContentPane().add(lastNameTF);
		lastNameTF.setColumns(10);
		// sex label and combo box
		lblSex_Add.setBounds(6, 114, 40, 16);
		frmEmergencyRoomManagement.getContentPane().add(lblSex_Add);
		sexComboBox_Add.setModel(new DefaultComboBoxModel<String>(new String[] { "Male", "Female" }));
		sexComboBox_Add.setBounds(42, 110, 100, 27);
		frmEmergencyRoomManagement.getContentPane().add(sexComboBox_Add);
		// age label and combo box
		lblAge_Add.setBounds(192, 114, 40, 16);
		frmEmergencyRoomManagement.getContentPane().add(lblAge_Add);
		ageComboBox_Add.setBounds(224, 110, 77, 27);
		frmEmergencyRoomManagement.getContentPane().add(ageComboBox_Add);
		for (int i = 0; i < 101; i++) {
			ageComboBox_Add.addItem(i);
		}
		// reason for entrance combo box
		lblEnterReason_Add.setBounds(6, 142, 127, 16);
		frmEmergencyRoomManagement.getContentPane().add(lblEnterReason_Add);
		// most frequent reasons for ER entrances
		comboBox_Add.setModel(new DefaultComboBoxModel<String>(new String[] { "Abdominal Pain",
				"Accidents and Injuries", "Adverse Effects and Complications of Medical Treatment", "Chest Pain",
				"Chronic Obstructive Pulmonary Disease", "Heart Disease", "Pneumonia", "Spinal Disorders", "Stroke",
		"Urinary Tract Infection" }));
		comboBox_Add.setBounds(145, 138, 381, 27);
		frmEmergencyRoomManagement.getContentPane().add(comboBox_Add);
		// level label and combo box
		lblLevel_Add.setBounds(346, 114, 100, 16);
		frmEmergencyRoomManagement.getContentPane().add(lblLevel_Add);
		for (int i = 1; i < 11; i++) {
			levelComboBox_Add.addItem(i);
		}
		levelComboBox_Add.setBounds(451, 110, 74, 27);
		frmEmergencyRoomManagement.getContentPane().add(levelComboBox_Add);
		// buttons
		btnConfirm_Add.setBounds(270, 177, 117, 29);
		frmEmergencyRoomManagement.getContentPane().add(btnConfirm_Add);
		btnCancel_Add.setBounds(409, 177, 117, 29);
		frmEmergencyRoomManagement.getContentPane().add(btnCancel_Add);

		// - - - - - - - - - - - - - - - - - - - - - GUI

		// adding action and key listeners
		btnCancel_Add.addActionListener(this);
		btnConfirm_Add.addActionListener(this);

		btnConfirm_Add.addKeyListener(this);
		btnCancel_Add.addKeyListener(this);

		// setting texts & components to not-editable to initiate the
		// program.
		desetAdd();
	}

	/**
	 * Creates the discharge-a-patient section.
	 */
	public void createDischarge() {
		// GUI - - - - - - - - - - - - - - - - - - - - -
		// label and combo box
		lblDischargePatient.setBounds(6, 340, 462, 16);
		frmEmergencyRoomManagement.getContentPane().add(lblDischargePatient);
		lblReasonDischarge_Discharge.setBounds(6, 368, 149, 16);
		frmEmergencyRoomManagement.getContentPane().add(lblReasonDischarge_Discharge);
		comboBox_Discharge.setModel(new DefaultComboBoxModel(new String[] { "Transfer", "Healed", "Death" }));
		comboBox_Discharge.setBounds(156, 364, 188, 27);
		frmEmergencyRoomManagement.getContentPane().add(comboBox_Discharge);
		btnConfirm_Discharge.setBounds(364, 363, 117, 29);
		frmEmergencyRoomManagement.getContentPane().add(btnConfirm_Discharge);
		// - - - - - - - - - - - - - - - - - - - - - GUI

		// adding action and key listeners
		btnConfirm_Discharge.addActionListener(this);
		btnConfirm_Discharge.addKeyListener(this);

		// setting texts & combo box to not-editable to initiate the
		// program.
		desetDischarge();
	}

	/**
	 * Create a change level section
	 */
	private void createUpdate() {
		// GUI - - - - - - - - - - - - - - - - - - - - -
		// create a label for changing the injury level of patient
		lblChangeInjuryLevel.setBounds(6, 239, 90, 16);
		frmEmergencyRoomManagement.getContentPane().add(lblChangeInjuryLevel);
		lblNewLevelOf.setBounds(6, 267, 136, 16);
		frmEmergencyRoomManagement.getContentPane().add(lblNewLevelOf);
		// add "" as the first item in the combo box
		arrivalPatient_ComboBox.addItem("");
		// the values in combo box is 1 to 10
		changeLevelComboBox
		.setModel(new DefaultComboBoxModel(new String[] 
				{ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
		changeLevelComboBox.setBounds(141, 263, 90, 27);
		frmEmergencyRoomManagement.getContentPane().add(changeLevelComboBox);
		// confirm button in change
		btnConfirm_change.setBounds(270, 262, 117, 29);
		frmEmergencyRoomManagement.getContentPane().add(btnConfirm_change);
		btnChange.setBounds(143, 34, 117, 29);
		frmEmergencyRoomManagement.getContentPane().add(btnChange);
		// change button in change
		btnCancel_change.setBounds(409, 262, 117, 29);

		frmEmergencyRoomManagement.getContentPane().add(btnCancel_change);
		// - - - - - - - - - - - - - - - - - - - - - GUI

		btnConfirm_change.addActionListener(this);
		btnConfirm_change.addKeyListener(this);
		btnCancel_change.addActionListener(this);
		btnCancel_change.addKeyListener(this);

		// default is not showing ths section
		desetUpdate();
	}

	/**
	 * Create the search-a-patient section.
	 */
	public void createSearch() {
		// GUI - - - - - - - - - - - - - - - - - - - - -
		// label and combo box
		lblSearchPatient.setBounds(6, 435, 100, 16);
		frmEmergencyRoomManagement.getContentPane().add(lblSearchPatient);
		comboBox_Search.addItem("");
		comboBox_Search.setBounds(100, 431, 368, 27);
		frmEmergencyRoomManagement.getContentPane().add(comboBox_Search);

		// reporting sex, age, reason of entrance & exit
		lblSex_Search.setBounds(6, 459, 117, 16);
		frmEmergencyRoomManagement.getContentPane().add(lblSex_Search);
		lblAge_Search.setBounds(124, 459, 134, 16);
		frmEmergencyRoomManagement.getContentPane().add(lblAge_Search);
		lblReasonEntrance_Search.setBounds(6, 483, 462, 16);
		frmEmergencyRoomManagement.getContentPane().add(lblReasonEntrance_Search);
		lblReasonDischarge_Search.setBounds(6, 506, 468, 16);
		frmEmergencyRoomManagement.getContentPane().add(lblReasonDischarge_Search);

		arrivalPatient_ComboBox.setBounds(100, 235, 368, 27);
		frmEmergencyRoomManagement.getContentPane().add(arrivalPatient_ComboBox);

		// - - - - - - - - - - - - - - - - - - - - - GUI
		// adding action and key listeners
		comboBox_Search.addActionListener(this);
		comboBox_Search.addKeyListener(this);

		// setting texts & combo box to not-editable to initiate the
		// program.
		desetSearch();
	}
	
	@Override
	/**
	 * Sets what happens when certain buttons and other components are clicked on.
	 */
	public void actionPerformed(ActionEvent e) {
		// if the user clicks on add button on the top, show add section
		if (e.getSource() == btnAdd) {
			setAdd();
			desetDischarge();
			desetSearch();
			desetUpdate();
			// if the user clicks on change button on the top, show change section
		} else if (e.getSource() == btnChange) {
			setUpdate();
			desetAdd();
			desetDischarge();
			desetSearch();
			// if the user clicks on discharge button on the top, show discharge section
		} else if (e.getSource() == btnDischarge) {
			setDischarge();
			desetAdd();
			desetSearch();
			desetUpdate();
			// if the user clicks on search button on the top, show search section
		} else if (e.getSource() == btnSearch) {
			setSearch();
			desetAdd();
			desetDischarge();
			desetUpdate();
			// if the "Confirm" button in the add section
			// is pressed on, make the confirm pop up:
		} else if (e.getSource() == btnConfirm_Add) {
			makeConfirmPopup();
			// if the "Cancel" button of the add section is
			// pressed on, deset all information of the patient
			// that would have been added to the system.
		} else if (e.getSource() == btnCancel_Add) {
			desetAdd();
			// if the "Confirm" button of the discharge section
			// is clicked on, add the discharged patient to
			// the search combo box, remove the patient from the
			// priority queue, and show the new current patient.
		} else if (e.getSource() == btnConfirm_Discharge) {
			addDischargePatient();
			logic.removePatient();
			showCurrentPatient();
			// if the user clicks on a name in the
			// search combo box, show the user the patient's
			// information.
		} else if (e.getSource() == comboBox_Search) {
			searchPatient();
			//if the user clicks on confirm in change section, a confirm window will pop up
		} else if (e.getSource() == btnConfirm_change) {
			confirmWindowForChange();
			//update current patient
			showCurrentPatient();
			//if the user clicks on cancel button in change section, it will be un-editable
		} else if (e.getSource() == btnCancel_change) {
			desetUpdate();
		}
	}

	@Override
	/**
	 * The logic of the key events are the same as in ActionEvent. The purpose of
	 * adding a KeyListener in the first place is to make sure that nurses/doctors
	 * can press "enter" to perform certain tasks with ease.
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == btnAdd) {
			setAdd();
			desetDischarge();
			desetSearch();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == btnChange) {
			setUpdate();
			desetAdd();
			desetDischarge();
			desetSearch();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == btnDischarge) {
			setDischarge();
			desetAdd();
			desetSearch();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == btnSearch) {
			setSearch();
			desetAdd();
			desetDischarge();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == btnConfirm_Add) {
			makeConfirmPopup();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == btnCancel_Add) {
			desetAdd();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == btnConfirm_Discharge) {
			addDischargePatient();
			logic.removePatient();
			showCurrentPatient();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == comboBox_Search) {
			searchPatient();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == btnConfirm_change) {
			confirmWindowForChange();
			showCurrentPatient();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == btnCancel_change){
			desetUpdate();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}