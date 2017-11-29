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
            for (int curIdx = 0; curIdx < len; curIdx++) {
                permute(remaining.substring(0, curIdx) + remaining.substring(Math.min((curIdx + 1), len)),
                    used + remaining.substring(curIdx, curIdx + 1));
            }
        }
    }

    private static void permuteArray(char[] remaining, char[] used) {
        if (remaining.length == 0) {
            permutations.add(new String(used));
        } else {
            for (int curIdx = 0; curIdx < remaining.length; curIdx++) {
                permuteArray(buildNewRemaining(remaining, curIdx), buildNewUsed(used, remaining[curIdx]));
            }
        }
    }

    private static char[] buildNewRemaining(char[] remaining, int curIdx) {
        char[] newRemaining = new char[remaining.length - 1];
        int remainIdx;
        for (remainIdx = 0; remainIdx < curIdx; remainIdx++) {
            newRemaining[remainIdx] = remaining[remainIdx];
        }
        //skip curIdx
        for (remainIdx = curIdx + 1; remainIdx < remaining.length; remainIdx++) {
            newRemaining[remainIdx - 1] = remaining[remainIdx];
        }
        return newRemaining;
    }

    private static char[] buildNewUsed(char[] used, char c) {
        char[] newUsed = new char[used.length + 1];
        System.arraycopy(used, 0, newUsed, 0, used.length);
        newUsed[newUsed.length - 1] = c;
        return newUsed;
    }

    private static void printSetSorted() {
        permutations.stream().sorted().forEach(System.out::println);
    }
}