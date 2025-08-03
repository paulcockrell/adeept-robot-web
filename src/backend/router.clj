(ns backend.router
  (:require
   [taoensso.sente :as sente]
   [clojure.string :as str]
   [compojure.route :as route]
   [compojure.core :as comp :refer (defroutes GET POST)]
   [backend.endpoints :as endpoints]
   [backend.handlers :as handlers]
   [backend.socket :as socket]))

(defroutes ring-routes
  (GET "/chsk" ring-req (socket/ring-ajax-get-or-ws-handshake ring-req))
  (POST "/chsk" ring-req (socket/ring-ajax-post ring-req))
  (GET "*" req
    (if (str/inclues? (get-in req [:headers "accept"] "") "text/html")
      ;; always serve the index page for text/html requests
      (endpoints/home-handler req)
      ;; non text/html requests are not handled
      (route/not-found "Not found"))))

(defonce router_ (atom nil))

(defn stop! []
  (when-let [stop-fn @router_] (stop-fn)))

(defn start! []
  (stop!)
  (reset! router_ (sente/start-server-chsk-router! socket/ch-chsk handlers/event-msg-handler)))

