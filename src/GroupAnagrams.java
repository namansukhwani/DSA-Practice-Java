import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

    // https://leetcode.com/problems/group-anagrams/description/
    public static void main(String[] args) {
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };
        System.out.println(groupAnagrams.groupAnagrams(strs));
    }

    // My Best Optimized Solution
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
}
