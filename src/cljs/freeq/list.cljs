(ns freeq.list
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [freeq.state :as frState]
            [freeq.comment :as cmnt]
            [cljs.core.async :refer [<!]]))

(defn like-request [id]
  (go (let [response (<! (http/post (str "/like-request/" id)))]
        (frState/go-index)
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
          [:button.btn.btn-default
           {:on-click (fn [] (like-request (:_id request)))}
           "like it"]]
         [:span.addComment 
          [:button {:on-click (fn [] (swap! frState/app-state #(assoc % :page :comment)))} 
           "add comment"]]
         [:span.likeit [:button "like it"]]
         [:span.addComment 
          [:button {:on-click (fn [] 
                                  (swap! frState/app-state #(assoc % :page :comment)) 
                                  (cmnt/get-request (:_id request)))} "add comment"]]
         ]
       ]
      ]
     )
    ]
   ])

