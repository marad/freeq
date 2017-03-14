(ns freeq.list
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [freeq.state :refer [go-index refresh-requests]]
            [cljs.core.async :refer [<!]]))

(defn like-request [id]
  (go (let [response (<! (http/post (str "/like-request/" id)))]
        (go-index)
        )))

(defn reqlist [requests]
  [:div
   [:ul
    (for [request (:requests requests)]
      ^{:key (:_id request)}
      [:li
       [:div
        [:h1 (:title request)]
        [:div.desc (:desc request)]
        [:div
         [:span.likes (str " " (:likes request) " users likes this")]
         [:span.likeit
          [:button
           {:on-click (fn [] (like-request (:_id request)))}
           "like it"]]
         ]
       ]
      ]
     )
    ]
   ])

