import java.util.Arrays;

public class TaskScheduler {

    // https://leetcode.com/problems/task-scheduler/description/
    public static void main(String[] args) throws Exception {
        TaskScheduler taskScheduler = new TaskScheduler();
        taskScheduler.leastInterval(new char[] { 'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'D', 'D', 'E' }, 2);
    }

    private int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }
        Arrays.sort(freq);
        int chunk = freq[25] - 1;
        int idle = chunk * n;

        for (int i = 24; i >= 0; i--) {
            idle -= Math.min(chunk, freq[i]);
        }

        return idle < 0 ? tasks.length : tasks.length + idle;
    }

    // A B C A B C A B C D E INT D

    public int leastInterval1(char[] tasks, int n) {
        int[] taskTypeFreq = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            taskTypeFreq[this.getAsciValueNormalized(tasks[i])]++;
        }
        this.sortArr(taskTypeFreq);
        System.out.println(Arrays.toString(taskTypeFreq));

        int intervals = 0;
        int bucket = n + 1;
        int maxSum = Arrays.stream(taskTypeFreq).sum();
        int currentSum = maxSum;
        while (currentSum > 0) {
            for (int i = 0; i < taskTypeFreq.length; i++) {
                System.out.println(Arrays.toString(taskTypeFreq));
                System.out.println(bucket + "/" + i);

                if (bucket == 0) {
                    break;
                }
                if (taskTypeFreq[i] == 0) {
                    continue;
                }
                taskTypeFreq[i]--;
                currentSum--;
                System.out.println("increasing interval by 1");
                intervals++;
                bucket--;
            }

            this.sortArr(taskTypeFreq);

            if (bucket > 0 && currentSum > 0) {
                System.out.println("increasing interval by " + (bucket));
                intervals += bucket;
            }
            bucket = n + 1;
        }

        System.out.println("##################");
        System.out.println(intervals);
        return intervals;
    }

    private void sortArr(int[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

    private int getAsciValueNormalized(char a) {
        return (int) a - 65;
    }
}
