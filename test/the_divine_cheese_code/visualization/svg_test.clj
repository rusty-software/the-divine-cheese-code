(ns the-divine-cheese-code.visualization.svg-test
  (:require [clojure.test :refer :all]
            [the-divine-cheese-code.visualization.svg :refer :all])
  (:refer-clojure :exclude [min max]))


(deftest latlng->point-test
  (testing "Given a map containing lat and lng, returns those elements as a comma-delimited string"
    (is (= "6.97,50.95" (latlng->point {:location "place1" :cheese-name "cheese1" :lat 50.95 :lng 6.97})))))

(deftest points-test
  (testing "Given a sequence of location maps, returns the latlng points as a space-delimited string"
    (is (= "6.97,50.95" (points [{:location "place1" :cheese-name "cheese1" :lat 50.95 :lng 6.97}])))
    (is (= "6.97,50.95 3.14,15.97" (points [{:location "place1" :cheese-name "cheese1" :lat 50.95 :lng 6.97}
                                            {:location "place2" :cheese-name "cheese2" :lat 15.97 :lng 3.14}])))))

(deftest min-test
  (is (= {:lat 1 :lng 0} (min [{:lat 1 :lng 3} {:lat 5 :lng 0}]))))

(deftest max-test
  (is (= {:lat 5 :lng 3} (max [{:lat 1 :lng 3} {:lat 5 :lng 0}]))))

(deftest translate-to-00-test
  (is (= [{:lat 0 :lng 0}] (translate-to-00 [{:lat 50 :lng 25}])))
  (is (= [{:lat 10 :lng 0} {:lat 0 :lng 5}] (translate-to-00 [{:lat 50 :lng 25} {:lat 40 :lng 30}]))))

(deftest scale-test
  (is (= [{:lat 100 :lng 100}] (scale 100 100 [{:lat 50 :lng 25}])))
  (is (= [{:lat 300 :lng 250} {:lat 240 :lng 300}] (scale 300 300 [{:lat 50 :lng 25} {:lat 40 :lng 30}]))))
