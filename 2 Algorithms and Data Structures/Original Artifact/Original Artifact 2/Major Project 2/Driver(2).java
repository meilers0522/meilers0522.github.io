// Mark Eilers
// IT-145-H7737 Found in App Development
// 7-1 Project Two
// December 10,2022

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    
    // Initialize a private static ArrayList for Dogs and Monkeys
    private static ArrayList<Dog> dogList = new ArrayList<Dog>();
    private static ArrayList<Monkey> monkeyList = new ArrayList<Monkey>();

    public static void main(String[] args) {
    	
    	Scanner input = new Scanner(System.in);

        initializeDogList();
        initializeMonkeyList();

        // Add a loop that displays the menu, accepts the users input
        // and takes the appropriate action.
	// For the project submission you must also include input validation
        // and appropriate feedback to the user.
        // Hint: create a Scanner and pass it to the necessary
        // methods 
	// Hint: Menu options 4, 5, and 6 should all connect to the printAnimals() method.

        
        // Create a while loop statement to display the menu. 
        while(true) {

            displayMenu();
        	int choice = input.nextInt();// Prompt user to enter integer command for different commands
            input.nextLine();
        	
        	if (choice == '1') // Set first if choice statement.
        		intakeNewDog(input);
        	
        	else if (choice == '2') // Set second choice statement.
        		intakeNewMonkey(input);
        	
        	else if (choice == '3') // Set third choice statement.
        		reserveAnimal (input);
        	
        	else if (choice == '4') // Set fourth choice statement.
        		printAnimals("dog");
        	
        	else if (choice == '5') // Set fifth choice statement.
        		printAnimals("monkey");
        	
        	else if (choice == '6')  // Set sixth choice statement.
        		printAnimals("available");
        	
        	else if (choice == 'q')  // Set exit choice option.
        		System.exit(0);
        	
        	else // Inform user of invalid choice. 
        		System.out.println("Wrong choice");
        }
    }

    // This method prints the menu options
    public static void displayMenu() {
        System.out.println("\n\n");
        System.out.println("\t\t\t\tRescue Animal System Menu");
        System.out.println("[1] Intake a new dog");
        System.out.println("[2] Intake a new monkey");
        System.out.println("[3] Reserve an animal");
        System.out.println("[4] Print a list of all dogs");
        System.out.println("[5] Print a list of all monkeys");
        System.out.println("[6] Print a list of all animals that are not reserved");
        System.out.println("[q] Quit application");
        System.out.println();
        System.out.println("Enter a menu selection");
    }


    // Adds dogs to a list for testing
    public static void initializeDogList() {
        Dog dog1 = new Dog("Spot", "German Shepherd", "male", "1", "25.6", "05-12-2019", "United States", "intake", false, "United States");
        Dog dog2 = new Dog("Rex", "Great Dane", "male", "3", "35.2", "02-03-2020", "United States", "Phase I", false, "United States");
        Dog dog3 = new Dog("Bella", "Chihuahua", "female", "4", "25.6", "12-12-2019", "Canada", "in service", true, "Canada");

        dogList.add(dog1);
        dogList.add(dog2);
        dogList.add(dog3);
    }


    // Adds monkeys to a list for testing
    //Optional for testing
    public static void initializeMonkeyList() {
    
    // Initialize a new monkey for the list using the template from the dog above.  
        Monkey monkey1 = new Monkey("Samantha","Female","4", "70", "Capuchin", "3.1", "2.4", "1.7", "11-26-2022", "United States", "intake", false, "United states");
        Monkey monkey2 = new Monkey("Jimmy","Male","5", "89", "Mandril", "2.8", "1.5", "3.1", "12-10-2022", "United States", "intake", true, "United states");

        monkeyList.add(monkey1);
        monkeyList.add(monkey2);
    }


    // Complete the intakeNewDog method
    // The input validation to check that the dog is not already in the list
    // is done for you
    public static void intakeNewDog(Scanner scanner) {
        System.out.println("What is the dog's name?");
        String name = scanner.nextLine();
        for(Dog dog: dogList) {
            if(dog.getName().equalsIgnoreCase(name)) {
                System.out.println("\n\nThis dog is already in our system\n\n");
                return; //returns to menu
            }
        }

        // Add the code to instantiate a new dog and add it to the appropriate list
    
        // Prompt the user to enter the breed of the animal.
        System.out.print("Breed of Dog: ");
        String breed = scanner.nextLine();
        
        // Prompt the user to enter the gender, male or female of the animal.  
        System.out.print("Gender of Dog (Male/Female): ");
        String gender = scanner.nextLine();
        
        // Prompt the user to enter the age of the animal.
        System.out.print("Age of Dog (in years): ");
        String age = scanner.nextLine();
        
        // Prompt the user to enter the weight of the animal.  
        System.out.print("Enter Weight of Dog: ");
        String weight = scanner.nextLine();
        
        // Prompt the user to enter the acquisition date of animal.
        System.out.print("Acquisition date: ");
        String acquisitionDate = scanner.nextLine();
        
        // Prompt the user to enter the acquisition country for the animal.
        System.out.print("Acquisition country: ");
        String acquisitionCountry = scanner.nextLine();
        
        // Prompt the user to enter the training status.  
        System.out.print("Training status: ");
        String trainingStatus = scanner.nextLine();
        
        // Create a if else statement for reserving the animal.  
        System.out.print("Reserved (1 for true, 0 for false): ");
        String choice = scanner.nextLine();
        boolean reserved;
        if(choice.equals("1"))
            reserved = true;
        else
            reserved = false;
        
        // Prompt the user to include the country the animal is in service at. 
        System.out.println("Country of Animal Service: ");
        String InServiceCountry = scanner.nextLine();
        
        // Initiate new dog to the correct list.
        Dog newDog = new Dog(name, breed, gender, age, weight, acquisitionDate, acquisitionCountry, trainingStatus, reserved, InServiceCountry);
        dogList.add(newDog);
    }

        // Complete intakeNewMonkey
	//Instantiate and add the new monkey to the appropriate list
        // For the project submission you must also  validate the input
	// to make sure the monkey doesn't already exist and the species type is allowed
        public static void intakeNewMonkey(Scanner scanner) {
        	System.out.println("What is the monkey's name?: ");
            String name = scanner.nextLine();
            for(Monkey monkey: monkeyList) {
                if(monkey.getName().equalsIgnoreCase(name)) {
                    System.out.println("\n\nThis Monkey is already in our system\n\n");
                    return; //returns to menu
                }
            }

            // Add the code to instantiate a new monkey and add it to the appropriate list
            
            // Prompt the user to enter the gender, male or female of the animal.  
            System.out.print("Gender of Monkey (Male/Female): ");
            String gender = scanner.nextLine();
            
            // Prompt the user to enter the age of the animal.
            System.out.print("Age of Monkey (in years): ");
            String age = scanner.nextLine();
            
            // Prompt the user to enter the weight of the animal.  
            System.out.print("Enter Weight of Monkey: ");
            String weight = scanner.nextLine();
            
            // Prompt the user to enter the species type of the Monkey.
            System.out.print("Specie Type of Monkey: ");
            String species = scanner.nextLine();
            
            // Prompt the user to enter the tail length.
            System.out.print("Monkey Tail length: ");
            String tailLength = scanner.nextLine();
            
            // Prompt the user to enter the height of the Monkey.
            System.out.print("Monkey Height: ");
            String height = scanner.nextLine();
            
            // Prompt the user to enter the body length of the Monkey.
            System.out.print("Monkey Body Length: ");
            String bodyLength = scanner.nextLine();
            
            // Prompt the user to enter the acquisition date of animal.
            System.out.print("Acquisition date: ");
            String acquisitionDate = scanner.nextLine();
            
            // Prompt the user to enter the acquisition country for the animal.
            System.out.print("Acquisition country: ");
            String acquisitionCountry = scanner.nextLine();
            
            // Prompt the user to enter the training status.  
            System.out.print("Training status: ");
            String trainingStatus = scanner.nextLine();
            
            // Create a if else statement for reserving the animal.  
            System.out.print("Reserved (1 for true, 0 for false): ");
            String choice = scanner.nextLine();
            boolean reserved;
            if(choice.equals("1"))
                reserved = true;
            else
                reserved = false;
            
            // Prompt the user to include the country the animal is in service at. 
            System.out.println("Country of Animal Service: ");
            String InServiceCountry = scanner.nextLine();
            
            // Initiate new monkey to the correct list.
            Monkey newMonkey = new Monkey(name, gender, age, weight, species, tailLength, height, bodyLength, acquisitionDate, acquisitionCountry, trainingStatus, reserved, InServiceCountry);
            monkeyList.add(newMonkey);
        }

        // Complete reserveAnimal
        // You will need to find the animal by animal type and in service country
        public static void reserveAnimal(Scanner scanner) {
            
        	// Ask the customer what kind of animal they would like to reserve, Dog or Monkey.
        	System.out.println("Would you like to reserve a Dog or Monkey?: ");
        	String input = scanner.nextLine();
        	
        	// Ask the customer what Country will the service animal and customer be in.
        	System.out.println("What Country will the service animal be needed in?: ");
        	String serviceCountry = scanner.nextLine();
        	
        	// Create the if, elif, else loop for reserving a Monkey or dog.
        	//Convert user input to all lower case letters so the program will recognize the response.
        	if(input.equalsIgnoreCase("monkey")) {
        		// Setting available Monkey to 0.
        		int flag = 0;
        		
        		// Creating the for loop for Monkey list. 
        		for(Monkey monkey: monkeyList) {
        			
        			//Check available Monkeys in service country requested. 
        			//Use equalIgnoreCase to convert COuntry into lowercase. 
        			if(monkey.getInServiceLocation().equalsIgnoreCase(serviceCountry)) {
        				
        				if(! monkey.getReserved()) {
        					monkey.setReserved(true);
        					System.out.println("Monkey " + monkey.getName() + " is now on reserve." );
        					flag = 1;
                            break;
        				}
        			}
        		}
        		
        		// If there are no Monkeys, inform the user that they are not available to reserve. 
        		if(flag == 0)
        			System.out.println( "No Monkeys are available to reserve, please check the Dog list.");
        	}
        	else if (input.equalsIgnoreCase("dog")) {
        		// Setting available Dogs to 0.
        		int flag = 0;
        		
        		// Creating the for loop for Dog list. 
        		for(Dog dog: dogList) {
        			
        			//Check available Monkeys in service country requested. 
        			//Use equalIgnoreCase to convert COuntry into lower case. 
        			if(dog.getInServiceLocation().equalsIgnoreCase(serviceCountry)) {
        				
        				if(! dog.getReserved()) {
        					dog.setReserved(true);
        					System.out.println("Dog " + dog.getName() + " is now on reserve." );
        					flag = 1;
                            break;
        				}
        			}
        		}
        		
        		// If there are no dogs available, inform the user.
        		if(flag == 0)
                    System.out.println("No Dogs are available to reserve, please check the Monkey list.");
        	}
        // Create the else statement if someone request neither a dog or monkey, inform them of their choices.
        	else {
        		System.out.println("Wrong species of animal requested.  Please reserve a Dog or a Monkey");
        	}
        }

        // Complete printAnimals
        // Include the animal name, status, acquisition country and if the animal is reserved.
	// Remember that this method connects to three different menu items.
        // The printAnimals() method has three different outputs
        // based on the listType parameter
        // dog - prints the list of dogs
        // monkey - prints the list of monkeys
        // available - prints a combined list of all animals that are
        // fully trained ("in service") but not reserved 
	// Remember that you only have to fully implement ONE of these lists. 
	// The other lists can have a print statement saying "This option needs to be implemented".
	// To score "exemplary" you must correctly implement the "available" list.
        public static void printAnimals(String listType) {
        	
            //printAnimals for dogs that are available for reservation.  Include all dog infomration if the user selects dog.
        	if(listType.equalsIgnoreCase("dog")) {
        		for(Dog dog: dogList) {
        			
        	//Dog information: Name, Gender, Age, Acquisition Date, Acquisition Country, Reserved, Training Status, Service Country.
        			System.out.println ("\nDog " + dog.getName()
        				+ "\nGender: " + dog.getGender()
        				+ "\nWeight: " + dog.getWeight()
        				+ "\nAge: " + dog.getAge()
        				+ "\nAcquisition Dat: " + dog.getAcquisitionDate()
        				+ "\nAcquisition Country: " + dog.getAcquisitionLocation()
        				+ "\nReserved: " + dog.getReserved()
        				+ "\nTraining Status: " + dog.getTrainingStatus()
        				+ "\nService country: " + dog.getInServiceLocation());
        		}
        	}
        	
        	// Create the list for the monkeys.  
        	else if (listType.equalsIgnoreCase("monkey")) {
        		for(Monkey monkey: monkeyList) {
        			
        	//Monkey information: Name, Gender, Age, Acquisition Date, Acquisition Country, Reserved, Training Status, Service Country
            // Species, Tail Length, Height, Body Length
                    System.out.println ("\nMonkey " + monkey.getName()
                            + "\nGender: " + monkey.getGender()
                            + "\nWeight: " + monkey.getWeight()
                            + "\nAge: " + monkey.getAge()
                            + "\nSpecies: " + monkey.getSpecies()
                            + "\nTail Length: " + monkey.getTailLength()
                            + "\nHeight: " + monkey.getHeight()
                            + "\nBody Length: " + monkey.getBodyLength()
                            + "\nAcquisition Date: " + monkey.getAcquisitionDate()
                            + "\nAcquisition Country: " + monkey.getAcquisitionLocation()
                            + "\nReserved: " + monkey.getReserved()
                            + "\nTraining Status: " + monkey.getTrainingStatus()
                            + "\nService country: " + monkey.getInServiceLocation());
        		}
        	}

        	// Create the list for available dogs.
        	else if (listType.equalsIgnoreCase("available")) {
        		
        		//Write the available for dogs list.
        		for (Dog dog: dogList) {
        			
        			if(dog.getTrainingStatus().equalsIgnoreCase("in service") && (! dog.getReserved())) {
            			System.out.println ("Dog " + dog.getName()
        				+ "\nGender: " + dog.getGender()
        				+ "\nWeight: " + dog.getWeight()
        				+ "\nAge: " + dog.getAge()
        				+ "\nAcquisition Dat: " + dog.getAcquisitionDate()
        				+ "\nAcquisition Country: " + dog.getAcquisitionLocation()
        				+ "\nReserved: " + dog.getReserved()
        				+ "\nTraining Status: " + dog.getTrainingStatus()
        				+ "\nService country: " + dog.getInServiceLocation());
        			}
        		}
        		
        		// Create the list for available monkeys
        		
        		for (Monkey monkey: monkeyList) {
        			
        			if(monkey.getTrainingStatus().equalsIgnoreCase("in service")  && (! monkey.getReserved())) {
        				System.out.println ("Monkey " + monkey.getName()
        				+ "\nGender: " + monkey.getGender()
        				+ "\nWeight: " + monkey.getWeight()
        				+ "\nAge: " + monkey.getAge()
        				+ "\nSpecies " + monkey.getSpecies()
        				+ "\nHeight: " + monkey.getHeight()
        				+ "\nBody Length: " + monkey.getBodyLength()
        				+ "\nTail Length: " + monkey.getTailLength()
        				+ "\nAcquisition Dat: " + monkey.getAcquisitionDate()
        				+ "\nAcquisition Country: " + monkey.getAcquisitionLocation()
        				+ "\nReserved: " + monkey.getReserved()
        				+ "\nTraining Status: " + monkey.getTrainingStatus()
        				+ "\nService country: " + monkey.getInServiceLocation());
        			}
        		}
        	}
        	// inform the user of the wrong list if not monkey or dog.
        	else {
        		System.out.println("Incorrect list, please use either Dog or Monkey.");
        	}
        }
}