(ns the-divine-cheese-code.visualization.svg
  (:require [clojure.string :as str])
  (:refer-clojure :exclude [min max]))

(defn comparator-over-maps
  [comparison-fn ks]
  (fn [maps]
    (zipmap ks (map (fn [k] (apply comparison-fn (map k maps))) ks))))

(def min (comparator-over-maps clojure.core/min [:lat :lng]))

(def max (comparator-over-maps clojure.core/max [:lat :lng]))

(defn latlng->point
  "Given a map containing lat and lng, returns those elements as a comma-delimited string of lng,lat."
  [{:keys [lat lng]}]
  (str lat "," lng))

(defn points
  "Given a sequence of location maps, returns the latlng points as a space-delimited string"
  [locations]
  (str/join " " (map latlng->point locations)))
