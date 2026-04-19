import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements {

    // https://leetcode.com/problems/top-k-frequent-elements/description/
    public static void main(String[] args) {
        TopKFrequentElements topKFrequentElements = new TopKFrequentElements();
        int[] nums = new int[] { 1, 1, 1, 2, 2, 3 };
        int k = 2;
        int[] result = topKFrequentElements.topKFrequent(nums, k);
        System.out.println(Arrays.toString(result));
    }

    // Best Solution same logic with less code
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> mainMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (mainMap.containsKey(nums[i])) {
                mainMap.put(nums[i], mainMap.get(nums[i]) + 1);
            } else {
                mainMap.put(nums[i], 1);
            }
        }

        // Max Frequency can be N only. So create bucket array of size N+1.
        @SuppressWarnings("unchecked")
        List<Integer>[] frequencyBucket = new ArrayList[nums.length + 1];

        mainMap.forEach((key, value) -> {
            if (frequencyBucket[value] == null) {
                frequencyBucket[value] = new ArrayList<>();
            }
            frequencyBucket[value].add(key);
        });

        int[] result = new int[k];
        int count = 0;
        for (int i = frequencyBucket.length - 1; i >= 0 && count < k; i--) {
            if (frequencyBucket[i] != null && !frequencyBucket[i].isEmpty()) {
                List<Integer> numbers = frequencyBucket[i];
                for (int num : numbers) {
                    if (count < k) {
                        result[count] = num;
                        count++;
                    } else {
                        break;
                    }
                }
            }
        }
        return result;
    }

    // My Solution O(N) space O(N)
    public int[] topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> mainMap = new HashMap<>();
        Map<Integer, List<Integer>> frequencyMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (mainMap.containsKey(nums[i])) {
                mainMap.put(nums[i], mainMap.get(nums[i]) + 1);
            } else {
                mainMap.put(nums[i], 1);
            }
        }

        mainMap.forEach((key, value) -> {
            if (frequencyMap.containsKey(value)) {
                frequencyMap.get(value).add(key);
            } else {
                frequencyMap.put(value, new ArrayList<>());
                frequencyMap.get(value).add(key);
            }
        });

        List<Integer> sortedKeys = new ArrayList<>(frequencyMap.keySet());
        sortedKeys.sort(Comparator.reverseOrder());

        int[] result = new int[k];
        int j = 0;
        int i = 0;

        while (i < sortedKeys.size() && j < k) {
            List<Integer> numbers = frequencyMap.get(sortedKeys.get(i));
            for (int t = 0; t < numbers.size(); t++) {
                if (j >= k) {
                    break;
                }
                result[j] = numbers.get(t);
                j++;
            }
            ;
            i++;
        }

        return result;
    }
}
