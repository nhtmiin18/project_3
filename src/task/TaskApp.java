package task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Đã thêm công việc: " + task.getName());
    }

    public boolean removeTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                tasks.remove(task);
                System.out.println("Đã xóa công việc ID: " + id);
                return true;
            }
        }
        System.out.println("Không tìm thấy công việc với ID: " + id);
        return false;
    }

    public boolean updateTask(Task updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == updatedTask.getId()) {
                tasks.set(i, updatedTask);
                System.out.println("Đã cập nhật công việc ID: " + updatedTask.getId());
                return true;
            }
        }
        System.out.println("Không tìm thấy công việc với ID: " + updatedTask.getId());
        return false;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }
}

public class TaskApp {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Task task1 = new Task(1, "Làm bài tập Java", "Chưa xong", LocalDate.now());
        Task task2 = new Task(2, "Học Swing", "Đang làm", LocalDate.now());

        manager.addTask(task1);
        manager.addTask(task2);

        manager.removeTask(1);

        Task updatedTask = new Task(2, "Học Java Swing", "Hoàn thành", LocalDate.now());
        manager.updateTask(updatedTask);

        System.out.println("--- Danh sách công việc ---");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }
    }
}