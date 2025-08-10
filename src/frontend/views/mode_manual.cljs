(ns frontend.views.mode-manual
  (:require [re-frame.core :as rf]
            [reagent.core :as ra]
            [frontend.layout.layout :as layout]))

(defn on-mount []
  (js/console.log "Entering manual mode")
  (rf/dispatch [:robot/mode-manual]))

(defn on-dismount []
  (js/console.log "Leaving manual mode"))

(defn mode-manual []
  (ra/with-let [_ (on-mount)]

    [layout/layout
     [:section.mode-manual
      [:hgroup
       [:div.heading-icon
        [:span.material-symbols-outlined "joystick"]
        [:h1 "Manual mode"]
        [:small
         [:p.muted "Manual mode allows you to directly control the robots movements.
            It will still avoid collisions automatically."]]]]]]

    (finally (on-dismount))))

