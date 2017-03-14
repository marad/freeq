(ns freeq.list
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(defn reqlist [requests]
  [:div
   [:button "Dodaj"]
   [:ul
    (for [request (:requests requests)]
      ^{:key (:_id request)}
      [:li
       [:div
        [:h1 (:title request)]
        [:div.desc (:desc request)]
        [:div
         [:span.likes (str " " (:likes request) " users likes this")]
         [:span.likeit [:button "like it"]]
         ]
       ]
      ]
     )
    ]
   ])
