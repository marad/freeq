(ns freeq.requests
    (:require [freeq.mongo :as repo]
              [freeq.model :refer [PostRequest Request Comment]]
              [schema.core :as s]))

(defn uuid [] (str (java.util.UUID/randomUUID)))

(s/defn list-requests :- [Request] []
  (repo/get-requests))

(s/defn add-request :- [Request]
  [request :- PostRequest]
  (let [request (assoc request
                       :_id (uuid)
                       :likes 0)]
    (repo/add-request request)
    request))

(s/defn like-request :- [Request]
  [id]
  (repo/like-request id))
