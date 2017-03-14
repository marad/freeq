(ns freeq.requests
    (:require [freeq.mongo :as repo]
              [freeq.model :refer [Request Comment]]
              [schema.core :as s]))

(s/defn list-requests :- [Request] []
  (repo/get-requests)
  )

