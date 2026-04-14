# Two Sum

## Description
Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.

You may assume that each input would have **exactly one solution**, and you may not use the same element twice. You can return the answer in any order.

## Algorithm: One-pass Hash Map
The most efficient way to solve this is using a HashMap to store the numbers we've seen so far and their indices:
1.  **Initialize**: Create a `HashMap<Integer, Integer>` to store `value -> index`.
2.  **Iterate**: Loop through the array once.
3.  **Calculate Complement**: For each element `nums[i]`, calculate `complement = target - nums[i]`.
4.  **Lookup**:
    *   If the `complement` exists in the map, we found the pair. Return `[map.get(complement), i]`.
    *   Otherwise, add `nums[i]` and its index `i` to the map.
5.  **Result**: Since exactly one solution exists, the function will always return a valid pair.

## Implementation (Java)
```java
private int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
            return new int[] { map.get(complement), i };
        }
        map.put(nums[i], i);
    }
    return new int[] { -1, -1 };
}
```

## Complexity
- **Time Complexity**: $O(N)$
  - We traverse the list containing $N$ elements only once.
  - Each look-up in the table costs only $O(1)$ time.
- **Space Complexity**: $O(N)$
  - In the worst case, we store $N$ elements in the HashMap.

## Dry Run Trace
Example: `nums = [2, 7, 11, 15]`, `target = 9`

| Step | Index | `nums[i]` | `complement` (9 - `nums[i]`) | Map State | Found? |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 1 | 0 | 2 | 7 | `{2: 0}` | No |
| 2 | 1 | 7 | 2 | `{2: 0}` | **Yes!** (2 is at index 0) |

**Result**: `[0, 1]`

## Key Takeaway
The **One-pass Hash Map** approach allows us to reduce the time complexity from $O(N^2)$ (Brute Force) to **$O(N)$** by trading some space. It effectively looks "backwards" to see if the required number was already encountered.
