(ns backend.handlers
  (:require [backend.socket :as socket]))

(defmulti -event-msg-handler :id)

(defn event-msg-handler
  [{:as ev-msg :keys [id ?data event]}]
  (-event-msg-handler ev-msg))

(defmethod -event-msg-handler :robot/mode
  [{:keys [event id ?data ring-req ?reply-fn send-fn]}]
  (when-let [{:keys [mode]} ?data]
    (println "Recieved request to put robot in mode" mode)
    (socket/broadcast! {:key :robot/mode :message mode})))

(defmethod -event-msg-handler :chsk/ws-ping
  [{:keys [event id ?data ring-req ?reply-fn send-fn]}]
  (println  "RCV: " event))

(defmethod -event-msg-handler :chsk/ws-pong
  [{:keys [event id ?data ring-req ?reply-fn send-fn]}]
  (println  "RCV: " event))

(defmethod -event-msg-handler :default
  [{:keys [event id ?data ring-req ?reply-fn send-fn]}]
  (println  "Unhandled event: " event))
