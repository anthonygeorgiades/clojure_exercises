(ns user (:use clojure.test))

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
