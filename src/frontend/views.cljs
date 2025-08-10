(ns frontend.views
  (:require [frontend.views.not-found :as not-found]
            [frontend.views.home :as home]
            [frontend.views.mode-manual :as mode-manual]
            [frontend.views.mode-sentient :as mode-sentient]
            [frontend.views.mode-programmable :as mode-programmable]
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
(defmethod pages :mode-sentient [] [mode-sentient/mode-sentient])
(defmethod pages :mode-programmable [] [mode-programmable/mode-programmable])
(defmethod pages :settings [] [settings/settings])
(defmethod pages :help [] [help/help])
(defmethod pages :about [] [about/about])
