package algorithm.problem;

import sun.misc.Unsafe;

/**
 *
 * */
public class InputJudgment {

    /**
     * origin: word
     * input: wwword
     * */
    public static boolean judge(String origin, String input) {
        char[] a1 = origin.toCharArray(), a2 = input.toCharArray();
        int i1 = 0, j1 = 0, i2 = 0, j2 = 0;
        while (j1 < a1.length && j2 < a2.length) {
            while (j1 < a1.length && a1[i1] == a1[j1]) ++j1;
            while (j2 < a2.length && a2[i2] == a2[j2]) ++j2;
            if (j2 - i2 < j1 - i1) return false;
            i1 = j1;
            i2 = j2;
        }
        return j1 == a1.length && j2 == a2.length;
    }

    public static void main(String[] args) {
        assert judge("word", "wwwwwword");
        assert judge("word", "wordddd");
        assert !judge("word", "wordx");
        assert !judge("word", "xord");

        Unsafe
    }
}
