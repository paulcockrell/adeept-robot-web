(ns frontend.views
  (:require [frontend.views.not-found :as not-found]
            [frontend.views.home :as home]
            [frontend.views.mode-manual :as mode-manual]
            [frontend.views.mode-auto :as mode-auto]
            [frontend.views.mode-program :as mode-program]
            [frontend.views.settings :as settings]
            [frontend.views.help :as help]
            [frontend.views.about :as about]))

(defmulti pages identity)

;; Error pages
(defmethod pages :default [_] [not-found/not-found])
(defmethod pages nil [_] [not-found/not-found])

;; Pages
(defmethod pages :home [] [home/home])
(defmethod pages :mode-manual [] [mode-manual/mode-manual])
(defmethod pages :mode-auto [] [mode-auto/mode-auto])
(defmethod pages :mode-program [] [mode-program/mode-program])
(defmethod pages :settings [] [settings/settings])
(defmethod pages :help [] [help/help])
(defmethod pages :about [] [about/about])
