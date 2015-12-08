(ns the-divine-cheese-code.visualization.svg
  (:require [clojure.string :as str])
  (:refer-clojure :exclude [min max]))

(defn comparator-over-maps
  [comparison-fn ks]
  (fn [maps]
    (zipmap ks (map (fn [k] (apply comparison-fn (map k maps))) ks))))

(def min (comparator-over-maps clojure.core/min [:lat :lng]))

(def max (comparator-over-maps clojure.core/max [:lat :lng]))

(defn translate-to-00
  "Adjusts each location's lat and lng relative to the min coord"
  [locations]
  (let [min-coords (min locations)]
    (map #(merge-with - % min-coords) locations)))

(defn scale
  "Scales the drawing to the relative width and height of the frame and the max coord"
  [width height locations]
  (let [max-coords (max locations)
        ratio {:lat (/ height (:lat max-coords))
               :lng (/ width (:lng max-coords))}]
    (map #(merge-with * % ratio) locations)))

(defn latlng->point
  "Given a map containing lat and lng, returns those elements as a comma-delimited string of lng,lat."
  [{:keys [lat lng]}]
  (str lng "," lat))

(defn points
  "Given a sequence of location maps, returns the latlng points as a space-delimited string"
  [locations]
  (str/join " " (map latlng->point locations)))

(defn line
  [points]
  (str "<polyline points=\"" points "\" />"))

(defn transform
  [width height locations]
  (->> locations
       (translate-to-00)
       (scale width height)))

(defn xml
  [width height locations]
  (str "<svg height=\"" height "\" width=\"" width "\">"
       "<g transform=\"translate(0," height ") \">"
       "<g transform=\"scale(1,-1)\">"
       (-> (transform width height locations)
           (points)
           (line))
       "</g></g>"
       "</svg>"))