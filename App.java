package com.company;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;


public class App {

    private static int key;
    private static String resultTextForEncryption;
    private static String resultTextForDecryption;
    private static final String alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя.,””:-!? ";
    private static String resultText1 = "";
    private static String resultText2 = "";


    public static void caesarEncryption() {
        for (int i = 0; i < resultTextForEncryption.length(); i++) {
            int position = alphabet.indexOf(resultTextForEncryption.charAt(i));
            int keyVal = (key + position) % alphabet.length();
            char replaceVal = alphabet.charAt(keyVal);
            resultText1 += replaceVal;
        }
        System.out.println(resultText1);

        File file = new File("encryption.txt");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(resultText1);
            System.out.println("Файл " + file + " сохранен");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void caesarDecryption() {
        for (int i = 0; i < resultTextForDecryption.length(); i++) {
            int position = alphabet.indexOf(resultTextForDecryption.charAt(i));
            int keyVal = (position - key) % alphabet.length();
            if (keyVal < 0) {
                keyVal = alphabet.length() + keyVal;
            }
            char replaceVal = alphabet.charAt(keyVal);
            resultText2 += replaceVal;
        }
        System.out.println(resultText2);

        try {
            File file1 = new File("decryption.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file1));
            bufferedWriter.write(resultText2);
            System.out.println("Файл " + file1 + " сохранен");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи \"1\", если нужно зашифровать текст; \"2\", если нужно расшифровать ключом:");
        String str = scanner.nextLine();
        String str1 = "1";
        String str2 = "2";
        String str3 = "3";

        if (str.equals(str1)) {
            System.out.println("Пожалуйста, введи путь к файлу:");
            String path = scanner.nextLine();

            System.out.println("Введи любое целое число - будет ключом:");
            Scanner console = new Scanner(System.in);
            key = console.nextInt();

            StringBuffer text = new StringBuffer();
            try {
                BufferedReader file = new BufferedReader(new InputStreamReader((new FileInputStream(path))));
                int i = -1;
                while ((i = file.read()) != -1) {
                    text.append((char) i);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Упс. Проверь путь файла, что-то пошло не так:" + e);
            }
            resultTextForEncryption = new String(text);

            caesarEncryption();


        } else if (str.equals(str2)) {
            System.out.println("Пожалуйста, введи путь к файлу, который мы расшифруем:");
            String path = scanner.nextLine();

            System.out.println("Введи ключ шифрования");
            Scanner console = new Scanner(System.in);
            key = console.nextInt();

            StringBuffer text1 = new StringBuffer();
            try {
                BufferedReader file1 = new BufferedReader(new InputStreamReader((new FileInputStream(path))));
                int i = -1;
                while ((i = file1.read()) != -1) {
                    text1.append((char) i);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Упс. Проверь путь файла, что-то пошло не так:" + e);
            }
            resultTextForDecryption = new String(text1);

            caesarDecryption();
        }
    }
}



