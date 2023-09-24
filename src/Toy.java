import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Toy {
   int id;
   String name;
   int quantity;
   int weight;

   public void setId(int id) {this.id = id;}
   public void setName(String name) {this.name = name;}
   public void setQuantity(int quantity) {this.quantity = quantity;}
   public void setWeight(int weight) {this.weight = weight;}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id=" + id + ", name=" + name + ", quantity=" + quantity + ", weight=" + weight);
        return sb.toString();
    }

   public static void AddToy() throws IOException {
       Toy toy = new Toy();
       Scanner input = new Scanner(System.in);
        while (true){
            System.out.print("Введите id ишрушки: ");
            int toyId = input.nextInt();
            toy.setId(toyId);


            System.out.print("Введите название ишрушки: ");
            String toyName = input.next();
            toy.setName(toyName);

            System.out.print("Введите количество ишрушек: ");
            int toyQuantity = input.nextInt();
            toy.setQuantity(toyQuantity);

            System.out.print("Введите вес ишрушки: ");
            int toyWeight = input.nextInt();
            toy.setWeight(toyWeight);

            Writer writer = new FileWriter("src/ToysList.txt", true);
            writer.append(toy + "\n");
            writer.close();

            System.out.println("Ввести еще одну игрушку? (Да - 'д' / Нет - 'н') ");
            String choice = input.next();
            if(choice.equals("н")){
                System.out.println("Данные сохранены в файл.");
                System.out.println();
                Main.Start();
            }
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
               System.out.println(arrayList);
           }
       }catch(IOException e){
           e.printStackTrace();
       }
   }
}
