(ns freeq.add-requests
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs-http.client :as http]
            [freeq.model :refer [PostRequest]]
            [freeq.state :refer [go-index refresh-requests]]
            [cljs.core.async :refer [<!]]))

(enable-console-print!)

(def state (atom {:doc {:title "Title" :desc "Desc"} :saved? false }))


(defn row [label input]
  [:div.row
   [:div.col-md-2 [:label label]]
   [:div.col-md-5 input]])

(defn input-element
  "An input element"
  [id name type]
  [:input {:id       id
           :name     name
           :class    "form-control"
           :type     type
           :required ""
           :value (get-in @state [:doc :title])
           :on-change (fn [evt] (swap! state #(assoc-in % [:doc :title] (-> evt .-target .-value))))
           }])

(defn textarea-element
  "An textarea element"
  [id name]
  [:textarea {:id       id
              :name     name
              :class    "form-control"
              :required ""
              :value (get-in @state [:doc :desc])
              :on-change (fn [evt] (swap! state #(assoc-in % [:doc :desc] (-> evt .-target .-value))))
              }])

(defn save-request []
  (go (let [response (<! (http/post "/add-request" {:edn-params (:doc @state)}))]
        (go-index)
        )))

(defn add-request []
  [:div [:h1 "Add request form"]
   (row "Title" (input-element "title" "title" "text"))
   (row "Description" (textarea-element "description" "description"))
   [:button {:class "btn btn-default"
             :on-click save-request}
    "Submit"]
   ]
  )

