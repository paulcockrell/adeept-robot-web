(ns frontend.views.home
  (:require [re-frame.core :as rf]
            [reagent.core :as ra]
            [frontend.layout.layout :as layout]))

(defn on-mount []
  (println "Entering idle mode")
  (rf/dispatch [:command/mode-idle]))

(defn home []
  (ra/with-let [_ (on-mount)]
    [layout/layout
     [:section
      [:h1 "Hello, Robot!"]]]))
