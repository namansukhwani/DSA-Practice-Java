# Accounts Merge Problem

## Description
Given a list of `accounts` where `accounts[i][0]` is a name, and `accounts[i][1:]` are emails. If two accounts share an email, they belong to the same person. Return the merged accounts with sorted emails and the account name as the first element.

---

## Solution 1: Union Find (DSU) - Best Solution
This approach groups account indices by finding shared emails.

### Full Implementation
```java
public List<List<String>> accountsMerge(List<List<String>> accounts) {
    int n = accounts.size();
    DSU dsu = new DSU(n);
    Map<String, Integer> emailToId = new HashMap<>();

    // 1. Union accounts that share emails
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

    // 2. Group emails by their root parent index
    Map<Integer, List<String>> merged = new HashMap<>();
    for (String email : emailToId.keySet()) {
        int root = dsu.find(emailToId.get(email));
        merged.computeIfAbsent(root, x -> new ArrayList<>()).add(email);
    }

    // 3. Format result: Sort emails and prepend the name
    List<List<String>> result = new ArrayList<>();
    for (Map.Entry<Integer, List<String>> entry : merged.entrySet()) {
        List<String> emails = entry.getValue();
        Collections.sort(emails);
        emails.add(0, accounts.get(entry.getKey()).get(0));
        result.add(emails);
    }
    return result;
}

class DSU {
    int[] parent;
    public DSU(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }
    public int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]); // Path compression
    }
    public void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) parent[rootI] = rootJ;
    }
}
```

---

## Solution 2: Iterative Mapping - My Solution
This approach manually merges sets by updating email ownership whenever a conflict is found.

### Full Implementation
```java
public List<List<String>> accountsMerge1(List<List<String>> accounts) {
    Map<Integer, Set<String>> accountToEmails = new HashMap<>();
    Map<String, Integer> emailToAccount = new HashMap<>();
    Map<Integer, String> accountToName = new HashMap<>();
    int accountNumber = 0;

    for (List<String> data : accounts) {
        String name = data.get(0);
        accountToName.put(accountNumber, name);
        int currId = accountNumber;

        for (int i = 1; i < data.size(); i++) {
            String email = data.get(i);
            // If email seen, merge current set into existing one
            if (emailToAccount.containsKey(email) && emailToAccount.get(email) != currId) {
                int existingId = emailToAccount.get(email);
                Set<String> emails = accountToEmails.get(currId);
                if (emails != null) {
                    accountToEmails.get(existingId).addAll(emails);
                    for (String e : emails) emailToAccount.put(e, existingId);
                }
                accountToEmails.remove(currId);
                currId = existingId;
            }
            emailToAccount.put(email, currId);
            accountToEmails.computeIfAbsent(currId, x -> new HashSet<>()).add(email);
        }
        accountNumber++;
    }

    List<List<String>> result = new ArrayList<>();
    for (int id : accountToEmails.keySet()) {
        List<String> emails = new ArrayList<>(accountToEmails.get(id));
        Collections.sort(emails);
        emails.add(0, accountToName.get(id));
        result.add(emails);
    }
    return result;
}
```

---

## Detailed Dry Run (DSU)
Input: `[[John, A, B], [John, B, C], [Mary, D]]`

### Phase 1: Union Operations
| Account | Email | `emailToId` Action | `dsu.parent` State |
| :--- | :--- | :--- | :--- |
| **0** (John) | A | New: `{A:0}` | `[0, 1, 2]` |
| | B | New: `{A:0, B:0}` | `[0, 1, 2]` |
| **1** (John) | B | Conflict! Exist in 0. `union(1, 0)` | `[0, 0, 2]` |
| | C | New: `{A:0, B:0, C:1}` | `[0, 0, 2]` |
| **2** (Mary) | D | New: `{A:0, B:0, C:1, D:2}` | `[0, 0, 2]` |

### Phase 2: Grouping (Roots)
*   **A**: `Id=0`. `find(0) -> 0`. Map: `{0: [A]}`
*   **B**: `Id=0`. `find(0) -> 0`. Map: `{0: [A, B]}`
*   **C**: `Id=1`. `find(1) -> 0`. Map: `{0: [A, B, C]}`
*   **D**: `Id=2`. `find(2) -> 2`. Map: `{0: [A, B, C], 2: [D]}`

### Phase 3: Final Output
*   Group 0: `[John, A, B, C]`
*   Group 2: `[Mary, D]`

