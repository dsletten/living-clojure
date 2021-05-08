(ns fox-goose-bag-of-corn.puzzle
  (:use clojure.test
        clojure.set
        [clojure.pprint :only (cl-format)]))

;;;
;;;    Some interesting solutions (A*, etc...)
;;;
;;; https://github.com/davidpham87/wonderland-clojure-katas/tree/my-training/fox-goose-bag-of-corn
;;; https://github.com/Rigo85/wonderland-clojure-katas/tree/master/fox-goose-bag-of-corn
;;; https://github.com/saicheong/wonderland-clojure-katas/tree/master/fox-goose-bag-of-corn
;;;  [astar-search "0.2"]
;;;  [astar.core :refer [route]]))
;;;  (route graph dist (get-h end) start end))
;;; https://github.com/raydel95/wonderland-clojure-katas/tree/master/fox-goose-bag-of-corn
;;; https://github.com/manuel-colmenero/wonderland-clojure-katas/tree/master/fox-goose-bag-of-corn
;;; https://github.com/kuchichan/wonderland-clojure-katas/tree/master/fox-goose-bag-of-corn

(defrecord State [left boat right])

;;;
;;;    This is merely a convenience function to keep the tests happy.
;;;    States are `State` records, which are more convenient to work with.
;;;    However, the test suite expects these vectors of vectors to represent actions.
;;;    
(defn convert-state [state]
  [(vec (:left state)) (vec (cons :boat (vec (:boat state)))) (vec (:right state))])

(def start-state (State. #{:fox :goose :corn :you} #{} #{}))

(defn success? [state]
  (empty? (:left state))
  (empty? (:boat state))
  (= (:right state) #{:corn :fox :goose :you}))

(defn legal-boat? [boat]
  (and (or (empty? boat)
           (contains? boat :you))
       (<= (count boat) 2)))

(deftest test-legal-boat?
  (is (legal-boat? #{}))
  (is (legal-boat? #{:goose :you}))
  (is (not (legal-boat? #{:goose})))
  (is (not (legal-boat? #{:goose :you :fox}))))

(defn legal-bank? [bank]
  (cond (contains? bank :you) true
        (subset? #{:goose :fox} bank) false
        (subset? #{:goose :corn} bank) false
        :else true))

(deftest test-legal-bank?
  (is (legal-bank? #{:fox :goose :corn :you}))
  (is (legal-bank? #{:fox :corn}))
  (is (not (legal-bank? #{:fox :goose})))
  (is (not (legal-bank? #{:goose :corn}))) )

(defn located-at? [location item]
  (contains? location item))

(defn on-bank?
  ([bank] (on-bank? bank :you))
  ([bank item] (located-at? bank item)))

(defn on-boat?
  ([boat] (on-boat? boat :you))
  ([boat item] (located-at? boat item)))

(defn board-boat [state]
  (cond (on-boat? (:boat state)) (throw (IllegalStateException. "You are already on the boat."))
        (on-bank? (:left state)) (State. (disj (:left state) :you) (conj (:boat state) :you) (:right state))
        :else (State. (:left state) (conj (:boat state) :you) (disj (:right state) :you))))

(defn exit-boat [state bank]
  (if (not (on-boat? (:boat state)))
    (throw (IllegalStateException. "You are not on the boat."))
    (update (update state :boat disj :you) bank conj :you)))

(defn unload-boat [state]
  (if (on-bank? (:left state))
    (State. (union (:left state) (:boat state)) #{} (:right state))
    (State. (:left state) #{} (union (:right state) (:boat state)))) )

(defn load-boat [state item]
  (if (on-bank? (:left state))
    (State. (disj (:left state) item) (conj (:boat state) item) (:right state))
    (State. (:left state) (conj (:boat state) item) (disj (:right state) item))))

;;;
;;;    TODO: For completeness, should check for legal-boat? here when returning item...
;;;    
(defn successful-crossing [boarded-state unloaded-state & {:keys [return-item]}]
  (if return-item
    [boarded-state unloaded-state (board-boat (load-boat unloaded-state return-item)) (unload-boat (exit-boat (board-boat (load-boat unloaded-state return-item)) :left))]
    [boarded-state unloaded-state (board-boat unloaded-state) (exit-boat (board-boat unloaded-state) :left)]))

(defn fail [& msg-args]
  (apply cl-format true msg-args)
  false)

(defn cross
  "Cross the river from the left bank, transporting `item` to the right bank.
  Unload `item` and return to the left bank with any other item that would be
  in conflict if it remained on the right bank with `item`. Finished when nothing
  remains on left bank."
  [item state]
  (cl-format true "Crossing: ~A ~A~%" item (convert-state state))
  (let [boarded-state (board-boat (load-boat state item))]
    (cl-format true "Boarded: ~A~%" (convert-state boarded-state))
    (cond (not (legal-bank? (:left boarded-state))) (fail "Illegal state: ~A~%" (:left boarded-state))
          (not (legal-boat? (:boat boarded-state))) (fail "Illegal boat: ~A~%" (:boat boarded-state))
          :else (let [unloaded-state (unload-boat (exit-boat boarded-state :right))]
                  (cl-format true "Unloaded: ~A~%" (convert-state unloaded-state))
                  (cond (success? unloaded-state) [boarded-state unloaded-state]
                        (legal-bank? (disj (:right unloaded-state) :you)) (successful-crossing boarded-state unloaded-state)
                        :else (let [old-item (first (disj (:right unloaded-state) :you item))] ; Assumes only 2 items on right bank? I.e., simply removing one (old) item resolves conflict?
                                (successful-crossing boarded-state unloaded-state :return-item old-item)))) )))
          
;;
;;    Successful attempt - reset `attempts`? But must consider items 'returned' from right bank (due to conflict)
;;    

(defn attempt-crossing 
  ([initial-state] (attempt-crossing initial-state #{} []))
  ([state attempts success]
   (if (success? state)
     success
     (let [items (difference (disj (:left state) :you) attempts)
           item (first items)]
       (if (empty? items)
         (fail "I give up. We tried everything: ~A~%" attempts)
         (let [attempt (cross item state)]
           (if (false? attempt)
             (recur state (conj attempts item) success)
             (let [new-state (last attempt)
                   returns (difference (disj (:left new-state) :you) (disj (:left state) :you))]
               (if (empty? returns)
                 (recur new-state #{} (concat success (map convert-state attempt)))
                 (recur new-state (set returns) (concat success (map convert-state attempt)))) )))) ))))

(defn river-crossing-plan []
  (cons (convert-state start-state)
        (attempt-crossing start-state)))
