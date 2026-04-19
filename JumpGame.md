# Jump Game Problem

## Description
You are given an integer array `nums`. You are initially positioned at the array's **first index**, and each element in the array represents your maximum jump length at that position.

Return `true` if you can reach the last index, or `false` otherwise.

## Algorithm: Dynamic Programming
The problem is solved using a greedy-based Dynamic Programming approach (Maximum Reachability):
1.  **State**: `maxReach` stores the farthest index reachable from the starting position.
2.  **Transition**: At each index `i`, we calculate the potential reach `i + nums[i]`.
3.  **Optimization**: If the current index `i` is within the current `maxReach`, we update `maxReach = max(maxReach, i + nums[i])`.
4.  **Early Exit**: If `maxReach` reaches or exceeds the last index (`n - 1`), return `true`.
5.  **Result**: If the loop finishes and we haven't reached the end, return `false`.

## Implementation (Java)

```java
public class JumpGame {

    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
        int[] nums = new int[] { 2, 3, 1, 1, 4 };
        boolean result = jumpGame.canJump(nums);
        System.out.println(result);
    }

    private boolean canJump(int[] nums) {
        int n = nums.length;
        if (n <= 1)
            return true;
            
        int maxReach = 0;
        for (int i = 0; i <= maxReach; i++) {
            int currentReach = i + nums[i];
            if (currentReach > maxReach) {
                maxReach = currentReach;
            }
            if (maxReach >= n - 1) {
                return true;
            }
        }
        return false;
    }
}
```

## Complexity
- **Time Complexity**: $O(N)$ - We traverse the array at most once.
- **Space Complexity**: $O(1)$ - Only a single variable `maxReach` is used.

## Dry Run Trace
Example: `nums = [2, 3, 1, 1, 4]`

| Index `i` | `nums[i]` | `currentReach` (`i + nums[i]`) | `maxReach` (Updated) | `maxReach >= 4`? | Result |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | 2 | 0 + 2 = 2 | 2 | No | Continue |
| 1 | 3 | 1 + 3 = 4 | 4 | **Yes** | **True** |

Example: `nums = [3, 2, 1, 0, 4]`

| Index `i` | `nums[i]` | `currentReach` (`i + nums[i]`) | `maxReach` (Updated) | `maxReach >= 4`? | Result |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | 3 | 0 + 3 = 3 | 3 | No | Continue |
| 1 | 2 | 1 + 2 = 3 | 3 | No | Continue |
| 2 | 1 | 2 + 1 = 3 | 3 | No | Continue |
| 3 | 0 | 3 + 0 = 3 | 3 | No | Loop Ends |
| - | - | - | - | - | **False** |

### Key Takeaway
The Greedy approach works because at any point, we only care about the **farthest** point we can reach. If we can reach index `X`, we can also reach any index `< X`. By maintaining the `maxReach`, we avoid redundant calculations of sub-problems, effectively reducing the $O(N^2)$ DP solution to $O(N)$.
