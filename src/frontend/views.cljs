(ns frontend.views
  (:require [frontend.views.not-found :as not-found]
            [frontend.views.home :as home]))

(defmulti pages identity)

(defmethod pages :default [_] [not-found/not-found])

(defmethod pages nil [_] [not-found/not-found])

(defmethod pages :home [] [home/home])
