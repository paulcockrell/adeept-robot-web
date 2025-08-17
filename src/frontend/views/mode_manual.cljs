(ns frontend.views.mode-manual
  (:require [re-frame.core :as rf]
            [reagent.core :as ra]
            [frontend.layout.layout :as layout]))

(defn on-mount []
  (println "Entering manual mode")
  (rf/dispatch [:command/mode-manual]))

(defn on-dismount []
  (println "Leaving manual mode"))

(defn move-button [opts]
  (let [{:keys [command icon-name]} opts]
    [:button
     {:on-mouse-down #(rf/dispatch command)
      :on-mouse-up #(rf/dispatch [:command/robot-action :stop])}
     [:span.material-symbols-outlined icon-name]]))

(defn camera-button [opts]
  (let [{:keys [command icon-name]} opts]
    [:button
     {:on-mouse-down #(rf/dispatch command)}
     [:span.material-symbols-outlined icon-name]]))

(defn range-button [opts]
  (let [{:keys [name command colour]} opts]
    [:input {:name name
             :type "range"
             :on-change (fn [el]
                          (let [value (.. el -target -value)]
                            (rf/dispatch [command {:colour colour :value value}])))}]))

(defn motor-controls []
  [:div.circular-controller.pico-background-yellow-50
   [:div.top
    [move-button {:command [:command/robot-action :forward]
                  :icon-name "arrow_drop_up"}]]
   [:div.middle
    [move-button {:command [:command/robot-action :left]
                  :icon-name "arrow_left"}]
    [move-button {:command [:command/robot-action :right]
                  :icon-name "arrow_right"}]]
   [:div.bottom
    [move-button {:command [:command/robot-action :down]
                  :icon-name "arrow_drop_down"}]]])

(defn camera-controls []
  [:div.circular-controller.pico-background-yellow-50
   [:div.top
    [camera-button {:command [:command/camera-action :up]
                    :icon-name "arrow_drop_up"}]]
   [:div.middle
    [camera-button {:command [:command/camera-action :take-photo]
                    :icon-name "camera"}]]

   [:div.bottom
    [camera-button {:command [:command/camera-action :down]
                    :icon-name "arrow_drop_down"}]]])

(defn led-controls []
  [:div.led-control
   [:fieldset
    [:label
     "Red"
     [range-button {:name "led-red"
                    :command :command/led-action
                    :colour :red}]]
    [:label
     "Green"
     [range-button {:name "led-green"
                    :command :command/led-action
                    :colour :green}]]
    [:label
     "Blue"
     [range-button {:name "led-blue"
                    :command :command/led-action
                    :colour :blue}]]]])

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

