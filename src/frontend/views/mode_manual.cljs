(ns frontend.views.mode-manual
  (:require [frontend.layout.layout :as layout]))

(defn mode-manual []
  [layout/layout
   [:div "Manual mode"]])
