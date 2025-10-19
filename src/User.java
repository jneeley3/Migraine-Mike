import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User {
    String storedUser;
    public String login(String username, String password) {
        File file = new File("documents/users.txt");
        if (!file.exists()) {
            System.err.println("users.txt not found.");
            return null;
        }

        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                String[] parts = scan.nextLine().split(",");
                if (parts.length == 3) {
                    storedUser = parts[0].trim();
                    String storedPass = parts[1].trim();
                    String firstName = parts[2].trim();
                    if (storedUser.equals(username) && storedPass.equals(password)) {
                        return firstName;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading users.txt");
        }
        return null;
    }

    public void signUp(String username, String password, String firstName) {
        try (FileWriter fw = new FileWriter("documents/users.txt", true)) {
            fw.write(username + "," + password + "," + firstName + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to users.txt");
        }
        
    }
    
    /**
     * Reads user symptom data from a specific date, and prints to the console.
     * found, or it returns null if the data is not found.
     * @param userName The username of the user whose data is to be read.
     * @param year The year of the date to read data from.
     * @param month The month of the date to read data from.
     * @param day The day of the date to read data from.
     * @throws IOException If there is an error reading the file.
     * @throws IllegalStateException If the scanner is closed unexpectedly.
     * @throws NumberFormatException If any integer formats are invalid.
     */
    public static void readUserFromDate(String userName,String year, String month, String day) {
        String date = year+month+day;
        int dateYear = Integer.parseInt(date);
        String user = userName;
        boolean completed = false;
        File file = new File("documents/info.txt");
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                if(scan.nextLine().equals("<u>" + user)) {
                    System.out.println("User found: " + user +". Attempting to read symptoms from date "+ dateYear+".");
                    scan.useDelimiter(",");
                    if(scan.nextInt() == dateYear) {
                        System.out.println("Date Found: " + dateYear);
                        int[] symptoms = new int[17];
                        for(int i = 0; i < symptoms.length; i++) {
                            if(scan.hasNextInt()) {
                                symptoms[i] = scan.nextInt();
                            }
                        }
                        for(int i = 0; i < symptoms.length; i++) {
                            System.out.println(symptoms[i] + ", ");
                        }
                        completed = true;
                        break;
                    }
                    while(scan.hasNext()) {
                        scan.useDelimiter(",");
                          if(scan.findInLine("<P>") != null) {
                            if(scan.hasNextInt()) {
                                if(scan.nextInt() == dateYear) {
                                    System.out.println("Date Found: " + dateYear);
                                    int[] symptoms = new int[17];
                                    scan.useDelimiter(",");
                                    for(int i = 0; i < symptoms.length; i++) {
                                        if(scan.hasNextInt()) {
                                            symptoms[i] = scan.nextInt();
                                        }
                                    }
                                    for(int i = 0; i < symptoms.length; i++) {
                                        System.out.println(symptoms[i] + ", ");
                                    }
                                    completed = true;
                                    break;
                                }
                                else {
                                    System.out.println("Date not found. Proceeding to next date.");
                                }
                            }
                            else {
                                System.out.println("Date data not found. Closing Program.");
                                System.exit(1);
                            }
                          }
                          else {
                            System.out.println("Date not found in current user.");
                            completed = true;
                            break;
                          }
                    }
                }
                else {
                    System.out.println("User not found. Proceeding to next user.");
                }
                if(completed) {
                    scan.close();
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading info.txt");
        } catch (IllegalStateException e) {
            System.err.println("Scanner closed unexpectedly.");
        }
        
    }
    
    /**
     * Reads user symptom data from a specific date, and prints to the console.
     * found, or it returns null if the data is not found.
     * @param userName The username of the user whose data is to be read.
     * @param date The date to read data from in YYYYMMDD format.
     * @throws IOException If there is an error reading the file.
     * @throws IllegalStateException If the scanner is closed unexpectedly.
     * @throws NumberFormatException If any integer formats are invalid.
     */
    public static void readUserFromDate(String userName,String date) {
        int dateYear = Integer.parseInt(date);
        String user = userName;
        boolean completed = false;
        File file = new File("documents/info.txt");
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                if(scan.nextLine().equals("<u>" + user)) {
                    System.out.println("User found: " + user +". Attempting to read symptoms from date "+ dateYear+".");
                    scan.useDelimiter(",");
                    if(scan.nextInt() == dateYear) {
                        System.out.println("Date Found: " + dateYear);
                        int[] symptoms = new int[17];
                        for(int i = 0; i < symptoms.length; i++) {
                            if(scan.hasNextInt()) {
                                symptoms[i] = scan.nextInt();
                            }
                        }
                        for(int i = 0; i < symptoms.length; i++) {
                            System.out.println(symptoms[i] + ", ");
                        }
                        completed = true;
                        break;
                    }
                    while(scan.hasNext()) {
                        scan.useDelimiter(",");
                          if(scan.findInLine("<P>") != null) {
                            if(scan.hasNextInt()) {
                                if(scan.nextInt() == dateYear) {
                                    System.out.println("Date Found: " + dateYear);
                                    int[] symptoms = new int[17];
                                    scan.useDelimiter(",");
                                    for(int i = 0; i < symptoms.length; i++) {
                                        if(scan.hasNextInt()) {
                                            symptoms[i] = scan.nextInt();
                                        }
                                    }
                                    for(int i = 0; i < symptoms.length; i++) {
                                        System.out.println(symptoms[i] + ", ");
                                    }
                                    completed = true;
                                    break;
                                }
                                else {
                                    System.out.println("Date not found. Proceeding to next date.");
                                }
                            }
                            else {
                                System.out.println("Date data not found. Closing Program.");
                                System.exit(1);
                            }
                          }
                          else {
                            System.out.println("Date not found in current user.");
                            completed = true;
                            break;
                          }
                    }
                }
                else {
                    System.out.println("User not found. Proceeding to next user.");
                }
                if(completed) {
                    scan.close();
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading info.txt");
        } catch (IllegalStateException e) {
            System.err.println("Scanner closed unexpectedly.");
        }
        
    }
    
    /**
     * Reads user symptom data from a specific date, and formats the data into an integer array if the data is 
     * found, or it returns null if the data is not found.
     * @param userName The username of the user whose data is to be read.
     * @param year The year of the date to read data from.
     * @param month The month of the date to read data from.
     * @param day The day of the date to read data from.
     * @return An integer array containing the symptom data if found, or null if not found.
     * @throws IOException If there is an error reading the file.
     * @throws IllegalStateException If the scanner is closed unexpectedly.
     * @throws NumberFormatException If any integer formats are invalid.
     */
    public static int[] returnUserDataFromDate(String userName,String date) {
        int dateYear = Integer.parseInt(date);
        String user = userName;
        File file = new File("documents/info.txt");
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                if(scan.nextLine().equals("<u>" + user)) {
                    System.out.println("User found: " + user +". Attempting to read symptoms from date "+ dateYear+".");
                    scan.useDelimiter(",");
                    if(scan.nextInt() == dateYear) {
                        System.out.println("Date Found: " + dateYear);
                        int[] symptoms = new int[17];
                        for(int i = 0; i < symptoms.length; i++) {
                            if(scan.hasNextInt()) {
                                symptoms[i] = scan.nextInt();
                            }
                        }
                        for(int i = 0; i < symptoms.length; i++) {
                            System.out.println(symptoms[i] + ", ");
                        }
                        return symptoms;
                    }
                    while(scan.hasNext()) {
                        scan.useDelimiter(",");
                          if(scan.findInLine("<P>") != null) {
                            if(scan.hasNextInt()) {
                                if(scan.nextInt() == dateYear) {
                                    System.out.println("Date Found: " + dateYear);
                                    int[] symptoms = new int[17];
                                    scan.useDelimiter(",");
                                    for(int i = 0; i < symptoms.length; i++) {
                                        if(scan.hasNextInt()) {
                                            symptoms[i] = scan.nextInt();
                                        }
                                    }
                                    for(int i = 0; i < symptoms.length; i++) {
                                        System.out.println(symptoms[i] + ", ");
                                    }
                                    return symptoms;
                                }
                                else {
                                    System.out.println("Date not found. Proceeding to next date.");
                                }
                            }
                            else {
                                System.out.println("Date data not found. Closing Program.");
                                return null;
                            }
                          }
                          else {
                            System.out.println("Date not found in current user.");
                            return null;
                          }
                    }
                }
                else {
                    System.out.println("User not found. Proceeding to next user.");
                }
            }
            return null;
        } catch (IOException e) {
            System.err.println("Error reading info.txt");
            return null;
        } catch (IllegalStateException e) {
            System.err.println("Scanner closed unexpectedly.");
            return null;
        }
    }/**
     * Reads user symptom data from a specific date, and formats the data into an integer array if the data is 
     * found, or it returns null if the data is not found.
     * @param userName The username of the user whose data is to be read.
     * @param year The year of the date to read data from.
     * @param month The month of the date to read data from.
     * @param day The day of the date to read data from.
     * @return An integer array containing the symptom data if found, or null if not found.
     * @throws IOException If there is an error reading the file.
     * @throws IllegalStateException If the scanner is closed unexpectedly.
     * @throws NumberFormatException If any integer formats are invalid.
     */
    public static String getUserNoteFromDate(String userName, String date) {
    String user = userName;
    File file = new File("documents/info.txt");
    try (Scanner scan = new Scanner(file)) {
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.equals("<u>" + user)) {
                System.out.println("User found: " + user + ". Attempting to read symptoms from date " + date + ".");
                
                if (!scan.hasNextLine()) {
                    System.err.println("No entry line found after user tag.");
                    return "No entries found for user.";
                }

                String entriesLine = scan.nextLine();
                String[] entries = entriesLine.split("<P>");
                
                for (String entry : entries) {
                    if (entry.startsWith(date + ",")) {
                        String[] values = entry.split("<S>");
                        return values[1].substring(1,values[1].length()-1);
                        } 
                        else {
                            System.err.println("Entry does not contain enough fields.");
                            return "Incomplete entry for date.";
                        }
                    }
                }
                System.out.println("User not on this line. Continuing...");
            }
            System.out.println("User cannot be found, sorry.");
        }catch (IOException f) {
            System.err.println("Error reading info.txt");
            return "File read error.";
        } catch (IllegalStateException e) {
            System.err.println("Scanner closed unexpectedly.");
            return "Scanner error.";
        }
            return "User not found.";
        } 
    public void writeToInfo(int part, String level) {
        String user = this.storedUser;
        String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        File file = new File("documents/info.txt");
        List<String> updatedLines = new ArrayList<>();
        boolean userFound = false;
        boolean endTagFound = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Match user section
                if (line.equals("<u>" + user)) {
                    userFound = true;
                    updatedLines.add(line);

                    // Read the next line which contains all entries
                    String entriesLine = reader.readLine();
                    if (entriesLine != null && !entriesLine.equals("<E>")) {
                        StringBuilder updatedEntryLine = new StringBuilder();
                        String[] entries = entriesLine.split("<P>");
                        boolean dateUpdated = false;

                        for (String entry : entries) {
                            if (entry.startsWith(dateStr + ",")) {
                                String[] values = entry.split(",");
                                int painIndex = part + 1;
                                if (painIndex >= 1 && painIndex < values.length) {
                                    values[painIndex] = level;
                                }
                                entry = String.join(",", values);
                                dateUpdated = true;
                            }
                            updatedEntryLine.append(entry).append("<P>");
                        }

                        if (!dateUpdated) {
                            int totalParts = 17;
                            String[] newEntry = new String[totalParts + 1];
                            Arrays.fill(newEntry, "0");
                            newEntry[0] = dateStr;
                            if (part >= 0 && part < totalParts) {
                                newEntry[part + 1] = level;
                            }
                            updatedEntryLine.append(String.join(",", newEntry)).append("<P>");
                        }

                        updatedLines.add(updatedEntryLine.toString());
                    }

                    // Peek ahead to check for <E>
                    String nextLine = reader.readLine();
                    if (nextLine != null && nextLine.equals("<E>")) {
                        updatedLines.add("<E>");
                        endTagFound = true;
                    }

                    continue;
                }

                // Preserve other lines
                updatedLines.add(line);
                if (line.equals("<E>")) {
                    endTagFound = true;
                }
            }

            // If user not found, add new section
            if (!userFound) {
                updatedLines.add("<u>" + user);
                int totalParts = 18;
                String[] newEntry = new String[totalParts + 1];
                Arrays.fill(newEntry, "0");
                newEntry[0] = dateStr;
                if (part >= 0 && part < totalParts) {
                    newEntry[part + 1] = level;
                }
                updatedLines.add(String.join(",", newEntry) + "<s>\"\"<P>");
                updatedLines.add("<E>");
                endTagFound = true;
            }

            // Only add <E> once if not already present
            if (!endTagFound) {
                updatedLines.add("<E>");
            }

            // Write updated content back to file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            System.err.println("Error processing info.txt: " + e.getMessage());
        }
    }
    public void writeNoteToInfo(String note) {
        String user = this.storedUser;
        String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        File file = new File("documents/info.txt");
        List<String> updatedLines = new ArrayList<>();
        boolean userFound = false;
        boolean endTagFound = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Match user section
                if (line.equals("<u>" + user)) {
                    userFound = true;
                    updatedLines.add(line);

                    // Read the next line which contains all entries
                    String entriesLine = reader.readLine();
                    if (entriesLine != null && !entriesLine.equals("<E>")) {
                        StringBuilder updatedEntryLine = new StringBuilder();
                        String[] entries = entriesLine.split("<P>");
                        boolean dateUpdated = false;

                        for (String entry : entries) {
                            if (entry.startsWith(dateStr + ",")) {
                                String[] values = entry.split(",");
                                int strIndex = values.length - 1;
                                values[strIndex] = "<S>\"" + note + "\",";
                                entry = String.join(",", values);
                                dateUpdated = true;
                            }
                            updatedEntryLine.append(entry).append("<P>");
                        }

                        if (!dateUpdated) {
                            int totalParts = 19;
                            String[] newEntry = new String[totalParts + 1];
                            Arrays.fill(newEntry, "0");
                            newEntry[0] = dateStr;
                            newEntry[totalParts] = "<S>\"" + note + "\",";
                            updatedEntryLine.append(String.join(",", newEntry)).append("<P>");
                        }
                        updatedLines.add(updatedEntryLine.toString());
                    }

                    // Peek ahead to check for <E>
                    String nextLine = reader.readLine();
                    if (nextLine != null && nextLine.equals("<E>")) {
                        updatedLines.add("<E>");
                        endTagFound = true;
                    }

                    continue;
                }

                // Preserve other lines
                updatedLines.add(line);
                if (line.equals("<E>")) {
                    endTagFound = true;
                }
            }

            // If user not found, add new section
            if (!userFound) {
                updatedLines.add("<u>" + user);
                int totalParts = 19;
                String[] newEntry = new String[totalParts + 1];
                Arrays.fill(newEntry, "0");
                newEntry[0] = dateStr;
                newEntry[totalParts] = "<S>" + note + "";
                updatedLines.add(String.join(",", newEntry) + "<S><P>");
                updatedLines.add("<E>");
                endTagFound = true;
            }

            // Only add <E> once if not already present
            if (!endTagFound) {
                updatedLines.add("<E>");
            }

            // Write updated content back to file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            System.err.println("Error processing info.txt: " + e.getMessage());
        }
    }
    /**
     * Prints the symptom data to the console in a formatted manner.
     * @param symptoms An integer array containing the symptom data to be printed.
     */
    public static void printOutput(int[] symptoms) {
        System.out.println("Pain Levels: ");
        System.out.println("Upper Left: " + symptoms[0]);
        System.out.println("Upper Right: " + symptoms[1]);
        System.out.println("Middle Left: " + symptoms[2]);
        System.out.println("Middle Right: " + symptoms[3]);
        System.out.println("Lower Left: " + symptoms[4]);
        System.out.println("Lower Right: " + symptoms[5]);
        System.out.println("Eye Left: " + symptoms[6]);
        System.out.println("Eye Right: " + symptoms[7]);
        System.out.println("Ear Left: " + symptoms[8]);
        System.out.println("Ear Right: " + symptoms[9]);
        System.out.println("Teeth: " + symptoms[10]);
        System.out.println("Nose: " + symptoms[11]);
        System.out.println("Back Upper Left: " + symptoms[12]);
        System.out.println("Back Upper Right: " + symptoms[13]);
        System.out.println("Back Lower Left: " + symptoms[14]);
        System.out.println("Back Lower Right: " + symptoms[15]);
        System.out.println("Neck: " + symptoms[16]);
    }
    public static String parseOutput(String username, String date) {
        int symptoms[] = returnUserDataFromDate(username, date);
        String output = "";
        int count = 0;
        String[] symptomLabel = {"Upper Left", "Upper Right", "Middle Left", "Middle Right", "Lower Left",
         "Lower Right", "Eye Left", "Eye Right", "Ear Left", "Ear Right", "Teeth", "Nose", "Back Upper Left", 
         "Back Upper Right", "Back Lower Left", "Back Lower Right", "Neck"};
        if(symptoms == null) {
            return "No data collected for this day.";
        }
        for(int i = 0; i < symptoms.length; i++) {
            if(symptoms[i] > 0) {
                count++;
                output += (symptomLabel[i] + ": " + symptoms[i] + "/10\n");
            }
        }
        if(output.isEmpty()) {
            output = "No pain recorded.";
        }
        return output;
    }
}