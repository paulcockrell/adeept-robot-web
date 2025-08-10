(ns backend.handlers
  (:require [backend.socket :as socket]))

(defmulti -event-msg-handler :id)

(defn event-msg-handler
  [{:as ev-msg :keys [id ?data event]}]
  (-event-msg-handler ev-msg))

(defmethod -event-msg-handler :robot/mode-idle
  [_]
  (println "Recieved request to put robot in idle mode")
  (socket/broadcast! {:key :robot/mode :message :idle}))

(defmethod -event-msg-handler :robot/mode-manual
  [_]
  (println "Recieved request to put robot in manual mode")
  (socket/broadcast! {:key :robot/mode :message :manual}))

(defmethod -event-msg-handler :robot/mode-sentient
  [_]
  (println "Recieved request to put robot in sentient mode")
  (socket/broadcast! {:key :robot/mode :message :sentient}))

(defmethod -event-msg-handler :robot/mode-programmable
  [_]
  (println "Recieved request to put robot in programmable mode")
  (socket/broadcast! {:key :robot/mode :message :programmable}))

(defmethod -event-msg-handler :chsk/ws-ping
  [{:keys [event id ?data ring-req ?reply-fn send-fn]}]
  (println  "RCV: " event))

(defmethod -event-msg-handler :chsk/ws-pong
  [{:keys [event id ?data ring-req ?reply-fn send-fn]}]
  (println  "RCV: " event))

(defmethod -event-msg-handler :default
  [{:keys [event id ?data ring-req ?reply-fn send-fn]}]
  (println  "Unhandled event: " event))
