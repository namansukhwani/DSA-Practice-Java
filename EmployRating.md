# Employee Rating Problem

## Description
Given an employee's daily workload for $N$ days, find the maximum number of consecutive days where the employee's rating was strictly greater than 6.

## Algorithm: Dynamic Programming
This problem can be solved using a space-optimized dynamic programming approach:
1.  **State**: `count` represents the current number of consecutive days where the workload $> 6$.
2.  **Transitions**: For each day `i`:
    *   If `workload[i] > 6`: `count = count + 1`.
    *   Otherwise: `count = 0` (reset the streak).
3.  **Result**: `result` tracks the maximum value of `count` encountered during the iteration.

## Implementation (Java)

```java
public class EmployRating {
    public static void main(String[] args) {
        EmployRating employRating = new EmployRating();
        System.out.println(employRating.solve(12, new int[] { 2, 3, 7, 8, 7, 6, 3, 8, 12, 11, 12, 10 }));
    }

    private int solve(int N, int[] workload) {
        int result = 0;
        int rating = 6;
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (workload[i] > rating) {
                count++;
            } else {
                count = 0;
            }
            result = Math.max(count, result);
        }
        return result;
    }
}
```

## Complexity
- **Time Complexity**: $O(N)$ - Single pass through the workload array.
- **Space Complexity**: $O(1)$ - Only constant extra space used for variables.

## Dry Run Trace
Example: `N = 12`, `workload = [2, 3, 7, 8, 7, 6, 3, 8, 12, 11, 12, 10]`

| Day | Workload | `workload[i] > 6` | `count` | `result` (Max) |
| :--- | :--- | :--- | :--- | :--- |
| 1 | 2 | No | 0 | 0 |
| 2 | 3 | No | 0 | 0 |
| 3 | 7 | Yes | 1 | 1 |
| 4 | 8 | Yes | 2 | 2 |
| 5 | 7 | Yes | 3 | 3 |
| 6 | 6 | No | 0 | 3 |
| 7 | 3 | No | 0 | 3 |
| 8 | 8 | Yes | 1 | 3 |
| 9 | 12 | Yes | 2 | 3 |
| 10 | 11 | Yes | 3 | 3 |
| 11 | 12 | Yes | 4 | 4 |
| 12 | 10 | Yes | 5 | **5** |

### Key Takeaway
The problem is a variation of finding the longest subarray meeting a condition. By resetting the counter whenever the condition fails, we efficiently find the longest consecutive streak in a single linear pass without needing an auxiliary DP array.
