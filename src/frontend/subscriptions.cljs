(ns frontend.subscriptions
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :current-page
 (fn [db _]
   (get-in db [:current-page])))

(rf/reg-sub
 :app/alert
 (fn [db _]
   (get-in db [:app :alert])))

(rf/reg-sub
 :server/connection
 (fn [db _]
   (get-in db [:server :connection]))) ;; can be either :open :opening :closed :error
