(ns freeq.add-requests
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs-http.client :as http]
            [freeq.model :refer [PostRequest]]
            [cljs.core.async :refer [<!]]))

(enable-console-print!)

(def state (atom {:doc {} :saved? false}))


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
           }])

(defn textarea-element
  "An textarea element"
  [id name]
  [:textarea {:id       id
              :name     name
              :class    "form-control"
              :required ""
              }])

(defn save-request [PostRequest])

(defn add-request []
  [:div [:h1 "Add request form"]
   [:form 
    (row "Title" (input-element "title" "title" "text"))
    (row "Description" (textarea-element "description" "description"))
    [:button {:type "submit"
              :class "btn btn-default"
              :onClick save-request}
     "Submit"]
    ]
   ]
  )

