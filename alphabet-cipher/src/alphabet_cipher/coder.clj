(ns alphabet-cipher.coder
  (:require [clojure.string :as str]))

(defn char->offset [ch]
  (- (int ch) (int \a)))

(defn offset->char [i]
  (char (+ i (int \a))))

(defn encode-char [row col]
  (offset->char (mod (+ (char->offset row) (char->offset col)) 26)))

(defn decode-char [row ch]
  (offset->char (mod (- (char->offset ch) (char->offset row)) 26)))

(defn translate [keyword message f]
  (apply str (map f (cycle (str/lower-case keyword)) (str/lower-case message))))  

(defn encode [keyword clear]
  (translate keyword clear encode-char))

(defn decode [keyword encoded]
  (translate keyword encoded decode-char))

;;;
;;;    test-cycle and find-prefix are an ad hoc solution.
;;;    Take a look at actual work on the issue: https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm
;;;    https://github.com/raydel95/wonderland-clojure-katas/tree/master/alphabet-cipher
;;;    
(defn test-cycle [s1 s2] ; This is just every? over 2 sequences!
  (loop [p s1
         q s2]
    (cond (or (empty? p) (empty? q)) true
          (= (first p) (first q)) (recur (rest p) (rest q))
          :else false)))

(defn find-prefix [s]
  (loop [n 1]
    (let [head (take n s)
          tail (take n (drop n s))]
      (cond (and (= head tail) (test-cycle (cycle head) s)) (apply str head)
            (empty? tail) nil
            :else (recur (inc n)))) ))

(defn decipher [encoded message]
  (find-prefix (decode message encoded)))
