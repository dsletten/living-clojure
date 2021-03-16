;;;;
;;;;
;;;;   Clojure feels like a general-purpose language beamed back
;;;;     from the near future.
;;;;   -- Stu Halloway
;;;;
;;;;   Name:               week3.clj
;;;;
;;;;   Started:            Wed Mar 10 17:52:28 2021
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

(ns week3
  (:use clojure.test
        [clojure.pprint :only (cl-format)])
  (:import))

;;;
;;;    Day 1 210310
;;;    Not thrilled with either of these...
;;;
;;;    https://www.4clojure.com/problem/95
;;;
(fn tree? [obj]
  (cond (nil? obj) true
        (not (coll? obj)) false
        (not (== (count obj) 3)) false
        :otherwise (let [[val left right] obj]
                     (and (tree? left)
                          (tree? right)))) )

;;;
;;;    https://www.4clojure.com/problem/96
;;;
(fn sym?
  ([tree] (cond (nil? tree) true
                (not (coll? tree)) false
                (not (== (count tree) 3)) false
                :otherwise (let [[val left right] tree]
                             (sym? left right))))
  ([left right]
   (cond (and (nil? left) (nil? right)) true
         (not (and (coll? left) (coll? right))) false
         (not (== (count left) (count right) 3)) false
         :otherwise (let [[lval lleft lright] left
                          [rval rleft rright] right]
                      (and (= lval rval)
                           (sym? lleft rright)
                           (sym? lright rleft))))))

;;;
;;;    Day 2 210311
;;;
;;;    https://www.4clojure.com/problem/46
;;;
(fn [f] (fn [& args] (apply f (reverse args))))

;;;
;;;    https://www.4clojure.com/problem/44
;;;
(fn rotate [n seq]
  (cond (pos? n) (let [m (mod n (count seq))]
                   (concat (drop m seq) (take m seq)))
        (neg? n) (rotate (+ (count seq) n) seq)
        :else seq))
    
;;;
;;;    Day 3 210312
;;;
;;;    https://www.4clojure.com/problem/43
;;;    Any better way...???
;;;
;(defn segregate [seq n]
(fn [seq n]
  (loop [result (take n (repeat []))
         s seq]
    (if (empty? s)
      result
      (recur (map conj result (take n s)) (drop n s)))) )

;;;
;;;    https://www.4clojure.com/problem/50
;;;
(fn [s] (vals (group-by class s)))

;;;
;;;    Day 4 210315
;;;
;;;    https://www.4clojure.com/problem/67

(defn prime? [n]
  (cond (== n 1) false
        (== n 2) true
        (even? n) false
        :else (let [limit (int (Math/sqrt n))]
                (loop [divisor 3]
                  (cond (> divisor limit) true
                        (zero? (mod n divisor)) false
                        :else (recur (+ divisor 2)))) )))

(fn [m] (take m (remove false? (map (fn [n]
                                      (cond (== n 1) false
                                            (== n 2) n
                                            (even? n) false
                                            :else (let [limit (int (Math/sqrt n))]
                                                    (loop [divisor 3]
                                                      (cond (> divisor limit) n
                                                            (zero? (mod n divisor)) false
                                                            :else (recur (+ divisor 2)))) )))
                                    (iterate inc 2)))) )

