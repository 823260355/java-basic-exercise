import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class GrammarExercise {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String firstWordList = input.nextLine();
        String secondWordList = input.nextLine();

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);

        result.stream().forEach(System.out::println);

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        String[] firstWordArr = firstWordList.toUpperCase().split(",");
        String[] secondWordArr = secondWordList.toUpperCase().split(",");
        //验证输入是否合法
        String regEx = "[A-Z]+";
        Pattern pattern = Pattern.compile(regEx);
        for (String str: firstWordArr) {
            Matcher matcher = pattern.matcher(str);
            if (!matcher.matches()){
                throw new RuntimeException("input not valid");
            }
        }
        for (String str: secondWordArr) {
            Matcher matcher = pattern.matcher(str);
            if (!matcher.matches()){
                throw new RuntimeException("input not valid");
            }
        }
        List<String> firstList = Arrays.stream(firstWordArr).distinct().collect(Collectors.toList());
        List<String> secndList = Arrays.stream(secondWordArr).distinct().collect(Collectors.toList());
        List<String> result = secndList.stream().filter(firstList::contains).sorted().map(str -> str.replace("", " ").trim()).collect(Collectors.toList());

        return result;
    }
}
