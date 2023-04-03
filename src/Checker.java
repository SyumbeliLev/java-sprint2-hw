import java.util.ArrayList;

public class Checker {
    public YearlyReport yearlyReport;
    public MonthlyReport monthlyReport;

    public Checker(YearlyReport yearlyReport, MonthlyReport monthlyReport) {
        this.yearlyReport = yearlyReport;
        this.monthlyReport = monthlyReport;
    }
    public void  check(){
        if(yearlyReport.yearInfoProfit.isEmpty() || yearlyReport.yearInfoExp.isEmpty()) {
            System.out.println("Годовой отчёт ещё не считан");
        } else if(monthlyReport.monthInfo.isEmpty()){
                System.out.println("Месячные отчёты ещё не считаны!");
        }
        else{

            for(Integer month: monthlyReport.monthInfo.keySet()){
                ArrayList<ItemMonth> line = monthlyReport.monthInfo.get(month);
                int sumTrue = 0;
                int sumFalse = 0;

                for(ItemMonth lines : line){
                    if(lines.isExpense){
                        sumTrue += lines.mostProfitable;
                    }else {
                        sumFalse += lines.mostProfitable;
                    }
                }
            ItemYear itemYearTrue = yearlyReport.yearInfoExp.get(month-1);
            ItemYear itemYearFalse = yearlyReport.yearInfoProfit.get(month-1);

            if(sumTrue != itemYearTrue.amount || sumFalse != itemYearFalse.amount){
                System.out.println("В " + month + " месяце ошибка");
            }
        }
        System.out.println("Сверка завершена!");
        }
    }

}
