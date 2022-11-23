import java.io.FileInputStream;
import java.io.IOException;

//прога для автоматической нумерации вопросов и вариантов ответов (был просто список - стал с номерами)
public class Main {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("C://Java/test.txt");
            int i = 0;
            String text = "";
            while ((i = fis.read()) != -1) {   //пока есть что читать
                if (i == 13)
                    continue;  //пропуск возврата каретки при переходе на новую строку (13 - код в таблице аскии)
                text += (char) i;
            }
            String[] textArr = text.split("\n\n");//отделяем вопросы от ответов по 2 переносам строки (четные элементы массива это ответы, нечетные - вопросы)
            int questionCount = 1;
            String result = "";
            for (int j = 0; j < textArr.length; j++) {
                if (j % 2 == 0) {
                    //Это вопрос
                    result += questionCount + ". " + textArr[j]+"\n\n";
                    questionCount++;
                } else {
                    //Это ответы
                    String[] answers = textArr[j].split("\n");
                    for (int k = 0; k < answers.length; k++) {
                        String end = "\n";
                        answers[k] = answers[k].replaceAll("\\w\\.\\s*","");//меняем буквы перед вариантами ответов на пустоту
                        if (k == answers.length - 1) end += "\n";
                        result += (k + 1) + ") " + answers[k] + end;
                    }
                }
            }
            System.out.println(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
