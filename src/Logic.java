import DataStructures.DoublyLinkedList;
import DataStructures.PriorityQueue;
import DataStructures.PriorityQueueBH;

/**
 * This class provides the main logic of the management system, add a patient,
 * remove a patient, and change a patient's injury level.
 * 
 * @author Yvonne Tran, Ching Ching Huang
 *
 */
public class Logic {
	// list for discharged patients
	protected DoublyLinkedList<Patient> dischargeList;
	// pq for the order of treatment
	protected PriorityQueueBH<Patient> pq;
	// a list to store arrival patients
	protected DoublyLinkedList<Patient> arrivalList;
	// the patient to remove from pq
	protected Patient removedPatient;

	/**
	 * Constructor initiates dischargeList, pq, and arrivalList
	 */
	public Logic() {
		pq = new PriorityQueueBH<Patient>(50);
		dischargeList = new DoublyLinkedList<Patient>();
		arrivalList = new DoublyLinkedList<Patient>();

	}

	/**
	 * Add a patient to ER management system
	 * @param String patient's name
	 * @param String patient's sex
	 * @param int patient's age
	 * @param int patient's level of injury
	 * @param String patient's reason of entrance
	 * @param int order in which patient entered ER
	 * @return Patient the patient
	 */
	public Patient insertPatient(String name, String sex, int age, int level, String reason, int patientNum) {
		Patient patient = new Patient(name, sex, age, level, reason, patientNum);
		// add patient to pq
		pq.insert(patient);
		// add patient to arrival list
		arrivalList.add(patient);
		return patient;
	}

	/**
	 * Change patient's level of injury
	 * @param Patient the patient of interest
	 * @param int level the new level
	 */
	public void changeLevel(Patient patient, int level) {
		patient.setLevel(level);
		pq.increaseKey(pq.getIndex(patient), patient);
	}

	/**
	 * Discharge a patient from the management system
	 */
	public void removePatient() {
		// if there are patients in the ER, extract max
		if (pq.getSize() > 0) {
			Patient patient = pq.extractMax();
			dischargeList.add(patient);
			// remove the patient from arrival list
			int index = arrivalList.getIndex(patient);
			arrivalList.remove(index);
		}
	}

	/**
	 * Get current patient by getting the maximum in the pq
	 * @return Patient the curr patient
	 */
	public Patient getCurrentPatient() {
		// if there are patients in the system, get the maximum
		if (pq.getSize() > 0) {
			return pq.maximum();
		}
		// otherwise, return null
		return null;
	}

	/**
	 * Get discharge doubly linked list
	 * @return DoublyLinkedList<Patient> the list of discharged patients
	 */
	public DoublyLinkedList<Patient> getDischarge() {
		return dischargeList;
	}

	/**
	 * Get the order of patient treatment
	 * @return PriorityQueue<Patient> the pq of patients
	 */
	public PriorityQueue<Patient> getPQ() {
		return pq;
	}

	/**
	 * Get the arrival doubly linked list
	 * @return DoublyLinkedList<Patient> the list of arrived patients
	 */
	public DoublyLinkedList<Patient> getArrival() {
		return arrivalList;
	}
}