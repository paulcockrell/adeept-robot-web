(ns frontend.events
  (:require [re-frame.core :as rf]
            [frontend.client :as client]))

(rf/reg-event-db
 :app/init
 (fn [_ _]
   {:app {:alert nil} ;; {:type <success|warning|error> :message "err"}
    :current-page :home
    :server {:connection :closed ;; server websocket connection can be either open opening closed
             :timeout-id nil ;; connection timeout timer id
             }}))

(re-frame.core/reg-event-db
 :app/alert-set
 (fn [db [_ {:keys [type message]}]]
   (assoc-in db [:app :alert] {:type type :message message})))

(rf/reg-event-db
 :app/alert-clear
 (fn [db [_ _]]
   (assoc-in db [:app :alert] nil)))

(rf/reg-event-db
 :server/connection
 (fn [db [_ value]]
   (assoc-in db [:server :connection] value)))

(rf/reg-event-fx
 :server/connection-timeout
 (fn [{:keys [db]} _]
   (when (not= :open (get-in db [:server :connection]))
     {:dispatch-n [[:server/connection :closed]
                   [:app/alert-set {:type "error" :message "Connection to server failed"}]]})))

(rf/reg-event-fx
 :server/connect
 (fn [_ _]
   (client/start!)
   {:dispatch [:server/connection :opening]
    :dispatch-later [{:ms 1000 :dispatch [:server/connection-timeout]}]}))

(rf/reg-event-db
 :navigate
 (fn [db [_ route]]
   (assoc db :current-page route)))
