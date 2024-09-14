import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("Введите первое число: ");
        int number = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число: ");
        int number_two = new Scanner(System.in).nextInt();
        System.out.println("Сумма чисел: " + (number+number_two) + "\n" + "Разность чисел: " + (number - number_two) + "\n" + "Произведение чисел: " + (number_two*number) + "\n" + "Частность чисел: " + ((double)number/(double)number_two));

    }


}
