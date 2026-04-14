import java.util.Arrays;

public class ValidAnagram {

    // https://leetcode.com/problems/valid-anagram/description/
    public static void main(String[] args) {
        ValidAnagram validAnagram = new ValidAnagram();
        System.out.println(validAnagram.isAnagram("anagram", "nagaram"));
    }

    // My Best Optimized Solution
    private boolean isAnagram(String s, String t) {
        if (s.length() == t.length()) {
            int[] freq = new int[26];
            for (int i = 0; i < s.length(); i++) {
                freq[s.charAt(i) - 'a']++;
                freq[t.charAt(i) - 'a']--;
            }
            for (int i = 0; i < 26; i++) {
                if (freq[i] != 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // My First Solution
    private boolean isAnagram1(String s, String t) {
        if (s.length() == t.length()) {
            char[] sarr = s.toCharArray();
            char[] tarr = t.toCharArray();
            Arrays.sort(sarr);
            Arrays.sort(tarr);
            for (int i = 0; i < sarr.length; i++) {
                if (sarr[i] != tarr[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}