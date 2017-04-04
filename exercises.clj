(ns clojureexercisescis554.core)
;; (ns user (:use clojure.test))


(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

;test basic function
(defn double-num [n]
  (* n 2))

(double-num 5)

;;;;;;FIRST GROUP;;;;;

;(atomic? v)
;Returns true if v is not any kind of a collection (check with coll?), and false otherwise.
(defn atomic? [v]
  (if (not (coll? v ))
  true
  false))

(atomic? 5)
(atomic? nil)
(atomic? '[5 3])

;(member? x lst)
;Returns a true value if x is in lst.
(defn member? [x lst]
  (if (empty? lst)
    false
  (if (= x (first lst))
    true
  (member? x (rest lst)))))

(member? 5 [1 2 5 6])
(member? 5 [1 2 6])


;(my-count lst)
;Returns the number of "top level" elements in lst. For example, (a (b c (d e)) f) has 3 top level elements
;(b c (d e)) is a single top-level element. (a b () d) has 4 top-level elements.
(defn my-count [lst]
  (if (empty? lst)
    0
    (+ 1 (my-count (rest lst)))))

(my-count '(a (b c (d e)) f))


;(append lst1 lst2)
;Combine lst1 and lst2 into a single list. For example, if lst1 is (:a :b :c) and lst2 is (1 (2 3))
;the result should be (:a :b :c 1 (2 3)).
(defn append [lst1 lst2]
    (if (empty? lst1) lst2
    (if-not (empty? lst1)
    (cons
      (first lst1)
      (append (rest lst1) lst2)) )))

;;   (cons lst1 lst2))


(append '(:a :b :c) '(1 (2 3)))


;(zip lst1 lst2)
;Combine corresponding elements of lst1 and lst2 into a list of two-element lists
;stop when you run out of elements in either list. For example, if lst1 is (:a :b :c) and lst2 is (1 (2 3) 4 5)
;the result should be ((:a 1) (:b (2 3)) (:c 4)).
(defn zip [lst1 lst2]
  (if
    (or (empty? lst1) (empty? lst2) )
    ()
  (cons
    (concat (list (first lst1)) (list (first lst2) ))
            (zip (rest lst1) (rest lst2)))
            )
    )



(zip '(:a :b :c) '(1 (2 3) 4 5))


;(lookup key list-of-pairs)
;Given an S-expression key and a list of (key value) pairs, return the value that corresponds to the key
;or nil if there is no such pair. You can assume that there is only one such pair.

(defn lookup [key list-of-pairs]
  (cond
    (empty? list-of-pairs) nil
    (= key (first (first list-of-pairs))) (second (first list-of-pairs))
       :else (lookup key (rest list-of-pairs))))

(lookup 5 '((5, 5) (5, 3)))


;(my-merge lst1 lst2)
;Given two lists of integers, where each list is in ascending order, merge them into a single list that is also in ascending order
;For example, if lst1 is (3 7 12 19 19 25 30) and lst2 is (4 7 10 12 20), the result should be (3 4 7 7 10 12 12 19 19 20 25 30).
(defn my-merge [lst1 lst2]
  (cond
    (empty? lst1) lst2
    (empty? lst2) lst1
  (< (first lst1) (first lst2)) (cons (first lst1) (my-merge (rest lst1) lst2))
    :else (cons (first lst2) (my-merge lst1 (rest lst2)))))

(my-merge '(3 7 12 19 19 25 30) '(4 7 10 12 20))


;(count-all lst)
;Returns the total number of atomic elements in lst, at all levels.
;For example, (a (b c () (25) nil) ()) has 5 atomic elements: a, b, c, 25, and nil.
;; (defn count-all [lst]
;;   (if (empty? (first lst) ) (count-all (rest lst))
;;   (if (empty? (rest lst) ) 0
;;     (+ 1 (count-all (list ((rest (first lst)) ) ) ) ) ) ) )

(defn count-all [lst]
    (if (empty? lst)
    0
    (if (atomic? (first lst)) (+ 1 (count-all (rest lst)))
      (+ (count-all (first lst)) (count-all (rest lst))) )))

(count-all '( (a b) (b c () (25) nil) ()) )


;(my-drop n lst)
;Returns the lst with the first n elements removed. For example, (my-drop 3 '(a b c d e)) should return (d e))
;If n is equal to or greater than the length of the list lst, return the empty list, ().

(defn my-drop [n lst]
  (if (>= n (count lst ))
  (rest lst)
  (rest (my-drop (+ 1 (count lst) )   (rest lst)  ) )))

(my-drop 3 '(a b c d e) )

;(my-take n lst)
;Returns a list of the first n elements of lst. If lst has fewer than n elements, the result is just lst.
;Strong hint: Use two parameters lists, the second one using an "accumulator" parameter to collect the elements taken from lst.
;You are likely to find reverse to be useful.
(defn my-take [n lst]
  (if (>= (+ n 1) (count lst ) ) ()
  (cons (first lst) (my-take (+ 1 (count (list (first lst)) ) ) (rest lst)))))

(my-take 3 '(1 2 3 4 5 6))

;(my-reverse lst)
;Reverses the elements of list lst. For example, the list (1 2 (3 4)) becomes the sequence ((3 4) 2 1).
(defn my-reverse [lst]
  (if (empty? lst) ()
      (cons (last lst) (my-reverse (butlast lst)))))

(my-reverse '(1 2 3 4 5 6))

;(remove-duplicates lst)
;Removes duplicate top-level elements of lst. For example, given (1 2 3 1 4 1 2),
;remove-duplicates returns a sequence containing the elements (1 2 3 4), in some order.

(defn remove-duplicates [lst]
  (if-not (empty? lst)
    (cons
      (first lst)
      (filter
        (fn [item] (not= item (first lst)))
        (remove-duplicates (next lst))))))

(remove-duplicates '(2 4 5 6 5))

;(my-flatten list-of-lists)
;Removes one level of parentheses (or brackets) removed, returning a "flatter" list of values.
;For example, if lst is (   ( (1 1) (2 3) )  ( (5 7) )    ), the result should be ((1 1) (2 3) (5 7)).
;; (defn my-flatten [lst]
;;   (if (empty? lst) ()
;;       (concat (if (seq? (first lst)) (my-flatten (first lst))
;;                   (list (first lst)))
;;               (my-flatten (rest lst)))))

(defn my-flatten [lst]
  (if (empty? lst) ()
       (concat (first lst) (my-flatten (rest lst)))))


(my-flatten '(   ( (1 1) (2 3) )  ( (5 7) )    ))

;;;;;;SECOND GROUP;;;;;

;(buzz list-of-ints)
;Return the same list of integers as given as an argument, except that every number divisible by 7, or containing the digit 7,
;has been replaced by :buzz. Hint: (seq string) returns a list of characters, and map is useful.
(defn buzz [list-of-ints]
  (map #(cond (or
                (= 0 (mod % 7))
                       (.contains (seq "%") 7 ) ) "Buzz" ;;seq sring doesn't work?

              :else %) list-of-ints))

(buzz '(7 77 5))

;(divisors-of n)
;For positive integer n, returns the divisors of n, other than 1 and n itself. For example, 12 has divisors (2, 3, 4, 6).
;Hint: Use mod and filter.

(defn divisors-of [n]
  (filter (fn [x] (= 0 (mod n x))) (range 2 n)))

(divisors-of 6)

;(longest list-of-strings)
;Returns the longest string in the list-of-strings; if there is more than one longest string,
;return the earlier one. Assume that list-of-strings is not empty. Hint: reduce.
(defn longest [list-of-strings]
  (reduce (fn [fst scnd]
            (if (> (count fst) (count scnd)) fst
            scnd))
          "" list-of-strings))


(longest '("abcde", "abc", "abcdqjf"))

;;;;;;THIRD GROUP;;;;;

;red0
;(my-map f lst)
;Apply the function f to each element of lst, returning a list of the results.
(defn my-map [f lst]
    (cond (empty? lst) ()
          :else (cons (f (first lst)) (map f (rest lst)))))

(my-map (+ 2) '(2 4 6))

;(my-filter pred lst)
;Apply the test pred to each element of lst, returning a list of the ones that pass the test.

(defn my-filter [pred lst]
    (cond (empty? lst) ()
          (my-filter (first lst)) (cons (pred (first list)) (map pred (rest list)))
          :else (filter my-filter (rest list))))

(my-filter (> 3) '(2 4 6))

;(my-reduce f value? lst)
;Applies the two-parameter function f to the value? and the first element of the sequence (if value? is present),
;else to the first two elements of the sequence; applies the function to the result and the next element in the list, recursively.
;Hint: Since this function takes two or three arguments, you have to use the proper syntax for this. See Defining and calling functions in my Concise Guide to Clojure.
(defn init [s]
  [[] (ref {:s s :index 0})])

(defn my-reduce [f value? lst]
  (cond (value? nil) (my-reduce f (first lst) (second lst) )
       ([f init lst]
        (cons init (lazy-seq if (empty? lst) nil)
        :else (my-reduce (f (apply f (list init (first lst))) (rest lst) ) )  ) ) ))


;(my-flat-map f lst)
;f must be a function that returns lists. my-flat-map applies the function f to each element of lst, flattens the resultant list by removing one level of parentheses, and returns the result.
(defn my-flat-map [f lst]
  (cond (empty? lst) ()
        (number? (first lst))
        (cons (my-map (f (first lst)) (my-flat-map (rest lst)))
        :else (append (my-flat-map (first lst)) (my-flat-map (rest lst))))))

(my-flat-map (+ 1 (2 (3 4) (6 (5 7)))) '(3 4 (5 6) (8 (7 9))))

;;TESTS;

(deftest  test-atomic?
  (is (= true (atomic? 5))))

(deftest  test-atomic?
  (is (= true (atomic? nil))))

(deftest  test-atomic?
  (is (= false (atomic? '[5 3]))))

(deftest test-member?
  (is (= true (member? 5 '(1 2 3 5)))))

(deftest test-member?
  (is (= false (member? 6 '(1 2 3 5)))))

(deftest test-my-count
  (is (= 3 (my-count '(a (b c (d e)) f) ) )))

(deftest test-my-count
  (is (= 4 (my-count '(a (b c (d e)) f g) ) )))

(deftest test-append
  (is (= (:a :b :c 1 (2 3)) (append '(:a :b :c) '(1 (2 3))) ) ))

(deftest test-append
  (is (= (:a :b :c 1 (2 3)) (append '(a (b c (d e)) f g) ) ) ))

(deftest test-zip
  (is (= ((:a 1) (:b (2 3)) (:c 4)) (zip '(:a :b :c) '(1 (2 3) 4 5)))))

(deftest test-lookup
  (is (= 5 (lookup 5 '((5, 5) (5, 3))))))

(deftest test-my-merge
   (is (= (3 4 7 7 10 12 12 19 19 20 25 30) (my-merge '(3 7 12 19 19 25 30) '(4 7 10 12 20)))))

(deftest test-count-all
  (is (= 5 (count-all '(a (b c () (25) nil) ()) ) )))

(deftest test-my-drop
  (is (= (d e) (my-drop 3 '(a b c d e)))))

(deftest test-my-take
  (is (= (1 2 3) (my-take 3 '(1 2 3 4 5 6)))))

(deftest test-my-reverse
  (is (= (6 5 4 3 2 1) (my-reverse '(1 2 3 4 5 6)))))

(deftest test-remove-duplicates
  (is (= (2 4 5 6) (remove-duplicates '(2 4 5 6 5)))))

(deftest test-my-flatten
  (is (= ((1 1) (2 3) (5 7)) (my-flatten '(   ( (1 1) (2 3) )  ( (5 7) )    )))))

(deftest test-buzz
  (is (= ("Buzz" "Buzz" 5) (buzz '(7 77 5)))))

(deftest test-divisors-of
  (is (= (2 3) (divisors-of 6))))

(deftest test-divisors-of
  (is (= (2 5) (divisors-of 10))))

(deftest test-longest
  (is (= "abcdqjf" (longest '("abcde", "abc", "abcdqjf")))))

(deftest test-longest
  (is (= "abcdqjf" (longest '("", "", "abcdqjf")))))

(deftest test-my-map
  (is (= (4 6 8) (my-map (+ 2) '(2 4 6)))))

(deftest test-my-filter
  (is (= (6 8) (my-filter ((> 3) '(2 4 6))))))

(deftest test-my-reduce
  (is (= (25) '(my-reduce (+ 2 lst) (5) '(3 4 5)))))

(deftest test-my-flat-map
  (is (= (3 4 (5 6) (8 (7 9)))) (my-flat-map (+ 1 (2 (3 4) (6 (5 7)))))))

 (run-tests)
