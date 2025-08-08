(ns frontend.layout.layout
  (:require [frontend.layout.header :as header]
            [frontend.layout.footer :as footer]
            [frontend.layout.main :as main]))

(defn layout [children]
  [:<>
   [header/header]
   [main/main children]
   [footer/footer]])
