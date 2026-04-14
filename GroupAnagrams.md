# Group Anagrams

## Description
Given an array of strings `strs`, group the **anagrams** together. You can return the answer in any order.

An **Anagram** is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

## Algorithm: Dynamic Programming
*(Note: While categorized here as requested, this solution optimally uses **Frequency Hashing**)*:
1.  **Map Grouping**: Use a `Map<String, List<String>>` where the key is a unique representation of character frequencies and the value is a list of strings sharing those frequencies.
2.  **Compact Frequency Key**: For each string:
    *   Initialize a `char[26]` array (to handle counts up to 65535, which is plenty for string lengths).
    *   Iterate through the string and increment the corresponding character index.
    *   Convert this `char[]` directly into a `String`. This `String` serves as a perfectly unique and lightweight key.
3.  **Grouping**: Use `computeIfAbsent` to either add the string to an existing list or create a new one for that specific frequency signature.

## Implementation (Java)
```java
private List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    for (String s : strs) {
        String key = getFrequencyKey(s);
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
    }
    return new ArrayList<>(map.values());
}

private String getFrequencyKey(String s) {
    char[] counts = new char[26];
    for (char c : s.toCharArray()) {
        counts[c - 'a']++;
    }
    return new String(counts);
}
```

## Complexity
- **Time Complexity**: $O(N \cdot K)$
  - $N$ is the number of strings and $K$ is the maximum length of a string.
  - We iterate through each string once to build the frequency key ($O(K)$) and perform $O(1)$ map operations.
- **Space Complexity**: $O(N \cdot K)$
  - We store all characters of all strings in the HashMap grouping.

## Dry Run Trace
Example: `strs = ["eat", "tea", "tan"]`

| Step | String | Frequency counts (simplified) | Map Key (Internal) | Map State |
| :--- | :--- | :--- | :--- | :--- |
| 1 | "eat" | `{a:1, e:1, t:1}` | `\u0001...\u0001` | `{key1: ["eat"]}` |
| 2 | "tea" | `{a:1, e:1, t:1}` | `\u0001...\u0001` | `{key1: ["eat", "tea"]}` |
| 3 | "tan" | `{a:1, n:1, t:1}` | `\u0001...\u0001` | `{key1: ["eat", "tea"], key2: ["tan"]}` |

**Final Result**: `[["eat", "tea"], ["tan"]]`

## Key Takeaway
Using a **Frequency Array converted to a String** is the most efficient way to group anagrams. It avoids the $O(K \log K)$ cost of sorting each string and the high memory/hashing overhead of `Arrays.toString(int[])`. It provides a clean $O(N \cdot K)$ linear time solution.
