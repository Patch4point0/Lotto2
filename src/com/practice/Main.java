package com.practice;


import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {


        //Ask the user whether they want to use QuickPick or Manual Selection
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to use QuickPick? \n Yes or No");
        String response1 = scanner.nextLine();

        while (!ValidResponse(response1)) {
            System.out.println("Please type: Yes or No");
            response1 = scanner.nextLine();
        }

        //Create the list that will store the lines regardless of which selection method the user chooses
        List<List> completedLines = new ArrayList<>();
        int linesize = 6; //I put this here to avoid using "magic numbers" later

        //QuickPick selection section

        if (PositiveResponse(response1)) {
            System.out.println("QuickPick Selected");
            System.out.println("How many QuickPick lines would you like? \n 1-20");
            int qpLines = scanner.nextByte();

            while (!LinesRange(qpLines)) {
                System.out.println("Please pick a number between: 1-20");
                qpLines = scanner.nextByte();
            }

            int qpCycle = 0;
            System.out.print("Completed lines: ");
            while (qpCycle < qpLines){
                List<Integer> qpActiveLine = new ArrayList<>();

                while (qpActiveLine.size() < linesize) {
                    Random quickPicker = new Random();
                    int qpActiveNumber = quickPicker.nextInt(6) + 1;

                    while (qpActiveLine.contains(qpActiveNumber)){
                        qpActiveNumber = quickPicker.nextInt(6) + 1;
                    }

                    qpActiveLine.add(qpActiveNumber);
                }

                Collections.sort(qpActiveLine);
                completedLines.add(qpActiveLine);
                qpCycle++;
                System.out.print(qpCycle+ " ");                 //Just a bit of user feedback here, like a loading bar
                TimeUnit.MILLISECONDS.sleep(350);       //It was happening too fast so I made it look like we're really doing stuff behind the scenes ;)
            }
        }
        //Manual Selection section
        else {
            boolean anotherLine = true;
            while (anotherLine) {
                List<Integer> msActiveLine = new ArrayList<>();

                while (msActiveLine.size() < linesize) {
                    System.out.println("Please choose a number between 1 - 48");
                    int msActiveNumber = scanner.nextInt();

                    while (msActiveLine.contains(msActiveNumber) || !NumbersRange(msActiveNumber)) {
                        System.out.println("Please choose a unique number between 1 - 48");
                        msActiveNumber = scanner.nextInt();
                    }

                    msActiveLine.add(msActiveNumber);
                }
                Collections.sort(msActiveLine);
                completedLines.add(msActiveLine);
                System.out.println("Would you like to choose another line? \n Yes or No");
                String response2 = scanner.nextLine();

                while (!ValidResponse(response2)) {
                    System.out.println("Please type: Yes or No");         //Why does this line always print?
                    response2 = scanner.nextLine();
                }

                if (!PositiveResponse(response2)){
                    anotherLine = false;
                }
            }
        }
        //print the users lines back to them
        int lineNumber = 0;
        for (Object line : completedLines){
            lineNumber++ ;
            System.out.printf("%nLine number %d: %s", lineNumber, line);
            TimeUnit.MILLISECONDS.sleep(200);                   //It looks smoother than just shitting them out all at once
        }
        //Ask the user if they would like to save their lines to a file
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                            "Would you like to save these lines to a file? \n Yes or No");
        String response3 = scanner.next();

        if (PositiveResponse(response3)) {
            System.out.println("What would you like to name your file?: ");
            String fileName = scanner.next();
            File fileToSave = new File(fileName + ".txt");

            while (fileToSave.exists()) {
                System.out.println("This File already exists, Do you want to overwrite it?  yes or no");
                String response4 = scanner.next();

                if (PositiveResponse(response4)) {
                    File excistingFile = new File(fileName + ".txt");
                    excistingFile.delete();
                } else {
                    System.out.println("Please name the file something else?: ");
                    fileName = scanner.next();
                    fileToSave = new File(fileName + ".txt");
                }
            }

            for (Object saveLine : completedLines) {
                String recordForFile = saveLine.toString();
                System.out.println("These are your saved lines:" + recordForFile);

                try {
                    FileWriter pwriterfile = new FileWriter(fileName + ".txt", true);
                    PrintWriter pwriter = new PrintWriter(pwriterfile);
                    pwriter.println(recordForFile);
                    pwriter.close();
                }

                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        //Generate the Lotto Result
        List<Integer> lottoResult = new ArrayList<>();
        while (lottoResult.size() < linesize) {
            Random quickPicker = new Random();
            int lottoNumber = quickPicker.nextInt(6) + 1;
            while (lottoResult.contains(lottoNumber)){
                lottoNumber = quickPicker.nextInt(6) + 1;
            }
            lottoResult.add(lottoNumber);
        }
        Collections.sort(lottoResult);
        System.out.println("The Results are in: " + lottoResult);

        //Compare their lines to the result and display matches
        int resultComparison = 1;
        for(List comparisonLine : completedLines){
            System.out.print("\nLine number " + resultComparison + " - ");
            Checking(3);
            if(comparisonLine.equals(lottoResult)){
                System.out.print("We have a winner");
            }

            else {
                List<Object> matches = new ArrayList<>();
                for (Object comparisonNumber : comparisonLine){
                    if (lottoResult.contains(comparisonNumber)){
                        matches.add(comparisonNumber);
                    }
                }
                if (matches.size() > 0){
                    System.out.printf("Number of matches: %d = %s", matches.size(), matches);
                }
                else {
                    System.out.print("No matches this time :'(");
                }
            }
            resultComparison++;
        }
    }

    public static boolean ValidResponse(String answer) {
        return (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("no"));
    }

    public static boolean PositiveResponse(String answer) {
        return (answer.toLowerCase().equals("yes"));
    }

    public static boolean LinesRange(int lineNum) {
        return lineNum > 0 && lineNum < 21;
    }

    public static boolean NumbersRange(int num) {
        return num > 0 && num < 49;
    }

    public static void Checking (int time) throws InterruptedException {
        System.out.print("Checking");
        for (int i = 0; i < time; i++){
            for (int t = 0; t < time; t++){
                System.out.print(".");
                TimeUnit.MILLISECONDS.sleep(200);
            }
            System.out.print("\b\b\b");
        }
        System.out.print("\b\b\b\b\b\b\b\b");
    }
}
