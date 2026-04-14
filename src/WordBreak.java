import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    // https://leetcode.com/problems/word-break/description/
    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        var result = wordBreak.wordBreak("aaaaaaa", List.of("aaaa", "aaa"));
        System.out.println(result);
    }

    private boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> dictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}
