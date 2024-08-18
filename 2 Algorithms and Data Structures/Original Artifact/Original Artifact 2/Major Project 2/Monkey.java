// Mark Eilers
// IT-145 Found in App Development
// 7-1 Project Two
// December 10, 2022

public class Monkey extends RescueAnimal {

	// Create the private variables for just the Monkey class that are not included in dog.
	// Variables include, species, tail length, height, and body length.
	private String species;
	private String tailLength;
	private String height;
	private String bodyLength;

	// Initialize all the Monkey constructor for all the variables from both Monkey and parent class.
	public Monkey(String name, String gender, String age, String weight, String species, String tailLength,
				  String height, String bodyLength, String acquisitionDate, String acquisitionCountry, String trainingStatus,
				  boolean reserved, String inServiceCountry) {

		// List from the Rescue Animal Class.
		setName(name);
		setGender(gender);
		setAge(age);
		setWeight(weight);
		setAcquisitionDate(acquisitionDate);
		setAcquisitionLocation(acquisitionCountry);
		setTrainingStatus(trainingStatus);
		setReserved(reserved);
		setInServiceCountry(inServiceCountry);

		// List for just the Monkey Class.
		setSpecies(species);
		setTailLength(tailLength);
		setHeight(height);
		setBodyLength(bodyLength);

	}

	// Accessor Method for the getSpecies variable.
	public String getSpecies() {
		return species;
	}

	// Mutator Method for the setSpecies variable.
	public void setSpecies(String monkeySpecies) {
		species = monkeySpecies;
	}

	// Accessor Method for the getTailLength variable.
	public String getTailLength() {
		return tailLength;
	}

	// Mutator Method for the setTailLength variable.
	public void setTailLength(String monkeyTailLength) {
		tailLength = monkeyTailLength;
	}

	// Accessor Method for the getHeight variable.
	public String getHeight() {
		return height;
	}

	// Mutator Method for the setHeight variable.
	public void setHeight(String monkeyHeight) {
		height = monkeyHeight;
	}

	// Accessor Method for the getBodyLength variable.
	public String getBodyLength() {
		return bodyLength;
	}

	// Mutator Method for the setBodyLength variable.
	public void setBodyLength(String monkeyBodyLength) {
		bodyLength = monkeyBodyLength;
	}
}