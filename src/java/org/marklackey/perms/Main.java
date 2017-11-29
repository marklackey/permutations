package org.marklackey.perms;

import java.util.HashSet;
import java.util.Set;

public class Main {

    private static Set<String> permutations = new HashSet<>();

    public static void main(String[] args) {
        permutations.clear();
        permute(args[0], "");
        printSetSorted();
        System.out.println("\n-----\n");
        permutations.clear();
        permuteArray(args[0].toCharArray(), new char[0]);
        printSetSorted();
    }

    private static void permute(String remaining, String used) {
        int len = remaining.length();
        if (len == 0) {
            permutations.add(used);
        } else {
            for (int i = 0; i < len; i++) {
                permute(
                    remaining.substring(0, i) + remaining.substring(Math.min((i + 1), len)),
                    used + remaining.substring(i, i + 1)
                );
            }
        }
    }

    private static void permuteArray(char[] remaining, char[] used) {
        if (remaining.length == 0) {
            permutations.add(new String(used));
        } else {
            for (int i = 0; i < remaining.length; i++) {
                permuteArray(buildNewRemaining(remaining, i), buildNewUsed(used, remaining[i]));
            }
        }
    }

    private static char[] buildNewUsed(char[] used, char c) {
        char[] newUsed = new char[used.length + 1];
        System.arraycopy(used, 0, newUsed, 0, used.length);
        newUsed[newUsed.length - 1] = c;
        return newUsed;
    }

    private static char[] buildNewRemaining(char[] remaining, int i) {
        char[] newRemaining = new char[remaining.length - 1];
        for (int j = 0; j < i; j++) {
            newRemaining[j] = remaining[j];
        }
        for (int k = i + 1; k < remaining.length; k++) {
            newRemaining[k - 1] = remaining[k];
        }
        return newRemaining;
    }

    private static void printSetSorted() {
        permutations.stream().sorted().forEach(System.out::println);
    }
}