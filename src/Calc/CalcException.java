package Calc;

public class CalcException extends Exception{
    public CalcException() {
        System.out.println("Не верно задано выражение");
    }

    public CalcException(String message){
        this();
        System.out.println(message);
    }
}
