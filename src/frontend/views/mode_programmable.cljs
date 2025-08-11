(ns frontend.views.mode-programmable
  (:require [re-frame.core :as rf]
            [reagent.core :as ra]
            [frontend.layout.layout :as layout]))

(defn on-mount []
  (println "Entering programmable mode")
  (rf/dispatch [:command/mode-programmable]))

(defn on-dismount []
  (println "Leaving programmable mode"))

(defn mode-programmable []
  (ra/with-let [_ (on-mount)]

    [layout/layout
     [:div "Programmable mode"]]

    (finally (on-dismount))))
