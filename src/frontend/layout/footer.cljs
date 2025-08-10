(ns frontend.layout.footer
  (:require [reitit.frontend.easy :as rfe]
            [re-frame.core :as rfc]))

(defn nav-button [route tooltip icon-name]
  (let [current-page @(rfc/subscribe [:current-page])
        status @(rfc/subscribe [:server/connection])]
    [:button
     {:on-click #(rfe/push-state route)
      :data-tooltip tooltip
      :aria-busy (and (not= :home current-page) (not= :open status))
      :disabled  (and (not= :home current-page) (not= :open status))
      :class (str "nav-button" (when (= current-page route) " active"))}
     [:span.material-symbols-outlined icon-name]]))

(defn footer []
  [:footer {:class "container-fluid"}
   [:nav {:id "device-ribbon"}
    [:nav {:class "ribbon-buttons"}
     [nav-button :home "Home" "home"]
     [nav-button :mode-manual "Manual mode" "joystick"]
     [nav-button :mode-sentient "Sentient mode" "robot"]
     [nav-button :mode-programmable "Programable mode" "laptop_car"]
     [nav-button :settings "Settings" "settings_applications"]
     [nav-button :help "Help" "help"]
     [nav-button :about "About" "info"]]]])
