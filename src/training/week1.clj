;;;;
;;;;
;;;;   Clojure feels like a general-purpose language beamed back
;;;;     from the near future.
;;;;   -- Stu Halloway
;;;;
;;;;   Name:               week1.clj
;;;;
;;;;   Started:            Fri Feb 26 23:43:43 2021
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
;;;;   Notes: Do not emulate any of these solutions!!!!
;;;;
;;;;
;;;;(doseq [elt [37 57 68 71 72 145]] (cl-format true ";;;~%;;;    https://www.4clojure.com/problem/~D~%;;;~2%" elt))
;;;;

(ns week1
  (:use clojure.test
        [clojure.pprint :only (cl-format)])
  (:import))

;;;
;;;    https://www.4clojure.com/problem/1
;;;    
(= (zero? (mod (count (reduce (fn [m elt] (if (> (count elt) (count m)) elt m)) ["Is" "this" "not" "pung?"])) 5)))

;;;
;;;    https://www.4clojure.com/problem/2
;;;    
(mod 69 5)

;;;
;;;    Day 1 210226
;;;
;;;    https://www.4clojure.com/problem/3
;;;    
(apply str (map char '(72 69 76 76 79 32 87 79 82 76 68)))

;;;
;;;    https://www.4clojure.com/problem/4
;;;
(first (map (comp keyword str) (seq "abc"))) (keyword 'b) (keyword "c")

;;;
;;;    https://www.4clojure.com/problem/5
;;;
(apply concat (map reverse '((2 1) (4 3))))

;;;
;;;    https://www.4clojure.com/problem/6
;;;
(first (keys {:a 1 :b 2})) (last [:c :a :b]) (keyword (str (char 99)))

;;;
;;;    https://www.4clojure.com/problem/7
;;;
(map mod '(1 2 3 4) (repeat 5))

;;;
;;;    https://www.4clojure.com/problem/8
;;;
(into #{} (map (comp keyword str) (take-while (fn [ch] (pos? (compare \e ch))) (drop-while #(= % \space) (sort "the quick brown fox jumps over the lazy dog")))))

;;;
;;;    https://www.4clojure.com/problem/9
;;;
((fn [x] (+ (mod x 7) (mod x 9))) 64)

;;;
;;;    https://www.4clojure.com/problem/10
;;;
(int (+ (Math/pow (Math/log10 (* 100 100)) 2) (Math/sqrt 16)))

;;;
;;;    https://www.4clojure.com/problem/11
;;;
(select-keys {:x 1 :y 9 :z 5 :b 2} [:b])

;;;
;;;    https://www.4clojure.com/problem/12
;;;
(nth (take 99 (repeat 3)) 17)

;;;
;;;    Day 2 210227
;;;   
;;;    https://www.4clojure.com/problem/13
;;;
(take 3 (drop 1 (map (partial * 10) (iterate inc 1))))

;;;
;;;    https://www.4clojure.com/problem/14
;;;
((fn [pung] (* 2 (count pung))) "pung")

;;;
;;;    https://www.4clojure.com/problem/15
;;;
(fn [x] (/ (* x 10) 5))

;;;
;;;    https://www.4clojure.com/problem/16
;;;
((fn [name] (str ((fn [lang] ({:en "Hello" :sp "Hola" :es "Saluton"} lang)) ({"Dave" :en "Jenn" :en "Rhea" :en} name)) ", " name "!")) "Jenn")

;;;
;;;    https://www.4clojure.com/problem/17
;;;
(map (fn [x] (int (/ (Math/log x) (Math/log 3)))) [729 2187 6561])

;;;
;;;    https://www.4clojure.com/problem/18
;;;
(map (fn [ch] (/ (int ch) 9)) [\6 \?])

;;;
;;;    https://www.4clojure.com/problem/35
;;;
(/ (int \?) 9)

;;;
;;;    https://www.4clojure.com/problem/36
;;;
[x (int (/ (Math/log 2097152) (Math/log 2) 3)) y (quot x 2) z (mod x 6)]

;;;
;;;    Day 2 210227
;;;   
;;;    https://www.4clojure.com/problem/37
;;;
(apply str (take 3 (map (comp char (partial + (int \A))) (iterate inc 0))))

;;;
;;;    https://www.4clojure.com/problem/57
;;;
(reverse (take 5 (iterate inc 1)))

;;;
;;;    https://www.4clojure.com/problem/68
;;;
(take 5 (map (partial mod 15) (iterate inc 8)))

;;;
;;;    https://www.4clojure.com/problem/71
;;;
(fn [_] 5)

;;;
;;;    https://www.4clojure.com/problem/72
;;;
(fn [xs] (Integer/parseInt (Integer/toBinaryString (count xs))))

;;;
;;;    https://www.4clojure.com/problem/145
;;;
(map (comp (fn [n] (- n (int \A))) inc int first) (re-seq #"\S+" "At Each Inconceivable Moment Question Unanticipated Yearnings ] albert einstein"))

;;;
;;;    Day 4 210301
;;;
;;;    https://www.4clojure.com/problem/20
;;;
(comp second reverse)

;;;
;;;    https://www.4clojure.com/problem/24
;;;
(partial apply +)

;;;
;;;    https://www.4clojure.com/problem/25
;;;
(partial filter odd?)

;;;
;;;    https://www.4clojure.com/problem/27
;;;
(fn [s] (= (seq s) (reverse s)))

;;;
;;;    https://www.4clojure.com/problem/32
;;;
(partial mapcat #(vector % %))
