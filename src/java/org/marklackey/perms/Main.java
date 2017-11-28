package org.marklackey.perms;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Set<String> permutations = new HashSet<>();
        permute(new StringBuilder(args[0]), new StringBuilder(), permutations);
        permutations.stream().sorted().forEach(System.out::println);
        System.out.println("\n-----\n");
        permutations = new HashSet<>();
        permuteArray(args[0].toCharArray(), new char[0], permutations);
        permutations.stream().sorted().forEach(System.out::println);
    }

    private static void permute(StringBuilder remaining, StringBuilder used, Set<String> permutations) {
        int len = remaining.length();
        if (len == 0) {
            permutations.add(used.toString());
        } else {
            for (int i = 0; i < len; i++) {
                String after = (i + 1) < len ? remaining.substring(i + 1) : "";
                permute(
                    new StringBuilder(remaining.substring(0, i) + after),
                    new StringBuilder(used + remaining.substring(i, i + 1)),
                    permutations);
            }
        }
    }

    private static void permuteArray(char[] remaining, char[] used, Set<String> permutations) {
        if (remaining.length == 0) {
            permutations.add(new String(used));
        } else {
            for (int i = 0; i < remaining.length; i++) {
                char[] newRemaining = new char[remaining.length - 1];
                for (int j = 0; j < i; j++) {
                    newRemaining[j] = remaining[j];
                }

                for (int k = i + 1; k < remaining.length; k++) {
                    newRemaining[k - 1] = remaining[k];
                }
                char[] newUsed = new char[used.length + 1];
                System.arraycopy(used, 0, newUsed, 0, used.length);
                newUsed[newUsed.length - 1] = remaining[i];
                permuteArray(newRemaining, newUsed, permutations);
            }
        }
    }

}
