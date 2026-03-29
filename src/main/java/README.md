# LeetCode solutions (Java)

Sources live in the [`leetcode/`](leetcode/) directory (package `leetcode`). Below: **this implementation** (runtime / space), then **known best asymptotics** for the problem (or "Is most optimal solution" when this code matches them).

---

## [Leet0001TwoSums.java](leetcode/Leet0001TwoSums.java) — [1. Two Sum](https://leetcode.com/problems/two-sum/)

- **This solution:** \(O(n)\) time, \(O(n)\) space (hash map of value → index).
- **Known optimal:** Is most optimal solution.

## [Leet0002AddTwoNumbers.java](leetcode/Leet0002AddTwoNumbers.java) — [2. Add Two Numbers](https://leetcode.com/problems/add-two-numbers/)

- **This solution:** \(O(\max(m,n))\) time, \(O(1)\) extra space (output list length not counted as extra).
- **Known optimal:** Is most optimal solution.

## [Leet0003LongestSubstringWithoutRepeatingCharacters.java](leetcode/Leet0003LongestSubstringWithoutRepeatingCharacters.java) — [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

- **This solution:** \(O(n)\) time, \(O(\min(n, |\Sigma|))\) space (last-seen map).
- **Known optimal:** Is most optimal solution.

## [Leet0004MedianOfTwoSortedArrays.java](leetcode/Leet0004MedianOfTwoSortedArrays.java) — [4. Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/)

- **This solution:** \(O(m+n)\) time, \(O(m+n)\) space (full merge into a new array).
- **Known optimal:** Binary search on partitions: \(O(\log(\min(m,n)))\) time, \(O(1)\) space — finds split points without materializing the merge.

## [Leet0005LongestPalindromicSubstring.java](leetcode/Leet0005LongestPalindromicSubstring.java) — [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)

- **This solution:** \(O(n^2)\) time, \(O(1)\) extra space (expand around centers).
- **Known optimal:** Manacher’s algorithm: \(O(n)\) time, \(O(n)\) space.

## [Leet0006ZigzagConversion.java](leetcode/Leet0006ZigzagConversion.java) — [6. Zigzag Conversion](https://leetcode.com/problems/zigzag-conversion/)

- **This solution:** \(O(n)\) time, \(O(n)\) space (row builders plus result).
- **Known optimal:** Is most optimal solution (output size is \(\Theta(n)\)).

## [Leet0007ReverseInteger.java](leetcode/Leet0007ReverseInteger.java) — [7. Reverse Integer](https://leetcode.com/problems/reverse-integer/)

- **This solution:** \(O(\log_{10}|x|)\) digit operations, \(O(1)\) space.
- **Known optimal:** Is most optimal solution.

## [Leet0008StringToIntegerAtoi.java](leetcode/Leet0008StringToIntegerAtoi.java) — [8. String to Integer (atoi)](https://leetcode.com/problems/string-to-integer-atoi/)

- **This solution:** \(O(n)\) time, \(O(1)\) space.
- **Known optimal:** Is most optimal solution.

## [Leet0009PalindromeNumber.java](leetcode/Leet0009PalindromeNumber.java) — [9. Palindrome Number](https://leetcode.com/problems/palindrome-number/)

- **This solution:** \(O(\log_{10} x)\) time, \(O(1)\) space.
- **Known optimal:** Is most optimal solution.

## [Leet0010RegularExpressionMatching.java](leetcode/Leet0010RegularExpressionMatching.java) — [10. Regular Expression Matching](https://leetcode.com/problems/regular-expression-matching/)

- **This solution:** \(O(mn)\) time, \(O(mn)\) space (2D DP).
- **Known optimal:** Same \(O(mn)\) time is standard; space can be reduced to \(O(n)\) with a single DP row (rolling array).

## [Leet0011ContainerWithMostWater.java](leetcode/Leet0011ContainerWithMostWater.java) — [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water/)

- **This solution:** \(O(n)\) time, \(O(1)\) space (two pointers).
- **Known optimal:** Is most optimal solution.

## [Leet0012IntegerToRoman.java](leetcode/Leet0012IntegerToRoman.java) — [12. Integer to Roman](https://leetcode.com/problems/integer-to-roman/)

- **This solution:** \(O(1)\) time (bounded input), \(O(1)\) extra space beyond the output string.
- **Known optimal:** Is most optimal solution.

