public class JumpGame {

    // https://leetcode.com/problems/jump-game/description/
    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
        int[] nums = new int[] { 2, 3, 1, 1, 4 };
        boolean result = jumpGame.canJump(nums);
        System.out.println(result);
    }

    // Best Solution
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

    // My Solution
    public boolean canJump1(int[] nums) {
        int n = nums.length;
        if (n < 2)
            return true;
        int i = 0;
        while (i < n) {
            int jump = nums[i];
            int maxJump = i + jump;
            int previousI = i;

            if (jump == 0) {
                return false;
            }

            if (maxJump < n && (maxJump == n - 1 || nums[maxJump] != 0)) {
                i = maxJump;
            } else if (maxJump < n && nums[maxJump] == 0) {
                for (int j = maxJump; j > i; j--) {
                    if (nums[j] > 0) {
                        i = j;
                        break;
                    }
                }
            } else if (maxJump >= n) {
                i = (n - 1);
            } else {
                return false;
            }

            if (previousI == i) {
                return false;
            }
            System.out.println(i);

            if (i == n - 1) {
                return true;
            }
        }

        return false;
    }
}
