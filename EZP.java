//Programmer Name: Austin
//GitHub Username: welkins1

public class EZP {

    //This constant String serves as instructions for the program 
    public static final String USAGE = """
        
         Usage: [Password size] [Include letters: y/n] [Include numbers: y/n]
         [Include special characters: y/n] Example: 15 y y y
                         
                           """;

    //This method generates a random integer between 0 & int a - 1 using Math.random()
    public static int randomInt(int a) {
        a = (int) (Math.random() * a);
        return a;
    }

    //This method generates a random integer between two numbers using Math.random()
    public static int randomIntBetween(int a, int b) {
        int number = (int) (Math.random() * (b - a + 1) + a);

        return number;
    }

    //This method prints each index of an array using a for loop
    public static void printArray(char[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
        System.out.println("\n");
    }

    //This method acts as a input validation for the size of the generated password
    //It returns true or false based on whether the input is valid or not 
    public static boolean isSizeValid(String args) {
        try {
            Integer.parseInt(args); //Converts args to int 
            return true;
        } catch (NumberFormatException e) { //Catches if an int is not entered 
            System.out.println("\n Error: \"" + args + "\" cannot be parsed into an integer.\n");
            return false;
        }

    }

    /*This method returns true or false based on whether an argument in ther terminal is null.
   It also returns false if more than 4 arguments are entered 
     */
    public static boolean isArgsValid(String[] args, int index) {

        try {
            // Ensure args has at least 4 elements
            if (args == null || args.length <= index || args[index] == null || args[index].trim().isEmpty()) {
                System.out.println("\n Error: Argument " + (index + 1) + " is missing or empty\n");
                System.out.println(USAGE);
                return false;
            } else if (args.length > 4) { //Ensures args has less than 4 arguments 
                System.out.println("\nError: Too many arguments\n");
                System.out.println(USAGE); //Prints usage string 
                System.exit(0); //exits the program 
            }
        } catch (Exception e) { //catches Exception based on invalid args input 

            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        //This string contains all the possible characters a password can have
        String GetChars
                = //0-25 - charAt index for uppercase letters
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + //26-51 - charAt index for lowercase letters
                "abcdefghijklmnopqrstuvwxyz"
                + //52-61 - charAt index for numbers
                "0123456789"
                + //62-71 - charAt index for special characters 
                "!@#$%^&*()";

        //If the args array is blank, a usage guide appears, ex: java EZP.java 
        if (args.length == 0 || args[0].isBlank() || args[0].isEmpty()) {
            System.out.println(USAGE);
            return; //exits the program 
        }

        //If the method call is false, the program exists 
        if (!isSizeValid(args[0])) {
            return;
        }

        //If the size of the password is not between 5 and 31, the program exits 
        if (!((Integer.parseInt(args[0])) >= 5) || !((Integer.parseInt(args[0])) <= 31)) {
            System.out.println("\nError: Password size must be between 5 and 31\n");
            System.out.println(USAGE);
            return;
        }

        //Size's value is based on the user's terminal input of args[0] 
        int size = Integer.parseInt(args[0]);

        //Creation of the password array with size declared in args[0] 
        char password[] = new char[size];

        if (!isArgsValid(args, 1)) {
            return; //xits program if method call based on args[1] is false
        }
        //Value of AddLetters is args[1] which must be a value of Y or N 
        String AddLetters = args[1];

        if (AddLetters.equalsIgnoreCase("Y")) {
            //for loop fills password[] with random uppercase and lowercase chars 
            for (int i = 0; i < size; i++) {
                password[i]
                        = GetChars.charAt((randomIntBetween(0, 51)));
            }                           //randomiztion expression 
        }

        if (!isArgsValid(args, 2)) {
            return; // Exits program if method call based on args[2] is false
        }

        //Value of AddNumbers is Y or N based on args[2] input 
        String AddNumbers = args[2];

        if (AddNumbers.equalsIgnoreCase("Y")
                && AddLetters.equalsIgnoreCase("N")) { //fills entire password array with numbers
            for (int i = 0; i < size; i++) {
                password[i]
                        = GetChars.charAt((randomIntBetween(52, 61)));
            }
        }

        if (AddNumbers.equalsIgnoreCase("Y")) {
            for (int i = 0;
                    i < randomIntBetween(2, 5); //randomizes the index 
                    i++) {
                password[randomIntBetween(0, size - 1)]
                        = GetChars.charAt((randomIntBetween(52, 61)));
            }                               //fills indexes with random numbers
        }

        if (!isArgsValid(args, 3)) {
            return; // Exits program if method call based on args[3] is false
        }

        //Value of AddSpecialChars is Y or N based on input of args[3]
        String AddSpecialChars = args[3];

        if (AddNumbers.equalsIgnoreCase("N")
                && AddLetters.equalsIgnoreCase("N")
                && AddSpecialChars.equalsIgnoreCase("Y")) { //fills entire password array with special chars
            for (int i = 0; i < size; i++) {
                password[i]
                        = GetChars.charAt((randomIntBetween(62, 71)));
            }
        }

        if (AddSpecialChars.equalsIgnoreCase("Y")) {
            for (int i = 0;
                    i < randomIntBetween(2, 5); //randomizes the index
                    i++) {
                password[randomIntBetween(0, size - 1)]
                        = GetChars.charAt((randomIntBetween(62, 71)));
                //fills indexes with random special chars
            }
        }

        //Corresponding erros  will appear if user does not enter Y or N for args[1-3]
        if (!AddLetters.equalsIgnoreCase("Y") && !AddLetters.equalsIgnoreCase("N")) {
            System.out.println("\nError: You must specify y/n if you want to add letters\n");
            System.out.println(USAGE);
            return;
        }

        if (!AddNumbers.equalsIgnoreCase("Y") && !AddNumbers.equalsIgnoreCase("N")) {
            System.out.println("\nError: You must specify y/n if you want to add numbers\n");
            System.out.println(USAGE);
            return;
        }

        if (!AddSpecialChars.equalsIgnoreCase("Y") && !AddSpecialChars.equalsIgnoreCase("N")) {
            System.out.println("\nError: You must specify y/n if you want to add special characters\n");
            System.out.println(USAGE);
            return;
        }

        System.out.print("\nYour password is: ");

        //Prints password to console 
        printArray(password);

    }
}

