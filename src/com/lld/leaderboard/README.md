# Design Leader Board

**Requirements**

1. The user should be able to add or update an entry. An entry contains user 
specified key and value.
2. The system should be able to assign a score to the entries, and the ranking
of the entries will be based on the score.
3. The user should be able to fetch top N entries by score in ascending order.
4. Get / Delete should be supported by key.
5. Handle concurrent update of entries.

**Good-to-have**
1. Fetch all entries whose score is in given range.
2. Allow ranking by descending order of score.