import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        YearlyReport yearlyManager = new YearlyReport();
        MonthlyReport monthlyManager = new MonthlyReport();
        Checker checker = new Checker(yearlyManager, monthlyManager);
        Scanner sc = new Scanner(System.in);

        while (true){
            printMenu();
            int userInput = sc.nextInt();
            if(userInput == 1){
                monthlyManager.addMonth();
                System.out.println("Месячные отчёты считаны");
            } else if(userInput == 2) {
                yearlyManager.addYear();
                System.out.println("Годовой отчёт считан");
            } else if (userInput == 3) {
                checker.check();
            } else if (userInput == 4){
                monthlyManager.printStat();
            } else if (userInput == 5) {
                yearlyManager.printState();
            } else if (userInput == 0) {
                break;
            }
        }
    }


    public static void printMenu(){
        System.out.println("Что вы хотите сделать ?");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выйти из приложения.");
        System.out.println("");
    }
}

