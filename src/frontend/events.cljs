(ns frontend.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]
            [frontend.client :as client]))

(reg-event-db
 :app/init
 (fn [_ _]
   {:app {:alert nil} ;; {:type <success|warning|error> :message "err"}
    :current-page :home
    :server {:connection :closed ;; server websocket connection can be either open opening closed
             :timeout-id nil ;; connection timeout timer id
             }
    :robot {:mode :idle}}))

(reg-event-db
 :app/alert-set
 (fn [db [_ {:keys [type message]}]]
   (assoc-in db [:app :alert] {:type type :message message})))

(reg-event-db
 :app/alert-clear
 (fn [db [_ _]]
   (assoc-in db [:app :alert] nil)))

(reg-event-db
 :server/connection
 (fn [db [_ value]]
   (assoc-in db [:server :connection] value)))

(reg-event-db
 :navigate
 (fn [db [_ route]]
   (assoc db :current-page route)))

(reg-event-fx
 :server/connection-timeout
 (fn [{:keys [db]} _]
   (when (not= :open (get-in db [:server :connection]))
     {:dispatch-n [[:server/connection :closed]
                   [:app/alert-set {:type "error" :message "Connection to server failed"}]]})))

(reg-event-fx
 :server/connect
 (fn [_ _]
   (client/start!)
   {:dispatch [:server/connection :opening]
    :dispatch-later [{:ms 1000 :dispatch [:server/connection-timeout]}]}))

(reg-event-fx
 :server/connect
 (fn [_ _]
   (client/start!)
   {:dispatch [:server/connection :opening]
    :dispatch-later [{:ms 1000 :dispatch [:server/connection-timeout]}]}))

(reg-event-fx
 :command/mode-idle
 (fn [_ _]
   (client/send! :command/mode-idle {})
   {}))

(reg-event-fx
 :command/mode-manual
 (fn [_ _]
   (client/send! :command/mode-manual {})
   {}))

(reg-event-fx
 :command/mode-sentient
 (fn [_ _]
   (client/send! :command/mode-sentient {})
   {}))

(reg-event-fx
 :command/mode-programmable
 (fn [_ _]
   (client/send! :command/mode-programmable {})
   {}))

(reg-event-fx
 :robot/mode-updated
 (fn [_ [_ mode]]
   {:dispatch-n [[:app/alert-set {:type "warning" :message (str "Robot has entered " (name mode) " mode")}]
                 [:state-update/robot-mode {:mode mode}]]}))

(reg-event-db
 :state-update/robot-mode
 (fn [db [_ {:keys [mode]}]]
   (assoc-in db [:robot :mode] {:mode mode})))

(reg-event-fx
 :command/robot-action
 (fn [_ [_ action]]
   (client/send! :command/robot-action action)
   {}))

(reg-event-fx
 :command/camera-action
 (fn [_ [_ action]]
   (client/send! :command/camera-action action)
   {}))

(reg-event-fx
 :command/led-action
 (fn [_ [_ action]]
   (println action)
   (client/send! :command/led-action action)
   {}))
