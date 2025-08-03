(ns backend.main
  (:require [backend.router :as router]
            [backend.server :as server]))

(defn start! []
  (router/start!)
  (server/start! 3000))

(defn stop! []
  (router/stop!)
  (server/stop!))

(defn -main [] (start!))
