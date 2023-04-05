package by.teachmeskills.penkovsky.homework15.textblacklistfilter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextBlackListFilter {

    private List<String> blackList;

    public TextBlackListFilter(String[] blackList) {
        this.blackList = Arrays.asList(blackList);
    }

    public boolean containsBlackListWords(String text) {
        String lowerCaseText = text.toLowerCase();
        for (String word : blackList) {
            String lowerCaseWord = word.toLowerCase();
            if (lowerCaseText.contains(lowerCaseWord)) {
                return true;
            }
        }
        return false;
    }

    public int countBlackListWords(String text) {
        int count = 0;
        String lowerCaseText = text.toLowerCase();
        for (String word : blackList) {
            String lowerCaseWord = word.toLowerCase();
            Pattern pattern = Pattern.compile("\\b" + lowerCaseWord + "\\b");
            Matcher matcher = pattern.matcher(lowerCaseText);
            while (matcher.find()) {
                count++;
            }
        }
        return count;
    }

    public String filterBlackListWords(String text) {
        String lowerCaseText = text.toLowerCase();
        for (String word : blackList) {
            String lowerCaseWord = word.toLowerCase();
            Pattern pattern = Pattern.compile("\\b" + lowerCaseWord + "\\b");
            Matcher matcher = pattern.matcher(lowerCaseText);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(sb, "#".repeat(word.length()));
            }
            matcher.appendTail(sb);
            lowerCaseText = sb.toString();
        }
        return lowerCaseText;
    }
}

