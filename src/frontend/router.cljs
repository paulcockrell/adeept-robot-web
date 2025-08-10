(ns frontend.router
  (:require [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]
            [re-frame.core :as rfc]))

(def routes
  [["/" {:name :home}]
   ["/mode/manual" {:name :mode-manual}]
   ["/mode/sentient" {:name :mode-sentient}]
   ["/mode/programmable" {:name :mode-programmable}]
   ["/settings" {:name :settings}]
   ["/help" {:name :help}]
   ["/about" {:name :about}]])

(defn on-navigate [new-match]
  (when new-match
    (rfc/dispatch [:navigate (:name (:data new-match))])))

(defn init-routes! []
  (rfe/start!
   (rf/router routes)
   on-navigate
   {:use-fragment false}))
