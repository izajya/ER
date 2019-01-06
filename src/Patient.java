/**
 * Create a patient that takes in name, sex, age, injury level, reason of
 * entering ER, and patient number.
 * 
 * @author Ching Ching Huang, Yvonne Tran
 *
 */
public class Patient implements Comparable<Patient> {

	private String name;
	private String sex;
	private int age;
	private Integer injuryLevel;
	private String enterReason;
	private String dischargeReason;
	private Integer num;

	/**
	 * Constructor creates a new patient with information
	 * 
	 * @param name
	 * @param sex
	 * @param age
	 * @param injuryLevel
	 * @param enterReason
	 * @param num
	 */
	public Patient(String name, String sex, int age, int injuryLevel, String enterReason, int num) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.injuryLevel = injuryLevel;
		this.enterReason = enterReason;
		this.num = num;

	}

	/**
	 * Set the reason of discharge
	 * @param String reason of discharge
	 */
	public void setDischrage(String dischargeReason) {
		this.dischargeReason = dischargeReason;
	}

	/**
	 * Get the reason of discharge
	 * @return String the reason of discharge
	 */
	public String getDischarge() {
		return dischargeReason;
	}

	/**
	 * Get the reason of enter
	 * @return String reason of entrance
	 */
	public String getEnter() {
		return enterReason;
	}

	/**
	 * Set injury level
	 * @param int patient's level
	 */
	public void setLevel(int level) {
		this.injuryLevel = level;
	}

	/**
	 * Get the injury level
	 * @return int the patient's injury level
	 */
	public int getLevel() {
		return injuryLevel;
	}

	/**
	 * Get patient's sex
	 * @return String patient's sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Get patient's age
	 * @return int patient's age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Get patient's name
	 * @return String patient's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get patient's number
	 * @return Integer the patient's order of entrance
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * Compares any 2 patients
	 */
	@Override
	public int compareTo(Patient o) {
		// first comparing if 1 patient has a higher
		// injury level than the other. the patient
		// with the higher level is treated first
		if (injuryLevel.compareTo(o.getLevel()) != 0) {
			return injuryLevel.compareTo(o.getLevel());
		} else {
		// if the injury levels are equal, compare the
		// order in which they entered the ER
			return num.compareTo(o.getNum()) * -1;
		}
	}
}
