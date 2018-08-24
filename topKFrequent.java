import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/*
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
*/

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        //get the hashmap
        Map<Integer, Integer> hash = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            Integer count = hash.get(nums[i]);

            if (count == null) {
                hash.put(nums[i], 1);
            } else {
                hash.put(nums[i], count + 1);
            }
        }
        //Create a pq with asc order on value
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(k, new Comparator<Map.Entry<Integer, Integer>>(){
            @Override
            public int compare(Map.Entry<Integer, Integer> a1, Map.Entry<Integer, Integer> a2){
                return a1.getValue() - a2.getValue();
            }
        });

        //populate the pq
        for (Map.Entry<Integer, Integer> entry: hash.entrySet()) {
            if (pq.size() < k) {
                pq.add(entry);
            } else if (pq.peek().getValue() < entry.getValue()) {
                pq.remove();
                pq.add(entry);
            }
        }

        List<Integer> result = new LinkedList<Integer>();

        while (!pq.isEmpty()) {
            result.add(pq.remove().getKey());
        }

        return result;
    }
}

