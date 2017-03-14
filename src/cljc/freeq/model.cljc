(ns freeq.model
    (:require [schema.core :as s]))

(def Request {:_id s/Str
              :title s/Str
              :desc s/Str
              :likes s/Int})

(def Comment {:_id s/Str
              :request-id s/Str
              :comment s/Str})

