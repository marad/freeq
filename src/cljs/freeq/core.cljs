(ns freeq.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [freeq.list :as reqlist]
            [freeq.add-requests :as addr]
            [freeq.comment :as cmnt]
            [freeq.state :refer [app-state refresh-requests]]
            [cljs.core.async :refer [<!]]
            ))

(enable-console-print!)

(def pages {:index #(reqlist/reqlist (:requests @app-state))
            :add   addr/add-request
            :comment #(cmnt/add-comment (:request @app-state))
            })

(defn render-page []
  [:div
   [:button {:on-click (fn []
                         (swap! app-state #(assoc % :page :index))
                         (refresh-requests))} "List"]
   [:button {:on-click (fn [] (swap! app-state #(assoc % :page :add)))} "Add"]
   ((pages (:page @app-state)))])

(refresh-requests)
(reagent/render [render-page] (js/document.getElementById "app"))
