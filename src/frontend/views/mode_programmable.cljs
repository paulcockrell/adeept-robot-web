(ns frontend.views.mode-programmable
  (:require [re-frame.core :as rf]
            [reagent.core :as ra]
            [frontend.layout.layout :as layout]))

(defn on-mount []
  (js/console.log "Entering programmable mode")
  (rf/dispatch [:robot/mode-programmable]))

(defn on-dismount []
  (js/console.log "Leaving programmable mode"))

(defn mode-programmable []
  (ra/with-let [_ (on-mount)]

    [layout/layout
     [:div "Programmable mode"]]

    (finally (on-dismount))))
