;;;;
;;;;
;;;;   Clojure feels like a general-purpose language beamed back
;;;;     from the near future.
;;;;   -- Stu Halloway
;;;;
;;;;   Name:               week2.clj
;;;;
;;;;   Started:            Wed Mar  3 23:19:03 2021
;;;;   Modifications:
;;;;
;;;;   Purpose:
;;;;
;;;;
;;;;
;;;;   Calling Sequence:
;;;;
;;;;
;;;;   Inputs:
;;;;
;;;;   Outputs:
;;;;
;;;;   Example:
;;;;
;;;;   Notes:
;;;;
;;;;

(ns week2
  (:use clojure.test
        [clojure.pprint :only (cl-format)])
  (:import))

;;;
;;;    Day 1 210303
;;;
;;;    https://www.4clojure.com/problem/26
;;;    Fibonacci
;;;    Old notes pg. 126
(fn [n] (take n (map first (iterate (fn [[a b]] [b (+ a b)]) [1 1]))))

;;;
;;;    https://www.4clojure.com/problem/29
;;;
(fn [s] (apply str (filter #(Character/isUpperCase %) s)))

;;;
;;;    https://www.4clojure.com/problem/48
;;;
(reduce * (map inc (range 3)))

;;;
;;;    https://www.4clojure.com/problem/42
;;;
(fn [n] (reduce * (take n (iterate inc 1))))

;;;
;;;    https://www.4clojure.com/problem/52
;;;
;(vector c e)

;;;
;;;    Day 2 210304
;;;
;;;    https://www.4clojure.com/problem/51
;;;
(take 5 (iterate inc 1))

;;;
;;;    https://www.4clojure.com/problem/83
;;;
(fn [& more] (and (true? (some true? more)) (not-every? true? more))) ; ????????????
(fn [& more] (= (set more) #{true false}))

;;;
;;;    https://www.4clojure.com/problem/66
;;;
(fn gcd [m n] (if (zero? n) (Math/abs m) (recur n (mod m n))))

;;;
;;;    Day 3 210305
;;;
;;;
;;;    https://www.4clojure.com/problem/107
;;;
((fn [n] (fn [x] (loop [base x
                        i n
                        result 1]
                   (cond (zero? i) result
                         (even? i) (recur (* base base) (/ i 2) result)
                         :else (recur base (dec i) (* result base)))) )) 3)

(defn pow [x n]
  (loop [base x
         i n
         result 1]
    (cond (zero? i) result
          (even? i) (recur (* base base) (/ i 2) result)
          :else (recur base (dec i) (* result base)))) )

;;;
;;;    https://www.4clojure.com/problem/90
;;;
(fn [as bs] (set (for [a as b bs] [a b])))

;;;
;;;    Day 4 210307
;;;
;;;
;;;    https://www.4clojure.com/problem/88
;;;
(fn [a b] (clojure.set/union (clojure.set/difference a b) (clojure.set/difference b a)))

;;;
;;;    https://www.4clojure.com/problem/100
;;;    https://en.wikipedia.org/wiki/Least_common_multiple
;;;
;;;    This problem is ill-defined. It states: "Your function should accept a variable number of positive integers or ratios."
;;;    1. LCM is only defined for integers, not the entire set of rational numbers.
;;;    2. LCM is reasonable for negative integers, but ABS should be used.
;;;       However, Clojure does not behave properly with absolute values of ratios (See 1.):
;;;       (Math/abs (/ 2 3)) => Execution error (IllegalArgumentException) No matching method abs found taking 1 args
;;;
;;;    SBCL:
;;;    * (lcm 1/3 2/5) => TYPE-ERROR The value 1/3 is not of type INTEGER
;;;    * (lcm -4 12 -15) => 60
;;;
;;;    4clojure answer:
(fn [& ns] (reduce (fn [a b] (let [gcd (loop [m a n b] (if (zero? n) m (recur n (mod m n))))] (/ (* a b) gcd))) ns))
;;;    Should be:
(fn [& ns] (reduce (fn [a b] (let [gcd (loop [m a n b] (if (zero? n) (Math/abs m) (recur n (mod m n))))] (/ (Math/abs (* a b)) gcd))) ns))

;;;
;;;    Day 5 210308
;;;
;;;
;;;    https://www.4clojure.com/problem/88
;;;
(defn pascal [n]
  (if (zero? n)
    [1]
    (let [p (pascal (dec n))]
      (vec (map + (cons 0 p) (conj p 0)))) ))

(defn pascal [n]
  (loop [i 0
         result [1]]
    (if (== i n)
      result
      (recur (inc i) (vec (map + (cons 0 result) (conj result 0)))) )))

(defn pascal [n]
  (first (take 1 (drop n (iterate #(vec (map + (cons 0 %) (conj % 0))) [1])))) )

(defn pascal [n]
  (nth (iterate #(vec (map + (cons 0 %) (conj % 0))) [1]) n))

(defn pascal [n]
  (if (== n 1)
    [1]
    (let [p (pascal (dec n))]
      (vec (map + (cons 0 p) (conj p 0)))) ))

(fn pascal [n]
  (if (== n 1)
    [1]
    (let [p (pascal (dec n))]
      (vec (map + (cons 0 p) (conj p 0)))) ))

(fn [n] (first (take 1 (drop (dec n) (iterate #(vec (map + (cons 0 %) (conj % 0))) [1])))) )
(fn [n] (nth (iterate #(vec (map + (cons 0 %) (conj % 0))) [1]) (dec n)))

;;;  !!!!
;((fn [n] (nth (iterate #(vec (map +' (cons 0 %) (conj % 0))) [1]) (dec n))) 1000)
