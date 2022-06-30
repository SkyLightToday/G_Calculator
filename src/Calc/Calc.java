package Calc;

import java.util.Arrays;
import java.util.List;//Интерфейс List сохраняет последовательность добавления элементов и позволяет осуществлять доступ к элементу по индексу

public class Calc {
    private int num1;
    private int num2;
    private String operator;//+-*/

    //int-только цел числа
    private int calcExpression(int n1, String op, int n2){
        int res;
        switch (op) {
            case "+":
                res = n1+n2;
                break;
            case "-":
                res = n1-n2;
                break;
            case "*":
                res = n1*n2;
                break;
            case "/":
                res = n1/n2;
                break;
            default:
                throw new AssertionError();
                //Класс AssertionError. Брошенный, чтобы указать, что утверждение перестало работать. имеет как его сообщение детали преобразование строк выражения
        }
        return res;
    }

    //-- метод проверки и вывод рез
    public String result(String exp) throws CalcException{
        boolean isRomanExp;// isRomanExp-- римские ли цифры?
        Parse parse = new Parse();//Parser — это набор инструментов для парсинга, анализа, трансформации и генерации Java-кода.
        //преобразование из строки в цифру

        //разделяет исходное выражение String по разделителю " "
        List<String> expItems = Arrays.asList(exp.split(" "));

        //проверка на создание 3 элементов: num1, оператор, num2
        if (expItems.size()!=3){
            throw new CalcException("ОШИБКА. Выражение должно иметь вид: [5 - 4] или [V + II], разделенные пробелом...");
        }

        // проверка оператора + - * /
        if (parse.checkOperator(expItems.get(1))){
            operator = expItems.get(1);
        } else {
            throw new CalcException("ОШИБКА. Оператор '" + expItems.get(1) + "' не корректен, должен быть: + - * / ");
        }

        //проверка чисел, оба арабские или оба римские
        if (parse.isNumeric(expItems.get(0)) && parse.isNumeric(expItems.get(2))){  //проверка, что два числа арабские
            num1 = Integer.parseInt(expItems.get(0));
            num2 = Integer.parseInt(expItems.get(2));
            isRomanExp = false;
        } else if (parse.isRoman(expItems.get(0)) && parse.isRoman(expItems.get(2))){   //проверка, что два числа римские
            num1 = parse.romeToArabConvert(expItems.get(0));
            num2 = parse.romeToArabConvert(expItems.get(2));
            isRomanExp = true;
        } else {
            throw new CalcException("ОШИБКА. Выражение должно состоять или из арабских, или из римских чисел.");
        }

        // числа должны быть от 1 до 10 вкл
        if (!(num1>=1 && num1<=10)){
            throw new CalcException("ОШИБКА. Число #1 должно быть от 1 до 10 или от I до X включительно");
        }

        if (!(num2>=1 && num2<=10)){
            throw new CalcException("ОШИБКА. Число #2 должно быть от 1 до 10 или от I до X включительно");
        }

        //результат
        int result = calcExpression(num1, operator, num2);

        //если числа римские, то конвертируем в римские и возвращаем результат
//        if (isRomanExp){
//            String sign = result < 0 ? "-" : "";
//            return sign + parse.arabToRomeConvert(Math.abs(result));
//        }
        if(isRomanExp) {
            String sign = result > 0? "": "";
            if(result <= 0) {
                throw new CalcException("ОШИБКА. В римской системе счисления нет нуля и отрицательных чисел.");
            } else
            return sign + parse.arabToRomeConvert(Math.abs(result));
        }

        //ответ - арабское число
        return String.valueOf(result);
    }
}