## [Leet0013RomanToInteger.java](leetcode/Leet0013RomanToInteger.java) — [13. Roman to Integer](https://leetcode.com/problems/roman-to-integer/)

- **This solution:** \(O(n)\) time, \(O(1)\) space (symbol map).
- **Known optimal:** Is most optimal solution.

## [Leet0014LongestCommonPrefix.java](leetcode/Leet0014LongestCommonPrefix.java) — [14. Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/)

- **This solution:** \(O(S)\) time in the total length of all strings (horizontal scan), \(O(1)\) extra besides the running prefix reference.
- **Known optimal:** Is most optimal solution (same \(O(S)\) character work as vertical approaches in the worst case).

## [Leet0015ThreeSum.java](leetcode/Leet0015ThreeSum.java) — [15. 3Sum](https://leetcode.com/problems/3sum/)

- **This solution:** \(O(n^2)\) time after \(O(n \log n)\) sort, \(O(1)\) auxiliary beyond output.
- **Known optimal:** Is most optimal solution for the usual comparison-based \(O(n^2)\) approach.

## [Leet0016ThreeSumClosest.java](leetcode/Leet0016ThreeSumClosest.java) — [16. 3Sum Closest](https://leetcode.com/problems/3sum-closest/)

- **This solution:** \(O(n^2)\) time after sort, \(O(1)\) auxiliary.
- **Known optimal:** Is most optimal solution.

## [Leet0017LetterCombinationsOfAPhoneNumber.java](leetcode/Leet0017LetterCombinationsOfAPhoneNumber.java) — [17. Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/)

- **This solution:** \(O(4^n \cdot n)\) worst-case time bound tied to output size, \(O(n)\) recursion stack.
- **Known optimal:** Is most optimal solution (must list every combination).

## [Leet0018FourSum.java](leetcode/Leet0018FourSum.java) — [18. 4Sum](https://leetcode.com/problems/4sum/)

- **This solution:** \(O(n^3)\) time after sort, \(O(1)\) auxiliary beyond output.
- **Known optimal:** Is most optimal solution for the standard two-pointer reduction after sorting.

## [Leet0019RemoveNthNodeFromEndOfList.java](leetcode/Leet0019RemoveNthNodeFromEndOfList.java) — [19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)

- **This solution:** \(O(L)\) time, \(O(1)\) space (fast/slow pointers).
- **Known optimal:** Is most optimal solution.

## [Leet0020ValidParentheses.java](leetcode/Leet0020ValidParentheses.java) — [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)

- **This solution:** \(O(n)\) time, \(O(n)\) space (stack).
- **Known optimal:** Is most optimal solution.

## [Leet0021MergeTwoSortedLists.java](leetcode/Leet0021MergeTwoSortedLists.java) — [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)

- **This solution:** \(O(m+n)\) time, \(O(m+n)\) extra space — allocates new `ListNode`s instead of relinking.
- **Known optimal:** In-place merge by rewiring `next` pointers: \(O(m+n)\) time, \(O(1)\) extra space.

## [Leet0022GenerateParentheses.java](leetcode/Leet0022GenerateParentheses.java) — [22. Generate Parentheses](https://leetcode.com/problems/generate-parentheses/)

- **This solution:** Time proportional to Catalan-sized output, \(O(n)\) recursion stack.
- **Known optimal:** Is most optimal solution (must enumerate all valid strings).

## [Leet0023MergeKSortedLists.java](leetcode/Leet0023MergeKSortedLists.java) — [23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/)

- **This solution:** \(O(N \log k)\) time, \(O(k)\) heap space; also allocates new nodes for the result (extra \(O(N)\) if counted).
- **Known optimal:** \(O(N \log k)\) time is standard; reuse input nodes for \(O(1)\) extra besides the heap (or divide-and-conquer merge, \(O(N \log k)\) time, \(O(\log k)\) recursion stack).

## [Leet0024SwapNodesInPairs.java](leetcode/Leet0024SwapNodesInPairs.java) — [24. Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/)

- **This solution:** \(O(n)\) time, \(O(1)\) space.
- **Known optimal:** Is most optimal solution.

## [Leet0025ReverseNodesInKGroup.java](leetcode/Leet0025ReverseNodesInKGroup.java) — [25. Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group/)

