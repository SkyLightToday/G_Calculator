package Calc;

import java.io.BufferedReader;//BufferedReader читает текст из потока ввода символов, буферизуя прочитанные символы, чтобы обеспечить эффективное считывание символов
import java.io.IOException;//класс исключений
import java.io.InputStreamReader;//счит. вход поток данных

public class CalcM {
    public static void main(String[] args) {
        try{
            System.out.println("Добро Пожаловать в Calculator!!!");
            System.out.println("Калькулятор работает с арабскими и римскими цифрами.");
            System.out.println("Введите выражение [2 + 3] или [V + II], через пробел. Допускаются числа от 1 до 10 или от I до X включительно. Операции: + - * /");
            System.out.println("Для получения ответа, нажмите Enter");
            System.out.print("Введите выражение: ");
            BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));//System.in- входящий поток ввода дан с клавы
            String calcString = bReader.readLine();//метод readLine читает данные строками, а не символ

            Calc calc = new Calc();
            String res = calc.result(calcString);
            System.out.println("Ответ: " + res);
        }
        catch(CalcException | IOException e){// | -- осталн записи будут проверятся

        }

    }
}
