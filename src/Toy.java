import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Toy {
   String id;
   String name;
   String quantity;
   String weight;

   public void setId(String id) {this.id = id;}
   public void setName(String name) {this.name = name;}
   public void setQuantity(String quantity) {this.quantity = quantity;}
   public void setWeight(String weight) {this.weight = weight;}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id=" + id + ", name=" + name + ", quantity=" + quantity + ", weight=" + weight);
        return sb.toString();
    }

   public static void AddToy() throws IOException {
       BufferedReader br = new BufferedReader(new FileReader("src/ToysID.txt"));
       int toysIdCounter =Integer.parseInt(br.readLine());
       br.close();

       Toy toy = new Toy();
       Scanner input = new Scanner(System.in);
        while (true){

            toy.setId(Integer.toString(toysIdCounter));
            System.out.print("ID ишрушки: " + toysIdCounter + "\n");

            System.out.print("Введите название игрушки: ");
            String toyName = input.next();
            toy.setName(toyName);

            System.out.print("Введите количество ишрушек: ");
            String toyQuantity = input.next();
            if(isNumeric(toyQuantity)){
                toy.setQuantity(toyQuantity);
            }else{
                System.out.println("Введено не коректоное значение! Повторите попытку.");
                AddToy();
            }

            System.out.print("Введите вес ишрушки (в формате 0.0): ");
            String toyWeight = input.next();
            if(isDouble(toyWeight)){
                toy.setWeight(toyWeight);
            }else{
                System.out.println("Введено не коректоное значение! Повторите попытку.");
                AddToy();
            }

            Writer writer1 = new FileWriter("src/ToysList.txt", true);
            writer1.append(toy + "\n");
            writer1.close();

            System.out.println("Ввести еще одну игрушку? (Да - 'д' / Нет - 'н') ");
            String choice = input.next();
            if(choice.equals("н")){
                System.out.println("Данные сохранены в файл.");
                System.out.println();
                Main.Start();
            }
            toysIdCounter += 1;
            Writer writer2 = new FileWriter("src/ToysID.txt");
            writer2.append(Integer.toString(toysIdCounter));
            writer2.close();
        }

   }
   public static void ShowToysList(){
       try(BufferedReader br = new BufferedReader(new FileReader("src/ToysList.txt"))){
           String line;
           while ((line = br.readLine()) != null){
               System.out.println(line);
           }
       }catch (IOException e){
           e.printStackTrace();
       }
   }
   public static void ChangeToysWeight(){
       ArrayList arrayList = new ArrayList();
       Scanner input = new Scanner(System.in);
       System.out.print("Введите ID игрушки: ");
       int toyID = input.nextInt();

       try(BufferedReader br = new BufferedReader(new FileReader("src/ToysList.txt"))){
           String line;
           StringBuilder sb = new StringBuilder();
           while ((line = br.readLine()) != null){
               if(Character.digit(line.charAt(3), 10) == toyID){
                   StringBuilder tempLine = new StringBuilder(line);

                   System.out.println("Текущая информация об игрушке:");
                   System.out.println(line);
                   System.out.print("Введите новое значение веса игрушки: ");
                   String newWeight = input.next();

                   tempLine.replace(line.lastIndexOf("=") + 1, line.lastIndexOf("=") + 4,newWeight);

                   sb.append(tempLine);
                   sb.append("\n");
               }else{
                   sb.append(line);
                   sb.append("\n");
               }
           }
           Writer writer = new FileWriter("src/ToysList.txt");
           writer.append(sb);
           writer.close();
       }catch (IOException e){
           e.printStackTrace();
       }
    }
   public static void PrizeDrow(){
       Scanner input = new Scanner(System.in);
       try{
          ArrayList<String> arrayList = new ArrayList<>(Files.readAllLines(Paths.get("src/ToysList.txt")));
           while (!arrayList.isEmpty()){
               int index = (int) (Math.random() * arrayList.size());
               System.out.println("!!! ПОЗДРАВЛЯЕМ !!! Вы выиграли игрушку: " + arrayList.get(index));
               Writer writer = new FileWriter("src/PrizeList.txt", true);
               writer.append(arrayList.get(index) + "\n");
               writer.close();
               arrayList.remove(index);
               System.out.println("Игрушки оставшиеся в списке: " + arrayList);

               System.out.println("Желаете продолжить? (ДА -> 'д'/ НЕТ -> 'н')");
               String choice = input.next();
               if(choice.equals("н")){Main.Start();}

           }
       }catch(IOException e){
           e.printStackTrace();
       }
   }

   public static boolean isNumeric(String value){
       try {
           Integer.parseInt(value);
           return true;
       }catch (NumberFormatException e){
           return false;
       }
   }
   public static boolean isDouble(String value){
        try {
            Double.parseDouble(value);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
