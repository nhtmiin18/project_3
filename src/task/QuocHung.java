package task;

import java.time.LocalDate;
import java.util.Scanner;

class Task {
    private int id;
    private String name;
    private String status;
    private LocalDate createdDate;

    public Task(int id, String name, String status, LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + name + " - " + status + " (" + createdDate + ")";
    }
}

public class QuocHung {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhap ten nhiem vu: ");
        String name = scanner.nextLine();

        System.out.print("Nhap trang thai (Pending/In Progress/Completed): ");
        String status = scanner.nextLine();

        LocalDate createdDate = LocalDate.now();

        Task task = new Task(id, name, status, createdDate);

        System.out.println("\nThong tin nhiem vu:");
        System.out.println(task);

        scanner.close();
    }
}