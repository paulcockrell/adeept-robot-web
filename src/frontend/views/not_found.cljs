(ns frontend.views.not-found
  (:require [frontend.layout.layout :as layout]))

(defn not-found []
  [layout/layout
   [:div "Error: page not found"]])
