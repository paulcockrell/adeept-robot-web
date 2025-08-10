(ns frontend.views.about
  (:require [frontend.layout.layout :as layout]))

(defn about []
  [layout/layout
   [:div "About"]])
