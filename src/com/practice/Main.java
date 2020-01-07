package com.practice;

import com.practice.line.LottoLine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // write your code here

        List<List<Integer>> lines = new ArrayList<>();
        // pretend we filled it

        List<Integer> result = new ArrayList<>();

        Map<List<Integer>, Integer> lineAndResultMap = new HashMap<>();

        for (List<Integer> line : lines) {
            int matches = 0;
            for (Integer number : line) {
                if (result.contains(number)) {
                    matches++;
                }
//                for(Integer resultNum : result){
//                    if(number == resultNum){
//                    }
//                }
            }
            lineAndResultMap.put(line, matches);
        }


        int x = 0;
        Integer y = 0;

        List<int> myList = new ArrayList<>();


        List<Object> addresses = new ArrayList<>();
        // Populate addresses

        Object searchedAddress = null;

        for (Object address : addresses) {
            boolean complicatedCriteria = true;
            if (complicatedCriteria) {
                searchedAddress = address;
                break;
            }
        }

        if (searchedAddress != null) {

        }


        int lineSize = 6;
        List<LottoLine> completedLines = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to use QuickPick? \n Yes or No");
        String response1 = scanner.nextLine();

        while (!validResponse(response1)) {
            System.out.println("Please type: Yes or No");
            response1 = scanner.nextLine();
        }


        //QuickPick Section
        if (positiveResponse(response1)) {
            System.out.println("QuickPick Selected");
            System.out.println("How many QuickPick lines would you like? \n 1-20");
            int qpLines = scanner.nextByte();

            while (!linesRange(qpLines)) {
                System.out.println("Please pick a number between: 1-20");
                qpLines = scanner.nextByte();
            }

            for (int qplinesDone = 0; qplinesDone < qpLines; qplinesDone++) {

                SortedSet<Integer> qpActiveLine = new TreeSet<>();
                while (qpActiveLine.size() < lineSize) {
                    Random quickPicker = new Random();
                    int qpActiveNumber = quickPicker.nextInt(48) + 1;
                    qpActiveLine.add(qpActiveNumber);
                }

                for (Integer theLoopGuy : qpActiveLine) {


                }

                List<Integer> qpActiveArray = new ArrayList(qpActiveLine);
                completedLines.add(new LottoLine(qpActiveArray.get(0), qpActiveArray.get(1), qpActiveArray.get(2), qpActiveArray.get(3), qpActiveArray.get(4), qpActiveArray.get(5)));
            }
        }


        //Manual Entry section
        else {
            System.out.println("Manual Entry Selected");

            boolean msAnotherLine = true;

            while (msAnotherLine) {
                SortedSet<Integer> msActiveLine = new TreeSet<>();

                while (msActiveLine.size() < lineSize) {

                    System.out.print("Number " + (msActiveLine.size() + 1) + ": ");
                    int msActiveNumber = scanner.nextInt();

                    while (!numbersRange(msActiveNumber) || msActiveLine.contains(msActiveNumber)) {
                        System.out.print("Please choose a unique number between 1 - 48: ");
                        msActiveNumber = scanner.nextInt();
                    }

                    msActiveLine.add(msActiveNumber);
                }
                List<Integer> msActiveArray = new ArrayList(msActiveLine);
                completedLines.add(new LottoLine(msActiveArray.get(0), msActiveArray.get(1), msActiveArray.get(2), msActiveArray.get(3), msActiveArray.get(4), msActiveArray.get(5)));

                System.out.println("Would you like to select another line? \nYes or No");
                String response2 = scanner.nextLine();

                while (!validResponse(response2)) {
                    System.out.println("Please type: Yes or No");
                    response2 = scanner.nextLine();
                }

                if (!positiveResponse(response2)) {
                    msAnotherLine = false;
                }
            }
        }
        System.out.println("These are your lines:");
        printLotto(completedLines);


        //Save the numbers to file section
        System.out.println("Would you like to save these lines to a file? \n Yes or No");
        String response3 = scanner.next();

        if (positiveResponse(response3)) {
            System.out.println("What would you like to name your file?: ");
            String fileName = scanner.next();
            File fileToSave = new File(fileName + ".txt");

            while (fileToSave.exists()) {
                System.out.println("This File already exists, Do you want to overwrite it?  yes or no");
                String response4 = scanner.next();

                if (positiveResponse(response4)) {
                    File excistingFile = new File(fileName + ".txt");
                    excistingFile.delete();
                } else {
                    System.out.println("Please name the file something else?: ");
                    fileName = scanner.next();
                    fileToSave = new File(fileName + ".txt");
                }
            }

            for (int b = 0; b < completedLines.size(); b++) {
                String recordForFile = completedLines.get(b).toString();
                System.out.println("These are your saved lines:" + recordForFile);

                try {
                    FileWriter pwriterfile = new FileWriter(fileName + ".txt", true);
                    PrintWriter pwriter = new PrintWriter(pwriterfile);
                    pwriter.println(recordForFile);
                    pwriter.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        //Run the lotto and display results section

        SortedSet<Integer> lottoResults = new TreeSet<>();
        while (lottoResults.size() < lineSize) {
            Random lottoPicker = new Random();
            int lottoActiveNumber = lottoPicker.nextInt(6) + 1;
            lottoResults.add(lottoActiveNumber);
        }
        System.out.println("Lotto Results: \n" + lottoResults);

        //Comapare lines to the result
        List<LottoLine> matchesList = new ArrayList();
        List noOfMatches = new ArrayList();

        for (int g = 0; g < completedLines.size(); g++) {
            int matches = 0;
            matchesList.add(new LottoLine(0, 0, 0, 0, 0, 0));

            if (lottoResults.contains(completedLines.get(g).getNum1())) {
                matchesList.get(g).num1 = completedLines.get(g).num1;
                matches++;
            }
            if (lottoResults.contains(completedLines.get(g).num2)) {
                matchesList.get(g).num2 = completedLines.get(g).num2;
                matches++;
            }
            if (lottoResults.contains(completedLines.get(g).num3)) {
                matchesList.get(g).num3 = completedLines.get(g).num3;
                matches++;
            }
            if (lottoResults.contains(completedLines.get(g).num4)) {
                matchesList.get(g).num4 = completedLines.get(g).num4;
                matches++;
            }
            if (lottoResults.contains(completedLines.get(g).num5)) {
                matchesList.get(g).num5 = completedLines.get(g).num5;
                matches++;
            }
            if (lottoResults.contains(completedLines.get(g).num6)) {
                matchesList.get(g).num6 = completedLines.get(g).num6;
                matches++;
            }
            noOfMatches.add(matches);
        }

        new printMatches(noOfMatches);
        printLotto(completedLines);
        printLotto(matchesList);
    }


    public static boolean numbersRange(int numToCheck) {
        return numToCheck > 0 && numToCheck < 49;
    }

    public static boolean linesRange(int lineNum) {
        return lineNum > 0 && lineNum < 21;
    }

    public static boolean validResponse(String answer) {
        return (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("no"));
    }

    public static boolean positiveResponse(String answer) {
        return (answer.toLowerCase().equals("yes"));
    }


    public static void printLotto(List<LottoLine> givenList) {

        for (int a = 0; a < givenList.size(); a++) {
            System.out.println("Line " + (a + 1) + ": " + givenList.get(a).getNum1() + " " + givenList.get(a).getNum2() + " " + givenList.get(a).getNum3() + " " + givenList.get(a).getNum4() + " " + givenList.get(a).getNum5() + " " + givenList.get(a).getNum6());
        }
    }

    public static class printMatches {

        public printMatches(List givenList) {

            for (int b = 0; b < givenList.size(); b++) {

                System.out.println("No. of Matches on line " + (b + 1) + ":  " + givenList.get(b));

                List<Integer> newList = new ArrayList<>(givenList);
                if (newList.get(b) == 6) {
                    System.out.println("Line " + (b + 1) + " is a JackPot Winner!!!!!\n");
                }

            }
        }
    }

}
