package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();
        printData(tasksData);
        System.out.println("\nPrinting deadlines");
        printDeadlines(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        //printDataWithStreams(tasksData);
        printDeadlinesWithStream(tasksData);
        System.out.println("Total number of deadlines with stream: " + countDeadlinesWithStreams(tasksData));
        printDeadlinesWithStreamSorted(tasksData);

        ArrayList<Task> filteredList = filterTasksByString(tasksData,"11");
        System.out.println(filteredList);

    }

    private static ArrayList<Task> filterTasksByString(ArrayList<Task> tasksData,String S) {
        ArrayList<Task> filteredList;
        filteredList = (ArrayList<Task>) tasksData.stream().filter(t -> t.getDescription().contains(S))
                .collect(toList());
        return filteredList;
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }
    private static int countDeadlinesWithStreams(ArrayList<Task> tasksData) {
        int count = 0;
        count = (int)tasksData.stream()
                .filter(t -> t instanceof Deadline)
                .count();
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataWithStreams(ArrayList<Task> tasksData){
        System.out.println("\nPrint tasks using streams");
        tasksData.stream()
                .forEach(System.out::println); // terminal operation
    }
    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesWithStream(ArrayList<Task> tasksData) {
        System.out.println("\nPrint deadline using streams");
        tasksData.stream()
                .filter(task -> task instanceof Deadline)
                .forEach(System.out::println);
    }
    public static void printDeadlinesWithStreamSorted(ArrayList<Task> tasksData) {
        System.out.println("\nPrint deadline using streams");
        tasksData.stream()
                .filter(task -> task instanceof Deadline)
                .sorted((a,b) -> a.getDescription().toLowerCase().compareTo(b.getDescription().toLowerCase()))
                .forEach(System.out::println);
    }
}
