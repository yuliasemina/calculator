import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Введите формулу с 2мя числами: ");
        String str = in.nextLine();
        in.close();

        validate(str);
        }
    static  Boolean isRomFirst = false;
    static  Boolean isRomLast = false;
    private static void validate(String str) {

        char[] ch = new char[str.length()];
        ConverterRoman converterRoman = new ConverterRoman();

        ArrayList<String> arrayList = new ArrayList<>();
        String numStr = "";
        for (int i = 0; i < str.length(); i++) {
            ch[i] = str.charAt(i);
            String numStr0 = String.valueOf(ch[i]);
            if (numStr0.isEmpty()||numStr0.equals(Symbols.SPACE.getValue())||numStr0=="") {
                continue;
            } else
            if (isDigit(numStr0) || converterRoman.isRome(numStr0) || !existSymbol(numStr0)) {
                numStr += numStr0;
            }
            if (existSymbol(numStr0)) {
                isRomFirst = converterRoman.isRome(numStr);
                if (isRomFirst==true){
                    arrayList.add(String.valueOf(converterRoman.convertRomanToInt(numStr)));
                } else { arrayList.add(numStr); }

                arrayList.add(numStr0);
                numStr = "";
            }
            if (i==str.length()-1){
                isRomLast = converterRoman.isRome(numStr);
                if (isRomFirst != isRomLast)  throw new NumberFormatException("Формула должна содержать только римские или только арабские цифры");
                if (isRomLast==true){
                    arrayList.add(String.valueOf(converterRoman.convertRomanToInt(numStr)));
                } else { arrayList.add(numStr); }
            }
            if (arrayList.size()>3)throw new NumberFormatException("Формула должна состоять из двух чисел " + arrayList.size());

        }

        if (isRomLast==true) System.out.println(converterRoman.convertIntegerToRoman(calculate(arrayList)));
        else
            System.out.println(calculate(arrayList));

    }

    public static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    static String[] massStringA = {Symbols.PLUS.getValue(), Symbols.MINUS.getValue(),
            Symbols.DIVIDE.getValue(), Symbols.MULTIPLAY.getValue(), Symbols.SPACE.getValue()};

    public static boolean existSymbol(String a) {
        for (String s : massStringA) {
            if (a.equals(s)) {
                return true;
            }
        }
        return false;
    }

    private static int calculate(ArrayList<String> arrayList) {
      //  System.out.println(arrayList.toString());
        int a;
        int b;
        int  result =0;

            for (int i = 0; i < arrayList.size(); i++) {
                try {

                    a = Integer.parseInt(arrayList.get(0));
                    b = Integer.parseInt(arrayList.get(2));
                }catch (Exception e){
                    throw new ArithmeticException("числа должны быть целыми");
                }
            if (a>10 || b>10)   throw new ArithmeticException("числа должны быть <= 10");

            if (arrayList.get(i).equals(Symbols.MULTIPLAY.getValue())){
                result =  a*b;
            }
            if (arrayList.get(i).equals(Symbols.DIVIDE.getValue())){
                if (b==0)
                    throw new ArithmeticException("Деление на 0");

                result =  a/b;
            }
            if (arrayList.get(i).equals(Symbols.PLUS.getValue())){
                result =  a+b;
            }
            if (arrayList.get(i).equals(Symbols.MINUS.getValue())){
                result =  a-b;
            }

        }

        return result;
    }
}