- **This solution:** \(O(n)\) time, \(O(1)\) space.
- **Known optimal:** Is most optimal solution.

## [Leet0026RemoveDuplicatesFromSortedArray.java](leetcode/Leet0026RemoveDuplicatesFromSortedArray.java) — [26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)

- **This solution:** \(O(n)\) time, \(O(1)\) space (two pointers).
- **Known optimal:** Is most optimal solution.

## [Leet0027RemoveElement.java](leetcode/Leet0027RemoveElement.java) — [27. Remove Element](https://leetcode.com/problems/remove-element/)

- **This solution:** \(O(n)\) time, \(O(1)\) space.
- **Known optimal:** Is most optimal solution.

## [Leet0028FindTheIndexOfTheFirstOccurrenceInAString.java](leetcode/Leet0028FindTheIndexOfTheFirstOccurrenceInAString.java) — [28. Find the Index of the First Occurrence in a String](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/)

- **This solution:** \(O(n \cdot m)\) worst-case time (naive compare), \(O(1)\) space.
- **Known optimal:** KMP (or similar): \(O(n + m)\) time, \(O(m)\) space for the failure table.

## [Leet0029DivideTwoIntegers.java](leetcode/Leet0029DivideTwoIntegers.java) — [29. Divide Two Integers](https://leetcode.com/problems/divide-two-integers/)

- **This solution:** \(O(\log^2 |quotient|)\)-style steps via repeated doubling, \(O(1)\) space.
- **Known optimal:** Is most optimal solution among common bit-doubling approaches (logarithmic in the quotient).

## [Leet0030SubstringWithConcatenationOfAllWords.java](leetcode/Leet0030SubstringWithConcatenationOfAllWords.java) — [30. Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words/)

- **This solution:** \(O(|s| \cdot \text{numWords} \cdot \text{wordLen})\) in the worst case (rebuilds counts per start), \(O(\text{numWords})\) extra per window attempt.
- **Known optimal:** Sliding window with frequency maps: \(O(|s| \cdot \text{wordLen})\) or \(O(|s|)\) when word length is constant.

## [Leet0031NextPermutation.java](leetcode/Leet0031NextPermutation.java) — [31. Next Permutation](https://leetcode.com/problems/next-permutation/)

- **This solution:** \(O(n)\) time, \(O(1)\) space.
- **Known optimal:** Is most optimal solution.

## [Leet0032LongestValidParentheses.java](leetcode/Leet0032LongestValidParentheses.java) — [32. Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses/)

- **This solution:** \(O(n)\) time, \(O(n)\) space (stack of indices).
- **Known optimal:** \(O(n)\) time, \(O(1)\) space — two-pass scan without a stack.

## [Leet0033SearchInRotatedSortedArray.java](leetcode/Leet0033SearchInRotatedSortedArray.java) — [33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)

- **This solution:** \(O(\log n)\) time, \(O(1)\) space.
- **Known optimal:** Is most optimal solution.

## [Leet0034FindFirstAndLastPositionOfElementInSortedArray.java](leetcode/Leet0034FindFirstAndLastPositionOfElementInSortedArray.java) — [34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

- **This solution:** \(O(\log n)\) time, \(O(1)\) space (two binary searches).
- **Known optimal:** Is most optimal solution.

## [Leet0035SearchInsertPosition.java](leetcode/Leet0035SearchInsertPosition.java) — [35. Search Insert Position](https://leetcode.com/problems/search-insert-position/)

- **This solution:** \(O(\log n)\) time, \(O(1)\) space.
- **Known optimal:** Is most optimal solution.

## [Leet0036ValidSudoku.java](leetcode/Leet0036ValidSudoku.java) — [36. Valid Sudoku](https://leetcode.com/problems/valid-sudoku/)

- **This solution:** \(O(1)\) time (81 cells), \(O(1)\) extra space.
- **Known optimal:** Is most optimal solution.

## [Leet0037SudokuSolver.java](leetcode/Leet0037SudokuSolver.java) — [37. Sudoku Solver](https://leetcode.com/problems/sudoku-solver/)

- **This solution:** Backtracking; worst-case exponential in empty cells, \(O(1)\) board space.
- **Known optimal:** Backtracking is standard; Algorithm X / DLX can be faster in practice without better worst-case asymptotics for general CSP.

## [Leet0038CountAndSay.java](leetcode/Leet0038CountAndSay.java) — [38. Count and Say](https://leetcode.com/problems/count-and-say/)

