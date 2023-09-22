import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Start();
    }
        public static void Start() throws IOException {
            while (true){
                System.out.println("ВЫБЕРИТЕ ОПЕРАЦИЮ: \n" +
                                   "- Добавит игрушку:        - 1;\n" +
                                   "- Вывести список игрушек: - 2;\n" +
                                   "- Изменить вес игрушки:   - 3;\n" +
                                   "- Запустить розыгрыш:     - 4;\n" +
                                   "- Выйти из приложения:    - 5;");
                Scanner input = new Scanner(System.in);
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Операция добавления игрушки:");
                        Toy.AddToy();
                        break;
                    case 2:
                        System.out.println("Список игрушек:");
                        Toy.ShowToysList();
                        break;
                    case 3:
                        System.out.println("Изменение веса игрушки:");
                        Toy.ChangeToysWeight();
                        break;
                    case 4:
                        System.out.println("Операция запуска розыгрыша игрушек:");
                        break;
                    case 5:
                        System.out.println("Работа приложения завершена:");
                        System.exit(1);
                }
            }
       }
}
