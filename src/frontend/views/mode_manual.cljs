(ns frontend.views.mode-manual
  (:require [re-frame.core :as rf]
            [reagent.core :as ra]
            [frontend.layout.layout :as layout]))

(defn on-mount []
  (println "Entering manual mode")
  (rf/dispatch [:command/mode-manual]))

(defn on-dismount []
  (println "Leaving manual mode"))

(defn command-button [opts]
  (let [{:keys [command icon-name]} opts]
    [:button
     {:on-mouse-down #(rf/dispatch command)
      :on-mouse-up #(rf/dispatch [:command/robot-action :stop])}
     [:span.material-symbols-outlined icon-name]]))

(defn motor-controls []
  [:div.circular-controller.pico-background-yellow-50
   [:div.top
    [command-button {:command [:command/robot-action :forward]
                     :icon-name "arrow_drop_up"}]]
   [:div.middle
    [command-button {:command [:command/robot-action :left]
                     :icon-name "arrow_left"}]
    [command-button {:command [:command/robot-action :right]
                     :icon-name "arrow_right"}]]
   [:div.bottom
    [command-button {:command [:command/robot-action :down]
                     :icon-name "arrow_drop_down"}]]])

(defn camera-controls []
  [:div.circular-controller.pico-background-yellow-50
   [:div.top
    [command-button {:command [:command/camera-action :up]
                     :icon-name "arrow_drop_up"}]]
   [:div.middle
    [command-button {:command [:command/camera-action :take-photo]
                     :icon-name "camera"}]]

   [:div.bottom
    [command-button {:command [:command/camera-action :down]
                     :icon-name "arrow_drop_down"}]]])

(defn led-controls []
  [:div.led-control
   [:fieldset
    [:label
     "Red"
     [:input {:name "led-red" :type "range"}]]
    [:label
     "Green"
     [:input {:name "led-green" :type "range"}]]
    [:label
     "Blue"
     [:input {:name "led-blue" :type "range"}]]]])

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
            It will still avoid collisions automatically."]]]]
      [:div.grid
       [:article.motor-controls
        [:header "Motor controls"]
        [:div.body
         [motor-controls]]]
       [:article.camera-controls
        [:header "Camera controls"]
        [:div.body
         [camera-controls]]]
       [:article.led-controls
        [:header "LED controls"]
        [:div.body
         [led-controls]]]]]]

    (finally (on-dismount))))

