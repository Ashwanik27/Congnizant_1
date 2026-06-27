package week1.DSA;
public class FinancialForecast {

    static double predictValue(double amount, double growthRate, int years) {

        if (years == 0)
            return amount;

        return predictValue(amount, growthRate, years - 1)
                * (1 + growthRate);
    }

    public static void main(String[] args) {

        double presentValue = 10000;
        double growthRate = 0.10; // 10%
        int years = 5;

        double futureValue =
                predictValue(presentValue, growthRate, years);

        System.out.printf("Future Value after %d years = %.2f",
                years, futureValue);
    }
}