(ns freeq.state
    (:require-macros [cljs.core.async.macros :refer [go]])
    (:require [reagent.core :as reagent :refer [atom]]
              [cljs-http.client :as http]
              [cljs.core.async :refer [<!]]))

(defonce app-state (atom {:page :index
                          :requests []}))

(defn refresh-requests []
  (go (let [requests (<! (http/get "/requests"))]
        (swap! app-state #(assoc % :requests (:body requests))))))

(defn go-index []
  (refresh-requests)
  (swap! app-state #(assoc % :page :index)))

(defn go-add []
  (swap! app-state #(assoc % :page :add)))
