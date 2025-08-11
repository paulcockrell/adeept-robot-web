(ns frontend.views.mode-sentient
  (:require [re-frame.core :as rf]
            [reagent.core :as ra]
            [frontend.layout.layout :as layout]))

(defn on-mount []
  (println "Entering sentient mode")
  (rf/dispatch [:command/mode-sentient]))

(defn on-dismount []
  (println "Leaving sentient mode"))

(defn mode-sentient []
  (ra/with-let [_ (on-mount)]

    [layout/layout
     [:div "Sentient mode"]]

    (finally (on-dismount))))
