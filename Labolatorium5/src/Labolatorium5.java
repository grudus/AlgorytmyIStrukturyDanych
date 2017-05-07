import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Labolatorium5 {
    public static void main(String[] args) {
        Pattern INFIX_PATTERN = Pattern.compile("(\\d+)|([+-/*])");

        String a = "12+322/(411+4)";
        String b = "12   +322/  (411    +4       )";
        String c = "12 + 322 / ( 411 + 4 ) ";

        List<List<String>> lists = Stream.of(a, b, c).map(INFIX_PATTERN::matcher)
                .map(Labolatorium5::matcherToList).collect(toList());

        for (int i = 0; i < lists.get(0).size(); i++) {
            String l1 = lists.get(0).get(i);
            String l2 = lists.get(1).get(i);
            String l3 = lists.get(2).get(i);
            System.out.println(l1 + " -> " + (l1.equals(l2) && l2.equals(l3)));
        }
        System.out.println("postfix: " + matcherToList(INFIX_PATTERN.matcher("4 12 +/-*   4+*2")));
    }

    private static List<String> matcherToList(Matcher m) {
        List<String> list = new ArrayList<>();
        while (m.find()) list.add(m.group());
        return list;
    }
}
