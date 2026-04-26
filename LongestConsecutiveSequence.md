# Longest Consecutive Sequence

## Description
Given an unsorted array of integers `nums`, return the length of the longest consecutive elements sequence. The algorithm must run in $O(n)$ time.

## Algorithm: Dynamic Programming
While typically solved using a `HashSet`, the logic follows an optimized iterative approach that identifies the start of sequences to avoid redundant work:
1.  **State**: We use a `HashSet` to store all numbers for $O(1)$ lookups.
2.  **Sequence Detection**: For every number `num`, we check if it is the **start** of a sequence by verifying if `num - 1` exists in the set.
3.  **Transition**: If `num` is a start, we increment `current` and count how many consecutive integers (`num + 1, num + 2, ...`) exist in the set.
4.  **Optimization**: By only starting the inner loop for values that have no predecessor in the set, each element is processed at most twice, ensuring linear time complexity.

## Implementation (Java)

```java
import java.util.HashSet;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;
        for (int num : set) {
            // Only start counting if 'num' is the beginning of a sequence
            if (!set.contains(num - 1)) {
                int current = num;
                int count = 1;

                while (set.contains(current + 1)) {
                    current++;
                    count++;
                }

                longest = Math.max(longest, count);
            }
        }
        return longest;
    }
}
```

## Complexity
- **Time Complexity**: $O(n)$ - Each number is added to the set and visited in the loops at most twice.
- **Space Complexity**: $O(n)$ - Required to store the elements in the `HashSet`.

## Dry Run Trace
Example: `nums = [100, 4, 200, 1, 3, 2]`
`set = {100, 4, 200, 1, 3, 2}`

| `num` | `num - 1` in set? | Start? | `while` sequence | Length | `longest` |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 100 | 99 (No) | **Yes** | [100] | 1 | 1 |
| 4 | 3 (Yes) | No | - | - | 1 |
| 200 | 199 (No) | **Yes** | [200] | 1 | 1 |
| **1** | **0 (No)** | **Yes** | **[1, 2, 3, 4]** | **4** | **4** |
| 3 | 2 (Yes) | No | - | - | 4 |
| 2 | 1 (Yes) | No | - | - | 4 |

## Key Takeaway
The "start of sequence" check (`!set.contains(num - 1)`) is the defining optimization. It transforms a potential $O(n^2)$ search into a $O(n)$ scan by ensuring that the inner `while` loop only triggers for the head of each consecutive sequence.
