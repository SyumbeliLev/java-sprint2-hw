import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MonthlyReport {
    public HashMap<Integer, ArrayList<ItemMonth>> monthInfo= new HashMap<>();
    public void addMonth(){

        for (int i = 0; i < 12; i++) {
            String path ="resources/m.20210" + i + ".csv";



        List<String> lines = readFileContents(path);
        ArrayList<ItemMonth> items = new ArrayList<>();
        for (int j = 1;j < lines.size(); j++) {
            String line = lines.get(j);
            String[] parts = line.split(",");

            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);

            items.add(new ItemMonth(itemName,isExpense,quantity,sumOfOne));
        }
        if(items.isEmpty()){
            continue;
        }else{
        monthInfo.put(i, items);
        }
    }
    }
    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }



    public void printStat(){
        if(monthInfo.isEmpty()){
            System.out.println("Месячные отчёты ещё не считаны!");
        }else{
            for(Integer month: monthInfo.keySet()){
                System.out.println("Месяц №" + month);
                ArrayList<ItemMonth> line = monthInfo.get(month);

                int sum1 = 0;
                int sum2 = 0;
                String item1 = " ";
                String item2 = " ";

                for(ItemMonth lines : line) {

                    if(!lines.isExpense) {

                        if(lines.mostProfitable > sum1){
                            sum1 = lines.mostProfitable;
                            item1 = lines.itemName;
                    }

                    } else{
                            if (lines.mostProfitable > sum2){
                            sum2 = lines.mostProfitable;
                            item2 = lines.itemName;
                            }
                    }
                }
                System.out.println("Cамый прибыльный товар: " + item1+ " - " + sum1);
                System.out.println("Самая большая трата: " + item2 +" - " + sum2);

            }
        }
    }
}


