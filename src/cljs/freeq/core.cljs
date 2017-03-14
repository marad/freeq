(ns freeq.core
    (:require-macros [cljs.core.async.macros :refer [go]])
    (:require [reagent.core :as reagent :refer [atom]]
              [cljs-http.client :as http]
              [cljs.core.async :refer [<!]]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello Chestnut!!"}))

(defn greeting []
  [:div
   [:button {:on-click (fn [] 
                           (go (let [requests (<! (http/get "/requests"))]
                                 (swap! app-state #(assoc % :text (:body requests))))))} 
    "GO"]
   "TEST"
   [:h1 (str (:text @app-state))]])

(reagent/render [greeting] (js/document.getElementById "app"))