- **This solution:** Iterative string growth; time/space grow with the length of successive terms.
- **Known optimal:** Is most optimal solution (must materialize the \(n\)-th term under the usual definition).

## [Leet0039CombinationSum.java](leetcode/Leet0039CombinationSum.java) — [39. Combination Sum](https://leetcode.com/problems/combination-sum/)

- **This solution:** Exponential search; depth bounded by target/min candidate.
- **Known optimal:** Is most optimal solution for full backtracking (output can be exponential).

## [Leet0040CombinationSumII.java](leetcode/Leet0040CombinationSumII.java) — [40. Combination Sum II](https://leetcode.com/problems/combination-sum-ii/)

- **This solution:** Same backtracking class as 39 with duplicate skipping after sort.
- **Known optimal:** Is most optimal solution for exhaustive listing.

## [Leet0041FirstMissingPositive.java](leetcode/Leet0041FirstMissingPositive.java) — [41. First Missing Positive](https://leetcode.com/problems/first-missing-positive/)

- **This solution:** \(O(n)\) time, \(O(1)\) space (index placement / cyclic sort).
- **Known optimal:** Is most optimal solution.

## [Leet0042TrappingRainWater.java](leetcode/Leet0042TrappingRainWater.java) — [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)

- **This solution:** \(O(n)\) time, \(O(1)\) space (two pointers).
- **Known optimal:** Is most optimal solution.

## [Leet0043MultiplyStrings.java](leetcode/Leet0043MultiplyStrings.java) — [43. Multiply Strings](https://leetcode.com/problems/multiply-strings/)

- **This solution:** \(O(mn)\) time, \(O(m+n)\) space (digit buffer + output).
- **Known optimal:** Grade-school multiply is \(O(mn)\); FFT-based methods are \(O(n \log n)\) for very large inputs but are not the usual LeetCode baseline.

## [Leet0044WildcardMatching.java](leetcode/Leet0044WildcardMatching.java) — [44. Wildcard Matching](https://leetcode.com/problems/wildcard-matching/)

- **This solution:** Greedy with `*` backtracking; \(O(n)\) typical, \(O(nm)\) worst-case time, \(O(1)\) space.
- **Known optimal:** DP \(O(nm)\) time, \(O(n)\) or \(O(m)\) rolling space — worst-case tight for general patterns; greedy is the common practical solution.

## [Leet0045JumpGameII.java](leetcode/Leet0045JumpGameII.java) — [45. Jump Game II](https://leetcode.com/problems/jump-game-ii/)

- **This solution:** \(O(n)\) time, \(O(1)\) space (greedy layers / BFS).
- **Known optimal:** Is most optimal solution.

## [Leet0046Permutations.java](leetcode/Leet0046Permutations.java) — [46. Permutations](https://leetcode.com/problems/permutations/)

- **This solution:** \(O(n \cdot n!)\) time, output size \(O(n \cdot n!)\) (swap-based backtracking).
- **Known optimal:** Is most optimal solution (must list all permutations).

## [Leet0047PermutationsII.java](leetcode/Leet0047PermutationsII.java) — [47. Permutations II](https://leetcode.com/problems/permutations-ii/)

- **This solution:** Same order as 46 with duplicate handling.
- **Known optimal:** Is most optimal solution relative to output size.

## [Leet0048RotateImage.java](leetcode/Leet0048RotateImage.java) — [48. Rotate Image](https://leetcode.com/problems/rotate-image/)

- **This solution:** \(O(n^2)\) time, \(O(1)\) extra space (transpose + flip).
- **Known optimal:** Is most optimal solution.

## [Leet0049GroupAnagrams.java](leetcode/Leet0049GroupAnagrams.java) — [49. Group Anagrams](https://leetcode.com/problems/group-anagrams/)

- **This solution:** Sort each string as key: \(O(N \cdot K \log K)\) time, \(O(NK)\) space.
- **Known optimal:** Character-count key (26 letters): \(O(N \cdot K)\) time, \(O(NK)\) space.

## [Leet0050PowXN.java](leetcode/Leet0050PowXN.java) — [50. Pow(x, n)](https://leetcode.com/problems/powx-n/)

- **This solution:** \(O(\log |n|)\) time, \(O(1)\) space (binary exponentiation).
- **Known optimal:** Is most optimal solution.

