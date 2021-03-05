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
;;;
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
