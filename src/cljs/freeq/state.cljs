(ns freeq.state
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(defonce app-state (atom {:page     :index
                          :requests []
                          :request  {}}))

(defn refresh-requests []
  (go (let [requests (<! (http/get "/requests"))]
        (swap! app-state #(assoc % :requests (:body requests))))))

(defn find-request [id]
  (->> @app-state
      :requests :requests
      (filter #(= (:_id %) id))
      first
      ))

(defn get-request [id]
  (println "Searching request" id)
  (println "Showing request" (find-request id))
  (swap! app-state #(assoc % :request (find-request id))))

(defn go-index []
  (refresh-requests)
  (swap! app-state #(assoc % :page :index)))

(defn go-add []
  (swap! app-state #(assoc % :page :add)))

(defn go-comment [id]
  (get-request id)
  (swap! app-state #(assoc % :page :comment)))
