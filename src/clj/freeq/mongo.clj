(ns freeq.mongo
    (:require [mount.core :as mount :refer [defstate]]
              [monger.core :as mg]
              [monger.collection :as mc]
              [schema.core :as s]
              [freeq.model :refer [Request Comment]]
              ))

(def db-name "freeq")
(def request-collection "requests")
(def comment-collection "comments")

(declare mongo)
(def db (atom nil))

(defn- start []
  (let [conn (mg/connect)]
    (reset! db (mg/get-db conn db-name))
    conn))

(defn- stop []
  (reset! db nil)
  (mg/disconnect mongo))

;;(defstate mongo
;;  :start (start)
;;  :stop (stop))
(def mongo (start))

;; Requests

(s/defn add-request :- (s/eq nil)
  [request :- Request]
  (mc/insert @db request-collection request)
  nil)

(s/defn get-requests :- [Request] []
  (mc/find-maps @db request-collection {}))

(s/defn get-request :- Request [id]
  (mc/find @db request-collection {:_id id}))

(s/defn like-request :- Request [id]
  (mc/update @db request-collection {:_id id} {"$inc" {:likes 1}}))


;; Comments

(s/defn add-comment :- (s/eq nil)
  [cmnt :- Comment]
  (mc/insert @db comment-collection cmnt)
  nil)

(s/defn get-request-comments :- [Comment]
  [request-id :- s/Str]
  (mc/find-maps @db comment-collection {:request-id request-id}))

