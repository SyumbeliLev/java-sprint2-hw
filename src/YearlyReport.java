import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YearlyReport {

    ArrayList<ItemYear> yearInfoExp = new ArrayList<>();
    ArrayList<ItemYear> yearInfoProfit = new ArrayList<>();
    public void addYear(){
        String path = "resources/y.2021.csv";
        List<String> contetn = readFileContents(path);

        for (int i = 1; i < contetn.size(); i++){
            String line = contetn.get(i);
            String [] parts = line.split(",");

            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            if(isExpense){
                yearInfoExp.add(new ItemYear(month, amount,true));
            }
            else {
                yearInfoProfit.add(new ItemYear(month,amount, false));
            }

        }

    }
    public void printState() {
            if(yearInfoProfit.isEmpty() || yearInfoExp.isEmpty()){
                System.out.println("Годовой отчёт ещё не считан");
            }
            else {
                int allExp = 0;
                int allProfit = 0;
                System.out.println("Отчёт за 2021 год"); //всё ручками заполняем, пока что)))
                    for (int i = 0; i < yearInfoProfit.size(); i++) {
                        ItemYear ItemP = yearInfoProfit.get(i);
                        ItemYear ItemE = yearInfoExp.get(i);

                        System.out.println("Прибыль за "+ (i+1) +" месяц - " + (ItemP.amount - ItemE.amount));
                        allExp += ItemE.amount;
                        allProfit += ItemP.amount;
                    }
                System.out.println("Средний расход за все месяцы - " + allExp/yearInfoExp.size());
                System.out.println("Средний доход за все месяцы - " + allProfit/yearInfoProfit.size());
            }
    }

    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
}
