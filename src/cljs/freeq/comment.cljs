(ns freeq.comment
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs-http.client :as http]
            [freeq.state :as frState]
            [freeq.model :refer [PostRequest]]
            [cljs.core.async :refer [<!]]))

(enable-console-print!)


(defn add-comment [request]
  [:div
   [:h1 (:title request)]
   [:p (:desc request)]])
