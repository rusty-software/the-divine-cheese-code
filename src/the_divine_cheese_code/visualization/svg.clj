(ns the-divine-cheese-code.visualization.svg)

(defn latlng->point
  "Given a map containing lat and lng, returns those elements as a comma-delimited string of lng,lat."
  [{:keys [lat lng]}]
  (str lat "," lng))

(defn points
  "Given a sequence of location maps, returns the latlng points as a space-delimited string"
  [locations]
  (clojure.string/join " " (map latlng->point locations)))
