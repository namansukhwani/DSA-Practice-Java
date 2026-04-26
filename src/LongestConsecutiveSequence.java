import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LongestConsecutiveSequence {

    // https://leetcode.com/problems/longest-consecutive-sequence/description/
    public static void main(String[] args) {
        LongestConsecutiveSequence longestConsecutiveSequence = new LongestConsecutiveSequence();
        int[] nums = { 100, 4, 200, 1, 3, 2 };
        int result = longestConsecutiveSequence.longestConsecutive(nums);
        System.out.println(result);
    }

    // Best Solution (O(n))
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums)
            set.add(num);

        int longest = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int count = 1;
                int current = num;

                while (set.contains(current + 1)) {
                    current++;
                    count++;
                }

                longest = Math.max(longest, count);
            }
        }
        return longest;

    }

    // My Solution
    public int longestConsecutive1(int[] nums) {
        Map<Integer, Integer> mapSet = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (mapSet.containsKey(nums[i])) {
                continue;
            }

            if (mapSet.containsKey(nums[i] - 1) && mapSet.containsKey(nums[i] + 1)) {
                int currentPlusOne = mapSet.get(nums[i] + 1);
                int currentMinusOne = mapSet.get(nums[i] - 1);
                mapSet.put(nums[i], nums[i]);
                mapSet.put(currentPlusOne, currentMinusOne);
                mapSet.put(currentMinusOne, currentPlusOne);
            } else if (mapSet.containsKey(nums[i] - 1)) {
                mapSet.put(nums[i], mapSet.get(nums[i] - 1));
                mapSet.put(mapSet.get(nums[i] - 1), nums[i]);
            } else if (mapSet.containsKey(nums[i] + 1)) {
                mapSet.put(nums[i], mapSet.get(nums[i] + 1));
                mapSet.put(mapSet.get(nums[i] + 1), nums[i]);
            } else {
                mapSet.put(nums[i], nums[i]);
            }
        }

        System.out.println(mapSet.toString());

        int maxValue = 0;

        for (Map.Entry<Integer, Integer> entry : mapSet.entrySet()) {
            if ((Math.abs(entry.getValue() - entry.getKey()) + 1) > maxValue) {
                maxValue = Math.abs(entry.getValue() - entry.getKey()) + 1;
            }
        }

        return maxValue;
    }

}
