public class EmployRating {
    // https://www.hackerearth.com/practice/algorithms/searching/linear-search/practice-problems/algorithm/employee-rating-8cd8dc10/

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
