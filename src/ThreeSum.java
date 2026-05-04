import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ThreeSum {
    // https://leetcode.com/problems/3sum/description/
    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        int[] nums = { -1, 0, 1, 2, -1, -4 };
        System.out.println(threeSum.threeSum(nums));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        // Most optimized approach using two pointers
        Set<List<Integer>> resultSet = new HashSet<>();
        int target = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                if (currentSum == target) {
                    resultSet.add(List.of(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                } else if (currentSum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return resultSet.stream().toList();
    }

    public List<List<Integer>> threeSum1(int[] nums) {
        int target = 0;
        Set<List<Integer>> resultSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < nums.length; j++) {
                int requiredSum = target - nums[i] - nums[j];
                if (map.containsKey(requiredSum)) {
                    List<Integer> tempResult = new ArrayList<>();
                    tempResult.add(requiredSum);
                    tempResult.add(nums[i]);
                    tempResult.add(nums[j]);
                    Collections.sort(tempResult);
                    resultSet.add(tempResult);
                }
                map.putIfAbsent(nums[j], j);
            }
        }

        return resultSet.stream().toList();
    }
}
