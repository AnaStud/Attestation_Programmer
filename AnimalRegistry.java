import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimalRegistry {

    public static void main(String[] args) {

        System.out.println("Добро пожаловать в реестр животных!");
        Scanner scanner = new Scanner(System.in);
        AnimalRegistry registry = new AnimalRegistry();

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Завести новое животное");
            System.out.println("2. Вывести список команд животного");
            System.out.println("3. Обучить животное новым командам");
            System.out.println("4. Выход");

            System.out.print("Введите номер действия: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registry.addAnimal(scanner);
                    break;
                case 2:
                    registry.getCommands(scanner);
                    break;
                case 3:
                    registry.setCommands(scanner);
                    break;
                case 4:
                    System.out.println("До свидания!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, введите число от 1 до 4.");
            }
        }
    }

    private List<Animal> animals = new ArrayList<>();

    private void addAnimal(Scanner scanner) {

        try (AnimalCounter counter = new AnimalCounter()) {
            System.out.println("\nЗаведение нового животного:");
            System.out.print("Введите тип животного (Pet/PackAnimal): ");
            String animalType = scanner.nextLine();

            Animal animal;
            if (animalType.equalsIgnoreCase("Pet")) {
                System.out.print("Введите тип питомца (Dog/Cat/Hamster): ");
                String petType = scanner.nextLine();
                switch (petType.toLowerCase()) {
                    case "dog":
                        animal = new Dog();
                        break;
                    case "cat":
                        animal = new Cat();
                        break;
                    case "hamster":
                        animal = new Hamster();
                        break;
                    default:
                        System.out.println("Неверный тип питомца.");
                        return;
                }
            } else if (animalType.equalsIgnoreCase("PackAnimal")) {
                System.out.print("Введите тип питомца (Horse/Camel/Donkey): ");
                String packAnimalType = scanner.nextLine();
                switch (packAnimalType.toLowerCase()) {
                    case "horse":
                        animal = new Horse();
                        break;
                    case "camel":
                        animal = new Camel();
                        break;
                    case "donkey":
                        animal = new Donkey();
                        break;
                    default:
                        System.out.println("Неверный тип питомца.");
                        return;
                }
            } else {
                System.out.println("Неверный тип животного.");
                return;
            }

            System.out.print("Введите имя животного: ");
            animal.setName(scanner.nextLine());

            System.out.print("Введите дату рождения животного (YYYY-MM-DD): ");
            animal.setBirthDate(LocalDate.parse(scanner.nextLine()));

            System.out.print("Введите команды, которые выполняет животное (разделенные пробелами): ");
            animal.setCommands(scanner.nextLine().split(" "));

            counter.add();
            animal.setId(counter.getCount());

            animals.add(animal);
            System.out.println("Животное успешно добавлено в реестр.");
            
        } catch (InvalidResourceUsageException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) { 
            System.out.println("Ошибка при добавлении животного: " + e.getMessage());
        }
    }

    private void getCommands(Scanner scanner) {
        System.out.println("\nВыберите животное:");
        for (int i = 0; i < animals.size(); i++) {
            System.out.println((i + 1) + ". " + animals.get(i).getName());
        }

        System.out.print("Введите номер животного: ");
        int animalIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (animalIndex >= 0 && animalIndex < animals.size()) {
            Animal animal = animals.get(animalIndex);
            System.out.println("Команды животного " + animal.getName() + ":");
            for (String command : animal.getCommands()) {
                System.out.println("- " + command);
            }
        } else {
            System.out.println("Неверный номер животного.");
        }
    }

    private void setCommands(Scanner scanner) {
        System.out.println("\nВыберите животное:");
        for (int i = 0; i < animals.size(); i++) {
            System.out.println((i + 1) + ". " + animals.get(i).getName());
        }

        System.out.print("Введите номер животного: ");
        int animalIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline character

        if (animalIndex >= 0 && animalIndex < animals.size()) {
            Animal animal = animals.get(animalIndex);
            System.out.print("Введите новые команды (разделенные пробелами): ");
            String[] newCommands = scanner.nextLine().split(" ");

            String[] combinedCommands = new String[animal.getCommands().length + newCommands.length];
            System.arraycopy(animal.getCommands(), 0, combinedCommands, 0, animal.getCommands().length);
            System.arraycopy(newCommands, 0, combinedCommands, animal.getCommands().length, newCommands.length);

            animal.setCommands(combinedCommands);
            System.out.println("Животное обучено новым командам.");
        } else {
            System.out.println("Неверный номер животного.");
        }
    }
}

abstract class Animal {
    private int id;
    private String name;
    private String[] commands;
    private LocalDate birthDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCommands() {
        return commands;
    }

    public void setCommands(String[] commands) {
        this.commands = commands;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}

abstract class Pet extends Animal {
    
}

class Dog extends Pet {
    
}

class Cat extends Pet {
    
}

class Hamster extends Pet {
    
}

abstract class PackAnimal extends Animal {
    
}

class Horse extends PackAnimal {
    
}

class Camel extends PackAnimal {
    
}

class Donkey extends PackAnimal {
    
}

class AnimalCounter implements AutoCloseable {
    private int count;

    public void add() {
        count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void close() throws Exception {
        if (count == 0) {
            throw new InvalidResourceUsageException("Ресурс не использовался.");
        }
    }
}

class InvalidResourceUsageException extends Exception {
    public InvalidResourceUsageException(String message) {
        super(message);
    }
}
