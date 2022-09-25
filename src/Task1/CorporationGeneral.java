package Task1;

import java.io.*;

public class CorporationGeneral {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static Worker worker;
    public static void main(String[] args) {

        // Отображение информации при запуске
        worker = readFromFile();
        System.out.println("Информация о сотруднике:\n" + worker + "\n");

        //начало программы
        start();
    }

    public static void start() {
        showMenu();
        int choice;
        try {
            choice = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        switch (choice) {
            case 1: {
                writeInformationWorker();
                break;
            }
            case 2: {
                replaceSalary();
                break;
            }
            case 3: {
                writeInFile();
                break;
            }
            case 0: {
                break;
            }
        }
    }

    public static void showMenu() {
        System.out.print(
                "1 - Ввод данных сотрудника;\n" +
                "2 - Редактирование зарплаты сотрудника;\n" +
                "3 - Сохранить информацию в файл\n" +
                "0 - Выход из программы\n" +
                "Выберите действие: ");
    }

    public static void writeInformationWorker() {
        try {
            System.out.print("Введите фамилию: ");
            worker.setSurname(reader.readLine());
            System.out.print("Введите возраст: ");
            worker.setAge(Integer.parseInt(reader.readLine()));
            System.out.print("Введите должность: ");
            worker.setPosition(reader.readLine());
            System.out.print("Введите ставку ЗП: ");
            worker.setSalaryRate(Double.parseDouble(reader.readLine()));
            System.out.println("Данные успешно внесены!\n");
            start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void replaceSalary() {
        try {
            System.out.print("Введите новую ставку ЗП: ");
            worker.setSalaryRate(Double.parseDouble(reader.readLine()));
            System.out.println("Данные успешно изменены!\n");
            start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeInFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("Corporation.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(worker);
            System.out.println("Данные сохранены в файл!\n");
            start();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Worker readFromFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(System.getProperty("user.dir") + File.separator, "Corporation.txt"));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Worker) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
