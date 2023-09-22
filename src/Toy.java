import java.io.*;
import java.util.Scanner;

public class Toy {
   int id;
   String name;
   int quantity;
   double weight;

   public void setId(int id) {
        this.id = id;
    }
   public void setName(String name) {
        this.name = name;
    }
   public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
   public void setWeight(double weight) {
        this.weight = weight;
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

            System.out.print("Введите количество ишрушки: ");
            int toyQuantity = input.nextInt();
            toy.setQuantity(toyQuantity);

            System.out.print("Введите вес ишрушки: ");
            double toyWeight = input.nextDouble();
            toy.setWeight(toyWeight);

            Writer writer = new FileWriter("ToysList.txt", true);
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
       try(BufferedReader br = new BufferedReader(new FileReader("ToysList.txt"))){
           String line;
           while ((line = br.readLine()) != null){
               System.out.println(line);
           }
       }catch (IOException e){
           e.printStackTrace();
       }
   }
   public static void ChangeToysWeight(){
       Scanner input = new Scanner(System.in);
       System.out.print("Введите ID игрушки: ");
       int toyID = input.nextInt();

       try(BufferedReader br = new BufferedReader(new FileReader("ToysList.txt"))){
           String line;
           StringBuilder sb = new StringBuilder();
           while ((line = br.readLine()) != null){
               if(Character.digit(line.charAt(3), 10) == toyID){

                   System.out.println("Текущая информация об игрушке: " + line);
                   System.out.print("Введите новое значение веса игрушки (в формате 0.0): ");
                   String newWeight = input.next();

//                   sb.append(line);
//                   sb.setCharAt(line.lastIndexOf("=") + 1, newWeight.charAt(0));
//                   sb.setCharAt(line.lastIndexOf("=") + 3, newWeight.charAt(2));
//                   sb.append("\n");
               }else{
                   sb.append(line + "\n");
               }
           }
           Writer writer = new FileWriter("ToysList.txt");
           writer.append(sb);
           writer.close();
       }catch (IOException e){
           e.printStackTrace();
       }
}
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id=" + id + ", name=" + name + ", quantity=" + quantity + ", weight=" + weight);
        return sb.toString();
    }
}
