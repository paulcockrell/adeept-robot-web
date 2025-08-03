(ns frontend.router
  (:require [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]
            [re-frame.code :as rfc]))

(def routes
  [["/" {:name :home}]])

(defn on-navigate [new-match]
  (when new-match
    (rfc/dispatch [:navigate (:name (:data new-match))])))

(defn init-routes! []
  (rfe/start!
   (rf/router routes)
   on-navigate
   {:use-fragment false}))
