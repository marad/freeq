(ns freeq.comment
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs-http.client :as http]
            [freeq.model :refer [PostRequest]]
            [freeq.state :refer [go-index refresh-requests]]
            [cljs.core.async :refer [<!]]))

(enable-console-print!)

(defn get-request []
  (go (let [requests (<! (http/get "/requests"))]
        (swap! app-state #(assoc % :requests (:body requests))))))

(defn add-comment []
  [:div
   [:h1 "Title"]
   [:p "Request"]
   ]
  )