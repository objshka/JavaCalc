import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        try {
            String result = calc(input);
            System.out.println(result);
        } catch (Exception e){
            System.out.println(e);
        }
    }


    public static String calc(String input) throws Exception  {
        String[] parts = input.split(" ");

        if (parts.length != 3){
            throw new Exception("Неверный вариант воода");
        }

        String firstNumber = parts[0];
        String operator = parts[1];
        String secondNumber = parts[2];
        boolean isRoman = false;
        int a, b;

        try {
            a = Integer.parseInt(firstNumber);
            b = Integer.parseInt(secondNumber);
        } catch (NumberFormatException e) {
            a = convertToArabic(firstNumber);
            b = convertToArabic(secondNumber);
            isRoman = true;
            if (a == 0 || b == 0){
                throw new Exception("Неправильный формат ввода чисел");
            }
            if (a < 1 || a > 10 || b < 1 || b > 10) {
                throw new Exception("Числа должны быть от 1 до 10 включительно");
            }
        }

        int result = 0;

        switch (operator){
            case "+" -> result = a + b;
            case "-" -> result = a - b;
            case "/" -> result = a / b;
            case "*" -> result = a * b;
            default -> throw new Exception("Такой операции нет");
        }

        if (isRoman){
            if (result <= 0){
                throw new Exception("Результат не может быть меньше или равен нулю");
            }

            return convertToRoman(result);
        }

        return String.valueOf(result);
    }

    private static String convertToRoman(int number) {
        String[] romanNumbers = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arabicNumbers = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder tempString = new StringBuilder();

        for (int i = 0; i < arabicNumbers.length; i++) {
            while (number >= arabicNumbers[i]) {
                tempString.append(romanNumbers[i]);
                number -= arabicNumbers[i];
            }
        }

        return tempString.toString();
    }

    private static int convertToArabic(String romanNumber) {
        int[] arabicNumbers = {10, 9, 5, 4, 1};
        String[] romanNumbers = {"X", "IX", "V", "IV", "I"};
        int result = 0;

        for (int i = 0; i < romanNumbers.length; i++) {
            while (romanNumber.startsWith(romanNumbers[i])) {
                result += arabicNumbers[i];
                romanNumber = romanNumber.substring(romanNumbers[i].length());
            }
        }

        return result;
    }
}