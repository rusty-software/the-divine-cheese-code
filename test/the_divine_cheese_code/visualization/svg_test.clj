(ns the-divine-cheese-code.visualization.svg-test
  (:require [clojure.test :refer :all]
            [the-divine-cheese-code.visualization.svg :refer :all])
  (:refer-clojure :exclude [min max]))


(deftest latlng->point-test
  (testing "Given a map containing lat and lng, returns those elements as a comma-delimited string"
    (is (= "50.95,6.97" (latlng->point {:location "place1" :cheese-name "cheese1" :lat 50.95 :lng 6.97})))))

(deftest points-test
  (testing "Given a sequence of location maps, returns the latlng points as a space-delimited string"
    (is (= "50.95,6.97" (points [{:location "place1" :cheese-name "cheese1" :lat 50.95 :lng 6.97}])))
    (is (= "50.95,6.97 15.97,3.14" (points [{:location "place1" :cheese-name "cheese1" :lat 50.95 :lng 6.97}
                                            {:location "place2" :cheese-name "cheese2" :lat 15.97 :lng 3.14}])))))

(deftest min-test
  (is (= {:lat 1 :lng 0} (min [{:lat 1 :lng 3} {:lat 5 :lng 0}]))))

(deftest max-test
  (is (= {:lat 5 :lng 3} (max [{:lat 1 :lng 3} {:lat 5 :lng 0}]))))
