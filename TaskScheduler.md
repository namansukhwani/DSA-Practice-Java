# Task Scheduler Problem

## Description
Given a characters array `tasks`, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks can be done in any order. Each task is done in one unit of time. For each unit of time, the CPU can complete either one task or be idle.

However, there is a non-negative integer `n` that represents the cooldown period between two **same tasks** (i.e., there must be at least `n` units of time between any two identical tasks).

Return the least number of units of time that the CPU will take to finish all the given tasks.

## Algorithm: Greedy Math
The most efficient approach uses a mathematical calculation based on the most frequent task:

1.  **Count Frequencies**: Count the occurrences of each task.
2.  **Find Max Frequency**: Identify the maximum frequency `f_max`.
3.  **Calculate Idle Slots**:
    *   Imagine the most frequent tasks as "anchors" for blocks.
    *   Number of chunks = `f_max - 1`.
    *   Initial idle slots = `chunks * n`.
4.  **Fill Idle Slots**: Iterate through other tasks and subtract their frequencies from the idle slots.
5.  **Result**: If idle slots are still positive, the answer is `tasks.length + idle`. Otherwise, it's just `tasks.length`.

## Implementation (Java)

```java
private int leastInterval(char[] tasks, int n) {
    int[] freq = new int[26];
    for (char task : tasks) {
        freq[task - 'A']++;
    }
    Arrays.sort(freq);
    
    int maxFreq = freq[25];
    int chunks = maxFreq - 1;
    int idle = chunks * n;

    for (int i = 24; i >= 0; i--) {
        // Subtract the frequency of other tasks from idle slots
        // We use Math.min(chunks, freq[i]) because a task cannot fill 
        // more than one slot in each of the (maxFreq - 1) chunks.
        idle -= Math.min(chunks, freq[i]);
    }

    return idle < 0 ? tasks.length : tasks.length + idle;
}
```

## Complexity
- **Time Complexity**: $O(T)$ where $T$ is the total number of tasks (to count frequencies). Sorting 26 integers is $O(1)$.
- **Space Complexity**: $O(1)$ as we only use a fixed-size array of 26 to store frequencies.

## Dry Run Trace
Example: `tasks = ["A","A","A","B","B","B"], n = 2`

1.  **Frequencies**: `A: 3, B: 3`
2.  **Max Frequency**: `maxFreq = 3`
3.  **Chunks**: `3 - 1 = 2` blocks (e.g., `A _ _ A _ _ A`)
4.  **Initial Idle**: `2 * 2 = 4` slots.
5.  **Filling Slots**:
    *   Task `B` has frequency 3.
    *   `idle -= Math.min(2, 3)` $\rightarrow$ `idle = 4 - 2 = 2`.
    *   (The sequence looks like `A B _ A B _ A B`)
6.  **Final Result**: `tasks.length (6) + idle (2) = 8`.

### Key Takeaway
The bottleneck is always the task with the highest frequency. By arranging those tasks first with the required cooldown `n`, we create "slots" that can be filled by other tasks to reduce idle time.
