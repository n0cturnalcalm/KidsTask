import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static ArrayList<Task> notCompletedTasks = new ArrayList<Task>();
    public static ArrayList<Task> CompletedTasks = new ArrayList<Task>();
    public static ArrayList<Task> ApprovedTasks = new ArrayList<Task>();
    public static ArrayList<Task> PassedTasks = new ArrayList<Task>();

    public static ArrayList<Wish> requestedWishes = new ArrayList<Wish>();
    public static ArrayList<Wish> approvedWishes = new ArrayList<Wish>();

    public static Parent anna = new Parent("Anna");
    public static Teacher jack =new Teacher("Jack");
    public static Child billy = new Child("Billy", 1);

    static {
        anna.addChild(billy);
        jack.addStudent(billy);
        billy.addParent(anna);
        billy.addTeacher(jack);
    }

    public static void main(String[] args) {
        char key = 'a';
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("E:\\Uni\\KidsTask\\src\\Commands.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            String input = null;
            try {
                input = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (input == null) {
                continue;
            }

            if (input.equals("exit")) {
                System.out.println("Files are uptating...");
                jack.updateTasksFile();
                jack.updateWishesFile();
                System.out.println("Files are updated");
                System.out.println("Exiting...");
                break;
            }

            if (input.length() >= 8 && input.substring(0, 8).equals("ADD_TASK")) {
                String command;
                boolean type = false;
                char givenByChar;
                int taskId;
                String taskTitle;
                String taskDescription;
                LocalDateTime dateTime;
                int reward;
                int experience;

                String[] parsedInput = input.split(" ", 3);
                command = parsedInput[0];
                if (command.charAt(8) == 1) {
                    type = false;
                } else if (command.charAt(8) == 2) {
                    type = true;
                }
                givenByChar = parsedInput[1].charAt(0);
                String remainingCommand = parsedInput[2];

                int indexQ1 = remainingCommand.indexOf("\"");
                taskId = Integer.parseInt(remainingCommand.substring(0, indexQ1).trim());
                int indexQ2 = remainingCommand.indexOf("\"", indexQ1 + 1);
                taskTitle = remainingCommand.substring(indexQ1 + 1, indexQ2).trim();
                int indexQ3 = remainingCommand.indexOf("\"", indexQ2 + 1);
                int indexQ4 = remainingCommand.indexOf("\"", indexQ3 + 1);
                taskDescription = remainingCommand.substring(indexQ3 + 1, indexQ4).trim();

                String[] remainingCommand2 = remainingCommand.substring(indexQ4 + 1).trim().split(" ");
                if (type == false) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    dateTime = LocalDate.parse(remainingCommand2[0], formatter).atStartOfDay();
                } else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    dateTime = LocalDateTime.parse(remainingCommand2[0] + " " + remainingCommand2[1], formatter);
                }
                System.out.println("Parsed Input Length: " + parsedInput.length);
                System.out.println("Parsed Input: " + Arrays.toString(parsedInput));

                reward = Integer.parseInt(remainingCommand2[2]);
                experience = (int) (reward * 0.6);

                if (key == 'p' || key == 'a') {
                    anna.addTask(taskId, taskTitle, taskDescription, type, dateTime, reward, 0, 0);
                } else if (key == 't' || key == 'a') {
                    jack.addTask(taskId, taskTitle, taskDescription, type, dateTime, reward, 0, 0);
                } else {
                    System.out.println("You are not authorized to add a task!");
                }

            } else if (input.length() >= 14 && input.substring(0, 14).equals("LIST_ALL_TASKS")) {
                billy.listTasks();

            } else if (input.length() >= 15 && input.substring(0, 15).equals("LIST_ALL_WISHES")) {
                billy.listWishes();

            } else if (input.length() >= 10 && input.substring(0, 9).equals("TASK_DONE")) {
                System.out.println("task 101 done");
                int taskId = Integer.parseInt(input.substring(10));
                billy.completeTask(taskId);

            } else if (input.length() >= 12 && input.substring(0, 12).equals("TASK_CHECKED")) {
                System.out.println("task 101 checked");
                int taskId = Integer.parseInt(input.substring(13));
                anna.approveTask(taskId, 5);

            } else if (input.length() >= 8 && input.substring(0, 8).equals("ADD_WISH")) {
                String command;
                boolean type = Boolean.parseBoolean(null);
                String wishId;
                String wishTitle;
                String wishDescription;
                LocalDateTime dateTime = null;

                String[] parsedInput = input.split(" ", 3);
                command = parsedInput[0];
                if (command.charAt(8) == 1) {
                    type = false;
                } else if (command.charAt(8) == 2) {
                    type = true;
                }

                String remainingCommand = parsedInput[2];

                int indexQ1 = remainingCommand.indexOf("\"");
                wishId = remainingCommand.substring(0, indexQ1).trim();
                int indexQ2 = remainingCommand.indexOf("\"", indexQ1 + 1);
                wishTitle = remainingCommand.substring(indexQ1 + 1, indexQ2).trim();
                int indexQ3 = remainingCommand.indexOf("\"", indexQ2 + 1);
                int indexQ4 = remainingCommand.indexOf("\"", indexQ3 + 1);
                wishDescription = remainingCommand.substring(indexQ3 + 1, indexQ4).trim();

                if (type == true){
                    String[] remainingCommand2 = remainingCommand.substring(indexQ4 + 1).trim().split(" ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    dateTime = LocalDateTime.parse(remainingCommand2[0] + " " + remainingCommand2[1], formatter);
                }
                System.out.println("Parsed Input Length: " + parsedInput.length);
                System.out.println("Parsed Input: " + Arrays.toString(parsedInput));

                if (key == 'c' || key == 'a') {
                    billy.requestWish(wishId, wishTitle, wishDescription, type, dateTime);
                } else {
                    System.out.println("You are not authorized to add a task!");
                }

            } else if (input.length() >= 15 && input.substring(0, 15).equals("ADD_BUDGET_COIN")) {
                int amount = Integer.parseInt(input.substring(16));
                System.out.println("extra points given " + amount);
                anna.giveExtraPoints(billy, amount);

            } else if (input.length() >= 12 && input.substring(0, 12).equals("WISH_CHECKED")) {
                System.out.println("wish 101 checked");
                Integer levelRes = null;
                String[] parsedInput = input.split(" ");
                String wishId = parsedInput[1];
                String result = parsedInput[2];
                if (result.equals("APPROVED")) {
                    levelRes = Integer.parseInt(parsedInput[3]);
                }

                if (result.equals("APPROVED")) {
                    if (levelRes != null){
                        anna.approveWish(wishId, levelRes);
                    } else{
                        anna.approveWish(wishId, 45);
                    }
                } else if (result.equals("REJECTED")) {
                    anna.rejectWish(wishId);
                }

            } else if (input.length() >= 12 && input.substring(0, 12).equals("PRINT_BUDGET")) {
                System.out.println("Billy's points: " + billy.childPoints);

            } else if (input.length() >= 12 && input.substring(0, 12).equals("PRINT_STATUS")) {
                System.out.println("Billy's level: " + billy.childLevel);
                System.out.println("Billy's points: " + billy.childPoints);
                System.out.println("Succesful Tasks: " + billy.taskCount);


            } else {
                System.out.println("Invalid command!");

            }
        }
    }
}