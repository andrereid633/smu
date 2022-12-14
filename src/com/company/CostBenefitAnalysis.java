package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class CostBenefitAnalysis {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of projects: ");
        int n = scanner.nextInt();

        String [] projectName = new String[n];
        double [] upfrontCost = new double[n];
        double [] rateOfReturn = new double[n];
        int [] durationInYearsArr = new int[n];
        ArrayList<ArrayList<Double>> inOutflowArrayList = new ArrayList<>();


        storeUserInputs(scanner, n, projectName, upfrontCost, rateOfReturn, durationInYearsArr, inOutflowArrayList);

        double pvFactor; //1/(1+r)^n
        double amount = 0; //<Amount> = <Cash Inflow/Outflows> * <PV Factor>
        double totalIncome = 0; //<Sum of “Cash Inflow/Outflows”>
        double presentValueFutureBenefits = 0; //<Sum of “Amount”>
        double netPresentValue; //<Present Value of Future Benefits> - <Present Value of Future Costs>
        String highestIncomeProjectName = "";
        String executingProjectName = "";
        double maxTotalIncome = 0;
        double maxNPV = 0;


        for (int i = 0; i < n; i++) {
            System.out.printf("\t\t\t\t\t\t\t\t\t %s", projectName[i]);
            System.out.println("\n________________________________________________________________________________");
            System.out.println("Year            | Cash                  |       PV Factor    |       Amount");
            System.out.println("                | Inflows/Outflows      |      ");
            System.out.print("________________________________________________________________________________\n");

            int durationInYears = durationInYearsArr[i];

            double r = rateOfReturn[i] / 100;

            for (int y = 0; y < durationInYears; y++) {
                pvFactor = 1f/ Math.pow(1 + r, (y + 1) );
                amount += inOutflowArrayList.get(i).get(y) * pvFactor;
                totalIncome += inOutflowArrayList.get(i).get(y);
                presentValueFutureBenefits += amount;

                System.out.printf("%d               | %.2f              |       %.4f       |       $%.2f\n", y + 1, inOutflowArrayList.get(i).get(y), pvFactor, amount);
                amount = 0;
            }

            netPresentValue = presentValueFutureBenefits - upfrontCost[i];

            // Determine project with the highest income
            if(totalIncome > maxTotalIncome) {
                highestIncomeProjectName = projectName[i];
                maxTotalIncome = totalIncome;
            } else if(totalIncome == maxTotalIncome) {
                // then project with the highest npv should be selected
                if(netPresentValue > maxNPV) {
                    maxNPV = netPresentValue;
                    highestIncomeProjectName = projectName[i];
                }
            }

            // Determine project to be executed
            if(netPresentValue > maxNPV) {
                maxNPV = netPresentValue;
                executingProjectName = projectName[i];
            } else if(netPresentValue == maxNPV) {
                if(totalIncome > maxTotalIncome) {
                    maxTotalIncome = totalIncome;
                    executingProjectName = projectName[i];
                }
            }

            System.out.printf("\nTotal Income: $ $%.2f \n", totalIncome);
            System.out.printf("Present Value of Future Benefits: $%.2f \n", presentValueFutureBenefits);
            System.out.printf("Present Value of Future Cost: $%.2f \n", upfrontCost[i]);
            System.out.printf("Net Present Value (NPV): $%.2f \n", netPresentValue);

            totalIncome = 0;
            presentValueFutureBenefits = 0;
        }

        System.out.printf("\n\nThe highest income is generated by project:  %s\n", highestIncomeProjectName );
        System.out.printf("The company that should be selected is: %s \n", executingProjectName);

    }

    private static void storeUserInputs(Scanner scanner, int n, String[] projectName,
                                        double[] upfrontCost, double[] rateOfReturn,
                                        int[] durationInYearsArr, ArrayList<ArrayList<Double>> inOutflowArrayListOfLists) {
        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter the name of the project: ");
            projectName[i] = scanner.next();

            System.out.print("Enter the upfront cost for the project: ");
            upfrontCost[i] = scanner.nextDouble();

            System.out.print("Enter the rate of return or discount(in %): ");
            rateOfReturn[i] = scanner.nextDouble();

            System.out.print("Enter the duration (in years): ");
            durationInYearsArr[i] = scanner.nextInt();

            inOutflowArrayListOfLists.add(new ArrayList<>());

            for(int y = 0; y < durationInYearsArr[i]; y++) {
                System.out.printf("Enter the cash inflow-outflow during year %d: ", y + 1);
                double inflowOutFlowVal = scanner.nextDouble();
                inOutflowArrayListOfLists.get(i).add(inflowOutFlowVal);
            }
        }
    }
}
