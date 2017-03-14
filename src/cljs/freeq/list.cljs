(ns freeq.list
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [freeq.state :as frState]
            [freeq.comment :as cmnt]
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
<<<<<<< 5c809689b3acecfa8ceb5d21635f371adc398850
         [:span.likeit
          [:button
           {:on-click (fn [] (like-request (:_id request)))}
           "like it"]]
         [:span.addComment [:button {:on-click (fn [] (swap! frState/app-state #(assoc % :page :comment)))} "add comment"]]
=======
         [:span.likeit [:button "like it"]]
         [:span.addComment [:button {:on-click (fn [] (swap! frState/app-state #(assoc % :page :comment)) (cmnt/get-request (:_id request)))} "add comment"]]
>>>>>>> comment
         ]
       ]
      ]
     )
    ]
<<<<<<< 5c809689b3acecfa8ceb5d21635f371adc398850
   ])

=======
   ])
>>>>>>> comment
