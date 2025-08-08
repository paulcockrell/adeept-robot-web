(ns frontend.views.home
  (:require [frontend.layout.layout :as layout]))

(defn home []
  [layout/layout
   [:section
    [:h1 "Hello, Robot!"]]])
