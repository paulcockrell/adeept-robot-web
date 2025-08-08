(ns frontend.layout.main
  (:require [frontend.layout.alert :as alert]
            [re-frame.core :as rfc]))

(defn alert-section []
  (let [{:keys [type message]} @(rfc/subscribe [:app/alert])]
    (case type
      "error" [alert/alert-error message]
      "warning" [alert/alert-warning message]
      "info" [alert/alert-info message]
      "success" [alert/alert-success message]
      ;; default
      [:<>])))

(defn main [children]
  [:main.container
   [:section.alerts
    [alert-section]]
   children])
