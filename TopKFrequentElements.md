# Top K Frequent Elements Problem

## Description
Given an integer array `nums` and an integer `k`, return the `k` most frequent elements. You may return the answer in any order.

## Algorithm: Bucket Sort
The problem is solved using a Frequency Bucket approach (Bucket Sort):
1.  **Frequency Map**: Count the occurrences of each number using a `HashMap`.
2.  **Buckets**: Create an array of lists `frequencyBucket` where the index represents the frequency. Since the maximum frequency of any element is `n` (the array length), the bucket size is `n + 1`.
3.  **Grouping**: Place each number from the frequency map into the bucket corresponding to its count.
4.  **Collection**: Iterate through the buckets from highest index (most frequent) to lowest and collect the first `k` elements.

## Implementation (Java)

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        // 1. Count frequencies
        Map<Integer, Integer> mainMap = new HashMap<>();
        for (int num : nums) {
            mainMap.put(num, mainMap.getOrDefault(num, 0) + 1);
        }

        // 2. Create bucket array (Max frequency can be N)
        @SuppressWarnings("unchecked")
        List<Integer>[] frequencyBucket = new ArrayList[nums.length + 1];

        // 3. Fill buckets
        mainMap.forEach((num, freq) -> {
            if (frequencyBucket[freq] == null) {
                frequencyBucket[freq] = new ArrayList<>();
            }
            frequencyBucket[freq].add(num);
        });

        // 4. Collect top K
        int[] result = new int[k];
        int count = 0;
        for (int i = frequencyBucket.length - 1; i >= 0 && count < k; i--) {
            if (frequencyBucket[i] != null) {
                for (int num : frequencyBucket[i]) {
                    if (count < k) {
                        result[count++] = num;
                    } else break;
                }
            }
        }
        return result;
    }
}
```

## Complexity
- **Time Complexity**: $O(N)$ - One pass for the map, one pass for the buckets, and one pass to collect results.
- **Space Complexity**: $O(N)$ - To store the frequency map and the bucket array.

## Dry Run Trace
Example: `nums = [1, 1, 1, 2, 2, 3]`, `k = 2`

**Step 1: Frequency Map**
`{1: 3, 2: 2, 3: 1}`

**Step 2: Frequency Bucket (Size 7)**
| Index (Freq) | Elements |
| :--- | :--- |
| 0 | null |
| 1 | `[3]` |
| 2 | `[2]` |
| 3 | `[1]` |
| 4 | null |
| 5 | null |
| 6 | null |

**Step 3: Collecting Top 2**
1. Start from Index 6 down to 0.
2. Index 3: Found `[1]`. `result = [1]`, `count = 1`.
3. Index 2: Found `[2]`. `result = [1, 2]`, `count = 2`.
4. `count == k`, Return `[1, 2]`.

### Key Takeaway
By using the **frequency as an index** in an array (Bucket Sort), we eliminate the need for sorting ($O(N \log N)$) or using a Priority Queue ($O(N \log K)$). This optimization is possible because the range of frequencies is strictly bounded by the size of the input array.
