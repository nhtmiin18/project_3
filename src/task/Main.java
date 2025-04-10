package task;

import java.util.*;
import java.time.LocalDate;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();
        JobService jobService = new JobService();
        while (true) {
            System.out.println("\n======= MENU =======");
            System.out.println("1. Thêm công việc");
            System.out.println("2. Xóa công việc");
            System.out.println("3. Cập nhật công việc");
            System.out.println("4. Xem danh sách công việc");
            System.out.println("5. Tìm kiếm công việc (title)");
            System.out.println("6. Lọc công việc theo vị trí & lương");
            System.out.println("7. Đọc file + commit");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Vui lòng nhập số hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Nhập ID: ");
                    int id;
                    try {
                        id = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Vui lòng nhập ID là số hợp lệ!");
                        continue;
                    }
                    System.out.print("Tên công việc: ");
                    String name = scanner.nextLine();
                    System.out.print("Trạng thái: ");
                    String status = scanner.nextLine();
                    Task task = new Task(id, name, status, LocalDate.now());
                    manager.addTask(task);
                }
                case 2 -> {
                    System.out.print("Nhập ID cần xóa: ");
                    int id;
                    try {
                        id = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Vui lòng nhập ID là số hợp lệ!");
                        continue;
                    }
                    if (!manager.removeTask(id)) {
                        System.out.println("❌ Không tìm thấy công việc ID: " + id);
                    } else {
                        System.out.println("✅ Đã xóa công việc.");
                    }
                }
                case 3 -> {
                    System.out.print("ID cần cập nhật: ");
                    int id;
                    try {
                        id = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Vui lòng nhập ID là số hợp lệ!");
                        continue;
                    }
                    System.out.print("Tên mới: ");
                    String name = scanner.nextLine();
                    System.out.print("Trạng thái mới: ");
                    String status = scanner.nextLine();
                    Task task = new Task(id, name, status, LocalDate.now());
                    if (manager.updateTask(task)) {
                        System.out.println("✅ Đã cập nhật.");
                    } else {
                        System.out.println("❌ Không tìm thấy công việc.");
                    }
                }
                case 4 -> {
                    System.out.println("--- DANH SÁCH CÔNG VIỆC ---");
                    for (Task task : manager.getAllTasks()) System.out.println(task);
                }
                case 5 -> {
                    System.out.print("Nhập từ khóa: ");
                    String keyword = scanner.nextLine();
                    List<Job> jobs = jobService.searchByTitle(keyword);
                    jobs.forEach(System.out::println);
                }
                case 6 -> {
                    System.out.print("Nhập vị trí: ");
                    String location = scanner.nextLine();
                    System.out.print("Nhập lương tối thiểu: ");
                    double minSalary;
                    try {
                        minSalary = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Vui lòng nhập lương là số hợp lệ!");
                        continue;
                    }
                    List<Job> jobs = jobService.filterByLocationAndSalary(location, minSalary);
                    jobs.forEach(System.out::println);
                }
                case 7 -> {
                    readFileAndCommit(scanner); // Thêm {} để thống nhất cú pháp
                }
                case 0 -> {
                    System.out.println("👋 Thoát chương trình.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("⚠️ Lựa chọn không hợp lệ!");
            }
        }
    }

    public static void readFileAndCommit(Scanner scanner) {
        System.out.print("Nhập đường dẫn tới file cần đọc: ");
        String path = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            System.out.println("\n--- Nội dung file ---");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Không thể đọc file: " + e.getMessage());
        }

        System.out.print("\nNhập commit message: ");
        String message = scanner.nextLine();

        System.out.println("\n✅ COMMIT THÀNH CÔNG");
        System.out.println("File: " + path);
        System.out.println("Message: " + message);
        System.out.println("Thời gian: " + java.time.LocalDateTime.now());
    }
}