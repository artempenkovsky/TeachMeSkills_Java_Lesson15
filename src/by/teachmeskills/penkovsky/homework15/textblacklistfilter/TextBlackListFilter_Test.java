package by.teachmeskills.penkovsky.homework15.textblacklistfilter;

import java.util.Scanner;
public class TextBlackListFilter_Test {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите через запятую список неприемлемых слов и выражений:");
            String blackListString = scanner.nextLine();
            String[] blackList = blackListString.split("\\s*,\\s*");

            TextBlackListFilter filter = new TextBlackListFilter(blackList);

            System.out.println("Введите текст для фильтрации:");
            String text = scanner.nextLine();

            if (filter.containsBlackListWords(text)) {
                System.out.println("Текст содержит неприемлемые слова.");
            } else {
                System.out.println("Текст не содержит неприемлемых слов.");
            }

            int count = filter.countBlackListWords(text);
            System.out.println("Текст содержит " + count + " неприемлемых слов.");

            String filteredText = filter.filterBlackListWords(text);
            System.out.println("Фильтрованный текст:");
            System.out.println(filteredText);
        }
    }
