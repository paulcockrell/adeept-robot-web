(ns frontend.app
  (:require
   [reagent.dom.client :as rdomc]
   [re-frame.core :as rf]
   [frontend.subscriptions]
   [frontend.events]
   [frontend.views :as views]
   [frontend.router :as router]))

(defonce react-root (rdomc/create-root (.getElementById js/document "app")))

(defn app []
  (let [current-page @(rf/subscribe [:current-page])]
    [:div
     [views/pages current-page]]))

(defn init []
  (println "Initializing app")
  (router/init-routes!)
  (rf/dispatch-sync [:app/init])
  (rf/dispatch [:server/connect])
  (rdomc/render react-root [app]))
