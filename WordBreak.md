# Word Break Problem

## Description
Given a string `s` and a dictionary of strings `wordDict`, return `true` if `s` can be segmented into a space-separated sequence of one or more dictionary words.

## Algorithm: Dynamic Programming
The problem is solved using an iterative DP approach:
1.  **State**: `dp[i]` is a boolean indicating whether the substring `s[0...i-1]` can be segmented into dictionary words.
2.  **Base Case**: `dp[0] = true` (an empty string is always segmentable).
3.  **Transitions**: For each index `i` from 1 to `n`:
    *   Iterate through all previous indices `j` from 0 to `i-1`.
    *   If `dp[j]` is true AND the substring `s.substring(j, i)` is in the dictionary, then `dp[i] = true`.
4.  **Result**: `dp[n]` tells us if the entire string is segmentable.

## Implementation (Java)


```java
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
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
```

## Complexity
- **Time Complexity**: $O(N^2 + M \cdot L)$
- **Space Complexity**: $O(N + M \cdot L)$

## Dry Run Trace
Example: `s = "aaaaaaa"`, `wordDict = ["aaaa", "aaa"]`

| `i` (End) | `j` (Start) | `s.substring(j, i)` | `dp[j]` | Match? | Result |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 1 | 0 | "a" | T | No | `dp[1] = F` |
| 2 | 0, 1 | "aa", "a" | T, F | No | `dp[2] = F` |
| **3** | **0** | **"aaa"** | **T** | **Yes** | **`dp[3] = T`** |
| **4** | **0** | **"aaaa"** | **T** | **Yes** | **`dp[4] = T`** |
| 5 | 0..4 | - | - | No | `dp[5] = F` |
| **6** | **3** | **"aaa"** | **T** | **Yes** | **`dp[6] = T`** |
| **7** | **3** | **"aaaa"** | **T** | **Yes** | **`dp[7] = T`** |

### Key Takeaway
By checking all previous split points `j`, the DP solution ensures that if any valid partition exists (like $3+4$ or $4+3$), it will be found. This avoids the "greedy trap" where choosing the first available word might block a valid overall segmentation.

