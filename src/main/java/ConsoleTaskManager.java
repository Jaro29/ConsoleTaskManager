import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ConsoleTaskManager {
    static String[][] tasks = new String[0][3];

    static Path path1 = Paths.get("tasks.csv");

    public static void main(String[] args) {

        try {
            Scanner scan = new Scanner(path1);
            String singleTask;
            String[] taskData;
            while (scan.hasNextLine()) {
                singleTask = scan.nextLine();
                tasks = Arrays.copyOf(tasks, tasks.length + 1);
                taskData = singleTask.split(", ");
                tasks[tasks.length - 1] = taskData;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        menu();
    }

    public static void addTask() {
        System.out.println("Please add task description");
        Scanner scan = new Scanner(System.in);
        String[] taskData = new String[3];
        taskData[0] = scan.nextLine();

        System.out.println("Please add task due date");
        taskData[1] = scan.nextLine();

        System.out.println("Is your task important? true/false");
        taskData[2] = scan.nextLine();

        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = taskData;
        menu();
    }

    public static void remove() {
        if ((tasks.length - 1) >= 0) {

            System.out.println("Please select number from 0 to " + (tasks.length - 1) + " to remove");
            Scanner scan = new Scanner(System.in);
            try {
                int taskToRemove = scan.nextInt();

                if ((taskToRemove >= 0) && (taskToRemove < tasks.length)) {

                    tasks = ArrayUtils.remove(tasks, taskToRemove);
                    System.out.println("Value was successfully deleted.");
                } else {
                    System.out.println("You entered an invalid argument. Enter a number between 0 and " + (tasks.length - 1));
                    remove();
                }
            } catch (InputMismatchException e) {
                System.out.println("It has to be a number. Error " + e + " Start from the beginning.");
                scan.next();
            }
        } else {
            System.out.println("There is no task to remove.");
        }
        menu();
    }

    public static void list() {
        if ((tasks.length - 1) >= 0) {

            for (int i = 0; i < tasks.length; i++) {
                System.out.print(i + " :");
                for (int k = 0; k < tasks[i].length; k++) {
                    System.out.print((" " + tasks[i][k]));
                }
                System.out.print("\n");
            }
        } else {
            System.out.println("There is no task to list.");
        }
        menu();
    }

    public static void exit() {

        String toFile;
        List<String> outList = new ArrayList<>();
        try {
            if (tasks.length - 1 < 0) {
                Files.writeString(path1, "");
            }
            for (int i = 0; i < tasks.length; i++) {
                toFile = tasks[i][0] + ", " + tasks[i][1] + ", " + tasks[i][2];
                outList.add(toFile);
                Files.write(path1, outList);
            }
        } catch (IOException ex) {
            System.out.println("Nie można zapisać pliku.");
        }
        System.out.println(ConsoleColors.RED + "Bye, bye :-)");
    }

    public static void menu() {

        System.out.println(ConsoleColors.BLUE + "Please select an option: ");
        String[] options = {"add", "remove", "list", "exit"};
        for (int i = 0; i < options.length; i++) {
            System.out.println(ConsoleColors.RESET + options[i]);
        }
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        switch (input) {
            case "add" -> addTask();
            case "remove" -> remove();
            case "list" -> list();
            case "exit" -> exit();
            default -> {
                System.out.println("Please select a correct option.");
                menu();
            }
        }
    }

    public static class ConsoleColors {
        // Reset
        public static final String RESET = "\033[0m";  // Text Reset

        // Regular Colors
//        public static final String BLACK = "\033[0;30m";   // BLACK
        public static final String RED = "\033[0;31m";     // RED
        //        public static final String GREEN = "\033[0;32m";   // GREEN
//        public static final String YELLOW = "\033[0;33m";  // YELLOW
        public static final String BLUE = "\033[0;34m";    // BLUE
//        public static final String PURPLE = "\033[0;35m";  // PURPLE
//        public static final String CYAN = "\033[0;36m";    // CYAN
//        public static final String WHITE = "\033[0;37m";   // WHITE

        // Bold
//        public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
//        public static final String RED_BOLD = "\033[1;31m";    // RED
//        public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
//        public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
//        public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
//        public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
//        public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
//        public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

        // Underline
//        public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
//        public static final String RED_UNDERLINED = "\033[4;31m";    // RED
//        public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
//        public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
//        public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
//        public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
//        public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
//        public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

        // Background
//        public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
//        public static final String RED_BACKGROUND = "\033[41m";    // RED
//        public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
//        public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
//        public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
//        public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
//        public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
//        public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

        // High Intensity
//        public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
//        public static final String RED_BRIGHT = "\033[0;91m";    // RED
//        public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
//        public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
//        public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
//        public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
//        public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
//        public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

        // Bold High Intensity
//        public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
//        public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
//        public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
//        public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
//        public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
//        public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
//        public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
//        public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

        // High Intensity backgrounds
//        public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
//        public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
//        public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
//        public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
//        public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
//        public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
//        public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
//        public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
    }
}