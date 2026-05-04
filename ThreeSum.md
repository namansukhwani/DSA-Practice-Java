# Three Sum Problem

## Description
Given an integer array `nums`, return all the triplets `[nums[i], nums[j], nums[k]]` such that `i != j`, `i != k`, and `j != k`, and `nums[i] + nums[j] + nums[k] == 0`. The solution set must not contain duplicate triplets.

## Algorithm: Two Pointers
The most efficient approach uses a combination of sorting and the two-pointer technique:
1.  **Sort**: First, sort the array in ascending order. This allows us to use two pointers to navigate the sum space and easily skip duplicates.
2.  **Iterate**: Loop through the array from `i = 0` to `n - 3`.
3.  **Two Pointers**: For each fixed `nums[i]`, set `left = i + 1` and `right = n - 1`.
    *   If `nums[i] + nums[left] + nums[right] == 0`, a triplet is found.
    *   If the sum is less than 0, increment `left` to increase the sum.
    *   If the sum is greater than 0, decrement `right` to decrease the sum.
4.  **Handle Duplicates**: The sorted nature and the use of a `Set` (or manual index skipping) ensure that only unique triplets are collected.

## Implementation (Java)

```java
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSum {
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
}
```

## Complexity
- **Time Complexity**: $O(N^2)$ - Sorting takes $O(N \log N)$, and the nested loops (one for `i` and the while loop for `left`/`right`) take $O(N^2)$.
- **Space Complexity**: $O(1)$ to $O(N)$ - Depending on the sorting implementation and whether the output list is considered. The two-pointer logic itself uses $O(1)$ extra space.

## Dry Run Trace
Example: `nums = [-1, 0, 1, 2, -1, -4]`
Sorted: `[-4, -1, -1, 0, 1, 2]`

| `i` | `nums[i]` | `left` | `right` | `nums[i] + nums[l] + nums[r]` | Action | Result Set |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | -4 | 1 | 5 | -4 + (-1) + 2 = -3 | `left++` | `[]` |
| 0 | -4 | 2 | 5 | -4 + (-1) + 2 = -3 | `left++` | `[]` |
| ... | ... | ... | ... | ... | ... | ... |
| 1 | -1 | 2 | 5 | -1 + (-1) + 2 = 0 | **Match!** | `[[-1, -1, 2]]` |
| 1 | -1 | 3 | 4 | -1 + 0 + 1 = 0 | **Match!** | `[[-1, -1, 2], [-1, 0, 1]]` |
| 2 | -1 | 3 | 5 | -1 + 0 + 2 = 1 | `right--` | `[[-1, -1, 2], [-1, 0, 1]]` |
| 2 | -1 | 3 | 4 | -1 + 0 + 1 = 0 | **Match!** | `[[-1, -1, 2], [-1, 0, 1]]` |

### Key Takeaway
Sorting is the catalyst for optimization in this problem. It transforms a potential $O(N^3)$ brute-force search into an $O(N^2)$ solution by enabling the two-pointer technique and making duplicate detection trivial.