## [Leet0051NQueens.java](leetcode/Leet0051NQueens.java) — [51. N-Queens](https://leetcode.com/problems/n-queens/)

- **This solution:** Backtracking with column/diagonal flags; exponential in \(n\) worst case, \(O(n^2)\) board storage.
- **Known optimal:** Is most optimal solution for listing all valid boards (answer size is large).

## [Leet0052NQueensII.java](leetcode/Leet0052NQueensII.java) — [52. N-Queens II](https://leetcode.com/problems/n-queens-ii/)

- **This solution:** Same backtracking, count only — \(O(n!)\)-type worst time in naive search, \(O(n)\) recursion state.
- **Known optimal:** Is most optimal solution in the usual exhaustive backtracking sense.

## [Leet0053MaximumSubarray.java](leetcode/Leet0053MaximumSubarray.java) — [53. Maximum Subarray](https://leetcode.com/problems/maximum-subarray/)

- **This solution:** \(O(n)\) time, \(O(1)\) space (Kadane).
- **Known optimal:** Is most optimal solution.

## [Leet0054SpiralMatrix.java](leetcode/Leet0054SpiralMatrix.java) — [54. Spiral Matrix](https://leetcode.com/problems/spiral-matrix/)

- **This solution:** \(O(mn)\) time, \(O(mn)\) space for the output list (plus \(O(1)\) for boundaries).
- **Known optimal:** Is most optimal solution.

## [Leet0055JumpGame.java](leetcode/Leet0055JumpGame.java) — [55. Jump Game](https://leetcode.com/problems/jump-game/)

- **This solution:** \(O(n)\) time, \(O(1)\) space (farthest reachable index).
- **Known optimal:** Is most optimal solution.

## [Leet0056MergeIntervals.java](leetcode/Leet0056MergeIntervals.java) — [56. Merge Intervals](https://leetcode.com/problems/merge-intervals/)

- **This solution:** \(O(n \log n)\) time for sort, \(O(n)\) space for merged output.
- **Known optimal:** Is most optimal solution (sorting lower bound \(\Omega(n \log n)\) in the comparison model).

## [Leet0057InsertInterval.java](leetcode/Leet0057InsertInterval.java) — [57. Insert Interval](https://leetcode.com/problems/insert-interval/)

- **This solution:** \(O(n)\) time, \(O(n)\) space for output.
- **Known optimal:** Is most optimal solution.

## [Leet0058LengthOfLastWord.java](leetcode/Leet0058LengthOfLastWord.java) — [58. Length of Last Word](https://leetcode.com/problems/length-of-last-word/)

- **This solution:** \(O(n)\) time, \(O(1)\) space (reverse scan).
- **Known optimal:** Is most optimal solution.

## [Leet0059SpiralMatrixII.java](leetcode/Leet0059SpiralMatrixII.java) — [59. Spiral Matrix II](https://leetcode.com/problems/spiral-matrix-ii/)

- **This solution:** \(O(n^2)\) time, \(O(n^2)\) space for the matrix.
- **Known optimal:** Is most optimal solution.

## [Leet0060PermutationSequence.java](leetcode/Leet0060PermutationSequence.java) — [60. Permutation Sequence](https://leetcode.com/problems/permutation-sequence/)

- **This solution:** Cantor / factorial decomposition with `ArrayList.remove(index)`: \(O(n^2)\) time, \(O(n)\) space.
- **Known optimal:** Fenwick tree or balanced BST for order-statistics deletes: \(O(n \log n)\) time; careful \(O(n)\) construction is achievable.

## [Leet0061RotateList.java](leetcode/Leet0061RotateList.java) — [61. Rotate List](https://leetcode.com/problems/rotate-list/)

- **This solution:** \(O(n)\) time, \(O(1)\) space (length, ring, split).
- **Known optimal:** Is most optimal solution.

## [Leet0062UniquePaths.java](leetcode/Leet0062UniquePaths.java) — [62. Unique Paths](https://leetcode.com/problems/unique-paths/)

- **This solution:** DP \(O(mn)\) time, \(O(n)\) space (one row).
- **Known optimal:** Closed form \(\binom{m+n-2}{m-1}\): \(O(\min(m,n))\) time with multiplicative formula, \(O(1)\) extra space (ignoring big integers).

