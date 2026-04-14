# Valid Anagram

## Description
Given two strings `s` and `t`, return `true` if `t` is an anagram of `s`, and `false` otherwise.

An **Anagram** is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

## Algorithm: Frequency Counting (Frequency Array)
Instead of sorting the strings, we can use a frequency array to count occurrences of each character:
1.  **Length Check**: If lengths differ, they cannot be anagrams.
2.  **Frequency Array**: Initialize an integer array of size 26 (for lowercase English letters).
3.  **Single Pass**: Iterate through both strings simultaneously:
    *   Increment the count for the character in `s`.
    *   Decrement the count for the character in `t`.
4.  **Final Check**: If all values in the frequency array are zero, the strings are anagrams.

## Implementation (Java)
```java
private boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
        return false;
    }
    
    int[] freq = new int[26];
    for (int i = 0; i < s.length(); i++) {
        freq[s.charAt(i) - 'a']++;
        freq[t.charAt(i) - 'a']--;
    }
    
    for (int count : freq) {
        if (count != 0) {
            return false;
        }
    }
    
    return true;
}
```

## Complexity
- **Time Complexity**: $O(N)$
  - We traverse both strings exactly once ($O(N)$).
  - We traverse the frequency array exactly once ($O(26) \approx O(1)$).
- **Space Complexity**: $O(1)$
  - The frequency array size is constant (26) regardless of the input string length.

## Dry Run Trace
Example: `s = "anagram"`, `t = "nagaram"`

| Step | Index | `s[i]` | `t[i]` | `freq` Changes (indices) |
| :--- | :--- | :--- | :--- | :--- |
| 1 | 0 | 'a' | 'n' | `freq[0]++`, `freq[13]--` |
| 2 | 1 | 'n' | 'a' | `freq[13]++`, `freq[0]--` (Net 0 for both) |
| 3 | 2 | 'a' | 'g' | `freq[0]++`, `freq[6]--` |
| 4 | 3 | 'g' | 'a' | `freq[6]++`, `freq[0]--` (Net 0 for both) |
| 5 | 4 | 'r' | 'r' | `freq[17]++`, `freq[17]--` (Net 0) |
| 6 | 5 | 'a' | 'a' | `freq[0]++`, `freq[0]--` (Net 0) |
| 7 | 6 | 'm' | 'm' | `freq[12]++`, `freq[12]--` (Net 0) |

**Final Check**: All `freq` values are 0. Result: `true`.

## Key Takeaway
For character-based problems with a fixed character set (like lowercase 'a'-'z'), an **integer array** of size 26 is much more space-efficient than a `HashMap` and more time-efficient than **Sorting** ($O(N \log N)$).
