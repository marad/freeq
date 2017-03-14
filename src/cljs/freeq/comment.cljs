(ns freeq.comment
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs-http.client :as http]
            [freeq.state :as frState]
            [freeq.model :refer [PostRequest]]
            [cljs.core.async :refer [<!]]))

(enable-console-print!)

(defn get-request [id]
  (go (let [request (<! (http/get (str "/get-request/" id)))]
        (swap! frState/app-state #(assoc % :request (:body request))))))

(defn add-comment [request]
  ;(println @frState/app-state)
  (println "OKX")
  (println request)
  [:div
   [:h1 (:title request)]
   [:p (:desc request)]
   ]
  )