## [Leet0063UniquePathsII.java](leetcode/Leet0063UniquePathsII.java) — [63. Unique Paths II](https://leetcode.com/problems/unique-paths-ii/)

- **This solution:** \(O(mn)\) time, \(O(n)\) space (rolling array).
- **Known optimal:** Is most optimal solution for DP with obstacles.

## [Leet0064MinimumPathSum.java](leetcode/Leet0064MinimumPathSum.java) — [64. Minimum Path Sum](https://leetcode.com/problems/minimum-path-sum/)

- **This solution:** \(O(mn)\) time, \(O(n)\) space.
- **Known optimal:** Is most optimal solution (must consider each cell in the worst case).

## [Leet0065ValidNumber.java](leetcode/Leet0065ValidNumber.java) — [65. Valid Number](https://leetcode.com/problems/valid-number/)

- **This solution:** \(O(n)\) time, \(O(1)\) space (single pass with flags).
- **Known optimal:** Is most optimal solution.

## [Leet0066PlusOne.java](leetcode/Leet0066PlusOne.java) — [66. Plus One](https://leetcode.com/problems/plus-one/)

- **This solution:** \(O(n)\) worst-case time, \(O(1)\) extra space except when allocating a new array for all-9 inputs.
- **Known optimal:** Is most optimal solution.

## [Leet0067AddBinary.java](leetcode/Leet0067AddBinary.java) — [67. Add Binary](https://leetcode.com/problems/add-binary/)

- **This solution:** \(O(\max(m,n))\) time, \(O(\max(m,n))\) space for the result builder.
- **Known optimal:** Is most optimal solution.

## [Leet0068TextJustification.java](leetcode/Leet0068TextJustification.java) — [68. Text Justification](https://leetcode.com/problems/text-justification/)

- **This solution:** Linear in total characters, \(O(\text{output size})\) space.
- **Known optimal:** Is most optimal solution.

## [Leet0069SqrtX.java](leetcode/Leet0069SqrtX.java) — [69. Sqrt(x)](https://leetcode.com/problems/sqrtx/)

- **This solution:** \(O(\log x)\) time, \(O(1)\) space (binary search).
- **Known optimal:** Is most optimal solution (Newton’s method is also typical).

## [Leet0070ClimbingStairs.java](leetcode/Leet0070ClimbingStairs.java) — [70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)

- **This solution:** \(O(n)\) time, \(O(1)\) space (Fibonacci iteration).
- **Known optimal:** Matrix exponentiation or fast doubling: \(O(\log n)\) time, \(O(1)\) space — asymptotically faster than linear DP for very large \(n\).

## [Leet0101SymmetricTree.java](leetcode/Leet0101SymmetricTree.java) — [101. Symmetric Tree](https://leetcode.com/problems/symmetric-tree/)

- **This solution:** \(O(n)\) time, \(O(h)\) recursion stack (\(h\) = height).
- **Known optimal:** Is most optimal solution for time (must visit the tree in the worst case).

## [Leet0102BinaryTreeLevelOrderTraversal.java](leetcode/Leet0102BinaryTreeLevelOrderTraversal.java) — [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)

- **This solution:** \(O(n)\) time, \(O(n)\) space (queue; widest level up to \(\sim n/2\) nodes).
- **Known optimal:** Is most optimal solution.

## [Leet0103BinaryTreeZigzagLevelOrderTraversal.java](leetcode/Leet0103BinaryTreeZigzagLevelOrderTraversal.java) — [103. Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/)

- **This solution:** \(O(n)\) time, \(O(n)\) space (BFS queue + deque per level).
- **Known optimal:** Is most optimal solution (same traversal order as 102 with ordering tweak).

## [Leet0104MaximumDepthOfBinaryTree.java](leetcode/Leet0104MaximumDepthOfBinaryTree.java) — [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)

- **This solution:** \(O(n)\) time, \(O(h)\) recursion stack.
- **Known optimal:** Is most optimal solution.

## [Leet0105ConstructBinaryTreeFromPreorderAndInorderTraversal.java](leetcode/Leet0105ConstructBinaryTreeFromPreorderAndInorderTraversal.java) — [105. Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)

- **This solution:** \(O(n)\) time with hash map of inorder indices, \(O(n)\) space for map plus \(O(h)\) recursion stack.
- **Known optimal:** Is most optimal solution.
