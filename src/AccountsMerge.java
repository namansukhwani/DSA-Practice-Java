import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/accounts-merge/description/

public class AccountsMerge {
    public static void main(String[] args) {
        AccountsMerge accountsMerge = new AccountsMerge();
        List<List<String>> accounts = List.of(
                List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                List.of("John", "johnsmith@mail.com", "john00@mail.com"),
                List.of("Mary", "mary@mail.com"),
                List.of("John", "johnnybravo@mail.com"));
        // Print Response
        System.out.println(accountsMerge.accountsMerge(accounts));
    }

    // Best Solution
    private List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DSU dsu = new DSU(n);
        Map<String, Integer> emailToId = new HashMap<>();

        for (int i = 0; i < n; i++) {
            List<String> account = accounts.get(i);
            for (int k = 1; k < account.size(); k++) {
                String email = account.get(k);
                if (emailToId.containsKey(email)) {
                    dsu.union(i, emailToId.get(email));
                } else {
                    emailToId.put(email, i);
                }
            }
        }

        Map<Integer, List<String>> merged = new HashMap<>();
        for (String email : emailToId.keySet()) {
            int root = dsu.find(emailToId.get(email));
            merged.computeIfAbsent(root, x -> new ArrayList<>()).add(email);
        }

        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : merged.entrySet()) {
            List<String> emails = entry.getValue();
            Collections.sort(emails);
            emails.add(0, accounts.get(entry.getKey()).get(0)); // Get name from an account in this group
            result.add(emails);
        }
        return result;
    }

    // My Solution
    public List<List<String>> accountsMerge1(List<List<String>> accounts) {
        Map<Integer, Set<String>> accountToEmails = new HashMap<>();
        Map<String, Integer> emailToAccount = new HashMap<>();
        Map<Integer, String> accountToName = new HashMap<>();
        int accountNumber = 0;
        for (List<String> data : accounts) {
            String name = data.get(0);
            accountToName.put(accountNumber, name);

            int tempAccountNumber = accountNumber;
            for (int i = 1; i < data.size(); i++) {

                String email = data.get(i);

                if (emailToAccount.containsKey(email) && emailToAccount.get(email) != tempAccountNumber) {
                    int existingAccountNumber = emailToAccount.get(email);
                    Set<String> emails = accountToEmails.get(tempAccountNumber);
                    if (emails != null) {
                        accountToEmails.get(existingAccountNumber).addAll(emails);
                        for (String tempEmail : emails) {
                            emailToAccount.put(tempEmail, existingAccountNumber);
                        }
                    }
                    accountToEmails.remove(tempAccountNumber);
                    tempAccountNumber = existingAccountNumber;
                }

                emailToAccount.put(email, tempAccountNumber);
                accountToEmails.putIfAbsent(tempAccountNumber, new HashSet<>());
                accountToEmails.get(tempAccountNumber).add(email);

            }
            accountNumber++;
        }
        System.out.println(accountToEmails);
        // System.out.println(emailToAccount);

        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < accountNumber; i++) {
            if (!accountToEmails.containsKey(i)) {
                continue;
            }
            List<String> emails = new ArrayList<>(accountToEmails.get(i));
            String name = accountToName.get(i);
            Collections.sort(emails);
            emails.add(0, name);
            result.add(emails);
        }
        return result;
    }

    class DSU {
        int[] parent;

        public DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        public int find(int i) {
            if (parent[i] == i)
                return i;
            return parent[i] = find(parent[i]);
        }

        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ)
                parent[rootI] = rootJ;
        }
    }
}
