package Task2;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CorporationGeneralList {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static ListWorkers listWorkers = new ListWorkers();

    public static void main(String[] args) {

//        ArrayList<Worker> workers = new ArrayList<>();
//        Worker w1 = new Worker("Хорт", 25, "JAVA-программист", 2500);
//        Worker w2 = new Worker("Латушко", 24, "SEO-специалист", 2850);
//        Worker w3 = new Worker("Соколовская", 25, "Frontend-разработчик", 2450);
//        workers.add(w1);
//        workers.add(w2);
//        workers.add(w3);
//
//        listWorkers.setWorkersList(workers);
//        writeInFile();

        // Отображение информации при запуске
        listWorkers = readFromFile();
        System.out.println("Информация о сотрудниках:");
        listWorkers.getWorkersList().forEach(System.out::println);
        System.out.println();

        //начало программы
        start();
    }

    public static void start() {
        showMenu();
        int choice;
        try {
            choice = Integer.parseInt(reader.readLine());
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
                case 4: {
                    deleteWorker();
                    break;
                }
                case 5: {
                    findWorker();
                    break;
                }

                case 6: {
                    findAllInputAge();
                    break;
                }
                case 0: {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream("CorporationList.txt");
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                        objectOutputStream.writeObject(listWorkers);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showMenu() {
        System.out.print(
                "1 - Ввод нового сотрудника;\n" +
                "2 - Редактирование зарплаты сотрудника;\n" +
                "3 - Сохранить информацию;\n" +
                "4 - Удалить сотрудника;\n" +
                "5 - Поиск сотрудника;\n" +
                "6 - Поиск сотрудников по возрасту;\n" +
                "0 - Выход из программы.\n" +
                "Выберите действие: ");
    }

    public static void writeInformationWorker() {
        Worker worker = new Worker();
        try {
            System.out.print("Введите фамилию: ");
            worker.setSurname(reader.readLine());
            System.out.print("Введите возраст: ");
            worker.setAge(Integer.parseInt(reader.readLine()));
            System.out.print("Введите должность: ");
            worker.setPosition(reader.readLine());
            System.out.print("Введите ставку ЗП: ");
            worker.setSalaryRate(Double.parseDouble(reader.readLine()));
            listWorkers.getWorkersList().add(worker);
            System.out.println("Данные успешно внесены!\n");
            start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void replaceSalary() {
        try {
            System.out.println("Введите фамилию сотрудника для замены ЗП: ");
            String surname = reader.readLine();
            AtomicBoolean isHas = new AtomicBoolean(false);
            listWorkers.getWorkersList().forEach(worker -> {
                if (worker.getSurname().equalsIgnoreCase(surname)) {
                    System.out.print("Введите новую ставку ЗП: ");
                    try {
                        worker.setSalaryRate(Double.parseDouble(reader.readLine()));
                        isHas.set(true);
                        System.out.println("Данные успешно изменены!\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            if (!isHas.get()) {
                System.out.println("Такого сотрудника нет!\n");
            }
            start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteWorker() {
        try {
            System.out.print("Введите фамилию сотрудника для удаления: ");
            String surname = reader.readLine();
            AtomicBoolean isHas = new AtomicBoolean(false);

            listWorkers.getWorkersList().removeIf(worker -> {
                if (worker.getSurname().equals(surname)) {
                    isHas.set(true);
                    System.out.println("Сотрудник " + surname + " удален!\n");
                    return true;
                }
                return false;
            });

            if (!isHas.get()) {
                System.out.println("Такого сотрудника нет!\n");
            }
            start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void findWorker() {
        try {
            System.out.print("Введите фамилию сотрудника для поиска: ");
            String surname = reader.readLine();
            AtomicBoolean isHas = new AtomicBoolean(false);
            listWorkers.getWorkersList().forEach(worker -> {
                if (worker.getSurname().equals(surname)) {
                    isHas.set(true);
                    System.out.println("Сотрудник:\n" + worker + "\n");
                }
            });
            if (!isHas.get()) {
                System.out.println("Такого сотрудника нет!\n");
            }
            start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void findAllInputAge() {
        try {
            System.out.print("Введите возраст сотрудника для поиска: ");
            int ageInput = Integer.parseInt(reader.readLine());
            AtomicBoolean isHas = new AtomicBoolean(false);
            listWorkers.getWorkersList().stream()
                            .filter(worker -> {
                                if (worker.getAge() == ageInput) {
                                    isHas.set(true);
                                    return true;
                                }
                                return false;
                            })
                            .peek(System.out::println)
                            .forEach(worker -> {});

            if (!isHas.get()) {
                System.out.println("Таких сотрудников нет!\n");
            } else {
                System.out.println();
            }
            start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeInFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("CorporationList.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(listWorkers);
            System.out.println("Данные сохранены в файл!\n");
            start();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ListWorkers readFromFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(System.getProperty("user.dir") + File.separator, "CorporationList.txt"));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (ListWorkers) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
