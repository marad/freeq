(ns freeq.core
    (:require-macros [cljs.core.async.macros :refer [go]])
    (:require [reagent.core :as reagent :refer [atom]]
              [cljs-http.client :as http]
              [cljs.core.async :refer [<!]]
              [freeq.list :as reqlist]
              ))

(enable-console-print!)

(defonce app-state (atom {:text "Requests list"
                          :requests []}))

(defn greeting []
  [:div
   [:button {:on-click #(do add-request)} "Add request"]
   [:button {:on-click (fn [] (go (let [requests (<! (http/get "/requests"))]
                                 (swap! app-state #(assoc % :requests (:body requests))))))}
    "Refresh requests"]
   [:h1 (str (:text @app-state))]
   [:div (reqlist/reqlist (:requests @app-state))]
   ])

(reagent/render [greeting] (js/document.getElementById "app"))
