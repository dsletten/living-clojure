(ns wonderland-number.finder)

(defn wonderland-number []
  ;;
  ;;    This is an interesting mathematical puzzle. Not sure what it has to do with Clojure though...
  ;;
  ;;    The wording is a little unclear in the book:
  ;;    
  ;;       Wonderland is a strange place. There is a wonderland number that is also quite strange.
  ;;        You must find a way to generate this wonderland number.
  ;;       - It has six digits
  ;;       - If you multiply it by 2,3,4,5, or 6, the resulting number has all the same digits in at as
  ;;         the original number. The only difference is the position that they are in.
  ;;
  ;;   The phrase "has all the same digits" is vague. The numbers 123456 and 1234561 have all of the same
  ;;   digits, but the 2nd has more than 6 digits.
  ;;
  ;;   My proof merely relies on the products of the wonderland number (✕ 2, 3, 4, 5, 6) as being permutations
  ;;   of the digits of the original number. In particular, all products consist of 6-digit numbers. This
  ;;   constraint is significant below, implying that 6n is not actually a 7-digit number that consists of the
  ;;   digits of the wonderland number along with one duplicate. In addition, I think that I have to assume that
  ;;   the digits are unique: 6 distinct numbers.
  ;;
  ;;   Let the number in question be n, with the digits represented as ABCDEF.
  ;;   Let S = {A, B, C, D, E, F}. Then n, 2n, 3n, 4n, 5n, and 6n are all composed of the elements of S.
  ;;   
  ;;   The problem states that the wonderland number has 6 digits, thus: 10⁵ ≤ n < 10⁶. Furthermore, if 6n also
  ;;   has 6 digits, this implies 6n < 10⁶ as well. Consequently, n < 10⁶/6 = 166666.6...
  ;;
  ;;   This means that A = 1. (Assuming that a leading 0 is disqualified as it produces a 5-digit number.)
  ;;
  ;;   The next easiest digit to examine is F in the one's place since there is no carry involved when multiplying.
  ;;   If we look at the one's digit of the products (mod 10) of 1, 2, ..., 6 and the numbers 0-9 we have this table:
  ;;
  ;;   1 ✕ 0  1  2  3  4  5  6  7  8  9   <- Candidates for F
  ;;   2 ✕ 0  2  4  6  8  0  2  4  6  8      (mod (* m n) 10)
  ;;   3 ✕ 0  3  6  9  2  5  8  1  4  7
  ;;   4 ✕ 0  4  8  2  6  0  4  8  2  6
  ;;   5 ✕ 0  5  0  5  0  5  0  5  0  5
  ;;   6 ✕ 0  6  2  8  4  0  6  2  8  4
  ;;
  ;;   This means that multiplying F by each factor 2, ..., 6 will generate the digits in the corresponding column in
  ;;   the unit's place of the product. For example, assume that F = 3: 1BCDE3. Then 2n = .....6, 3n = .....9,
  ;;   and so on.
  ;;   
  ;;   If we assume that there are no repeat digits, then F cannot be 1 when A is too. Furthermore, 3 and 9 generate
  ;;   6 distinct values not including 1. But we know that A is 1, so these cannot be the value of F either, otherwise
  ;;   we would be dealing with 7 digits.
  ;;   
  ;;   Now suppose that F were 2, 4, 6, or 8. This would constrain S = {0, 1, 2, 4, 6, 8} given the table above and
  ;;   the fact that A = 1. However, we can see that none of the values {0, 2, 4, 6, 8} is then a candidate for B:
  ;;       • If B = 0, then n = 10CDEF so that 3n = 3xxxxx since there is no carry from the product of 3 ✕ 0. And
  ;;         3 is not a possible element of S.
  ;;       • If B = 2, then n = 12CDEF. This leads to 6n = 7xxxxx. The leading 2 digits would be 72.... with a possible
  ;;         carry from the product 6 ✕ C. But even if C were 8, this carry would be at most 4 (possibly 5). This would
  ;;         leave the leading digit as 7, which is not an element of S.
  ;;       • If B = 4 and n = 14CDEF, then 5n = 7xxxxx. Again it is not possible for a carry to change the leading 7.
  ;;       • If B = 6 with n = 16CDEF, then 2n = 3xxxxx. The leading 2 digits without a carry would be 32...., and no
  ;;         product of 2 could produce a carry large enough to affect the leading 3.
  ;;       • If B = 8, then n = 18CDEF. Again this produces 2n = 3xxxxx and no carry capable of affecting the leading 3.
  ;;
  ;;   We see now that 1, 2, 3, 4, 6, 8, and 9 cannot serve as values for F. 0 is a little trickier to eliminate.
  ;;   Assume that F = 0. Then we are essentially dealing with a 5-digit number in the remaining digits. The table above
  ;;   is still relevant, but we would be considering candidates for E instead.
  ;;
  ;;   This allows us to eliminate 1, 2, 3, 4, 6, 8, and 9 as possible values for E by the same reasoning. In addition,
  ;;   we see that E = 7 would produce 6 distinct values in the 10's place when we only have 5 digits left to consider.
  ;;   On the other hand, if E were 5, that would generate duplicate 0's in the 10's place. Consequently, there is no
  ;;   digit that fits E when F is 0, so F cannot be 0 either.
  ;;
  ;;   Finally, suppose that F = 5. This means that 0 would be a member of S since 0 would be the one's digit for
  ;;   2n, 4n, and 6n. It turns out that the above logic lets us conclude that no digit can, in fact, be 0.
  ;;
  ;;   First we eliminate the trivial cases. The leading digit A cannot be 0 as argued above. Furthermore, even with
  ;;   no additional constraints on S, if B were 0, then the leading digit of the products 2n, 3n, ..., 6n would be
  ;;   2xxxxx, ..., 6xxxxx. Consequently, the leading digits alone would constitute the set {1, 2, 3, 4, 5, 6}. But
  ;;   with B = 0, there would be 7 digits in the various permutations.
  ;;
  ;;   Now consider 0 in any of the remaining digits C, D, E, or F. This means that the digit to its left would not
  ;;   be affected by a carry in any of the products 2n, 3n, ..., 6n. We have already analyzed these cases for F and
  ;;   E, but the arguments stand for C and D as well. 1 would produce a duplicate with A. 2, 4, 6, 8 can't produce
  ;;   a viable candidate for B. 3, 9, and 7 yield too many distinct values, and 5 results in duplicate 0's.
  ;;
  ;;   Thus, if no digit can be 0, then F cannot be 5.
  ;;
  ;;   This leaves 7 as the value for F. And this implies that S = {7, 4, 1, 8, 5, 2}, the values that the unit's
  ;;   place assumes for each of the products: n = 1BCDE7, 2n = .....4, 3n = .....1, . . ., 6n = .....2
  ;;
  ;;   Now if we look at B, we already know that it is at most 6. This leaves only 2, 4, and 5 as candidates.
  ;;   But if we assume that B = 2, when n is multiplied by 5 we have 12CDEF ✕ 5 = 60xxxx with a possible carry
  ;;   from multiplying C by 5. But the largest possible value to carry from multiplying 2 digits is 8 (9 ✕ 9). So there
  ;;   is no way to carry a large enough value to affect the leading 6 (we can't turn it into a 7). We know that
  ;;   6 is not a valid digit, so B cannot be 2.
  ;;
  ;;   Likewise, assume B = 5. Multiplying n by 6 yields: 15CDEF ✕ 6 = 90xxxx. By the same reasoning the carry from
  ;;   the right won't be enough to affect the leading 9 (which would produce a 7-digit number anyway...). Thus,
  ;;   B is not 5. Consequently, B = 4.
  ;;
  ;;   We now know that n = 14CDE7, with 2, 8, and 5 remaining for the unidentified digits. But suppose that E = 8.
  ;;   Then n = 14CD87 and 3n = ....61, and 6 is not legal. Again, suppose E = 2: n = 14CD27. But 4n = ....08. Since
  ;;   0 is not an element of S, it must be the case that E = 5.
  ;;
  ;;   So now n = 14CD57 with 2 and 8 remaining. Either n = 142857 or n = 148257. But we find that 2 ✕ 148257 = 444771.
  ;;
  ;;   The answer then is that n is:
  142857)
